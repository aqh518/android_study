package com.android.tony.activity;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      final EditText text=  (EditText) this.findViewById(R.id.telphone);//��ȡ�绰�������
      final EditText sms=  (EditText) this.findViewById(R.id.sms);//��ȡ�������ݶ���
      Button button=(Button) this.findViewById(R.id.button);//���� ��ť
      Button send_sms=(Button) this.findViewById(R.id.send_sms);//����Ϣ��ť
      
      final Button goto_layout=(Button) this.findViewById(R.id.to_table);
      final Button to_ui_1=(Button) this.findViewById(R.id.to_ui_1);
      OnClickListener listener=new OnClickListener() {
  		@Override
  		public void onClick(View v) {
  			Intent intent=new Intent();
  			if(v==goto_layout){
  				intent.setClass(TelActivity.this, LayoutActivity.class);
  			}else if(v == to_ui_1){
  				intent.setClass(TelActivity.this,UIStudyOneActivity.class);
  			}
  			TelActivity.this.startActivity(intent);
  		}
  	};
  	
      goto_layout.setOnClickListener(listener);
      to_ui_1.setOnClickListener(listener);
      
      button.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String phoneNumber = text.getText().toString();
			//����һ intent�Ķ�������������.
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+phoneNumber));
			
			//�½�һ��intent���󣬽��е���ϵͳ�Ĵ�绰�ķ�����Ȼ�󴫵ݺ����ȥ   
           // Intent intent = new Intent(Intent.ACTION_CALL , Uri.parse("tel:" +  phoneNumber));   
			TelActivity.this.startActivity(intent);
		}

	});
        
      
      send_sms.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String phoneNumber = text.getText().toString();
			String sms_content=sms.getText().toString();
			
			//������ ��Ҫһ�� smsManager .���ڻ�����̫���� ֻ��֪��Ӧ����ôд��
			SmsManager smsManager = SmsManager.getDefault();  
			//�ж��Ƿ񳬳������ˣ�������������� �ָ���š�
            List<String> msgsList = smsManager.divideMessage(sms_content);
            
            //��������.
            for (String msg : msgsList) {  
                smsManager.sendTextMessage(phoneNumber, null, msg, null, null);  
            }  
            //���ͳɹ������ʾ��Ϣ.
            Toast.makeText(TelActivity.this, "���ŷ��ͳɹ�", Toast.LENGTH_LONG).show();  
		}
	});
      
      
      
    }
}