package usbong.android.algeops;

import java.util.Random;

import usbong.android.utils.UsbongUtils;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edmodo.rangebar.RangeBar;

public class MainActivity extends ActionBarActivity {
	private final int MAX_BOX = 8;
	private final int MAX_BALLOON = 8;

	private int var_a;
	private int var_b;
	private int var_c;
	private int var_d;
	
	private TextView leftEquationTextView;
	private TextView rightEquationTextView;
	
	private int currTotalBoxNumLeft=0;
	private int currTotalBalloonNumLeft=0;	
	private int currTotalBoxNumRight=0;
	private int currTotalBalloonNumRight=0;	
	
	private int currTotalBoxNumLeftCounter=0;
	private int currTotalBoxNumRightCounter=0;
	private int currTotalBalloonNumLeftCounter=0;
	private int currTotalBalloonNumRightCounter=0;
	
	private int leftBoxOffset=0;
	private int rightBoxOffset=0;
	private int leftBalloonOffset=0;
	private int rightBalloonOffset=0;

	private ImageView[] leftBox;
	private ImageView[] rightBox;
	private ImageView[] leftBalloon;
	private ImageView[] rightBalloon;
		
	private Button leftBoxPlusButton;
	private Button leftBoxMinusButton;
	private Button rightBoxPlusButton;
	private Button rightBoxMinusButton;

	private Button leftBalloonPlusButton;
	private Button leftBalloonMinusButton;
	private Button rightBalloonPlusButton;
	private Button rightBalloonMinusButton;

	private Button checkButton;
	private Button newButton;	

	private LinearLayout myLinearLayoutRangeBarSet1;
	private RangeBar myRangeBar1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}
	
	public void init() {
		updateEquations();
		
		leftBox = new ImageView[MAX_BOX];
		rightBox = new ImageView[MAX_BOX];
		leftBalloon = new ImageView[MAX_BALLOON];
		rightBalloon = new ImageView[MAX_BALLOON];
		
		int tempI;
		int resId;
		for (int i=0; i<MAX_BOX; i++) {
			tempI = i+1;
			resId = getResources().getIdentifier("left_box_" + tempI, "id", getPackageName());
			leftBox[i] = (ImageView)findViewById(resId);    	
			
			resId = getResources().getIdentifier("right_box_" + tempI, "id", getPackageName());
			rightBox[i] = (ImageView)findViewById(resId);    	

			resId = getResources().getIdentifier("left_balloon_" + tempI, "id", getPackageName());
			leftBalloon[i] = (ImageView)findViewById(resId);    	
			
			resId = getResources().getIdentifier("right_balloon_" + tempI, "id", getPackageName());
			rightBalloon[i] = (ImageView)findViewById(resId);    	
		}
		
		//-----------------------------------------------------------------------
		//Left Box
		//-----------------------------------------------------------------------		
    	leftBoxPlusButton = (Button)findViewById(R.id.left_box_plus_button);    	
    	leftBoxPlusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Math.abs(currTotalBoxNumLeft)<=MAX_BOX) {
					currTotalBoxNumLeft++;
					if (currTotalBoxNumLeft>0) { //positive
						leftBoxOffset=1;
					}
					else {
						leftBoxOffset=0;						
					}
					if (Math.abs(currTotalBoxNumLeft)>=MAX_BOX) {
						currTotalBoxNumLeft=MAX_BOX;
					}
					currTotalBoxNumLeftCounter=Math.abs(currTotalBoxNumLeft)-leftBoxOffset;						
					
					if (currTotalBoxNumLeft>0) { //if there are green boxes left						
						leftBox[currTotalBoxNumLeftCounter].setBackground(getResources().getDrawable(getResources().getIdentifier("box_green", "drawable", getPackageName())));					
						leftBox[currTotalBoxNumLeftCounter].setVisibility(ImageView.VISIBLE);					
					}
					else {
						leftBox[currTotalBoxNumLeftCounter].setVisibility(ImageView.INVISIBLE);
					}										
				}
			}
    	});
    	
    	leftBoxMinusButton = (Button)findViewById(R.id.left_box_minus_button);    	
    	leftBoxMinusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Math.abs(currTotalBoxNumLeft)<=MAX_BOX) {
					currTotalBoxNumLeft--;
					if (currTotalBoxNumLeft>=0) { //positive + 0
						leftBoxOffset=0;
					}
					else {
						leftBoxOffset=1;						
					}
					if (Math.abs(currTotalBoxNumLeft)>=MAX_BOX) {
						currTotalBoxNumLeft=-(MAX_BOX);
					}
					currTotalBoxNumLeftCounter=Math.abs(currTotalBoxNumLeft)-leftBoxOffset;						

					if (currTotalBoxNumLeft>=0) { //if there are green boxes left						
						leftBox[currTotalBoxNumLeftCounter].setVisibility(ImageView.INVISIBLE);					
					}
					else {
						leftBox[currTotalBoxNumLeftCounter].setBackground(getResources().getDrawable(getResources().getIdentifier("box_red", "drawable", getPackageName())));					
						leftBox[currTotalBoxNumLeftCounter].setVisibility(ImageView.VISIBLE);
					}										
				}
			}
    	});

		//-----------------------------------------------------------------------
		//Right Box
		//-----------------------------------------------------------------------		
    	rightBoxPlusButton = (Button)findViewById(R.id.right_box_plus_button);    	
    	rightBoxPlusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Math.abs(currTotalBoxNumRight)<=MAX_BOX) {
					currTotalBoxNumRight++;
					if (currTotalBoxNumRight>0) { //positive
						rightBoxOffset=1;
					}
					else {
						rightBoxOffset=0;						
					}
					if (Math.abs(currTotalBoxNumRight)>=MAX_BOX) {
						currTotalBoxNumRight=MAX_BOX;
					}
					currTotalBoxNumRightCounter=Math.abs(currTotalBoxNumRight)-rightBoxOffset;						
					
					if (currTotalBoxNumRight>0) { //if there are green boxes left						
						rightBox[currTotalBoxNumRightCounter].setBackground(getResources().getDrawable(getResources().getIdentifier("box_green", "drawable", getPackageName())));					
						rightBox[currTotalBoxNumRightCounter].setVisibility(ImageView.VISIBLE);					
					}
					else {
						rightBox[currTotalBoxNumRightCounter].setVisibility(ImageView.INVISIBLE);
					}										
				}
			}
    	});
    	
    	rightBoxMinusButton = (Button)findViewById(R.id.right_box_minus_button);    	
    	rightBoxMinusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Math.abs(currTotalBoxNumRight)<=MAX_BOX) {
					currTotalBoxNumRight--;
					if (currTotalBoxNumRight>=0) { //positive + 0
						rightBoxOffset=0;
					}
					else {
						rightBoxOffset=1;						
					}
					if (Math.abs(currTotalBoxNumRight)>=MAX_BOX) {
						currTotalBoxNumRight=-(MAX_BOX);
					}
					currTotalBoxNumRightCounter=Math.abs(currTotalBoxNumRight)-rightBoxOffset;						

					if (currTotalBoxNumRight>=0) { //if there are green boxes left						
						rightBox[currTotalBoxNumRightCounter].setVisibility(ImageView.INVISIBLE);					
					}
					else {
						rightBox[currTotalBoxNumRightCounter].setBackground(getResources().getDrawable(getResources().getIdentifier("box_red", "drawable", getPackageName())));					
						rightBox[currTotalBoxNumRightCounter].setVisibility(ImageView.VISIBLE);
					}										
				}
			}
    	});

		//-----------------------------------------------------------------------
		//Left Balloon
		//-----------------------------------------------------------------------		
    	leftBalloonPlusButton = (Button)findViewById(R.id.left_balloon_plus_button);    	
    	leftBalloonPlusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Math.abs(currTotalBalloonNumLeft)<=MAX_BALLOON) {
					currTotalBalloonNumLeft++;
					if (currTotalBalloonNumLeft>0) { //positive
						leftBalloonOffset=1;
					}
					else {
						leftBalloonOffset=0;						
					}
					if (Math.abs(currTotalBalloonNumLeft)>=MAX_BALLOON) {
						currTotalBalloonNumLeft=MAX_BALLOON;
					}
					currTotalBalloonNumLeftCounter=Math.abs(currTotalBalloonNumLeft)-leftBalloonOffset;						
					
					if (currTotalBalloonNumLeft>0) { //if there are green balloones left						
						leftBalloon[currTotalBalloonNumLeftCounter].setBackground(getResources().getDrawable(getResources().getIdentifier("balloon_green", "drawable", getPackageName())));					
						leftBalloon[currTotalBalloonNumLeftCounter].setVisibility(ImageView.VISIBLE);					
					}
					else {
						leftBalloon[currTotalBalloonNumLeftCounter].setVisibility(ImageView.INVISIBLE);
					}										
				}
			}
    	});
    	
    	leftBalloonMinusButton = (Button)findViewById(R.id.left_balloon_minus_button);    	
    	leftBalloonMinusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Math.abs(currTotalBalloonNumLeft)<=MAX_BALLOON) {
					currTotalBalloonNumLeft--;
					if (currTotalBalloonNumLeft>=0) { //positive + 0
						leftBalloonOffset=0;
					}
					else {
						leftBalloonOffset=1;						
					}
					if (Math.abs(currTotalBalloonNumLeft)>=MAX_BALLOON) {
						currTotalBalloonNumLeft=-(MAX_BALLOON);
					}
					currTotalBalloonNumLeftCounter=Math.abs(currTotalBalloonNumLeft)-leftBalloonOffset;						

					if (currTotalBalloonNumLeft>=0) { //if there are green balloones left						
						leftBalloon[currTotalBalloonNumLeftCounter].setVisibility(ImageView.INVISIBLE);					
					}
					else {
						leftBalloon[currTotalBalloonNumLeftCounter].setBackground(getResources().getDrawable(getResources().getIdentifier("balloon_red", "drawable", getPackageName())));					
						leftBalloon[currTotalBalloonNumLeftCounter].setVisibility(ImageView.VISIBLE);
					}										
				}
			}
    	});

		//-----------------------------------------------------------------------
		//Right Balloon
		//-----------------------------------------------------------------------		
    	rightBalloonPlusButton = (Button)findViewById(R.id.right_balloon_plus_button);    	
    	rightBalloonPlusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Math.abs(currTotalBalloonNumRight)<=MAX_BALLOON) {
					currTotalBalloonNumRight++;
					if (currTotalBalloonNumRight>0) { //positive
						rightBalloonOffset=1;
					}
					else {
						rightBalloonOffset=0;						
					}
					if (Math.abs(currTotalBalloonNumRight)>=MAX_BALLOON) {
						currTotalBalloonNumRight=MAX_BALLOON;
					}
					currTotalBalloonNumRightCounter=Math.abs(currTotalBalloonNumRight)-rightBalloonOffset;						
					
					if (currTotalBalloonNumRight>0) { //if there are green balloones left						
						rightBalloon[currTotalBalloonNumRightCounter].setBackground(getResources().getDrawable(getResources().getIdentifier("balloon_green", "drawable", getPackageName())));					
						rightBalloon[currTotalBalloonNumRightCounter].setVisibility(ImageView.VISIBLE);					
					}
					else {
						rightBalloon[currTotalBalloonNumRightCounter].setVisibility(ImageView.INVISIBLE);
					}										
				}
			}
    	});
    	
    	rightBalloonMinusButton = (Button)findViewById(R.id.right_balloon_minus_button);    	
    	rightBalloonMinusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Math.abs(currTotalBalloonNumRight)<=MAX_BALLOON) {
					currTotalBalloonNumRight--;
					if (currTotalBalloonNumRight>=0) { //positive + 0
						rightBalloonOffset=0;
					}
					else {
						rightBalloonOffset=1;						
					}
					if (Math.abs(currTotalBalloonNumRight)>=MAX_BALLOON) {
						currTotalBalloonNumRight=-(MAX_BALLOON);
					}
					currTotalBalloonNumRightCounter=Math.abs(currTotalBalloonNumRight)-rightBalloonOffset;						

					if (currTotalBalloonNumRight>=0) { //if there are green balloones left						
						rightBalloon[currTotalBalloonNumRightCounter].setVisibility(ImageView.INVISIBLE);					
					}
					else {
						rightBalloon[currTotalBalloonNumRightCounter].setBackground(getResources().getDrawable(getResources().getIdentifier("balloon_red", "drawable", getPackageName())));					
						rightBalloon[currTotalBalloonNumRightCounter].setVisibility(ImageView.VISIBLE);
					}										
				}
			}
    	});

		//-----------------------------------------------------------------------
		//Range Bar Set 1
		//-----------------------------------------------------------------------		    	
    	myLinearLayoutRangeBarSet1 = (LinearLayout) findViewById(R.id.linearlayout_box_range_bar_set);
    	myRangeBar1 = (RangeBar) findViewById(R.id.rangebar1);
    	int totalBarWidth = myLinearLayoutRangeBarSet1.getWidth();
    	for (int i=0; i<totalBarWidth; i+=2) { //why 2? the gap between points is 2    		
    		Log.d(">>>>barWidth", ""+i);
    		TextView tv = new TextView(this);
    		tv.setText("1");
    		myLinearLayoutRangeBarSet1.addView(tv);    		
    	}    	
    	
    	newButton = (Button)findViewById(R.id.new_button);    	
    	newButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		    	updateEquations();
			}
    	});

    	checkButton = (Button)findViewById(R.id.check_button);    	
    	checkButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//		    	checkAnswer();
			}
    	});
	}
	
	//References:
	//[1] http://stackoverflow.com/questions/6029495/how-can-i-generate-random-number-in-specific-range-in-android;
	//last accessed: 20160302; answer by: Ishtar (20110517), edited by Mr. Polywhirl (20140214)
	//[2] http://stackoverflow.com/questions/3938992/how-to-generate-random-positive-and-negative-numbers-in-java;
	//last accessed: 20160302; answer by: Pinichi (20101015)
	public void generateEquation() {
		Random r = new Random();
		var_a = r.nextInt(7+6) - 6; //randomizes from -6 to 6
		var_d = r.nextInt(7+6) - 6; //randomizes from -6 to 6

		//--------------------------------------------
		//var_b
		//--------------------------------------------
		if ((var_d==-5) || (var_d==-6)) {
			int temp = -10-var_d;
			var_b = r.nextInt(7+temp) - temp; //randomizes from -10-d to 6			
		}
		else if ((var_d==5) || (var_d==6)) {
			int temp = 10-var_d +1;
			var_b = r.nextInt(temp+6) - 6; //randomizes from -6 to 10-d
		}
		else { //-4 to 4
			var_b = r.nextInt(7+6) - 6; //randomizes from -6 to 6						
		}
		
		//--------------------------------------------
		//var_c
		//--------------------------------------------
		if ((var_a==-5) || (var_a==-6)) {
			int temp = -10-var_a;
			var_c = r.nextInt(7+temp) - temp; //randomizes from -10-d to 6			
		}
		else if ((var_a==5) || (var_a==6)) {
			int temp = 10-var_a +1;
			var_c = r.nextInt(temp+6) - 6; //randomizes from -6 to 10-d
		}
		else { //-4 to 4
			var_c = r.nextInt(7+6) - 6; //randomizes from -6 to 6						
		}	
		
//		Log.d(">>>var_a",""+var_a);
	}
	
	public void updateEquations() {
		generateEquation();		
		leftEquationTextView = (TextView)findViewById(R.id.left_equation);    			
		
		//added by Mike, 20160328
		if (var_b < 0) { //if negative number
			leftEquationTextView.setText(var_a+"x"+var_b);			
		}
		else {
			leftEquationTextView.setText(var_a+"x"+"+"+var_b);			
		}
		
		generateEquation();		
		rightEquationTextView = (TextView)findViewById(R.id.right_equation);    			
		//added by Mike, 20160328
		if (var_d < 0) { //if negative number
			rightEquationTextView.setText(var_c+"x"+var_d);		
		}
		else {
			rightEquationTextView.setText(var_c+"x"+"+"+var_d);					
		}
	}
	
	public void checkAnswer() {
		String leftEquation;
		String rightEquation;
		
		leftEquationTextView = (TextView)findViewById(R.id.left_equation);
		leftEquation = leftEquationTextView.getText().toString();
		
		rightEquationTextView = (TextView)findViewById(R.id.right_equation);    			
		rightEquation = rightEquationTextView.getText().toString();
/*
		private int currTotalBoxNumLeft=0;
		private int currTotalBalloonNumLeft=0;	
		private int currTotalBoxNumRight=0;
		private int currTotalBalloonNumRight=0;	
*/
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
	    	new AlertDialog.Builder(MainActivity.this).setTitle("About")
			.setMessage(UsbongUtils.readTextFileInAssetsFolder(MainActivity.this,"about.txt")) //don't add a '/', otherwise the file would not be found
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
