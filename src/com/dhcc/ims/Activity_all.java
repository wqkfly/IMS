package com.dhcc.ims;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class Activity_all extends TabActivity implements OnCheckedChangeListener{
	private TabHost mHost;
	private RadioGroup radioderGroup;
	private RadioButton button1;
	private RadioButton button2;
	private RadioButton button3;
    private static final int SWIPE_MIN_DISTANCE = 120;  
    private static final int SWIPE_MAX_OFF_PATH = 250;  
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;  
    private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	public RadioButton[] buttons=new RadioButton[3] ;
	
	int currentView = 0;  
    private static int maxTabIndex = 2;  
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_menu);
    	initView();
    }
	private void initView() {
		mHost=this.getTabHost();
		//添加选项卡
		mHost.addTab(mHost.newTabSpec("ONE").setIndicator("ONE")
				.setContent(new Intent(this,Activity_one.class)));
		mHost.addTab(mHost.newTabSpec("TWO").setIndicator("TWO")
        		.setContent(new Intent(this,Activity_two.class)));
        mHost.addTab(mHost.newTabSpec("THREE").setIndicator("THREE")
        		.setContent(new Intent(this,Activity_three.class)));
        
        radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
        button1=(RadioButton) findViewById(R.id.radio_button0);
        button2=(RadioButton) findViewById(R.id.radio_button1);
        button3=(RadioButton) findViewById(R.id.radio_button2);
        buttons[0]=button1;
        buttons[1]=button2;
        buttons[2]=button3;
        radioderGroup.setOnCheckedChangeListener(this);
        mHost.setCurrentTab(0);
        button1.setChecked(true);
        
        gestureDetector=new GestureDetector(new MyGestureDetector());
        gestureListener=new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(gestureDetector.onTouchEvent(event)){
					return true;
				}
				return false;
			}
		};
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId){
		case R.id.radio_button0:
			mHost.setCurrentTabByTag("ONE");
			break;
		case R.id.radio_button1:
			mHost.setCurrentTabByTag("TWO");
			break;
		case R.id.radio_button2:
			mHost.setCurrentTabByTag("THREE");
			break;
		}
		
	}
	  //当客户点击菜单当中的某一个选项时，会调用该方法  
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_settings:
			Intent intent = new Intent();
            intent.setClass(Activity_all.this, Activity_menu.class);
            startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	class MyGestureDetector extends SimpleOnGestureListener{
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			TabHost tabHost=getTabHost();
			
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					Log.i("test", "right");
					if (currentView == maxTabIndex) {
						currentView = 0;
					} else {
						currentView++;
					}
					tabHost.setCurrentTab(currentView);
					buttons[currentView].setChecked(true);
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					Log.i("test", "left");
					if (currentView == 0) {
						currentView = maxTabIndex;
					} else {
						currentView--;
					}
					tabHost.setCurrentTab(currentView);
					buttons[currentView].setChecked(true);
				}
			} catch (Exception e) {
			}
			return false;
		}
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (gestureDetector.onTouchEvent(event)) {
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}
}
