package com.penpen;

import com.penpen.discover.Fragment_Nearby;
import com.penpen.viewUtils.indicator.Indicator;
import com.penpen.viewUtils.indicator.IndicatorViewPager;
import com.penpen.viewUtils.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.penpen.viewUtils.indicator.slidebar.ColorBar;
import com.penpen.viewUtils.indicator.transition.OnTransitionTextListener;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentMainTabDiscover extends BaseFragment {
	private Context context;
	private View rootView;
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	private ViewPager viewPager;
	private Indicator indicator;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(rootView == null){
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
	private void init(){
		viewPager = (ViewPager) rootView.findViewById(R.id.fragment_tabmain_viewPager);
		indicator = (Indicator) rootView.findViewById(R.id.fragment_tabmain_indicator);
		indicator.setScrollBar(new ColorBar(context, Color.RED, 5));
		float unSelectSize = 16;
		float selectSize = unSelectSize * 1.2f;

		int selectColor = Color.BLUE;
		int unSelectColor = Color.GRAY;
		indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));
		viewPager.setOffscreenPageLimit(4);
		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getContext());
		indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
	}
	private class MyAdapter extends IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_second_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText("Dis");
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			Fragment_Nearby mainFragment = new Fragment_Nearby();
//			Bundle bundle = new Bundle();
//			bundle.putString(SecondLayerFragment.INTENT_STRING_TABNAME, tabName);
//			bundle.putInt(SecondLayerFragment.INTENT_INT_POSITION, position);
//			mainFragment.setArguments(bundle);
			return mainFragment;
		}
	}
}
