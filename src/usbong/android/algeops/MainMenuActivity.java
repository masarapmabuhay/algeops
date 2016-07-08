/*
 * Copyright 2012 Michael Syson
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package usbong.android.algeops;

import usbong.android.utils.UsbongUtils;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressWarnings("deprecation")
public class MainMenuActivity extends ActionBarActivity
{	
	private Button addButton;
	private Button subtractButton;
	private Intent addIntent;
	private Intent subtractIntent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        init();
    }
    
    /*
     * Initialize this activity
     */
    public void init()
    {
    	addButton = (Button)findViewById(R.id.add_button);
		addIntent = new Intent().setClass(this, MainActivity.class);
		
    	addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				finish();
				startActivity(addIntent);
			}
    	});    	
    	
    	subtractButton = (Button)findViewById(R.id.subtract_button);
		subtractIntent = new Intent().setClass(this, SubtractActivity.class);
		
    	subtractButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				finish();
				startActivity(subtractIntent);
			}
    	});    	
    }
    
    public void initGUI()
    {  	
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		/*if (id == R.id.action_settings) {
			return true;
		}
		else */if (id == R.id.about) {
	    	new AlertDialog.Builder(MainMenuActivity.this).setTitle("About")
			.setMessage(UsbongUtils.readTextFileInAssetsFolder(MainMenuActivity.this,"about.txt")) //don't add a '/', otherwise the file would not be found
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			}).show();
			return true;
		}		
		return super.onOptionsItemSelected(item);
	}
}