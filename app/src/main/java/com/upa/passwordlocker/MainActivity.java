package com.upa.passwordlocker;

import com.upa.passwordlocker.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	Button sqlupdate,sqlview;
	EditText sqlwebsite, sqlusername,sqlpassword;
	boolean finish, backpress;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        finish=false;
        
            sqlupdate=(Button) findViewById(R.id.button1);
           // sqlview=(Button) findViewById(R.id.button2);
            sqlusername=(EditText) findViewById(R.id.editText1);
            sqlpassword=(EditText) findViewById(R.id.editText2);
            sqlwebsite=(EditText) findViewById(R.id.editText3);
            sqlupdate.setOnClickListener(this);
           // sqlview.setOnClickListener(this);
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
     
        // Don't forget to do the below. very important if implementing the search in action bar
        // Associating searchable configuration with the SearchView
       /* SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));*/
        return super.onCreateOptionsMenu(menu); 
    }
    public static boolean containsWhiteSpace(final String testCode){
        if(testCode != null){
            for(int i = 0; i < testCode.length(); i++){
                if(Character.isWhitespace(testCode.charAt(i))){
                    return true;
                }
            }
        }
        return false;
    }
    
    

    
	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.button1:
			boolean worked=true;
				String username=sqlusername.getText().toString();
				String password=sqlpassword.getText().toString();
				String website=sqlwebsite.getText().toString();
				if(containsWhiteSpace(website))
				{
					Toast.makeText(MainActivity.this, "Website field cannot have spaces", Toast.LENGTH_SHORT).show();worked=false;
				}
				else if(containsWhiteSpace(username))
				{
					Toast.makeText(MainActivity.this, "Username field cannot have spaces", Toast.LENGTH_SHORT).show();worked=false;
				}
				else if(containsWhiteSpace(password))
				{
					Toast.makeText(MainActivity.this, "Password field cannot have spaces", Toast.LENGTH_SHORT).show();worked=false;
				}

				else if(website.equalsIgnoreCase(""))
					{Toast.makeText(MainActivity.this, "Website field cannot be empty", Toast.LENGTH_SHORT).show();worked=false;}
				else if((username.equalsIgnoreCase(""))&&(password.equalsIgnoreCase("")))
						{Toast.makeText(MainActivity.this, "Both fields cannot be empty", Toast.LENGTH_SHORT).show();worked=false;}
				else{
					if(username.equalsIgnoreCase(""))
						username="No_username_entered";
					if(password.equalsIgnoreCase(""))
						password="No_password_entered";
				
				//pass the context of the current class 
				try{
				AESHelper aesHelper=new AESHelper();
				String username_encr=aesHelper.encrypt(username);
				String password_encr=aesHelper.encrypt(password);				
				sqlWork entry=new sqlWork(MainActivity.this);
				entry.open();
				entry.createEntry(website, username_encr,password_encr);
				entry.close();
				sqlusername.setText(null);
				sqlpassword.setText(null);
				sqlwebsite.setText(null);
				} 
				catch (Exception e) {
				worked=false;
				}
				finally{
					if(worked)
					{
						Dialog d=new Dialog(this);
						d.setTitle("Done");
						TextView tv=new TextView(this);
						tv.setText("Entry Created Successfully");
						d.setContentView(tv);
						d.show();
					}
				}
				}
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
	        		    intent.setData(Uri.parse("market://details?id="+ MainActivity.this.getPackageName()));
	        		    if (!MyStartActivity(intent)) {
	        		        //Market (Google play) app seems not installed, let's try to open a webbrowser
	        		        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+MainActivity.this.getPackageName()));
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
