package com.dhcc.ims;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DoSharePre {

	public static void loginCall(SharedPreferences sp) {
		// TODO Auto-generated method stub
		Editor edit=sp.edit();
		edit.putString("webUrl", LoginUser.webUrl);
		edit.putString("name", LoginUser.name);
		edit.putString("ps", LoginUser.pass);
		edit.putString("id", LoginUser.id);
		edit.putString("DefaultRec", LoginUser.DefaultRec);
		edit.commit();
	}
	//被内存管理kill掉后调用
	public static void crashCall(SharedPreferences sp)
	{
		LoginUser.webUrl=sp.getString("webUrl", "");
		LoginUser.name=sp.getString("name", "");
		LoginUser.pass=sp.getString("ps", "");
		LoginUser.id=sp.getString("id", "");
		LoginUser.DefaultRec=sp.getString("DefaultRec", "");
		
	}

}
