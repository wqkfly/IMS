package com.dhcc.ims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.dhcc.entity.Person;

public class AddActivity extends Activity{
	private EditText etaddname,etaddgender,etadddept,etaddphone;
	
	
	//private int mImage=R.drawable.yuefei;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		initView();
		setListener();
	}

	private void setListener() {
		
		findViewById(R.id.addcancleBt).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		findViewById(R.id.addBt).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String name=etaddname.getText().toString();
				String gender=etaddgender.getText().toString();
				String dept=etadddept.getText().toString();
				String phone=etaddphone.getText().toString();
				
				 Person p= new Person();
				 p.setName(name);
				 p.setGender(gender);
				 p.setDept(dept);
				 p.setPhone(phone);
				 
				Intent intent=new Intent();
				intent.setClass(AddActivity.this,MainActivity.class);
				intent.putExtra("person", p);
				setResult(RESULT_OK,intent);
				finish();
			}});
		
	}

	private void initView() {
		
		etaddname=(EditText) findViewById(R.id.etaddname);
		etaddgender=(EditText) findViewById(R.id.etaddgender);
		etadddept=(EditText) findViewById(R.id.etadddept);
		etaddphone=(EditText) findViewById(R.id.etaddphone);
		
	}
}
