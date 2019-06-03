package com.upa.passwordlocker;

import com.upa.passwordlocker.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class question_activity extends Activity implements OnClickListener
{
	Button button1, button2, button3, button4, button5;
	public static final String PREFS_NAME = "app_pref";
	public static final String pass2 = "launch";
	public static final String pass1 = "question";
	public static final String pass3 = "question_value";
	String s="", buttonText="";
	encryptionWork encr=new encryptionWork();
	String s_hashed="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	 {
			requestpass2();
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_question);	
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
	        Typeface heading = Typeface.createFromAsset(getAssets(), "fonts/yoyo.otf");
	        button1=(Button) findViewById(R.id.button1);
	        button2=(Button) findViewById(R.id.button2);
	        button3=(Button) findViewById(R.id.button3);
	        button4=(Button) findViewById(R.id.button4);
	        button5=(Button) findViewById(R.id.button5);
	        button1.setOnClickListener(this);
	        button2.setOnClickListener(this);
	        button3.setOnClickListener(this);
	        button4.setOnClickListener(this);
	        button5.setOnClickListener(this);
	        TextView myTextView = (TextView)findViewById(R.id.textView1);
    	    myTextView.setTypeface(heading);
	        
	        
	 }
	

	@Override
	public void onClick(final View v) 
	{
		
		final View vi1 = getLayoutInflater().inflate(R.layout.question_dialog, null);
		// TODO Auto-generated method stub
			switch(v.getId())
			{
				case R.id.button1:
					Button b1 = (Button)v;
				    buttonText = b1.getText().toString();
				    
					// Toast.makeText(question_activity.this, "lauda mera", Toast.LENGTH_SHORT).show();
					AlertDialog.Builder mydialog1 = new AlertDialog.Builder(this)
                    .setTitle("Answer1").setView(vi1).setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                        }
                    })
                    .setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
            	        EditText txtanswer;
                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                        	//dialog.dismiss();
                        	 txtanswer=(EditText)vi1.findViewById(R.id.editText1);
                        	 s=txtanswer.getText().toString();
                        	 try{
                        	 s_hashed=encr.generateStorngPasswordHash(s);
                        	 //Toast.makeText(question_activity.this, s, Toast.LENGTH_SHORT).show();
                        	 SharedPreferences settings;
                     		SharedPreferences.Editor editor;
                     		settings = getSharedPreferences(PREFS_NAME, 2);
                     		editor = settings.edit();
                        	 editor.putString(pass1, s_hashed);
                			 editor.commit();
                        	 }catch(Exception e){}
                			 requestpass();
                			Intent i=new Intent("com.upa.passwordlocker.PASSWORD_ACTIVITY");
                          	 startActivity(i);
                          	finish();
      	   
                        }
                        
                    });
					mydialog1.create().show();	
					break;
				
				case R.id.button2:
					Button b2 = (Button)v;
				    buttonText = b2.getText().toString();
					AlertDialog.Builder mydialog2 = new AlertDialog.Builder(this)
                    .setTitle("Answer2").setView(vi1).setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                            // TODO Auto-generated method stub

                        }
                    })
                    .setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
            	        EditText txtanswer;
                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                        	 txtanswer=(EditText)vi1.findViewById(R.id.editText1);
                        	 s=txtanswer.getText().toString();
                        	 try{
                            	 s_hashed=encr.generateStorngPasswordHash(s);
                            	 //Toast.makeText(question_activity.this, s, Toast.LENGTH_SHORT).show();
                            	 SharedPreferences settings;
                         		SharedPreferences.Editor editor;
                         		settings = getSharedPreferences(PREFS_NAME, 2);
                         		editor = settings.edit();
                            	 editor.putString(pass1, s_hashed);
                    			 editor.commit();
                            	 }catch(Exception e){}
                			 requestpass();
                			Intent i=new Intent("com.upa.passwordlocker.PASSWORD_ACTIVITY");
                          	 startActivity(i);
                          	 finish();
                        	 
                        }
                    });
					mydialog2.create().show();
					//settings.edit().putString(pass1,s).commit();
					break;
					
					
				case R.id.button3:
					Button b3 = (Button)v;
				    buttonText = b3.getText().toString();
					AlertDialog.Builder mydialog3 = new AlertDialog.Builder(this)
                    .setTitle("Answer3").setView(vi1).setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                            // TODO Auto-generated method stub

                        }
                    })
                    .setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
            	        EditText txtanswer;
                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                        	 txtanswer=(EditText)vi1.findViewById(R.id.editText1);
                        	 s=txtanswer.getText().toString();
                        	 try{
                            	 s_hashed=encr.generateStorngPasswordHash(s);
                            	 //Toast.makeText(question_activity.this, s, Toast.LENGTH_SHORT).show();
                            	 SharedPreferences settings;
                         		SharedPreferences.Editor editor;
                         		settings = getSharedPreferences(PREFS_NAME, 2);
                         		editor = settings.edit();
                            	 editor.putString(pass1, s_hashed);
                    			 editor.commit();
                            	 }catch(Exception e){}
                			 requestpass();
                			Intent i=new Intent("com.upa.passwordlocker.PASSWORD_ACTIVITY");
                          	 startActivity(i);
                          	 finish();
                        	 
                        }
                    });
					mydialog3.create().show();
					//settings.edit().putString(pass1,s).commit();
					break;
					
				case R.id.button4:
					Button b4 = (Button)v;
				    buttonText = b4.getText().toString();
					AlertDialog.Builder mydialog4 = new AlertDialog.Builder(this)
                    .setTitle("Answer4").setView(vi1).setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                            // TODO Auto-generated method stub

                        }
                    })
                    .setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
            	        EditText txtanswer;
                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                        	 txtanswer=(EditText)vi1.findViewById(R.id.editText1);
                        	 s=txtanswer.getText().toString();
                        	 try{
                            	 s_hashed=encr.generateStorngPasswordHash(s);
                            	 //Toast.makeText(question_activity.this, s, Toast.LENGTH_SHORT).show();
                            	 SharedPreferences settings;
                         		SharedPreferences.Editor editor;
                         		settings = getSharedPreferences(PREFS_NAME, 2);
                         		editor = settings.edit();
                            	 editor.putString(pass1, s_hashed);
                    			 editor.commit();
                            	 }catch(Exception e){}
                			 requestpass();
                			Intent i=new Intent("com.upa.passwordlocker.PASSWORD_ACTIVITY");
                          	 startActivity(i);
                          	 finish();
                        	 
                        }
                    });
					mydialog4.create().show();
				//	settings.edit().putString(pass1,s).commit();
					break;
					
				case R.id.button5:
					Button b5 = (Button)v;
				    buttonText = b5.getText().toString();
				    
				  //  Toast.makeText(question_activity.this, buttonText, Toast.LENGTH_SHORT).show();
					AlertDialog.Builder mydialog5 = new AlertDialog.Builder(this)
                    .setTitle("Answer5").setView(vi1).setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                            // TODO Auto-generated method stub

                        }
                    })
                    .setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                    	
            	        EditText txtanswer;
                        @Override
                        public void onClick(DialogInterface dialog,
                                int which) {
                        	 txtanswer=(EditText)vi1.findViewById(R.id.editText1);
                        	 s=txtanswer.getText().toString();
                        	 try{
                            	 s_hashed=encr.generateStorngPasswordHash(s);
                            	 //Toast.makeText(question_activity.this, s, Toast.LENGTH_SHORT).show();
                            	 SharedPreferences settings;
                         		SharedPreferences.Editor editor;
                         		settings = getSharedPreferences(PREFS_NAME, 2);
                         		editor = settings.edit();
                            	 editor.putString(pass1, s_hashed);
                    			 editor.commit();
                            	 }catch(Exception e){}
                			 requestpass();
                			Intent i=new Intent("com.upa.passwordlocker.PASSWORD_ACTIVITY");
                          	 startActivity(i);
                          	 finish();
                        	 
                        	
                        }
                    });
					mydialog5.create().show();
					//settings.edit().putString(pass1,s).commit();
					break;
				
			}// switch

			SharedPreferences settings1;
     		SharedPreferences.Editor editor1;
     		settings1 = getSharedPreferences(PREFS_NAME, 2);
     		editor1 = settings1.edit();
        	editor1.putString(pass3, buttonText);
			editor1.commit();
			requestpass2();
	}//on click
	
	
	
	private void requestpass() 
	{
		final String PREFS_NAME1 = "MyPrefsFile";
		SharedPreferences settings = getSharedPreferences(PREFS_NAME1, 0);	
		if(settings.getBoolean(pass2, true)) 
		{
			settings.edit().putBoolean(pass2, false).commit();
			return;
		}
		else
		{
			Intent i=new Intent("com.upa.passwordlocker.PASSWORD_ACTIVITY");
			startActivity(i);
			finish();
		}
		
		// TODO Auto-generated method stub
		
	}
	
	
	
	private void requestpass2() 
	{
		final String PREFS_NAME1 = "MyPrefsFile";
		//int a=1;

		SharedPreferences settings = getSharedPreferences(PREFS_NAME1, 0);
		//SharedPreferences.Editor editor = settings.edit();	
		if(settings.getBoolean(pass2, true)) 
		//if(a==1)
		{
			//settings.edit().putBoolean(pass2, false).commit();
			return;
		}
		else
		{
			Intent i=new Intent("com.upa.passwordlocker.PASSWORD_ACTIVITY");
			startActivity(i);
			finish();
		}
		
		// TODO Auto-generated method stub
		
	}
}