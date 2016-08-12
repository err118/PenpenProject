package com.penpen.discover;

import com.penpen.BaseFragment;
import com.penpen.R;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Dis_Nearby extends BaseFragment {
	private Context context;
	private View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(rootView == null){
			rootView = super.onCreateView(inflater, R.layout.fragment_discover_nearby, container, false);
			context = getActivity();
		}
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) {
			parent.removeView(rootView);
		}
		return rootView;
	}
}
