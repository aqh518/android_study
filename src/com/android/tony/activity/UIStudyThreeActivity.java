package com.android.tony.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class UIStudyThreeActivity extends Activity {

	int[] stars_photos=new int[]{
		R.drawable.baiyang,	
		R.drawable.jinniu,
		R.drawable.shuangzi,
		R.drawable.juxie,
		R.drawable.shizhi,
		R.drawable.chunv,
		R.drawable.tiancheng,
		R.drawable.tianxie,
		R.drawable.sheshou,
		R.drawable.mojie,
		R.drawable.shuiping,
		R.drawable.shuangyu 
	};
	
	//定义进度条的状态值
	int status=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_study_3);
		/**
		 * AutoCompleteView 的用法
		 * 
		 * 2013年4月18日
		 */
		//从资源中找到配置的数组，然后生成数组，若直接将资源地址传入 adapter中，则不会出现提示效果。
		final String[] stars=getResources().getStringArray(R.array.star);
		//从配置文件中找到该对象
		AutoCompleteTextView actv=(AutoCompleteTextView) this.findViewById(R.id.auto_complete);
		//创建一个适配器对象
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stars);
		//设定适配器
		actv.setAdapter(adapter);
		//设定为 输入1个字符就提示
		actv.setThreshold(1);
		
		/**
		 * Spinner 的用法
		 * 
		 */
		
		//首先从配置文件中找到控件
		Spinner spinner=(Spinner) this.findViewById(R.id.spinner);
		
		//定义Adapter
		BaseAdapter base_adapter=new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				//创建一个水平方向的线性布局，里面放图片和汉字
				LinearLayout linear=new LinearLayout(UIStudyThreeActivity.this);
				linear.setOrientation(LinearLayout.HORIZONTAL);
				
				//动态创建ImageView对象
				ImageView image=new ImageView(UIStudyThreeActivity.this);
				image.setImageResource(stars_photos[position]);
				image.setLayoutParams(new LayoutParams(30, 30));
				linear.addView(image);
				
				//动态创建TextView
				TextView tv=new TextView(UIStudyThreeActivity.this);
				tv.setText(stars[position]);
				tv.setTextColor(getResources().getColor(R.color.black));
				tv.setTextSize(26);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);//文字居中
				
				//设置magin
				MarginLayoutParams  mlp=new MarginLayoutParams(60, 30);
				mlp.leftMargin=20;
				tv.setLayoutParams(mlp);
				linear.addView(tv);
				
				return linear;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getItem(int position) {
				return null;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return stars_photos.length;
			}
		};
		
		spinner.setAdapter(base_adapter);
		
		/**
		 * progressbar
		 */
		
		//从xml中找到progressBar对象
		final ProgressBar progress_bar=(ProgressBar) this.findViewById(R.id.progress_bar);
		//回调，时间长的运行事件需要Handler来处理
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what==0){
					progress_bar.setProgress(status);
				}
			}
		};
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(status<100){
					try {
						Thread.sleep(1000);
						status++;
						Message msg=new Message();
						msg.what=0;
						handler.sendMessage(msg);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

}
