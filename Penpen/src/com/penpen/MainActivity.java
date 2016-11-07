package com.penpen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.content.Context;
import android.os.Build;

public class MainActivity extends FragmentActivity {
	private Context mContext;
	// 定义FragmentTabHost对象
	private static FragmentTabHost mTabHost;
	private static TabWidget twFooter;
	// 定义布局
	private LayoutInflater layoutInflater;
	// 定义数组来存放Fragment界面
	private Class fragmentArray[] = { FragmentMainTabDiscover.class,
			FragmentMainTabCircle.class, FragmentMainTabMessage.class, FragmentMainTabMine.class};
	// 定义数组来存放按钮图片
	private int mImageViewArray[] = {R.drawable.tab_discover, R.drawable.tab_follow, R.drawable.tab_msg, R.drawable.tab_mine};
	// Tab选项卡底部的文字
	private int mTextviewArray[] = { R.string.tab_discover,  R.string.tab_follow, R.string.tab_msg, R.string.tab_mine};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_tab);
		mContext = getApplicationContext();
		initView();
	}
	/**
	 * 初始化数组
	 */
	private void initView() {
		twFooter = (TabWidget) findViewById(android.R.id.tabs);
		// 实例化布局
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		// 得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// 为每个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(getString(mTextviewArray[i])).setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			// mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
	}
	/**
	 * 给Tab按钮设置图标和文
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.tab_item_imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.tab_item_textview);
		textView.setText(mTextviewArray[index]);
		return view;
	}
}
