package com.penpen.discover;

import com.penpen.R;
import com.penpen.utils.Const;
import com.penpen.viewUtils.ImageLoader.AsyncImageLoader;
import com.penpen.viewUtils.indicator.IndicatorViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

public class PeopleUserinfoActivity extends Activity {
	String imageUrl = "";
	ImageView avatar;
	private AsyncImageLoader imageLoader;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_userinfo);
		context = PeopleUserinfoActivity.this;
		initData();
		initView();
	}

	private void initData() {
		imageLoader = new AsyncImageLoader(context);
		imageUrl = getIntent().getStringExtra(Const.PEO_IMAGEURL);
	}

	private void initView() {
		avatar = (ImageView) findViewById(R.id.peopleUserinfo_avatar);
		if (!TextUtils.isEmpty(imageUrl)) {
			Bitmap bitmap = imageLoader.loadImage(avatar, imageUrl);
			if (bitmap != null) {
				avatar.setImageBitmap(bitmap);
			}
		}
	}
}
