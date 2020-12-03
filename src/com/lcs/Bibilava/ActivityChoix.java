package com.lcs.Bibilava;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityChoix extends Activity{
	RadioGroup choix;
	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.choix_xml);

		choix=(RadioGroup) findViewById(R.id.choix);

		pref = getPreferences(Context.MODE_PRIVATE);
		String temp = pref.getString("txtChecked","glisser");
		Toast.makeText(this,temp,Toast.LENGTH_SHORT).show();
		int ch = temp.equals("glisser") ? R.id.glisser : R.id.bouton;
		choix.check(ch);
	}

	@Override
	protected void onPause(){
		super.onPause();
		SharedPreferences.Editor edit = pref.edit();
		RadioButton temp = (RadioButton) findViewById(choix.getCheckedRadioButtonId());
		edit.putString("txtChecked",temp.getText().toString());
		edit.commit();
	}
}