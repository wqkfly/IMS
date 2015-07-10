package com.dhcc.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.dhcc.ims.DoSharePre;
import com.dhcc.ims.LoginUser;
//import com.example.helloworld.MainActivity;

public class HttpGetPostCls {
	private static URL url = null;
	private InputStream inputStream = null;

	/**
	 *   ����������Ӧ��
	 *   <br>
	 */
	private int responseCode = 1;
	/**
	 *  408Ϊ���糬ʱ
	 */
	public static final int REQUEST_TIMEOUT_CODE = 408;
	/**
	 * �ɹ�*/
	public static final int REQUEST_SUCCESS = 200;
	
	/**
	 * �����ַ�����
	 */
	private static final String CHARSET = "UTF-8";
	/**
	 * �����������ʱʱ��
	 */
	private static final int REQUEST_TIME_OUT = 12000; 
	/**
	 * ��ȡ��Ӧ������ʱ��
	 */
	private static final int READ_TIME_OUT = 25000;
	
	public static String sCookie;
	public static boolean isConnect(Context context) {
		// ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ���
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// ��ȡ�������ӹ���Ķ���
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// �жϵ�ǰ�����Ƿ��Ѿ�����
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("error", e.toString());
		}
		return false;
	}

public static String LinkData(String cls,String mth,String Param,String Typ,Context frm)throws Exception 
{
try
   {/*
   	String enUft = URLEncoder.encode("�㶫ʡ������Ʊ������Ϣ��", "UTF-8");

	System.out.println(enUft); 

	java.net.URLDecoder urlDecoder = new java.net.URLDecoder(); 

	String s = urlDecoder.decode(enUft,"GB2312"); 

   */
	
	//��ͼ�����ʿվ�ڴ��������������ʿվ���ڴ����kill��������
	/*if(((LoginUser.UserDR==null)||(LoginUser.UserDR.equals("")))
			&&(frm.getClass().getName().equals("dhcc.com.mobilenurse.LogIn"))){
		DoSharePre.crashCall(PreferenceManager.getDefaultSharedPreferences(frm));
	}*/
	//���demo�ڴ������������demo���ڴ����kill��������
	if(((LoginUser.name==null)||(LoginUser.name.equals("")))
			&&(frm.getClass().getName().equals("com.dhcc.ims.MainActivity"))){
		DoSharePre.crashCall(PreferenceManager.getDefaultSharedPreferences(frm));
	}
   String[] arr=Param.split("\\&");
   String enparam="";
   for (int i=0;i<arr.length;i++)
   {
	   if (arr[i].equals("")) continue;
	   String[] itm=arr[i].split("\\=");
	   if (itm.length>1){
		   arr[i]=itm[0]+"="+URLEncoder.encode(itm[1],"UTF-8");
	   }else{
		   arr[i]=itm[0]+"=";  
	   }
	   enparam="&"+arr[i]+enparam;
   }
  // String WebUrl="http://10.1.41.109/dthealth/web/";
   //String WebUrl="http://10.1.40.13:8080/android_test/";
   if (LoginUser.webUrl.equals("")) LoginUser.webUrl="http://10.1.20.61:8080/android_test/";
   String linkcsp="StringService";
   //if (LoginUser.WebUrl.equals("")) LoginUser.WebUrl="http://10.1.41.114/dthealth/web/";
    //String enparam=URLEncoder.encode(Param,"UTF-8");
	
 	String url=LoginUser.webUrl+LoginUser.linkscp+"?className="+cls+"&methodName="+mth+"&type="+Typ+enparam;
    String ret=GetData(url,frm);
	return ret;
    }catch(Exception e) {
			e.printStackTrace();
			Toast.makeText(frm.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		return "error3";
	}
}
public static String GetData(String urlStr,Context frm)throws Exception {
	try
	{
		if (mobilecom.isConnect(frm) == false) {
			 Toast.makeText(frm, "���糬ʱ", 1000).show();
			 return "error";
			}

		StringBuffer sb = new StringBuffer();
		String line = null;
		//BufferedReader��һ��readLine��������������ÿ�ζ�ȡһ������
		BufferedReader buffer = null;
		url = new URL(urlStr);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        
		int num=0;
		while(true)
		{
			 
		try{
			//����һ��URL����
			//����һ��Http����
		    
			num=num+1;
			//�������ӳ�ʱʱ��
			urlConn.setConnectTimeout(REQUEST_TIME_OUT);
			urlConn.setReadTimeout(READ_TIME_OUT);  
	
			//urlConn.setDoOutput(true); 
			//urlConn.setUseCaches(false); 
            
	         if(sCookie!=null && sCookie.length()>0){   
	        	 urlConn.setRequestProperty("Cookie", sCookie);            
	         }
			urlConn.setRequestProperty("Charset", CHARSET); 
			urlConn.connect();
			//urlConn.setRequestMethod("POST");
			//urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//urlConn.setRequestProperty("Charset", "utf-8");
	
			//String aa=urlConn.getRequestProperties("Charset");
			//String reqheads=urlConn.getRequestProperties().toString();
			int code=urlConn.getResponseCode();
			if (code!= 404)
			{
			//ʹ��IO����ȡ���ݣ�InputStreamReader�����������ֽ���ת�����ַ���
			//�����ַ��������Ǻܷ��㣬��������������һ��BufferedReader��
			//��BufferedReader��readLine����������һ��һ�ж�ȡ����
				InputStream is = urlConn.getInputStream(); 
				InputStreamReader reads=new InputStreamReader(is);
				buffer = new BufferedReader(reads);
				while((line = buffer.readLine()) != null){
					sb.append(line);
				}
		      //is.close();
			  String cookie = urlConn.getHeaderField("set-cookie");   
			  if(cookie!=null && cookie.length()>0){   
				  sCookie = cookie;  
			  }
			    urlConn.disconnect(); 
			}
		}catch (Exception e) {
			urlConn.disconnect();
			sCookie=null;
			e.printStackTrace();
			continue;
		}finally{
			try{
				buffer.close();
				//sCookie=null;
				urlConn.disconnect();
			}catch (Exception e) {
				if (num>1) return "error1";
					e.printStackTrace();
					sCookie=null;
					urlConn.disconnect();
					//Toast.makeText(frm.getApplicationContext(), "���Ӵ���", Toast.LENGTH_LONG).show();
				continue;
			}
		}
		return sb.toString();
		}
	}catch(Exception e)
	{
		e.printStackTrace();
        return "error2";
	}
	}
public InputStream getInputStreamFromURL(String urlStr){
	HttpURLConnection urlConn = null;
	try {
		url = new URL(urlStr);
		urlConn = (HttpURLConnection) url.openConnection();
		inputStream = urlConn.getInputStream();
    } catch (MalformedURLException e) {  
    	e.printStackTrace();  
    } catch (IOException e) {  
    	e.printStackTrace();  
    }
	
	return inputStream;
}
}
