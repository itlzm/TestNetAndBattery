package com.example.testnet2;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	// 显示网络连接情况
	private TextView textView;
	// 显示当前电量情况
	private TextView textView1;
	// 实时监测网络连接工具类,该类继承BroadcastReceiver,将网络的实时情况显示到textView中。
	private NetReceiver netReceiver;
	private BatteryReceiver batteryReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textView);
		textView1 = (TextView) findViewById(R.id.textView1);

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		netReceiver = new NetReceiver(textView, getApplicationContext());
		registerReceiver(netReceiver, intentFilter);
		
		intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
		batteryReceiver = new BatteryReceiver(textView1, getApplicationContext());
		registerReceiver(batteryReceiver, intentFilter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//程序退出，销毁资源
	@Override
	protected void onDestroy() {
		if (netReceiver != null) {
			unregisterReceiver(netReceiver);
		}
		if (batteryReceiver != null) {
			unregisterReceiver(batteryReceiver);
		}
		super.onDestroy();
	}

}
