package com.hpinc.voter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper  {
    private static int DB_VERSION = 1;
    private static String DB_NAME = "Vot5.db";
    private Context context=null;
    String userName;
    String userPass;
    String userPhno;
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL("create table if not exists LOGGER (first_name TEXT,middle_name TEXT,last_name TEXT,age NUMBER,address TEXT,constitute TEXT,occupation TEXT,password TEXT,confirm TEXT,phone NUMBER,Register NUMBER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       db.execSQL("Drop Table LOGGER");
    }


    public int getyourdata2(String user, String pass) {
        SQLiteDatabase sb=this.getReadableDatabase();
        //SELECT
        String[] columns = {"Register"};

        //WHERE clause
        String selection = "first_name=? AND password=?";
        userName=user;
        userPass=pass;
        //WHERE clause arguments
        String[] selectionArgs = {userName, userPass};
        Cursor c;
        int data;
        int no_such_data=0;
        try{
            //SELECT name FROM login WHERE username=userName AND password=userPass
            c = sb.query("LOGGER", columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();

            int i = c.getCount();
            data = c.getInt(c.getColumnIndex("Register"));
            c.close();
            if(i <= 0){

                return no_such_data;
            }
            return data;
        }catch(Exception e){
            e.printStackTrace();
            return no_such_data;
        }
    }
    public int getyourdata3(String phno) {
        SQLiteDatabase sb=this.getReadableDatabase();
        //SELECT
        String[] columns = {"first_name"};

        //WHERE clause
        String selection = "phone=? ";

        userPhno=phno;
        //WHERE clause arguments
        String[] selectionArgs = {userPhno};
        Cursor c;

        try{
            //SELECT Name FROM LOGGER WHERE phno=userPhno;
            c = sb.query("LOGGER", columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();

            int i = c.getCount();
            //data = c.getString(c.getColumnIndex("Name"));
            c.close();
            if(i <= 0){

                return 1;
            }
            else {
                return -1;
            }

        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
