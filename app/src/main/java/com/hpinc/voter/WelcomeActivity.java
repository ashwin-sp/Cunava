package com.hpinc.voter;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;

import com.example.feedback.R;

public class WelcomeActivity extends Activity implements Runnable {

	ProgressBar loading;
    private static int PROGRESS_INITIAL=0;
    private static int PROGRESS_MAX=100;
    private static int PROGRESS_STEP=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        loading=(ProgressBar)findViewById(R.id.progressBar1);
        loading.setVisibility(ProgressBar.VISIBLE);
        loading.setProgress(PROGRESS_INITIAL);
        loading.setMax(PROGRESS_MAX);
        new Thread(this).start();
        }
    public void run() {
        int currentPosition = 0;
        while (currentPosition < PROGRESS_MAX) {
            try {
                Thread.sleep(1000);
                currentPosition += PROGRESS_STEP;
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            loading.setProgress(currentPosition);
        }
        Intent from=new Intent(WelcomeActivity.this,LoginActivity.class);
        startActivity(from);
    }



        


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_welcome, menu);
        return true;
    }
    
}
