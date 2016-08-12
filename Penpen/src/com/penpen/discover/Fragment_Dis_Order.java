package com.penpen.discover;

import java.lang.annotation.Annotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lidroid.xutils.db.sqlite.FinderLazyLoader;
import com.penpen.BaseFragment;
import com.penpen.R;
import com.penpen.utils.Const;
import com.penpen.viewUtils.listView.XListView;
import com.penpen.viewUtils.listView.XListView.IXListViewListener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Fragment_Dis_Order extends BaseFragment implements IXListViewListener {
	public static final String TAG = "Fragment_Dis_Order";
	private Context context;
	private View rootView;
	private XListView mListView;
	private BaseAdapter mAdapter;
	private ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (rootView == null) {
			rootView = super.onCreateView(inflater, R.layout.fragment_discover_order, container, false);
			context = getActivity();
			geneDatas();
			initView();
			initOnClick();
		}
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) {
			parent.removeView(rootView);
		}
		return rootView;
	}

	private void initView() {
		mHandler = new Handler();
		mListView = (XListView) rootView.findViewById(R.id.discover_order_list);
		mListView.setPullLoadEnable(true);
		mAdapter = new OrderListAdapter();
		mListView.setAdapter(mAdapter);
	}

	private void initOnClick() {
		mListView.setXListViewListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				toOrderDetailActivity(position);
			}
		});
	}

	// 跳转到订单详情页面
	private void toOrderDetailActivity(int position) {
		Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
		intent.putExtra("id", "" + position);
		startActivity(intent);
	}

	private void geneDatas() {
		for (int i = 0; i != 20; ++i) {
			Map<String , Object> map = new HashMap<String, Object>();
			map.put(Const.ORDER_SUM, "$" + (++start));
			map.put(Const.ORDER_TITLE, "Android仿小米动态曲线");
			map.put(Const.ORDER_TYPE, "线上");
			items.add(map);
		}
	}

	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneDatas();
				// mAdapter.notifyDataSetChanged();
				mAdapter = new OrderListAdapter();
				mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneDatas();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	public final class ViewHolder {
		public TextView sumTv;
		public TextView typeTv;
		public TextView titleTv;
	}
	class OrderListAdapter extends BaseAdapter{
		ViewHolder viewHolder = null;
		LayoutInflater mInflater;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			try {
				if (convertView == null) {
					viewHolder = new ViewHolder();
					convertView = LayoutInflater.from(context).inflate(R.layout.order_list_item, null);
					viewHolder.sumTv = (TextView) convertView.findViewById(R.id.order_item_sum);
					viewHolder.titleTv = (TextView) convertView.findViewById(R.id.order_item_title);
					viewHolder.typeTv = (TextView) convertView.findViewById(R.id.order_item_type);
					convertView.setTag(viewHolder);
				} else {
					viewHolder = (ViewHolder) convertView.getTag();
				}
				viewHolder.sumTv.setText(items.get(position).get(Const.ORDER_SUM).toString());
				viewHolder.titleTv.setText(items.get(position).get(Const.ORDER_TITLE).toString());
				viewHolder.typeTv.setText(items.get(position).get(Const.ORDER_TYPE).toString());
			} catch (Exception e) {
				// TODO: handle exception
				Log.e(TAG, e.toString());
			}
			return convertView;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.size();
		}
	};
}
