package com.upa.passwordlocker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class password_activity_choose extends Activity implements OnClickListener
{
	Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12;
	TextView t;
	String s="";
	public static final String PREFS_NAME = "app_pref";
	public static final String pass = "password";
	ToneGenerator mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	 {
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
			if(!prefs.getBoolean("firstTime", false)) {
				if(!prefs.getBoolean("version", false))
				{
					PackageInfo pinfo;
					try {
						pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
						int versionNumber = pinfo.versionCode;
						String versionName = pinfo.versionName;
						SharedPreferences.Editor editor = prefs.edit();
						editor.putBoolean("version", true);
						editor.putString("version", versionName);
						editor.commit();
					} catch (Exception e) {}
				}
				SharedPreferences.Editor editor = prefs.edit();
				editor.putBoolean("firstTime", true);
				editor.commit();
				try {
					//String PREFS_NAME="MyPrefsFile";
					//preferences.edit().clear().commit();
					encryptionWork encr=new encryptionWork();
					SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
					String securityAnswer=preferences.getString("question", null);
					String storedpass = preferences.getString("password", null);
					String pass_hashed=encr.generateStorngPasswordHash(storedpass);
					String answer_hashed=encr.generateStorngPasswordHash(securityAnswer);
					editor = preferences.edit();
					editor.putString(pass, pass_hashed);
					editor.putString("question", answer_hashed);
					editor.commit();
				} catch (Exception e) {}
			}
			requestpass();
	        super.onCreate(savedInstanceState);
	        Typeface heading = Typeface.createFromAsset(getAssets(), "fonts/yoyo.otf");
			Typeface allages = Typeface.createFromAsset(getAssets(), "fonts/babylove.ttf");
	        setContentView(R.layout.activity_password_choose);
	        mp = new ToneGenerator(AudioManager.STREAM_DTMF,70);
	        button1=(Button) findViewById(R.id.button1);
	        button2=(Button) findViewById(R.id.button2);
	        button3=(Button) findViewById(R.id.button3);
	        button4=(Button) findViewById(R.id.button4);
	        button5=(Button) findViewById(R.id.button5);
	        button6=(Button) findViewById(R.id.button6);
	        button7=(Button) findViewById(R.id.button7);
	        button8=(Button) findViewById(R.id.button8);
	        button9=(Button) findViewById(R.id.button9);
	        button10=(Button) findViewById(R.id.button10);
	        button11=(Button) findViewById(R.id.button11);
	        button12=(Button) findViewById(R.id.button12);
	        t=(TextView)findViewById(R.id.textView2);
	        button1.setOnClickListener(this);
	        button2.setOnClickListener(this);
	        button3.setOnClickListener(this);
	        button4.setOnClickListener(this);
	        button5.setOnClickListener(this);
	        button6.setOnClickListener(this);
	        button7.setOnClickListener(this);
	        button8.setOnClickListener(this);
	        button9.setOnClickListener(this);
	        button10.setOnClickListener(this);
	        button11.setOnClickListener(this);
	        button12.setOnClickListener(this);
	        TextView myTextView = (TextView)findViewById(R.id.textView1);
    	    myTextView.setTypeface(heading);
	        button1.setTypeface(allages);button2.setTypeface(allages);button3.setTypeface(allages);button4.setTypeface(allages);
	        button8.setTypeface(allages);button11.setTypeface(allages);button10.setTypeface(allages);button5.setTypeface(allages);
	        button7.setTypeface(allages);button12.setTypeface(allages);button9.setTypeface(allages);button6.setTypeface(allages);
	 }

	private void requestpass() 
	{
		final String PREFS_NAME = "MyPrefsFile";

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		//SharedPreferences.Editor editor = settings.edit();	
		if(settings.getBoolean(pass, true)) 
		{
			//settings.edit().putBoolean(pass, false).commit();
			return;
		}
		else
		{
			Intent i=new Intent("com.upa.passwordlocker.QUESTIONACTIVITY");
			startActivity(i);
			finish();
		}
		
		// TODO Auto-generated method stub
		
	}
	
	
	
	private void requestpass2() 
	{
		final String PREFS_NAME = "MyPrefsFile";

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		//SharedPreferences.Editor editor = settings.edit();	
		if(settings.getBoolean(pass, true)) 
		{
			settings.edit().putBoolean(pass, false).commit();
			return;
		}
		else
		{
			Intent i=new Intent("com.upa.passwordlocker.QUESTIONACTIVITY");
			startActivity(i);
			finish();
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.button1:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="1";
			t.setText(s);
			break;
		case R.id.button2:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="2";
			t.setText(s);
			break;
		case R.id.button3:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="3";
			t.setText(s);
			break;
		case R.id.button4:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="4";
			t.setText(s);
			break;
		case R.id.button5:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="5";
			t.setText(s);
			break;
		case R.id.button6:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="6";
			t.setText(s);
			break;
		case R.id.button7:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="7";
			t.setText(s);
			break;
		case R.id.button8:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="8";
			t.setText(s);
			break;
		case R.id.button9:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="9";
			t.setText(s);
			break;
		case R.id.button10:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			if(s.length()>0){
				s=s.substring(0,s.length()-1);
				t.setText(s);}
			break;
		case R.id.button11:
			mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
			s+="0";
			t.setText(s);
			break;
			case R.id.button12:
				/*Editor preferenceEditor = this.getSharedPreferences("password",2).edit();
				preferenceEditor.putString(pass,s);
				preferenceEditor.commit();*/
				mp.startTone(ToneGenerator.TONE_DTMF_1, 120);
				try{
				encryptionWork encr=new encryptionWork();
				String s_hashed=encr.generateStorngPasswordHash(s);
				SharedPreferences settings;
				SharedPreferences.Editor editor;
				settings = getSharedPreferences(PREFS_NAME, 2);
				editor = settings.edit();
				editor.putString(pass, s_hashed);
				editor.commit();
				//Toast.makeText(this, hashed, Toast.LENGTH_LONG).show();
				}catch(Exception e){}
				requestpass2();
				Intent i=new Intent("com.upa.passwordlocker.QUESTIONACTIVITY");
				startActivity(i);
				finish();
				break;
				
		}
		
	}

}