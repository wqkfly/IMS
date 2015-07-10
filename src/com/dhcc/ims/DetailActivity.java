package com.dhcc.ims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.dhcc.entity.Person;

public class DetailActivity extends Activity{
	private TextView name,gender,dept,phone,time;
	private Person person;
	
	//private int mImage=R.drawable.yuefei;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		initData();
		initView();
		setListener();
	}


	private void initData() {
		Intent intent=getIntent();
		person=(Person) intent.getSerializableExtra("person");
		System.out.println(person);
		
	}


	private void setListener() {
		findViewById(R.id.okBt).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
	}


	private void initView() {
		
		name=(TextView) findViewById(R.id.name);
		gender=(TextView) findViewById(R.id.gender);
		dept=(TextView) findViewById(R.id.dept);
		phone=(TextView) findViewById(R.id.phone);
		time=(TextView) findViewById(R.id.time);
		
		name.setText(person.getName());
		gender.setText(person.getGender());
		dept.setText(person.getDept());
		phone.setText(person.getPhone());
		time.setText(person.getTime());
		
		
	}
}
