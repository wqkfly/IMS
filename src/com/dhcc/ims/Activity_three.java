package com.dhcc.ims;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dhcc.webservice.mobilecom;

public class Activity_three extends Activity{
	EditText name;
	EditText ps;
	Button add;
	String user_name;
	String user_ps;
	mobilecom com=new mobilecom();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);
		initView();
		setListener();
		
	}

	private void setListener() {
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				user_name=name.getText().toString();
				user_ps=ps.getText().toString();
				if(user_name.equals("")||user_name.length()==0||user_ps.equals("")||user_ps.length()==0)
				{
					Toast.makeText(Activity_three.this, "用户名或密码不能为空", 1000).show();
					return;
				}else{
					String param="name="+user_name+"&ps="+user_ps;
					try{
						com.ThreadHttp("com.android.container.UserAction", "insert", param,"method", Activity_three.this, 0, handler);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
			}
			
		});
		
	}
	Handler handler = new Handler() 
	{
		public void handleMessage(Message paramMessage) 
		{
			if(paramMessage.what == 0)
		{
				if (com.RetData.indexOf("error")!=-1)
				{
					Toast.makeText(getApplicationContext(), "连接错误！", 1000).show();
					return;
				}
				if (!com.RetData.equals(""))
				{
					Toast.makeText(getApplicationContext(), com.RetData, 1000).show();
				}
    	   }
		}
	};

	private void initView() {
		name=(EditText) findViewById(R.id.user_name);
		ps=(EditText) findViewById(R.id.user_ps);
		add=(Button) findViewById(R.id.user_add);
		
	}
}
