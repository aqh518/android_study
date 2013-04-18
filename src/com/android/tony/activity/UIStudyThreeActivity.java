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
	
	//�����������״ֵ̬
	int status=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_study_3);
		/**
		 * AutoCompleteView ���÷�
		 * 
		 * 2013��4��18��
		 */
		//����Դ���ҵ����õ����飬Ȼ���������飬��ֱ�ӽ���Դ��ַ���� adapter�У��򲻻������ʾЧ����
		final String[] stars=getResources().getStringArray(R.array.star);
		//�������ļ����ҵ��ö���
		AutoCompleteTextView actv=(AutoCompleteTextView) this.findViewById(R.id.auto_complete);
		//����һ������������
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stars);
		//�趨������
		actv.setAdapter(adapter);
		//�趨Ϊ ����1���ַ�����ʾ
		actv.setThreshold(1);
		
		/**
		 * Spinner ���÷�
		 * 
		 */
		
		//���ȴ������ļ����ҵ��ؼ�
		Spinner spinner=(Spinner) this.findViewById(R.id.spinner);
		
		//����Adapter
		BaseAdapter base_adapter=new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				//����һ��ˮƽ��������Բ��֣������ͼƬ�ͺ���
				LinearLayout linear=new LinearLayout(UIStudyThreeActivity.this);
				linear.setOrientation(LinearLayout.HORIZONTAL);
				
				//��̬����ImageView����
				ImageView image=new ImageView(UIStudyThreeActivity.this);
				image.setImageResource(stars_photos[position]);
				image.setLayoutParams(new LayoutParams(30, 30));
				linear.addView(image);
				
				//��̬����TextView
				TextView tv=new TextView(UIStudyThreeActivity.this);
				tv.setText(stars[position]);
				tv.setTextColor(getResources().getColor(R.color.black));
				tv.setTextSize(26);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);//���־���
				
				//����magin
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
		
		//��xml���ҵ�progressBar����
		final ProgressBar progress_bar=(ProgressBar) this.findViewById(R.id.progress_bar);
		//�ص���ʱ�䳤�������¼���ҪHandler������
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
