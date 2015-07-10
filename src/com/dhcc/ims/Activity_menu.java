package com.dhcc.ims;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Activity_menu extends PreferenceActivity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.mobiledemo);
		
	}
}
