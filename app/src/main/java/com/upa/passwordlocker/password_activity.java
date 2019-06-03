package com.upa.passwordlocker;

import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class password_activity extends Activity implements OnClickListener
{
	Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13;
	TextView t;
	String s="", storedpass="", value="", question_value="";
	public static final String PREFS_NAME = "app_pref";
	public static final String pass = "password";
	public static final String pass1 = "question";
	fileWork filework=new fileWork(this);
	encryptionWork encr=new encryptionWork();
	ToneGenerator mp;
	int attempts=0;
	String n_hashed="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	 {
		filework.writeToFile("go", "waitstate");
		filework.writeToFile("123", "timestamp");
		Typeface heading = Typeface.createFromAsset(getAssets(), "fonts/yoyo.otf");
		Typeface allages = Typeface.createFromAsset(getAssets(), "fonts/babylove.ttf");
		
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			storedpass = settings.getString("password", null); //  password stored
			value = settings.getString("question", null); // security question answer
			question_value = settings.getString("question_value",null); // security question chosen

			//Toast.makeText(this, storedpass, Toast.LENGTH_LONG).show();
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_password);
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
	        button13=(Button) findViewById(R.id.button13);
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
	        button13.setOnClickListener(this);
	        button1.setTypeface(allages);button2.setTypeface(allages);button3.setTypeface(allages);button4.setTypeface(allages);
	        button8.setTypeface(allages);button11.setTypeface(allages);button10.setTypeface(allages);button5.setTypeface(allages);
	        button7.setTypeface(allages);button12.setTypeface(allages);button9.setTypeface(allages);button6.setTypeface(allages);
	   
    	    TextView myTextView = (TextView)findViewById(R.id.textView1);
    	    myTextView.setTypeface(heading);
	 }

	@Override
	public void onClick(View v) 
	{
		final View vi = getLayoutInflater().inflate(R.layout.answer_dialog, null);
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
				mp.startTone(ToneGenerator.TONE_DTMF_0, 120);
				s+="0";
				t.setText(s);
				break;
			case R.id.button12:
				SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
				storedpass = settings.getString("password", null); //  password stored
				try{
				String waitstate=filework.readFromFile("waitstate");
				java.text.DateFormat df = new java.text.SimpleDateFormat("hh:mm:ss");
				Time now=new Time();
				if(waitstate.equalsIgnoreCase("wait"))
				{
					now.setToNow();
					Date d1=df.parse(filework.readFromFile("timestamp"));
					Date d2=df.parse(now.format("%H:%M:%S"));
					if(((d2.getTime()-d1.getTime())/1000)>30){
						filework.writeToFile("0", "timestamp");
						filework.writeToFile("go", "waitstate");
						attempts=0;
						encryptionWork encr=new encryptionWork();
						
						boolean matched=encr.validatePassword(s, storedpass);
						if(matched)
						{
							attempts=0;
							Intent i=new Intent("com.upa.passwordlocker.SQLVIEW");
							startActivity(i);
							finish();
						}
					}
					else{
						Dialog d=new Dialog(this);
						d.setTitle("Wait");
						TextView tv=new TextView(this);
						tv.setText((30-((d2.getTime()-d1.getTime())/1000))+" seconds");
						d.setContentView(tv);
						d.show();
						s="";
						t.setText(s);
					}					
				}
				
				else{
					encryptionWork encr=new encryptionWork();
					boolean matched=false;
					try{
					matched=encr.validatePassword(s, storedpass);
					}catch(Exception e){}
					if(matched)
					{
						attempts=0;
						Intent i=new Intent("com.upa.passwordlocker.SQLVIEW");
						startActivity(i);
						finish();
					}
					else
					{
						s="";
						t.setText(s);
						attempts++;
						if (attempts>=3){
							now.setToNow();
							filework.writeToFile("wait", "waitstate");
							filework.writeToFile(now.format("%H:%M:%S"), "timestamp");
							Toast.makeText(password_activity.this,"Three wrong attempts !!\nWait for 30 secs then try again", Toast.LENGTH_LONG).show();
						}else
						Toast.makeText(password_activity.this, "Invalid Password !!\nAttempts= "+attempts, Toast.LENGTH_SHORT).show();
					}
				}
				}catch(Exception e){}
				break;
				
			case R.id.button13:
				Toast.makeText(this,"This will reset your password.",Toast.LENGTH_LONG).show();
				AlertDialog.Builder mydialog5 = new AlertDialog.Builder(this)
                .setTitle("Answer Your Security Question").setMessage(question_value).setView(vi).setNegativeButton("Cancel",
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
                    	 txtanswer=(EditText)vi.findViewById(R.id.editTexty);
                    	 s=txtanswer.getText().toString();
                    	 try{
                    	 Boolean matched_security=encr.validatePassword(s, value);
                    	 if(matched_security)
                    	 {
                    		 Random rnd=new Random();
                    		 int n=1000+ rnd.nextInt(9000);
                    		 Toast.makeText(password_activity.this, "Your New Password is : " + n, Toast.LENGTH_LONG).show();
                    		 n_hashed=String.valueOf(n);
                    		 n_hashed=encr.generateStorngPasswordHash(n_hashed);
                    		 SharedPreferences settings;
             				SharedPreferences.Editor editor;
             				settings = getSharedPreferences(PREFS_NAME, 2);
             				editor = settings.edit();
             				editor.putString(pass, n_hashed);
             				editor.commit();
            				s="";
            				t.setText(s);
            				attempts=0;
                    		 //Toast.makeText(password_activity.this, "Your Password is : " + n_hashed, Toast.LENGTH_LONG).show();
                    		// Toast.makeText(password_activity.this, "Your Password is : " + storedpass, Toast.LENGTH_LONG).show();
                    	 }
                    	 else
                    	 {
                    		 Toast.makeText(password_activity.this, "Wrong Answer", Toast.LENGTH_LONG).show();
             				s="";
            				t.setText(s);
            				//attempts=0;
                    	 }
                    	 }catch(Exception e){ }
                    }
                });
				mydialog5.create().show();
				s="";
				t.setText(s);
				break;
				
		}
		
	}
}