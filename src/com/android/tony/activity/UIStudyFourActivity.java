package com.android.tony.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class UIStudyFourActivity extends TabActivity {
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

	List<String> group;
	List<List<String>> child;
	ContactsInfoAdapter cia=new ContactsInfoAdapter();  //数据适配器 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//		setContentView(R.layout.ui_study_4);
		//从资源中找到配置的数组，然后生成数组，若直接将资源地址传入 adapter中，则不会出现提示效果。
		final String[] stars=getResources().getStringArray(R.array.star);
		TabHost tab_host=getTabHost();
		//设置TabHost 的布局
		LayoutInflater.from(this).inflate(R.layout.ui_study_4, tab_host.getTabContentView(), true);

		//假如选项卡内容
		tab_host.addTab(tab_host.newTabSpec("tab1").setIndicator("美女", getResources().getDrawable(R.drawable.seek_thumb)).setContent(R.id.tab_demo_content_1));
		tab_host.addTab(tab_host.newTabSpec("tab1").setIndicator("星座", getResources().getDrawable(R.drawable.seek_thumb)).setContent(R.id.tab_demo_content_2));
		tab_host.addTab(tab_host.newTabSpec("tab1").setIndicator("通讯录", getResources().getDrawable(R.drawable.seek_thumb)).setContent(R.id.tab_demo_content_3));

		tab_host.setBackgroundColor(Color.argb(150, 22, 70, 150));
		ListView list_view=(ListView) this.findViewById(R.id.list_view_simple_adapter);

		//定义Adapter
		BaseAdapter base_adapter=new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				//创建一个水平方向的线性布局，里面放图片和汉字
				LinearLayout linear=new LinearLayout(UIStudyFourActivity.this);
				linear.setOrientation(LinearLayout.HORIZONTAL);

				//动态创建ImageView对象
				ImageView image=new ImageView(UIStudyFourActivity.this);
				image.setImageResource(stars_photos[position]);
				image.setLayoutParams(new LayoutParams(30, 30));
				image.setPadding(5, 0, 5, 0);
				linear.addView(image);

				//动态创建TextView
				TextView tv=new TextView(UIStudyFourActivity.this);
				//不知道为啥不管用
				//String html_text="<span style=\"font-family:'楷体'\">"+stars[position]+"</span>";

				/* * 必须事先在assets底下创建一fonts文件夹 并放入要使用的字体文件(.ttf) 
				 	* 并提供相对路径给creatFromAsset()来创建Typeface对象                     */       
				//Typeface fontFace = Typeface.createFromAsset(getAssets(),"font/简启体.TTF"); 
				//tv.setTypeface(fontFace);
				tv.setText(stars[position]);
				tv.setTextColor(getResources().getColor(R.color.blue));
				tv.setTextSize(25);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);//文字居中
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

		list_view.setAdapter(base_adapter);


		/**
		 * ExpandableListView
		 */

		initData();

		ExpandableListView exand_listview=(ExpandableListView) this.findViewById(R.id.expand_listview);
		exand_listview.setAdapter(cia);
	}

	private void initData() {
		group=new ArrayList<String>();
		child=new ArrayList<List<String>>();

		addInfo("大学同学",new String[]{"唐岩","许立","孟令生","王平","王长征","刘玉华","李展","李正","李鹏程"});
		addInfo("高中同学",new String[]{"华飞","高志强","路艳科","常晓楠","乔先锋","王明娟","陆明","高晶","苏蕾"});
		addInfo("家里人",new String[]{"爸爸","妈妈","哥哥","嫂嫂","女朋友","爷爷","奶奶","弟弟","妹妹"});
	}

	private void addInfo(String g, String[] children) {
		group.add(g);
		List<String> temp=new ArrayList<String>();
		for(String c:children){
			temp.add(c);
		}
		child.add(temp);
	}


	//ExpandableListView 特有的adapter.
	class ContactsInfoAdapter extends BaseExpandableListAdapter{

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return child.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			String text=child.get(groupPosition).get(childPosition);
			return getGenericView(text);  
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return child.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return group.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return group.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String string = group.get(groupPosition);
			return getGenericView(string);   
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}


		//创建组/子视图    
		public TextView getGenericView(String s) {
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(    
					ViewGroup.LayoutParams.FILL_PARENT, 40);  

			//LinearLayout ll=(LinearLayout) UIStudyFourActivity.this.findViewById(R.id.tab_demo_content_3);
			TextView text_view = new TextView(UIStudyFourActivity.this);    
			text_view.setLayoutParams(lp);    
			// Center the text vertically    
			text_view.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);    
			// Set the text starting position    
			text_view.setPadding(36, 0, 0, 0);    
			text_view.setText(s);    
			text_view.setTextColor(getResources().getColor(R.color.blue));
			//	ll.addView(text_view);

			return text_view;   
		}    


	}
}
