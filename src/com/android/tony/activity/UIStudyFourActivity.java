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
	ContactsInfoAdapter cia=new ContactsInfoAdapter();  //���������� 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//		setContentView(R.layout.ui_study_4);
		//����Դ���ҵ����õ����飬Ȼ���������飬��ֱ�ӽ���Դ��ַ���� adapter�У��򲻻������ʾЧ����
		final String[] stars=getResources().getStringArray(R.array.star);
		TabHost tab_host=getTabHost();
		//����TabHost �Ĳ���
		LayoutInflater.from(this).inflate(R.layout.ui_study_4, tab_host.getTabContentView(), true);

		//����ѡ�����
		tab_host.addTab(tab_host.newTabSpec("tab1").setIndicator("��Ů", getResources().getDrawable(R.drawable.seek_thumb)).setContent(R.id.tab_demo_content_1));
		tab_host.addTab(tab_host.newTabSpec("tab1").setIndicator("����", getResources().getDrawable(R.drawable.seek_thumb)).setContent(R.id.tab_demo_content_2));
		tab_host.addTab(tab_host.newTabSpec("tab1").setIndicator("ͨѶ¼", getResources().getDrawable(R.drawable.seek_thumb)).setContent(R.id.tab_demo_content_3));

		tab_host.setBackgroundColor(Color.argb(150, 22, 70, 150));
		ListView list_view=(ListView) this.findViewById(R.id.list_view_simple_adapter);

		//����Adapter
		BaseAdapter base_adapter=new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				//����һ��ˮƽ��������Բ��֣������ͼƬ�ͺ���
				LinearLayout linear=new LinearLayout(UIStudyFourActivity.this);
				linear.setOrientation(LinearLayout.HORIZONTAL);

				//��̬����ImageView����
				ImageView image=new ImageView(UIStudyFourActivity.this);
				image.setImageResource(stars_photos[position]);
				image.setLayoutParams(new LayoutParams(30, 30));
				image.setPadding(5, 0, 5, 0);
				linear.addView(image);

				//��̬����TextView
				TextView tv=new TextView(UIStudyFourActivity.this);
				//��֪��Ϊɶ������
				//String html_text="<span style=\"font-family:'����'\">"+stars[position]+"</span>";

				/* * ����������assets���´���һfonts�ļ��� ������Ҫʹ�õ������ļ�(.ttf) 
				 	* ���ṩ���·����creatFromAsset()������Typeface����                     */       
				//Typeface fontFace = Typeface.createFromAsset(getAssets(),"font/������.TTF"); 
				//tv.setTypeface(fontFace);
				tv.setText(stars[position]);
				tv.setTextColor(getResources().getColor(R.color.blue));
				tv.setTextSize(25);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);//���־���
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

		addInfo("��ѧͬѧ",new String[]{"����","����","������","��ƽ","������","����","��չ","����","������"});
		addInfo("����ͬѧ",new String[]{"����","��־ǿ","·�޿�","�����","���ȷ�","������","½��","�߾�","����"});
		addInfo("������",new String[]{"�ְ�","����","���","ɩɩ","Ů����","үү","����","�ܵ�","����"});
	}

	private void addInfo(String g, String[] children) {
		group.add(g);
		List<String> temp=new ArrayList<String>();
		for(String c:children){
			temp.add(c);
		}
		child.add(temp);
	}


	//ExpandableListView ���е�adapter.
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


		//������/����ͼ    
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
