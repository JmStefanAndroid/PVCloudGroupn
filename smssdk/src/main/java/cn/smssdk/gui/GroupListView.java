/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2014年 mob.com. All rights reserved.
 */
package cn.smssdk.gui;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
/**自定义控件-城市列表*/
public class GroupListView extends RelativeLayout {
	private ListView lvBody;
	private InnerAdapter innerAdapter;
	private GroupAdapter adapter;
	private View tvTitle;
	private int curFirstItem;
	private int titleHeight;
	private OnScrollListener osListener;
	private OnItemClickListener oicListener;

	public GroupListView(Context context) {
		super(context);
		init(context);
	}

	public GroupListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public GroupListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		lvBody = new ListView(context);
		lvBody.setCacheColorHint(0);
		lvBody.setSelector(new ColorDrawable());
		lvBody.setVerticalScrollBarEnabled(false);
		lvBody.setOnScrollListener(new OnScrollListener() {

			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (osListener != null) {
					osListener.onScrollStateChanged(view, scrollState);
				}
			}

			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				curFirstItem = firstVisibleItem;
				if (tvTitle != null) {
					GroupListView.this.onScroll();
				}

				if (osListener != null) {
					osListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
				}
			}
		});
		// 设置国家列表的点击事件
		lvBody.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				if (oicListener != null) {
					int group = innerAdapter.getItemGroup(position);
					int item = position - innerAdapter.titleIndex.get(group) - 1;
					oicListener.onItemClick(GroupListView.this, view, group, item);
				}
			}
		});
		lvBody.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		addView(lvBody);
	}

	public void setDividerHeight(int height) {
		lvBody.setDividerHeight(height);
	}

	public void setDivider(Drawable divider) {
		lvBody.setDivider(divider);
	}

	public void setAdapter(GroupAdapter adapter) {
		this.adapter = adapter;
		innerAdapter = new InnerAdapter(adapter);
		lvBody.setAdapter(innerAdapter);
		setTitle();
	}

	public GroupAdapter getAdapter() {
		return adapter;
	}

	private void notifyDataSetChanged() {
		innerAdapter.notifyDataSetChanged();
		setTitle();
	}
	/** 设置每组的item的title标题*/
	private void setTitle() {
		if (tvTitle != null) {
			removeView(tvTitle);
		}
		if(innerAdapter.getCount() == 0 ){
			return;
		}
		int group = innerAdapter.getItemGroup(curFirstItem);
		int position = innerAdapter.titleIndex.get(group);
		tvTitle = innerAdapter.getView(position, null, this);
		LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(ALIGN_PARENT_LEFT);
		lp.addRule(ALIGN_PARENT_TOP);
		addView(tvTitle, lp);

		tvTitle.measure(0, 0);
		titleHeight = tvTitle.getMeasuredHeight();
		onScroll();
	}

	public void setSelection(int group) {
		setSelection(group, -1);
	}

	public void setSelection(int group, int position) {
		int titleIndex = innerAdapter.titleIndex.get(group);
		int selection = titleIndex + position + 1;
		lvBody.setSelection(selection);
	}
	/** listview 滚动监听，改变组标题*/
	private void onScroll() {
		LayoutParams lp = (LayoutParams) tvTitle.getLayoutParams();
		if (innerAdapter.isLastItem(curFirstItem)) {
			// 上一组最后一项
			int group = innerAdapter.getItemGroup(curFirstItem);
			String title = adapter.getGroupTitle(group);
			adapter.onGroupChange(tvTitle, title);
			int top = lvBody.getChildAt(1).getTop();
			if (top < titleHeight) {
				lp.setMargins(0, top - titleHeight, 0, 0);
				tvTitle.setLayoutParams(lp);
				return;
			}
		}
		lp.topMargin = 0;
		tvTitle.setLayoutParams(lp);

		if (innerAdapter.isTitle(curFirstItem)) {
			// 改变标题项
			int group = innerAdapter.getItemGroup(curFirstItem);
			String title = adapter.getGroupTitle(group);
			adapter.onGroupChange(tvTitle, title);
		}
	}

	public void setOnScrollListener(OnScrollListener l) {
		osListener = l;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		oicListener = listener;
	}
	/***自定义listview的适配器*/
	private static class InnerAdapter extends BaseAdapter {
		private GroupAdapter adapter;
		private ArrayList<Object> listData;
		private ArrayList<Integer> titleIndex;
		private ArrayList<Integer> lastItemIndex;

		public InnerAdapter(GroupAdapter adapter) {
			this.adapter = adapter;
			listData = new ArrayList<Object>();
			titleIndex = new ArrayList<Integer>();
			lastItemIndex = new ArrayList<Integer>();
			init();
		}

		private void init() {
			listData.clear();
			titleIndex.clear();
			lastItemIndex.clear();
			for (int g = 0, gc = adapter.getGroupCount(); g < gc; g++) {
				int c = adapter.getCount(g);
				if (c > 0) {
					titleIndex.add(listData.size());
					listData.add(adapter.getGroupTitle(g));
					for (int i = 0; i < c; i++) {
						listData.add(adapter.getItem(g, i));
					}
					lastItemIndex.add(listData.size() - 1);
				}
			}
		}

		public int getCount() {
			return listData.size();
		}

		public Object getItem(int position) {
			return listData.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public int getItemGroup(int position) {
			int size = titleIndex.size();
			for (int i = 0; i < size; i++) {
				int titleIndex = this.titleIndex.get(i);
				if (position < titleIndex) {
					return i - 1;
				}
			}
			return size - 1;
		}

		public boolean isTitle(int position) {
			for (int i = 0, size = titleIndex.size(); i < size; i++) {
				if (titleIndex.get(i) == position) {
					return true;
				}
			}
			return false;
		}

		public int getViewTypeCount() {
			return 2;
		}

		public int getItemViewType(int position) {
			return isTitle(position) ? 0 : 1;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			int group = getItemGroup(position);
			if (isTitle(position)) {
				if (convertView != null) {
					convertView = adapter.getTitleView(group, convertView, parent);
				} else {
					convertView = adapter.getTitleView(group, null, parent);
				}

			} else {
				int item = position - titleIndex.get(group) - 1;
				convertView = adapter.getView(group, item, convertView, parent);
			}
			return convertView;
		}

		public void notifyDataSetChanged() {
			init();
			super.notifyDataSetChanged();
		}

		public boolean isLastItem(int position) {
			for (int i = 0, size = lastItemIndex.size(); i < size; i++) {
				if (lastItemIndex.get(i) == position) {
					return true;
				}
			}
			return false;
		}

	}
	/**自定义抽象类，用于填充国家列表的item， 相当于对adapter就行封装*/
	public static abstract class GroupAdapter {
		protected final GroupListView view;

		public GroupAdapter(GroupListView view) {
			this.view = view;
		}

		/** 得到group listview 组的个数 */
		public abstract int getGroupCount();

		/**
		 * 得到group listview 某组的子item数
		 * @param group
		 */
		public abstract int getCount(int group);

		/**
		 * 得到group listview 某组的title 标题
		 * @param group
		 * @return
		 */
		public abstract String getGroupTitle(int group);

		/**
		 * 获取group listview 某组的第几个位置的数据对象
		 * @param group
		 * @param position
		 * @return
		 */
		public abstract Object getItem(int group, int position);

		/**
		 * 获取group listview某组的组标题的View
		 * @param group
		 * @param convertView
		 * @param parent
		 * @return
		 */
		public abstract View getTitleView(int group, View convertView, ViewGroup parent);

		/**
		 * 获取group listview 某组的第几个位置的item的View
		 * @param group
		 * @param position
		 * @param convertView
		 * @param parent
		 * @return
		 */
		public abstract View getView(int group, int position, View convertView, ViewGroup parent);

		public void notifyDataSetChanged() {
			view.notifyDataSetChanged();
		}

		public abstract void onGroupChange(View titleView, String title);

	}

	/**
	 * group list view 的 item 点击事件监听接口
	 *
	 */
	public static interface OnItemClickListener {

		public void onItemClick(GroupListView parent, View view, int group, int position);

	}

}
