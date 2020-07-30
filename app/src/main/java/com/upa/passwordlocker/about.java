package com.upa.passwordlocker;

import com.upa.passwordlocker.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class about extends Activity
{
	TextView t;
	boolean finish;
	boolean backpress;
	@Override
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.about);
		  finish=false;
		  t = (TextView)findViewById(R.id.textView1);
		   t.setText("" +
		   		"Are you fretted over forgetting your access data for various websites, applications, etc?" +
		   		" Well this app provides you a secure way of storing all your passwords instead of noting them down on some diary." +
		   		" All you need to remember is the master password to unlock the app. " +
		   		"You can trust Password Locker 100% as it does not have any access to the internet." +
		   		"\n\n" +
		   		"MAIN FEATURES\n" +
		   		"-The application does not attempt to send this data out to any other entity by any means." +
		   		"-Contains no advertisements.\n" +
		   		"-Secure storage of passwords. All Usernames and Passwords stored are fully encrypted\n" +
		   		"-Application has a user-friendly interface with easy access to credentials\n" +
		   		"-App gets AutoLocked on Home Button press, Incoming call and any other interruption\n" +
		   		"-Access via master password only.\n" +
		   		"-Change master password.\n" +
		   		"-Security question to reset the master password. Please remember the security question.\n" +
		   		"-Edit and delete database.\n\n" +
		   		"SECURITY FEATURES\n" +
		   		"-All data is encrypted including database and master password.\n" +
		   		"-The master password is encrypted using PBKDF2withHmacSHA1 encryption algorithm using salt. " +
		   		"-The key to open the data file is created by combining your master password with the 512-bit 'salt'. " +
		   		"The result is hashed 1000 times by SHA-256. Repetitive hashing makes a brute force attack more difficult." +
		   		"The SHA hash cannot be breached easily using rainbow tables. " +
		   		"-AES-128 bit encryption is used to keep the database fully secure.\n" +
		   		"-For additional security, the app gets locked for 30 secs on three wrong attempts.\n\n" +
		   		"Do not dither to contact us in case some bug is found :)\n\n" +
		   		"CONTACTS:\n" +
		   		"prateekchinu3011@gmail.com (Prateek Bhuwania)\nutkbits2k11@gmail.com (Utkarsh)\nanikettherockstar@gmail.com (Aniket Verma)");
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
	 public boolean onCreateOptionsMenu(Menu menu) 
	 {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return super.onCreateOptionsMenu(menu);
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
	        		    intent.setData(Uri.parse("market://details?id="+ about.this.getPackageName()));
	        		    if (!MyStartActivity(intent)) {
	        		        //Market (Google play) app seems not installed, let's try to open a webbrowser
	        		        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+about.this.getPackageName()));
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