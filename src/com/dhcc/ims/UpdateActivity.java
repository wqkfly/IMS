package com.dhcc.ims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.dhcc.entity.Person;

public class UpdateActivity extends Activity{
	private EditText etupname,etupgender,etupdept,etupphone;
	
	private Person person;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);
		initData();
		initView();
		setListener();
	}
	private void setListener(){
		findViewById(R.id.upcancleBt).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();	
			}
			
		});
		
		findViewById(R.id.upBt).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String name=etupname.getText().toString();
				String gender=etupgender.getText().toString();
				String dept=etupdept.getText().toString();
				String phone=etupphone.getText().toString();
				
				person.setName(name);
				person.setGender(gender);
				person.setDept(dept);
				person.setPhone(phone);
				
				Intent intent=new Intent();
				intent.setClass(UpdateActivity.this,MainActivity.class);
				intent.putExtra("person", person);
				setResult(RESULT_OK,intent);
				finish();
			}});
	}
	private void initView(){
		etupname=(EditText) findViewById(R.id.etupname);
		etupgender=(EditText) findViewById(R.id.etupgender);
		etupdept=(EditText) findViewById(R.id.etupdept);
		etupphone=(EditText) findViewById(R.id.etupphone);
		
		etupname.setText(person.getName());
		etupgender.setText(person.getGender());
		etupdept.setText(person.getDept());
		etupphone.setText(person.getPhone());
	}
	private void initData(){
		Intent intent=getIntent();
		person=(Person) intent.getSerializableExtra("person");
		System.out.println(person);
		
	}
}
