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
        
      final EditText text=  (EditText) this.findViewById(R.id.telphone);//获取电话号码对象
      final EditText sms=  (EditText) this.findViewById(R.id.sms);//获取短信内容对象
      Button button=(Button) this.findViewById(R.id.button);//拨号 按钮
      Button send_sms=(Button) this.findViewById(R.id.send_sms);//发信息按钮
      
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
			//方法一 intent的对象参数逐个设置.
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+phoneNumber));
			
			//新建一个intent对象，进行调用系统的打电话的方法，然后传递号码过去   
           // Intent intent = new Intent(Intent.ACTION_CALL , Uri.parse("tel:" +  phoneNumber));   
			TelActivity.this.startActivity(intent);
		}

	});
        
      
      send_sms.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String phoneNumber = text.getText().toString();
			String sms_content=sms.getText().toString();
			
			//发短信 需要一个 smsManager .现在还不是太懂， 只是知道应该这么写。
			SmsManager smsManager = SmsManager.getDefault();  
			//判断是否超出字数了，如果超出字数， 分割短信。
            List<String> msgsList = smsManager.divideMessage(sms_content);
            
            //逐条发送.
            for (String msg : msgsList) {  
                smsManager.sendTextMessage(phoneNumber, null, msg, null, null);  
            }  
            //发送成功后的提示信息.
            Toast.makeText(TelActivity.this, "短信发送成功", Toast.LENGTH_LONG).show();  
		}
	});
      
      
      
    }
}