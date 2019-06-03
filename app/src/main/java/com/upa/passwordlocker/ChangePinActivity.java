package com.upa.passwordlocker;

import com.upa.passwordlocker.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePinActivity extends Activity implements OnClickListener{

	encryptionWork encr=new encryptionWork();
	EditText oldpin,newpin,renewpin;
	Button changepin;
//	String newpin_hash="",rnewpin_hash="";
	public static final String PREFS_NAME = "app_pref";
	public static final String pass = "password";
	boolean finish, backpress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pin);
		finish=false;
		oldpin=(EditText)findViewById(R.id.oldPin);
		newpin=(EditText)findViewById(R.id.newPin);
		renewpin=(EditText)findViewById(R.id.reenterNewPin);
		changepin=(Button)findViewById(R.id.change);
		changepin.setOnClickListener(this);
	}
	
	  @Override
	  protected void onResume() {
		  
	    super.onResume();
	    if(finish){ 
		    Intent i=new Intent("com.upa.passwordlocker.PASSWORD_ACTIVITY");
			startActivity(i);
			finish();
		    	}
	  }
	
	  @Override
	  public void onBackPressed() {
		  //Toast.makeText(sqlView.this,"back press hua" , Toast.LENGTH_SHORT).show();
		  finish=false;
		  backpress=true;
		  Intent i=new Intent("com.upa.passwordlocker.SQLVIEW");
			startActivity(i);
			finish();
	  }
	  
	  @Override
	  public void onPause() {
		  super.onPause();
		  //Toast.makeText(sqlView.this, "pause", Toast.LENGTH_SHORT).show();
		  //if(fin&&fina)
		  if(!backpress)
			  finish=true;
		  //Toast.makeText(sqlView.this, "pause finish is "+finish, Toast.LENGTH_SHORT).show();
	  }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.change:
			String soldpin=oldpin.getText().toString();
			String snewpin=newpin.getText().toString();
			String srenewpin=renewpin.getText().toString();
			
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			String storedpass = settings.getString("password", null);
			boolean matched=false;
			try{
			matched=encr.validatePassword(soldpin, storedpass);
			}catch(Exception e){}
			if(matched)
			{
				if(snewpin.equalsIgnoreCase(srenewpin))
				{
					String npin_hashed="";
					try{
					npin_hashed=encr.generateStorngPasswordHash(snewpin);
					}catch(Exception e){}
					SharedPreferences.Editor editor;
					settings = getSharedPreferences(PREFS_NAME, 2);
					editor = settings.edit();
					editor.putString(pass, npin_hashed);
					editor.commit();
					Toast.makeText(this,"PIN Changed Successfully", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(this,"New Pin doesn't Match", Toast.LENGTH_SHORT).show();
				}
			}
			else{Toast.makeText(this,"Wrong Old Pin", Toast.LENGTH_SHORT).show();}
			break;
		}
	}
	
	
	  private boolean MyStartActivity(Intent aIntent) {
		    try
		    {
		        startActivity(aIntent);
		        return true;
		    }
		    catch (ActivityNotFoundException e)
		    {
		        return false;
		    }
		}
	  
	  

		@Override
		 public boolean onOptionsItemSelected(MenuItem item) {
		     // Handle presses on the action bar items
		     switch (item.getItemId()) {
		         case R.id.addEntry:
		             Intent iAddEntry=new Intent("com.upa.passwordlocker.MAIN_ACTIVITY");
		             startActivity(iAddEntry);
		             finish();
		             return true;
		         case R.id.changePin:
		        	 Intent iChangePin=new Intent("com.upa.passwordlocker.CHANGEPINACTIVITY");
		             startActivity(iChangePin);
		             finish();
		             return true;
		         case R.id.about:
		        	 Intent iAbout=new Intent("com.upa.passwordlocker.ABOUT");
		             startActivity(iAbout);
		             finish();
		        	 return true;
		         case R.id.rate:
		        		    Intent intent = new Intent(Intent.ACTION_VIEW);
		        		    //Try Google play
		        		    intent.setData(Uri.parse("market://details?id="+ ChangePinActivity.this.getPackageName()));
		        		    if (!MyStartActivity(intent)) {
		        		        //Market (Google play) app seems not installed, let's try to open a webbrowser
		        		        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+ChangePinActivity.this.getPackageName()));
		        		        if (!MyStartActivity(intent)) {
		        		            //Well if this also fails, we have run out of options, inform the user.
		        		            Toast.makeText(this, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
		        		        }
		        		    }
		        		    finish();
		        	 
		         default:
		             return super.onOptionsItemSelected(item);
		     }
		 }
	
}
