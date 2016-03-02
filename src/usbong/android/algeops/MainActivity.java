package usbong.android.algeops;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {
	private final int MAX_BOX = 8;
	private final int MAX_BALLOON = 8;
	
	private int currTotalBoxNumLeft=0;
	private int currTotalBalloonNumLeft=0;	
	private int currTotalBoxNumRight=0;
	private int currTotalBalloonNumRight=0;	
	
	private int currTotalBoxNumLeftCounter=0;
	
	private int leftBoxOffset=0;
	
	private ImageView[] leftBox;
	
	private boolean leftBoxLastPressedPlus;
	private int leftBoxCounterThreshold=0;
	
	private Button leftBoxPlusButton;
	private Button leftBoxMinusButton;
	private Button rightBoxPlusButton;
	private Button rightBoxMinusButton;

	private Button leftBalloonPlusButton;
	private Button leftBalloonMinusButton;
	private Button rightBalloonPlusButton;
	private Button rightBalloonMinusButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}
	
	public void init() {
		leftBox = new ImageView[MAX_BOX];
		int tempI;
		int resId;
		for (int i=0; i<MAX_BOX; i++) {
			tempI = i+1;
			resId = getResources().getIdentifier("left_box_" + tempI, "id", getPackageName());
			leftBox[i] = (ImageView)findViewById(resId);    	
		}
		
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
