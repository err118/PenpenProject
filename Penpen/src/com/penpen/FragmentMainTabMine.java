package com.penpen;

import com.penpen.mine.ProfileEditActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FragmentMainTabMine extends BaseFragment {
	private View rootView;
	private Context context;
	private RelativeLayout rvAvatar;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (rootView == null) {
			rootView = super.onCreateView(inflater, R.layout.fragment_main_tab_mine, container, false);
			context = getActivity();
			init();
			initOnClick();
		}
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) {
			parent.removeView(rootView);
		}
		return rootView;
	}
	
	private void init(){
		rvAvatar = (RelativeLayout) rootView.findViewById(R.id.mine_tab_avatar_rv);
	}
	private void initOnClick(){
		rvAvatar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				toProfileEditActivity();
			}
		});
	}
	private void toProfileEditActivity(){
		Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
		startActivity(intent);
	}
}
