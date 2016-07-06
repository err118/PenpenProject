package com.penpen;

import java.lang.reflect.Field;

import java.util.List;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;

public abstract class BaseFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, int id,
			ViewGroup container, boolean savedInstanceState) {
		View view = inflater.inflate(id, container, savedInstanceState);
		ViewUtils.inject(this, view);
		return view;
	}

	public void push(Fragment newFragment, boolean isBack) {
		push(newFragment, null, isBack);
	}

	public void push(Fragment newFragment, Fragment nowFragment, boolean isBack) {

		FragmentTransaction ft = getActivity().getSupportFragmentManager()
				.beginTransaction();

		// ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

		ft.replace(R.id.content, newFragment, "content");

		if (isBack) {
			ft.addToBackStack(null);
		} else if (nowFragment != null) {
			ft.remove(nowFragment);
		}

		ft.commit();

	}

	private static final String TAG = "BaseActivity";

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e(TAG, "Activity result no fragment exists for index: 0x"
				+ Integer.toHexString(requestCode));
		FragmentManager fm = getActivity().getSupportFragmentManager();
		int index = requestCode >> 16;
		if (index != 0) {
			index--;
			if (fm.getFragments() == null || index < 0
					|| index >= fm.getFragments().size()) {
				Log.w(TAG, "Activity result fragment  index out of range: 0x"
						+ Integer.toHexString(requestCode));
				return;
			}
			Fragment frag = fm.getFragments().get(index);
			if (frag == null) {
				Log.w(TAG, "Activity result no fragment exists for index: 0x"
						+ Integer.toHexString(requestCode));
			} else {
				handleResult(frag, requestCode, resultCode, data);
			}
			return;
		}

	}

	/**
	 * 递归调用，对所有子Fragement生效
	 * 
	 * @param frag
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	private void handleResult(Fragment frag, int requestCode, int resultCode,
			Intent data) {
		frag.onActivityResult(requestCode & 0xffff, resultCode, data);
		List<Fragment> frags = frag.getChildFragmentManager().getFragments();
		if (frags != null) {
			for (Fragment f : frags) {
				if (f != null)
					handleResult(f, requestCode, resultCode, data);
			}
		}
	}

}
