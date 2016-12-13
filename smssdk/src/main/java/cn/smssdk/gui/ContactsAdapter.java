/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2014年 mob.com. All rights reserved.
 */
package cn.smssdk.gui;

import static com.mob.tools.utils.R.getStringRes;

import java.util.ArrayList;
import java.util.HashMap;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import cn.smssdk.gui.ContactsListView.GroupAdapter;
import cn.smssdk.gui.layout.SizeHelper;

/**
 * 联系人列表adapter
 */
public class ContactsAdapter extends GroupAdapter {
	private ArrayList<String> titles;
	private ArrayList<ArrayList<HashMap<String, Object>>> contacts;

	private ArrayList<HashMap<String, Object>> friendsInApp = new ArrayList<HashMap<String, Object>>();
	private ArrayList<HashMap<String, Object>> contactsOutApp = new ArrayList<HashMap<String, Object>>();

	private ContactItemMaker itemMaker;
	private SearchEngine sEngine;

	public ContactsAdapter(ContactsListView view, ArrayList<HashMap<String, Object>> friendsInApp,
			ArrayList<HashMap<String, Object>> contactsOutApp) {
		super(view);
		this.friendsInApp = friendsInApp;
		this.contactsOutApp = contactsOutApp;
		initSearchEngine();
		search(null);
	}

	private void initSearchEngine() {
		sEngine = new SearchEngine();
		ArrayList<String> data = new ArrayList<String>();
		for (HashMap<String, Object> contact : friendsInApp) {
			String name = "";
			if (contact.containsKey("displayname")) {
				name = String.valueOf(contact.get("displayname"));
			}
			if (TextUtils.isEmpty(name)) {
				continue;
			}
			data.add(name);
		}
		for (HashMap<String, Object> contact : contactsOutApp) {
			String name = "";
			if (contact.containsKey("displayname")) {
				name = String.valueOf(contact.get("displayname"));
			}
			if (TextUtils.isEmpty(name)) {
				continue;
			}
			data.add(name);
		}
		sEngine.setIndex(data);
	}

	/**
	 * 搜索
	 * @param token  搜索的关键字
	 */
	public void search(String token) {
		ArrayList<String> res = sEngine.match(token);
		boolean isEmptyToken = false;
		if (res == null || res.size() <= 0) {
			res = new ArrayList<String>();
			isEmptyToken = true;
		}

		HashMap<String, String> resMap = new HashMap<String, String>();
		for (String r : res) {
			resMap.put(r, r);
		}

		titles = new ArrayList<String>();
		contacts = new ArrayList<ArrayList<HashMap<String, Object>>>();

		if (friendsInApp.size() > 0) {
			reSortFia(resMap, isEmptyToken, friendsInApp);
		}
		if (contactsOutApp.size() > 0) {
			reSortFoa(resMap, isEmptyToken, contactsOutApp);
		}

	}

	/** 数据处理 ,对应用内好友列表的数据进行排列*/
	private void reSortFia(HashMap<String, String> resMap, boolean isEmptyToken,
			ArrayList<HashMap<String, Object>> data) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (HashMap<String, Object> contact : data) {
			String name = "";
			if (contact.containsKey("displayname")) {
				name = String.valueOf(contact.get("displayname"));
			}
			if (TextUtils.isEmpty(name)) {
				continue;
			}
			if (isEmptyToken || resMap.containsKey(name)) {
				list.add(contact);
			}
		}

		if (list.size() > 0) {
			int resId = getStringRes(view.getContext(), "smssdk_contacts_in_app");
			if (resId > 0) {
				titles.add(view.getContext().getResources().getString(resId));
			}
			contacts.add(list);
		}
	}

	/** 数据处理 ,对不是应用内好友列表的数据进行排列 */
	private void reSortFoa(HashMap<String, String> resMap, boolean isEmptyToken,
			ArrayList<HashMap<String, Object>> data) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (HashMap<String, Object> contact : data) {
			String name = "";
			if (contact.containsKey("displayname")) {
				name = String.valueOf(contact.get("displayname"));
			}
			if (TextUtils.isEmpty(name)) {
				continue;
			}
			if (isEmptyToken || resMap.containsKey(name)) {
				list.add(contact);
			}
		}

		if (list.size() > 0) {
			int resId = getStringRes(view.getContext(), "smssdk_contacts_out_app");
			if (resId > 0) {
				titles.add(view.getContext().getResources().getString(resId));
			}
			contacts.add(list);
		}
	}

	public void setContactItemMaker(ContactItemMaker itemMaker) {
		this.itemMaker = itemMaker;
	}

//	/** 数据处理 */
//	private void reSortFia(String token, ArrayList<HashMap<String, Object>> data) {
//		boolean isEmptyToken = TextUtils.isEmpty(token);
//		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//		for (HashMap<String, Object> contact : data) {
//			String name = "";
//			if (contact.containsKey("nickname")) {
//				name = String.valueOf(contact.get("nickname"));
//			} else if (contact.containsKey("displayname")) {
//				name = String.valueOf(contact.get("displayname"));
//			} else if (contact.containsKey("phone")) {
//				name = String.valueOf(contact.get("phone"));
//			}
//			if (TextUtils.isEmpty(name)) {
//				continue;
//			}
//			if (isEmptyToken || (!TextUtils.isEmpty(name) && name.contains(token))) {
//				list.add(contact);
//			}
//		}
//
//		if (list.size() > 0) {
//			int resId = getStringRes(view.getContext(), "smssdk_contacts_in_app");
//			if (resId > 0) {
//				titles.add(view.getContext().getResources().getString(resId));
//			}
//			contacts.add(list);
//		}
//	}
//
//	/** 数据处理 */
//	private void reSortFoa(String token, ArrayList<HashMap<String, Object>> data) {
//		boolean isEmptyToken = TextUtils.isEmpty(token);
//		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//		for (HashMap<String, Object> contact : data) {
//			String name = "";
//			if (contact.containsKey("displayname")) {
//				name = String.valueOf(contact.get("displayname"));
//			} else if (contact.containsKey("phones")) {
//				@SuppressWarnings("unchecked")
//				ArrayList<HashMap<String, Object>> phones
//						= (ArrayList<HashMap<String, Object>>) contact.get("phones");
//				if (phones != null && phones.size() > 0) {
//					name = (String) phones.get(0).get("phone");
//				}
//			}
//			if (TextUtils.isEmpty(name)) {
//				continue;
//			}
//			if (isEmptyToken || (!TextUtils.isEmpty(name) && name.contains(token))) {
//				list.add(contact);
//			}
//		}
//
//		if (list.size() > 0) {
//			int resId = getStringRes(view.getContext(), "smssdk_contacts_out_app");
//			if (resId > 0) {
//				titles.add(view.getContext().getResources().getString(resId));
//			}
//			contacts.add(list);
//		}
//	}

	public int getGroupCount() {
		return titles == null ? 0 : titles.size();
	}

	public int getCount(int group) {
		if (contacts == null) {
			return 0;
		}

		ArrayList<HashMap<String, Object>> list = contacts.get(group);
		if (list == null) {
			return 0;
		}

		return list.size();
	}

	public String getGroupTitle(int group) {
		if (titles.size() > 0) {
			return titles.get(group).toString();
		} else {
			return null;
		}
	}

	public HashMap<String, Object> getItem(int group, int position) {
		if (contacts.size() > 0) {
			return contacts.get(group).get(position);
		} else {
			return null;
		}
	}

	public TextView getTitleView(int group, TextView convertView, ViewGroup parent) {
		if (convertView == null) {
			SizeHelper.prepare(parent.getContext());

			convertView = new TextView(parent.getContext());
			convertView.setBackgroundColor(0xffeae8ee);
			convertView.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeHelper.fromPxWidth(25));
			convertView.setTextColor(0xff999999);
			int padding = SizeHelper.fromPxWidth(18);
			convertView.setPadding(padding, 0, 0, 0);
			convertView.setWidth(LayoutParams.MATCH_PARENT);
			int height = SizeHelper.fromPxWidth(40);
			convertView.setHeight(height);
			convertView.setGravity(Gravity.CENTER_VERTICAL);
		}
		String title = getGroupTitle(group);
		if (!TextUtils.isEmpty(title)) {
			convertView.setText(title);
		}
		return convertView;
	}

	public View getView(final int group, final int position, View convertView, ViewGroup parent) {
		HashMap<String, Object> data = getItem(group, position);
		return itemMaker.getView(data, convertView, parent);
	}

}
