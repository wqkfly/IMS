package com.dhcc.ims;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dhcc.webservice.mobilecom;


public class MainActivity extends Activity {
	private EditText etusername;
	private EditText etpassword;
	private Button logBt;
	mobilecom com=new mobilecom();
	SharedPreferences perferences;
	@Override
	protected void onCreate(Bundle savedInstanceState){
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initData();
		setListener();
		
	}
	
	

	private void initData() {
		etusername=(EditText) findViewById(R.id.login_edit_name);
		etpassword=(EditText) findViewById(R.id.login_edit_pwd);
		logBt=(Button) findViewById(R.id.login_but);
		//menu  SharedPreferences数据存储,设置menu的值
		perferences=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		LoginUser.webUrl=perferences.getString("webAddress","");
		LoginUser.DefaultRec=perferences.getString("defaultRecord", "");
	}
	private void setListener() {
		logBt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				LoginUser.webUrl=perferences.getString("webAddress", "");//第一个参数为要获取的值的Key，第二个参数为没有Key对应的值返回的默认值。
				LoginUser.DefaultRec=perferences.getString("defaultRecord", "");
				String username=etusername.getText().toString();
				String password=etpassword.getText().toString();
				if(username.equals("")||username.length()==0||password.equals("")||password.length()==0)
				{
					Toast.makeText(MainActivity.this, "用户名或密码不能为空", 0).show();
					return;
				}else{
					LoginUser.name=username;
					LoginUser.pass=password;
					String param="name="+username+"&password="+password;
					
					try{
						com.ThreadHttp("com.android.container.UserAction", "getUser", param,"method", MainActivity.this, 0, handler);
					} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				}
			}});
		
	}
	Handler handler =new Handler(){
		public void handleMessage(Message paramMessage) {
			if(paramMessage.what == 0){
				if (com.RetData.indexOf("error")!=-1){
					Toast.makeText(getApplicationContext(), "连接错误！", 1000).show();
					return;
				}
				if (!com.RetData.equals("")){
					   String[] s=com.RetData.split("\\^");
	            	   String s1=LoginUser.pass;
	            	   if(!LoginUser.pass.equals(s[2])) {
	            		   Toast.makeText(MainActivity.this, "用户名或密码错误", 0).show();
	            		   return;
	            	   }else{
	            		   
	            	   LoginUser.id=s[0];
	            	   Intent i=new Intent();
	            	   i.setClass(MainActivity.this, Activity_all.class);
	            	   startActivity(i);
	            	   MainActivity.this.finish();
	            	   DoSharePre.loginCall(perferences); 
	            }
			}
		}
	};
};
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
	
}
public boolean onOptionsItemSelected(MenuItem item) {
	 switch (item.getItemId()) {
	case	R.id.action_settings:
	//	Toast.makeText(MainActivity.this, "设置", 0).show();
		Intent intent = new Intent();
       intent.setClass(MainActivity.this, Activity_menu.class);
       startActivity(intent);
		break;

	default:
		break;
	}
	 
	 return super.onContextItemSelected(item); 
}
}















