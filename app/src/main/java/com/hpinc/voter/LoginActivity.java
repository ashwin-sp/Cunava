package com.hpinc.voter;


import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.feedback.R;


public class LoginActivity extends Activity {
	EditText userName;
    EditText password;
    EditText entercode;

    DatabaseHelper db;
    SQLiteDatabase sb;
    public static String user;
    public static String pass;
    public static String c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		userName = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        entercode=(EditText)findViewById(R.id.editText3);

        db = new DatabaseHelper(LoginActivity.this);
        sb = db.getReadableDatabase();

	}
    public void Login(View v){

         user = userName.getText().toString();

         pass = password.getText().toString();
        
         c = entercode.getText().toString();

if(user.equals("")|| pass.equals("")||c.equals("")){
	 Toast.makeText(getApplicationContext(), "Please enter Name or Password or code",Toast.LENGTH_SHORT).show();
}
else{ 
        String[] args={user};
        Cursor crs=sb.rawQuery("SELECT * FROM LOGGER WHERE first_name = ?", args);
    int sappa = db.getyourdata2(user, pass);
    String s=Integer.toString(sappa);
        if(crs.getCount()==0){

            Toast.makeText(getApplicationContext(), "Username or Password Invalid",Toast.LENGTH_SHORT).show();

        }

        else{
            if(c.equals(s))
            {

                if(RegisterActivity.count[db.getyourdata2(user,pass)]==1)
                {
                    Toast.makeText(getApplicationContext(), "Already voted...",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent home = new Intent(LoginActivity.this, VoteActivity.class);

                    startActivity(home);
                    overridePendingTransition(R.anim.open_translate,R.anim.close_scale);
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Invalid Registration code"+sappa ,Toast.LENGTH_SHORT).show();
            }
        }
        

}

    }


    public void Register(View v){
        Intent register1 = new Intent(LoginActivity.this,RegisterActivity.class);

        startActivity(register1);

    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
