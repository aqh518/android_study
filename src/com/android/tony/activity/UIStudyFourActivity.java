package com.android.tony.activity;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class UIStudyFourActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.ui_study_4);
		
		TabHost tab_host=getTabHost();
		//设置TabHost 的布局
		LayoutInflater.from(this).inflate(R.layout.ui_study_4, tab_host.getTabContentView(), true);
		
		//假如选项卡内容
		tab_host.addTab(tab_host.newTabSpec("tab1").setIndicator("美女", null).setContent(R.id.tab_demo_content_1));
		tab_host.addTab(tab_host.newTabSpec("tab1").setIndicator("星座", null).setContent(R.id.tab_demo_content_2));
		tab_host.addTab(tab_host.newTabSpec("tab1").setIndicator("选项三", null).setContent(R.id.tab_demo_content_3));
		
		
		
		
		
	}

}
