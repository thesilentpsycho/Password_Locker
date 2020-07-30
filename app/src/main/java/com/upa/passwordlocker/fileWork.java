package com.upa.passwordlocker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;


public class fileWork{
	
	Context myContext;
	public fileWork(Context c){
		myContext=c;
	}
	public void writeToFile(String data,String FILENAME) {
		        try {
		            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(myContext.openFileOutput(FILENAME, Context.MODE_PRIVATE));
		            outputStreamWriter.write(data);
		            outputStreamWriter.close();
		        }
		        catch (IOException e) {} 
		         
		    }
 
    public String readFromFile(String FILENAME) {
         
        String ret = "";
         
        try {
            InputStream inputStream = myContext.openFileInput(FILENAME);
             
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                 
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                 
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {} catch (IOException e) {}
 
        return ret;
    }
  
}