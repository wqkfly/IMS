package com.dhcc.ims;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class Chart_HtmlActivity extends Activity{
	WebView wv;
	Button update_line;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart__html);
		wv = (WebView) findViewById(R.id.wv);
		update_line=(Button) findViewById(R.id.html_button);
		wv.getSettings().setJavaScriptEnabled(true);  //����WebView֧��javascript
		wv.getSettings().setUseWideViewPort(true);//�����ǵ�ǰhtml��������Ӧ��Ļ
		wv.getSettings().setSupportZoom(true); //����֧������
		wv.getSettings().setBuiltInZoomControls(true);//��ʾ���ſؼ�
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.requestFocus();
		wv.loadUrl("file:///android_asset/lineChart.html"); //����assertĿ¼�µ��ļ�
	
		update_line.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateBtn(v);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.chart__html, menu);
		return true;
	}

	//ģ���ȡԶ������ �������������������˻�ȡ����
	private String getRemoteData(){
		 try {  
	            JSONObject object1 = new JSONObject();  
	            object1.put("name", "����");  
	            object1.put("color", "#1f7e92");  
	            Random random = new Random();
	            //js�е���������Ҫʹ��JSONArray����
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
