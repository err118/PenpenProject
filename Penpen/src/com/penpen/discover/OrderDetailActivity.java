package com.penpen.discover;


import com.penpen.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends Activity {
	private TextView idTv;
	private String orderId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		getIntentData();
		initView();
	}
	private void getIntentData(){
//		orderId = getIntent().getStringExtra("id");
	}
	private void initView(){
//		idTv = (TextView) findViewById(R.id.orderDetailId);
//		idTv.setText(orderId);
	}
}
