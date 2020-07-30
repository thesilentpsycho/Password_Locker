package com.upa.passwordlocker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class sqlWork {

	public static final String DBNAME="dataofstudent";
	public static final String TABLENAME="UTKAPRANI";
	public static final String KEY_ROWID="_id";
	public static final String KEY_WEBSITE="Website";
	public static final String KEY_USERNAME="Username";
	public static final String KEY_PASSWORD="Password";
	public static final int dbversion=2;
	
	private dbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class dbHelper extends SQLiteOpenHelper{

		public dbHelper(Context context) {
			super(context, DBNAME, null, dbversion);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "+TABLENAME+" ( "+
						KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
						KEY_WEBSITE+" TEXT NOT NULL, "+KEY_USERNAME+" TEXT NOT NULL, "+KEY_PASSWORD+
						" TEXT NOT NULL);"
						);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			if(oldVersion==1){
				String[] columns=new String[]{KEY_ROWID,KEY_WEBSITE,KEY_USERNAME,KEY_PASSWORD};
				Cursor c=db.query(TABLENAME, columns, null, null, null, null, null);
				String data="";
				int iWebsite=c.getColumnIndex(KEY_WEBSITE);
				int iUsername=c.getColumnIndex(KEY_USERNAME);
				int iPassword=c.getColumnIndex(KEY_PASSWORD);
				
				for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
					data+=c.getString(iWebsite)+" "+c.getString(iUsername)+" "+ c.getString(iPassword)+" ";
				}
				c.close();
				String arr[] = data.split(" ");
				db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
				db.execSQL("CREATE TABLE "+TABLENAME+" ( "+
						KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
						KEY_WEBSITE+" TEXT NOT NULL, "+KEY_USERNAME+" TEXT NOT NULL, "+KEY_PASSWORD+
						" TEXT NOT NULL);"
						);
				AESHelper aesHelper=new AESHelper();
				for (int j = 0; j < arr.length; j=j+3) 
				  {
					try {
						arr[j+1]=aesHelper.encrypt(arr[j+1]);
						arr[j+2]=aesHelper.encrypt(arr[j+2]);
					} catch (Exception e) {}
					ContentValues cv=new ContentValues();
					cv.put(KEY_WEBSITE, arr[j]);
					cv.put(KEY_USERNAME, arr[j+1]);
					cv.put(KEY_PASSWORD, arr[j+2]);
					db.insert(TABLENAME, null, cv);
				  }
			}			
		}
	}
	
	public sqlWork(Context c){
		ourContext=c;
	}
	
	public sqlWork open() throws SQLException{
		ourHelper=new dbHelper(ourContext);
		ourDatabase=ourHelper.getWritableDatabase();
		return this;
	}
	
	public sqlWork close() throws SQLException{
		ourHelper.close();
		return this;
	}

	public long createEntry(String website, String username, String password) throws SQLException{
		ContentValues cv=new ContentValues();
		cv.put(KEY_WEBSITE, website);
		cv.put(KEY_USERNAME, username);
		cv.put(KEY_PASSWORD, password);
		return ourDatabase.insert(TABLENAME, null, cv);
		
	}
	
	public void deleteentry(int pos) {
		String whereClause = KEY_ROWID+"=?";
		String[]whereArgs = new String[] {String.valueOf(pos)};
		ourDatabase.beginTransaction();
		ourDatabase.delete(TABLENAME, whereClause, whereArgs);
		ourDatabase.setTransactionSuccessful();
		ourDatabase.endTransaction();
		refreshTable();
		
	}
	
	public void editTable(int pos, String website,String username,String password)
	{
		String whereClause = "_id"+"=?";
		String[]whereArgs = new String[] {String.valueOf(pos)};
		ContentValues cv=new ContentValues();
		cv.put(KEY_WEBSITE, website);
		cv.put(KEY_USERNAME, username);
		cv.put(KEY_PASSWORD, password);
		ourDatabase.beginTransaction();
		ourDatabase.update(TABLENAME, cv, whereClause, whereArgs);
		ourDatabase.setTransactionSuccessful();
		ourDatabase.endTransaction();
		refreshTable();
	}
	
	public void refreshTable()
	{
		String[] columns=new String[]{KEY_ROWID,KEY_WEBSITE,KEY_USERNAME,KEY_PASSWORD};
		Cursor c=ourDatabase.query(TABLENAME, columns, null, null, null, null, null);
		String data="";
		int iWebsite=c.getColumnIndex(KEY_WEBSITE);
		int iUsername=c.getColumnIndex(KEY_USERNAME);
		int iPassword=c.getColumnIndex(KEY_PASSWORD);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			data+=c.getString(iWebsite)+" "+c.getString(iUsername)+" "+ c.getString(iPassword)+" ";
		}
		c.close();
		String arr[] = data.split(" ");
		ourDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
		ourDatabase.execSQL("CREATE TABLE "+TABLENAME+" ( "+
				KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
				KEY_WEBSITE+" TEXT NOT NULL, "+KEY_USERNAME+" TEXT NOT NULL, "+KEY_PASSWORD+
				" TEXT NOT NULL);"
				);
		for (int j = 0; j < arr.length; j=j+3) 
		  {
			createEntry(arr[j],arr[j+1],arr[j+2]);
		  }
	}
	
	public String getData() {
		String[] columns=new String[]{KEY_ROWID,KEY_WEBSITE,KEY_USERNAME,KEY_PASSWORD};
		//we read from a database with the use of a Cursor
		Cursor c=ourDatabase.query(TABLENAME, columns, null, null, null, null, null);
		String result="";
		int iId=c.getColumnIndex(KEY_ROWID);
		int iWebsite=c.getColumnIndex(KEY_WEBSITE);
		int iUsername=c.getColumnIndex(KEY_USERNAME);
		int iPassword=c.getColumnIndex(KEY_PASSWORD);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			result+=c.getString(iWebsite)+"\n"+c.getString(iUsername)+"\n"+ c.getString(iPassword)+"\n";
		}
		c.close();
		
		return result;
	}
	
	
}
