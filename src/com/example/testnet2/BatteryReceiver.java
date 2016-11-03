package com.example.testnet2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class BatteryReceiver extends BroadcastReceiver {

	private TextView textView1;
	private Context context;
	
	public BatteryReceiver(TextView textView,Context context) {
		super();
		this.textView1 = textView;
		this.context = context;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {

		//判断它是否是为电量变化的Broadcast Action
		if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
			//获取当前电量
			int level = intent.getIntExtra("level", 0);
			//电量的总刻度
			int scale = intent.getIntExtra("scale", 100);
			int percent = level * 100 / scale;
			//把它转成百分比
			if(percent<100){
				textView1.setText("电池电量为"+percent+"%,请充电");
			}
		}
		
	}

}
