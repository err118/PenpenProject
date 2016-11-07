package com.penpen;

import java.lang.annotation.Annotation;

import java.nio.channels.SelectableChannel;

import com.lidroid.xutils.view.annotation.event.OnItemSelected;
import com.penpen.R.string;
import com.penpen.discover.Fragment_Dis_Order;
import com.penpen.discover.Fragment_Dis_People;
import com.penpen.discover.OrderPublishActivity;
import com.penpen.viewUtils.indicator.Indicator;
import com.penpen.viewUtils.indicator.Indicator.OnTransitionListener;
import com.penpen.viewUtils.indicator.IndicatorViewPager;
import com.penpen.viewUtils.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.penpen.viewUtils.indicator.slidebar.ColorBar;
import com.penpen.viewUtils.indicator.transition.OnTransitionTextListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentMainTabDiscover extends BaseFragment {
	public static final String TAG = "FragmentMainTabDiscover";
	private Context context;
	private View rootView;
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	private ViewPager viewPager;
	private Indicator indicator;
	private int textviewArray[] = { R.string.tab_order, R.string.tab_people };
	private int btnArray[] = { R.string.order_publish, R.string.people_filter };
	// 定义数组来存放Fragment界面
	private Fragment fragmentArray[] = { new Fragment_Dis_Order(), new Fragment_Dis_People() };
	private Button btnSelect;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (rootView == null) {
			rootView = super.onCreateView(inflater, R.layout.fragment_main_tab_discover, container, false);
			context = getActivity();
			init();
		}
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) {
			parent.removeView(rootView);
		}
		return rootView;
	}

	private void init() {
		btnSelect = (Button) rootView.findViewById(R.id.select);
		btnSelect.setText(btnArray[0]);
		viewPager = (ViewPager) rootView.findViewById(R.id.fragment_tabmain_viewPager);
		indicator = (Indicator) rootView.findViewById(R.id.fragment_tabmain_indicator);
		indicator.setScrollBar(new ColorBar(context, 0xFF3462FF, 5));
		float unSelectSize = 16;
		float selectSize = unSelectSize * 1.2f;
		int selectColor = 0xFF1E1E1E;
		int unSelectColor = Color.GRAY;
		indicator.setOnTransitionListener(
				new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));
		viewPager.setOffscreenPageLimit(2);
		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getContext());
		indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
		indicator.getPreSelectItem();
		indicatorViewPager.setOnIndicatorPageChangeListener(new IndicatorViewPager.OnIndicatorPageChangeListener() {

			@Override
			public void onIndicatorPageChange(int preItem, int currentItem) {
				// TODO Auto-generated method stub
				Log.e("   ", "-------->" + currentItem);
				if (currentItem == 0) {
					btnSelect.setText(getString(btnArray[currentItem]));
				} else if (currentItem == 1) {
					btnSelect.setText(getString(btnArray[currentItem]));
				}
			}
		});
		btnSelect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selectItem = indicatorViewPager.getCurrentItem();
				Log.e(TAG, "------->" + selectItem);
				if (selectItem == 0) {
					toOrderPublishActivity();
				} else if(selectItem == 1) {
					toOrderPublishActivity();
				}
			}
		});
	}

	private void toOrderPublishActivity() {
		Intent intent = new Intent(getActivity(), OrderPublishActivity.class);
		startActivity(intent);
	}

	private class MyAdapter extends IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_second_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(textviewArray[position]);
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			Fragment mainFragment = fragmentArray[position];
			// Bundle bundle = new Bundle();
			// bundle.putString(SecondLayerFragment.INTENT_STRING_TABNAME,
			// tabName);
			// bundle.putInt(SecondLayerFragment.INTENT_INT_POSITION, position);
			// mainFragment.setArguments(bundle);
			return mainFragment;
		}
	}
}
