package com.hpinc.voter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.feedback.R;


public class VoteActivity extends Activity {
	
	private RadioGroup radioGroup;
	private RadioButton candidate1,candidate2,nato;
	private Button button;

    DatabaseHelper db = new DatabaseHelper(VoteActivity.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote);
		
		
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		
	
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// find which radio button is selected
				if(checkedId == R.id.radio0) {
					Toast.makeText(getApplicationContext(), "choice: ASSA", Toast.LENGTH_SHORT).show();
				} else if(checkedId == R.id.radio1){
					Toast.makeText(getApplicationContext(), "choice: PHPRY", Toast.LENGTH_SHORT).show();
				}
                else if(checkedId == R.id.radioButton5)
                {
                    Toast.makeText(getApplicationContext(), "choice: NOTA", Toast.LENGTH_SHORT).show();
                }
				else
				{
					Toast.makeText(getApplicationContext(), "choose any one option", 
							Toast.LENGTH_SHORT).show();	
				}
			}
		});
	
		
		candidate1 = (RadioButton) findViewById(R.id.radio0);
		candidate2 = (RadioButton) findViewById(R.id.radio1);
	    nato= (RadioButton) findViewById(R.id.radioButton5);
	   
	    button = (Button)findViewById(R.id.button1);
	    button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int selectedId = radioGroup.getCheckedRadioButtonId();
				
				// find which radioButton is checked by id
				if(selectedId == candidate1.getId()) {
					Toast.makeText(getApplicationContext(),"You chose 'ASSA'",Toast.LENGTH_SHORT).show();
					RegisterActivity.count[db.getyourdata2(LoginActivity.user, LoginActivity.pass)]=1;
					
				}
				else if(selectedId == candidate2.getId()){
					Toast.makeText(getApplicationContext(),"You chose 'PHPRY'",Toast.LENGTH_SHORT).show();
                    RegisterActivity.count[db.getyourdata2(LoginActivity.user, LoginActivity.pass)]=1;
					}
                else if(selectedId == nato.getId()){
                    Toast.makeText(getApplicationContext(),"You chose 'NOTA'",Toast.LENGTH_SHORT).show();
                    RegisterActivity.count[db.getyourdata2(LoginActivity.user, LoginActivity.pass)]=1;
                }
				else {
					Toast.makeText(getApplicationContext(),"you did not choose any option",Toast.LENGTH_SHORT).show();	
				}

				Intent i =new Intent(getApplicationContext(),ResultActivity.class);
				
				startActivity(i);
			}
			});
	    
	}public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

}


