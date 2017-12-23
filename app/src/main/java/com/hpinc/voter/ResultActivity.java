package com.hpinc.voter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.feedback.R;
import com.hpinc.voter.LoginActivity;

public class ResultActivity extends Activity {
	
	private Button button;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		 button = (Button)findViewById(R.id.button1);
		 
		 button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {


                    Intent i =new Intent(getApplicationContext(),LoginActivity.class);

                    startActivity(i);
                    overridePendingTransition(R.anim.open_scale,R.anim.close_translate);
				}
	});
		 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
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
