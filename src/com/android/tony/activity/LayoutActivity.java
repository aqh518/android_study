package com.android.tony.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LayoutActivity extends Activity {
	
	int current_color=0;
	int[] colors=new int[]{
			R.color.color7,
			R.color.color6,
			R.color.color5,
			R.color.color4,
			R.color.color3,
			R.color.color2,
			R.color.color1
	};
	
	int[] names=new int[]{
		R.id.tv1,	
		R.id.tv2,	
		R.id.tv3,	
		R.id.tv4,	
		R.id.tv5,	
		R.id.tv6,	
		R.id.tv7
	};
	TextView[] views=new TextView[7];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_layout);
		
		Button back=(Button) this.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(LayoutActivity.this, TelActivity.class);
				LayoutActivity.this.startActivity(intent);
			}
		});
		
		
		//Framelayout 做成霓虹灯效果
		for(int i=0,length=views.length;i<length;i++){
			views[i]=(TextView) this.findViewById(names[i]);
		}
		
		final Handler handle=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what==0){
					for(int i=0,length=views.length;i<length;i++){
						views[i].setBackgroundResource(colors[(i+current_color)%7]);
					}
				}
			}
		};
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				current_color++;
				Message msg=new Message();
				msg.what=0;
				handle.sendMessage(msg);
			}
		}, 0, 100);
		
		
	}

}
