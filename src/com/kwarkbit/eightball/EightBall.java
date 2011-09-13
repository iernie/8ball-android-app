package com.kwarkbit.eightball;

import java.util.Random;

import com.kwarkbit.eightball.R;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EightBall extends Activity {
    /** Called when the activity is first created. */
	
	final static int DIALOG_HELP = 0;
	final static int DIALOG_ABOUT = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void randomize(View vew) {
    	String[] input = getAlts();
    	if(input.length < 2) {
    		Toast.makeText(this, "Not enough alternatives", Toast.LENGTH_SHORT).show();
    	} else {
    		Random r = new Random();
    		((TextView) findViewById(R.id.result_text)).setVisibility(View.VISIBLE);
    		((TextView) findViewById(R.id.result)).setText(getRandomAlt(input, r));
    		((TextView) findViewById(R.id.result2)).setText("");
    		((TextView) findViewById(R.id.result3)).setText("");
    	}
    }
    
    public void randomize3(View vew) {
    	String[] input = getAlts();
    	if(input.length < 2) {
    		Toast.makeText(this, "Not enough alternatives", Toast.LENGTH_SHORT).show();
    	} else {
    		Random r = new Random();
    		((TextView) findViewById(R.id.result_text)).setVisibility(View.VISIBLE);
    		((TextView) findViewById(R.id.result)).setText(getRandomAlt(input, r));
    		((TextView) findViewById(R.id.result2)).setText(getRandomAlt(input, r));
    		((TextView) findViewById(R.id.result3)).setText(getRandomAlt(input, r));
    	}
    }

	private String[] getAlts() {
		String[] input = (((EditText) findViewById(R.id.input)).getText().toString()).trim().split(":");
		return input;
	}

	private String getRandomAlt(String[] input, Random r) {
		return input[r.nextInt(input.length)].trim();
	}
    
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.about:
        	showDialog(DIALOG_ABOUT);
            return true;
        case R.id.help:
        	showDialog(DIALOG_HELP);
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        switch(id) {
        case DIALOG_HELP:
        	 dialog = new Dialog(this);
             dialog.setContentView(R.layout.dialog);
             dialog.setTitle(R.string.help);
             dialog.setCancelable(true);
             TextView help_text = (TextView) dialog.findViewById(R.id.dialog_text);
             help_text.setText(R.string.help_text);
             dialog.show();
            break;
        case DIALOG_ABOUT:
        	dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog);
            dialog.setTitle(R.string.about);
            dialog.setCancelable(true);
            TextView about_text = (TextView) dialog.findViewById(R.id.dialog_text);
            about_text.setText(R.string.about_text);
            dialog.show();
           break;
        default:
            dialog = null;
        }
        return dialog;
    }
}