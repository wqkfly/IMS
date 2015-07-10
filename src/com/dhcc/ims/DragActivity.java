package com.dhcc.ims;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class DragActivity extends Activity{

	WebView wv;
	Button drag_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drag);
		wv = (WebView) findViewById(R.id.wv);
		drag_button=(Button) findViewById(R.id.drag_button);
		wv.getSettings().setJavaScriptEnabled(true);  //设置WebView支持javascript
		wv.getSettings().setUseWideViewPort(true);//设置是当前html界面自适应屏幕
		wv.getSettings().setSupportZoom(false); //设置支持缩放
		wv.getSettings().setBuiltInZoomControls(true);//显示缩放控件
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.requestFocus();
		wv.loadUrl("file:///android_asset/drag.html"); //加载assert目录下的文件
	
		 
		 
	/*	update_line.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateBtn(v);
			}
		});*/
	}
	 //设置回退  
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {  
        	wv.goBack(); //goBack()表示返回WebView的上一页面  
           return true;
        }  
        return false;  
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.chart__html, menu);
		return true;
	}

	//模拟获取远程数据 这里可以是联网到服务端获取数据
	private String getRemoteData(){
		 try {  
	            JSONObject object1 = new JSONObject();  
	            object1.put("name", "北京");  
	            object1.put("color", "#1f7e92");  
	            Random random = new Random();
	            //js中的数组类型要使用JSONArray对象
	            JSONArray jadata= new JSONArray();  
	            for(int i=0;i<12;i++){
	            	jadata.put(random.nextInt(40));
	            }
	            object1.put("value", jadata);    
	            JSONArray jsonArray = new JSONArray();  
	            jsonArray.put(object1);  
	            return jsonArray.toString();  
	        } catch (JSONException e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	}
	
	public void updateBtn(View view){
		
		wv.loadUrl("javascript:setContentInfo('"+getRemoteData()+"')");  
	}

}
