package com.android.tony.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class UIStudyTwoActivity extends Activity {
	int[] images=new int[]{
			R.drawable.mm1,	
			R.drawable.mm2,	
			R.drawable.mm3,	
			R.drawable.mm4,	
			R.drawable.mm5,	
			R.drawable.mm6	
	};

	//����Ĭ����ʾ��ͼƬ
	int current_img=1;
	//����͸���� ,Ĭ��ֵΪ255
	int alpha=255;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_study_2);

		final Button plus=(Button) this.findViewById(R.id.plus);
		final Button minus=(Button) this.findViewById(R.id.minus);
		Button next=(Button) this.findViewById(R.id.next);
		final ImageView image1=(ImageView) this.findViewById(R.id.image1);
		final ImageView image2=(ImageView) this.findViewById(R.id.image2);
		
		Button nextPage=(Button) this.findViewById(R.id.next_page);
		nextPage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(UIStudyTwoActivity.this, UIStudyThreeActivity.class);
				UIStudyTwoActivity.this.startActivity(intent);
			}
		});
		
		OnClickListener listener=new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(v == plus){
					alpha+=20;

				}else if(v == minus){
					alpha-=20;
				}
				alpha=alpha>255?255:alpha;
				alpha=alpha<0?0:alpha;
				
				//�趨ͼƬ��͸����
				image1.setAlpha(alpha);
			}
		};


		//����͸����
		plus.setOnClickListener(listener);
		//��С͸����
		minus.setOnClickListener(listener);
		
		//�鿴��һ��ͼƬ
		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					if(current_img+1>=images.length){
						current_img=-1;
					}
				
					BitmapDrawable drawable=(BitmapDrawable) image1.getDrawable();
					//�����ͼƬû�б����գ��Ȼ���.
					if(!drawable.getBitmap().isRecycled())drawable.getBitmap().recycle();
					//�趨��ͼƬ
					image1.setImageResource(images[++current_img]);
			}
		});
		//Ϊ��һ��ͼƬ�ṩ�Ŵ�Ч����
		image1.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				BitmapDrawable bitmapDrawable=(BitmapDrawable) image1.getDrawable();
				//��ȡ��һ����ʾ���е�λͼ
				Bitmap bitmap=bitmapDrawable.getBitmap();
				
				//bitmap ���ű���
				double scale=bitmap.getWidth()/320.0;
				
				//��ȡ��Ҫ��ʾ��Ŀ�ʼ��
				int x=(int) (event.getX()*scale);
				int y=(int) (event.getY()*scale);
				
				if(x+120>bitmap.getWidth()){
					x=bitmap.getWidth()-120;
				}
				if(y+120>bitmap.getHeight()){
					y=bitmap.getHeight()-120;
				}
				
				image2.setImageBitmap(Bitmap.createBitmap(bitmap,x,y,120,120));
				image2.setAlpha(alpha);
				return false;
			}
		});
		
	}

}
