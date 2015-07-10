package com.dhcc.ims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity_two extends Activity{
	Button html;
	Button android;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		html=(Button) findViewById(R.id.two_html);
		android=(Button) findViewById(R.id.two_android);
		
		setListener();
		
		
		
	}
	private void setListener() {
		html.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i1=new Intent();
				i1.setClass(Activity_two.this, DragActivity.class);
				startActivity(i1);
				
			}});
		android.setOnClickListener(new  OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i2=new Intent();
				//i2.setClass(Activity_two.this, Chart_android_Activity.class);
				startActivity(i2);
				
			}});
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
	
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	

}
