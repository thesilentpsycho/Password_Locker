package com.upa.passwordlocker;

import com.upa.passwordlocker.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class sqlView extends Activity{
	boolean finish;
	boolean backpress;
	
	// more efficient than HashMap for mapping integers to objects
	  SparseArray<Group> groups = new SparseArray<Group>();
	  ExpandableListView listView;
	  MyExpandableListAdapter adapt;
 	 String s1="",s2="",s3="";
 	 sqlWork entry=new sqlWork(sqlView.this);
 	 int pos;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		  
		  super.onCreate(savedInstanceState);
		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
		  //if(savedInstanceState != null){
	            // get the restore value from the Bundle
		//  Toast.makeText(sqlView.this,"on create" , Toast.LENGTH_SHORT).show();
			  finish=false;
			  //Toast.makeText(sqlView.this, "call hua", Toast.LENGTH_SHORT).show();
	       // }
	  }

	  @Override
	  protected void onResume() {
		  
	    super.onResume();
	    //Toast.makeText(sqlView.this,"on resume" , Toast.LENGTH_SHORT).show();
	    //Toast.makeText(sqlView.this, "resume finish is "+finish, Toast.LENGTH_SHORT).show();
	    if(finish){ 
	    Intent i=new Intent("com.upa.passwordlocker.PASSWORD_ACTIVITY");
		startActivity(i);
		finish();
	    	}
	    createData();
		int layoutId=0;
	    if (groups.size()==0) layoutId=R.layout.viewsqlempty;
	    else layoutId=R.layout.viewsql;
	    setContentView(layoutId);
		    if (!(groups.size()==0))
		    {
			    listView = (ExpandableListView) findViewById(R.id.listView);
			    adapt = new MyExpandableListAdapter(this, groups);
			    listView.setAdapter(adapt);
			    registerForContextMenu(listView);
		    }
		    else
		    {
		    Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/kbzipadeedoodah.ttf");
    	    TextView myTextView = (TextView)findViewById(R.id.textView1);
    	    myTextView.setTypeface(myTypeface);
    	    }
	  }
	  
	  @Override
	  public void onBackPressed() {
		  //Toast.makeText(sqlView.this,"back press hua" , Toast.LENGTH_SHORT).show();
		  finish=false;
		  backpress=true;
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
	  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) 
	  {
		  super.onCreateContextMenu(menu, v, menuInfo);
	      if (v.getId()==R.id.listView) 
		    {
		        menu.add(Menu.NONE, 0, 0, "Edit");
		        menu.add(Menu.NONE, 0, 1, "Delete");
		    }
	      
	  }
	  
	  
	  @Override
	    public boolean onContextItemSelected(MenuItem item)
	    {
		 ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();
		  pos=ExpandableListView.getPackedPositionGroup(info.packedPosition);
	     if(item.getTitle().toString()=="Edit")
		     {
	    	 
			 //Toast.makeText(sqlView.this, String.valueOf(pos), Toast.LENGTH_SHORT).show();
	    	 final View v = getLayoutInflater().inflate(R.layout.editdialog, null);
	    	 AlertDialog.Builder mydialog = new AlertDialog.Builder(this)
	                    .setTitle("Edit")
	                    .setView(v)
	                    .setNegativeButton("Cancel",
	                            new DialogInterface.OnClickListener() {

	                                @Override
	                                public void onClick(DialogInterface dialog,
	                                        int which) {
	                                    // TODO Auto-generated method stub

	                                }
	                            })
	                    .setPositiveButton("Update",
	                            new DialogInterface.OnClickListener() {
	                    	        EditText txtwebsite,txtusername,txtpassword;
	                                @Override
	                                public void onClick(DialogInterface dialog,
	                                        int which) {
	                                	 txtwebsite=(EditText)v.findViewById(R.id.editText1);
	                                	 txtusername=(EditText)v.findViewById(R.id.editText2);
	                                	 txtpassword=(EditText)v.findViewById(R.id.editText3);
	                                	 s1=txtwebsite.getText().toString();
	                                	 s2=txtusername.getText().toString();
	                                	 s3=txtpassword.getText().toString();
	                                	 if(containsWhiteSpace(s1))
	                     				{
	                     					Toast.makeText(sqlView.this, "Website field cannot have spaces", Toast.LENGTH_SHORT).show();
	                     				}
	                     				else if(containsWhiteSpace(s2))
	                     				{
	                     					Toast.makeText(sqlView.this, "Username field cannot have spaces", Toast.LENGTH_SHORT).show();
	                     				}
	                     				else if(containsWhiteSpace(s3))
	                     				{
	                     					Toast.makeText(sqlView.this, "Password field cannot have spaces", Toast.LENGTH_SHORT).show();
	                     				}                 		
	                                	 
	                     				else if(s1.equalsIgnoreCase(""))
	                 					{Toast.makeText(sqlView.this, "Website field cannot be empty", Toast.LENGTH_SHORT).show();}
	                                	 else if((s2.equalsIgnoreCase(""))&&(s3.equalsIgnoreCase("")))
	                 						{Toast.makeText(sqlView.this, "Both fields cannot be empty", Toast.LENGTH_SHORT).show();}
	                                	 else{
	                                		 if(s2.equalsIgnoreCase(""))
	                                			 s2="No_username_entered";
	                                		 if(s3.equalsIgnoreCase(""))
	                                			 s3="No_password_entered";
	                                	try {
											AESHelper aesHelper=new AESHelper();
											String s2_encr=aesHelper.encrypt(s2);
											String s3_encr=aesHelper.encrypt(s3);
											entry.open();
											entry.editTable(pos+1,s1,s2_encr,s3_encr);
											entry.close();
											createData();
										} catch (Exception e) {
											}
	                        			adapt.notifyDataSetChanged();
	                                	}
	                                }
		     });
	    	 mydialog.create().show();
	    	//Toast.makeText(sqlView.this, "Edited", Toast.LENGTH_SHORT).show();
		     }
	     else if(item.getTitle().toString()=="Delete")
		     {
	    	 try{
	    	 entry.open();
	    	 entry.deleteentry(pos+1);
			 entry.close();
			 createData();
			 adapt.notifyDataSetChanged();
	    	 }
	    	 catch(Exception e){setContentView(R.layout.viewsqlempty);
	    	// Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
	    	 Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/kbzipadeedoodah.ttf");
	    	    TextView myTextView = (TextView)findViewById(R.id.textView1);
	    	    myTextView.setTypeface(myTypeface);}
		      }
	     return super.onContextItemSelected(item);
	    }
	  
	  

	  public void createData() 
	  {
		  try {
				int k=0;
				groups.clear();
				sqlWork info = new sqlWork(this);
				info.open();
				String data = info.getData();
				info.close();
				AESHelper aesHelper=new AESHelper();
				String username_decr;
				String password_decr;
				String arr[] = data.split("\n");
  
				for (int j = 0; j < arr.length; j=j+3) 
				  {
				  Group group = new Group(arr[j]);
				  try {
					username_decr=aesHelper.decrypt(arr[j+1]);
					password_decr=aesHelper.decrypt(arr[j+2]);
				  group.children.add("Username - " + username_decr);
				  group.children.add("Password - " + password_decr);
				  groups.append(k++, group);
				  } catch (Exception e) {}
				  }
			} catch (SQLException e) {} 

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
	 public boolean onCreateOptionsMenu(Menu menu) 
	 {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
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
	        		    intent.setData(Uri.parse("market://details?id="+ sqlView.this.getPackageName()));
	        		    if (!MyStartActivity(intent)) {
	        		        //Market (Google play) app seems not installed, let's try to open a webbrowser
	        		        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+sqlView.this.getPackageName()));
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
