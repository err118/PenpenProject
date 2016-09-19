package com.penpen.discover;

import com.penpen.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class OrderPublishActivity extends Activity {
	public static final String TAG = "OrderPublishActivity";
	RadioGroup typeGroup, taskTypeGroup; 
	LinearLayout askLayout, taskLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_publish);
		initView();
		initClick();
	}
	private void initView(){
		typeGroup = (RadioGroup)findViewById(R.id.publish_type);
		askLayout = (LinearLayout)findViewById(R.id.publish_ask_layout);
		taskLayout = (LinearLayout) findViewById(R.id.publish_task_layout);
	}
	private void initClick(){
		typeGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Log.e(TAG, "----->" + checkedId);
				if(checkedId == R.id.publish_type_ask){
					askLayout.setVisibility(View.VISIBLE);
					taskLayout.setVisibility(View.GONE);
				}
				else if(checkedId == R.id.publish_type_task){
					askLayout.setVisibility(View.GONE);
					taskLayout.setVisibility(View.VISIBLE);
				}
			}
		});
	}
}
