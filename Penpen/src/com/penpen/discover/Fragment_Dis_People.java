package com.penpen.discover;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import com.penpen.BaseFragment;
import com.penpen.R;
import com.penpen.discover.Fragment_Dis_Order.OrderListAdapter;
import com.penpen.discover.Fragment_Dis_Order.ViewHolder;
import com.penpen.utils.Const;
import com.penpen.viewUtils.ImageLoader.AsyncImageLoader;
import com.penpen.viewUtils.listView.XListView;
import com.penpen.viewUtils.listView.XListView.IXListViewListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_Dis_People extends BaseFragment implements IXListViewListener {
	public static final String TAG = "Fragment_Dis_People";
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
			rootView = super.onCreateView(inflater, R.layout.fragment_discover_people, container, false);
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
	
	private void geneDatas() {
		for (int i = 0; i != 20; ++i) {
			Map<String , Object> map = new HashMap<String, Object>();
			map.put(Const.PEO_IMAGEURL, "http://gbres.dfcfw.com/Files/picture/20160416/size500/843015D1E6D7E56712D5895EBB3CA146.jpg");
			map.put(Const.PEO_NICKNAME, "哈哈");
			map.put(Const.PEO_SEX, "女");
			map.put(Const.PEO_RANGE, + (++start) + "km");
			map.put(Const.PEO_UWRITE, "白日依山尽，黄河入海流");
			items.add(map);
		}
	}
	
	private void initView() {
		mHandler = new Handler();
		mListView = (XListView) rootView.findViewById(R.id.discover_people_list);
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
//				toOrderDetailActivity(position);
			}
		});
		
	}
//	附近的人适配器
	public final class ViewHolder {
		public ImageView avaIv;
		public TextView nicTv;
		public TextView sexTv;
		public TextView rangeTv;
		public TextView underTv;
	}
	class OrderListAdapter extends BaseAdapter{
		ViewHolder viewHolder = null;
		LayoutInflater mInflater;
		private AsyncImageLoader imageLoader = new AsyncImageLoader(context);
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			try {
				if (convertView == null) {
					viewHolder = new ViewHolder();
					convertView = LayoutInflater.from(context).inflate(R.layout.item_people_list, null);
					viewHolder.avaIv = (ImageView) convertView.findViewById(R.id.item_peo_avatar);
					viewHolder.nicTv = (TextView) convertView.findViewById(R.id.item_peo_nickname);
					viewHolder.sexTv = (TextView) convertView.findViewById(R.id.item_peo_sex);
					viewHolder.rangeTv = (TextView) convertView.findViewById(R.id.item_peo_range);
					viewHolder.underTv = (TextView) convertView.findViewById(R.id.item_peo_underwrite);
					convertView.setTag(viewHolder);
				} else {
					viewHolder = (ViewHolder) convertView.getTag();
				}
				viewHolder.nicTv.setText(items.get(position).get(Const.PEO_NICKNAME).toString());
				viewHolder.sexTv.setText(items.get(position).get(Const.PEO_SEX).toString());
				viewHolder.rangeTv.setText(items.get(position).get(Const.PEO_RANGE).toString());
				viewHolder.underTv.setText(items.get(position).get(Const.PEO_UWRITE).toString());
				final String imgUrl = items.get(position).get(Const.PEO_IMAGEURL).toString();
				// 给 ImageView 设置一个 tag
				viewHolder.avaIv.setTag(imgUrl);
				// 预设一个图片
				viewHolder.avaIv.setImageResource(R.drawable.defaultuser);

				if (!TextUtils.isEmpty(imgUrl)) {
					Bitmap bitmap = imageLoader.loadImage(viewHolder.avaIv, imgUrl);
					if (bitmap != null) {
						viewHolder.avaIv.setImageBitmap(bitmap);
					}
				}
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
			return position;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.size();
		}
	};
//	上下拉刷新
	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneDatas();
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

}
