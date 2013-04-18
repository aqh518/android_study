package com.android.tony.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UIStudyOneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_study_1);
		
		final Button button=(Button) this.findViewById(R.id.account);
		OnClickListener listener=new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v == button){
						Intent intent=new Intent();
						intent.setClass(UIStudyOneActivity.this, UIStudyTwoActivity.class);
						UIStudyOneActivity.this.startActivity(intent);
				}
			}
		};
		
		button.setOnClickListener(listener);
	}
	
}
