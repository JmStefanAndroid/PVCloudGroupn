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
import java.util.HashSet;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.layout.ContactListPageLayout;
import cn.smssdk.gui.layout.Res;
import cn.smssdk.utils.SMSLog;

import com.mob.tools.FakeActivity;

/** 联系人列表页面*/
public class ContactsPage extends FakeActivity implements OnClickListener, TextWatcher{

	private EditText etSearch;
	private ContactsListView listView;
	private ContactsAdapter adapter;
	private ContactItemMaker itemMaker;

	private Dialog pd;
	private EventHandler handler;
	private ArrayList<HashMap<String,Object>> friendsInApp;
	private ArrayList<HashMap<String,Object>> contactsInMobile;

	@Override
	public void onCreate() {
		if (pd != null && pd.isShowing()) {
			pd.dismiss();
		}
		pd = CommonDialog.ProgressDialog(activity);
		if (pd != null) {
			pd.show();
		}

		// 初始化搜索引擎
		SearchEngine.prepare(activity, new Runnable() {
			public void run() {
				afterPrepare();
			}
		});
	}

	private void afterPrepare() {
		runOnUIThread(new Runnable() {
			public void run() {
				friendsInApp = new ArrayList<HashMap<String,Object>>();
				contactsInMobile = new ArrayList<HashMap<String,Object>>();

				ContactListPageLayout page = new ContactListPageLayout(activity);
				LinearLayout layout = page.getLayout();

				if (layout != null) {
					activity.setContentView(layout);
					initView();
					initData();
				}
			}
		});
	}

	@Override
	public void onResume(){
	  	super.onResume();
	}

	@Override
	public void onPause() {
	   	super.onPause();
	}

	public void show(Context context) {
		show(context, new DefaultContactViewItem());
	}

	public void show(Context context, ContactItemMaker maker) {
		itemMaker = maker;
		super.show(context, null);
	}

	private void initView(){

		listView = (ContactsListView) activity.findViewById(Res.id.clContact);

		activity.findViewById(Res.id.ll_back).setOnClickListener(this);
		activity.findViewById(Res.id.ivSearch).setOnClickListener(this);
		activity.findViewById(Res.id.iv_clear).setOnClickListener(this);

		TextView tv = (TextView) activity.findViewById(Res.id.tv_title);
		int resId = getStringRes(activity, "smssdk_search_contact");
		if (resId > 0) {
				tv.setText(resId);
		}

		etSearch = (EditText) activity.findViewById(Res.id.et_put_identify);
		etSearch.addTextChangedListener(this);
	}

	private void initData(){
		handler = new EventHandler() {
			@SuppressWarnings("unchecked")
			public void afterEvent(final int event, final int result, final Object data) {
				if (result == SMSSDK.RESULT_COMPLETE) {
					if (event == SMSSDK.EVENT_GET_CONTACTS) {
						// 请求获取本地联系人列表
						ArrayList<HashMap<String,Object>> rawList = (ArrayList<HashMap<String,Object>>) data;
						if (rawList == null) {
							contactsInMobile = new ArrayList<HashMap<String,Object>>();
						} else {
							contactsInMobile = (ArrayList<HashMap<String,Object>>) rawList.clone();
						}
						refreshContactList();
					} else if (event == SMSSDK.EVENT_GET_FRIENDS_IN_APP) {
						// 请求获取服务器上，应用内的朋友
						friendsInApp = (ArrayList<HashMap<String,Object>>) data;
						SMSSDK.getContacts(false);
					}
				} else {
					runOnUIThread(new Runnable() {
						public void run() {
							if (pd != null && pd.isShowing()) {
								pd.dismiss();
							}
							// 网络错误
							int resId = getStringRes(activity, "smssdk_network_error");
							if (resId > 0) {
								Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show();
							}
						}
					});
				}
			}
		};
		// 注册事件监听器
		SMSSDK.registerEventHandler(handler);

		if(friendsInApp != null && friendsInApp.size() > 0){
			// 获取本地联系人
			SMSSDK.getContacts(false);
		}else{
			// 获取应用内的好友列表
			SMSSDK.getFriendsInApp();
		}
	}

	public boolean onKeyEvent(int keyCode, KeyEvent event) {
		try {
			int resId = Res.id.llSearch;
			if (keyCode == KeyEvent.KEYCODE_BACK
					&& event.getAction() == KeyEvent.ACTION_DOWN
					&& activity.findViewById(resId).getVisibility() == View.VISIBLE) {
				activity.findViewById(resId).setVisibility(View.GONE);
				resId = Res.id.llTitle;
				activity.findViewById(resId).setVisibility(View.VISIBLE);
				etSearch.setText("");
				return true;
			}
		} catch (Exception e) {
			SMSLog.getInstance().w(e);
		}
		return super.onKeyEvent(keyCode, event);
	}

	public void onDestroy() {
		// 销毁事件监听器
		SMSSDK.unregisterEventHandler(handler);
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (adapter != null) {
			adapter.search(s.toString());
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		int id_ll_back = Res.id.ll_back;
		int id_ivSearch = Res.id.ivSearch;
		int id_iv_clear = Res.id.iv_clear;

		if (id == id_ll_back) {
			finish();
		} else if (id == id_ivSearch) {
			int id_llTitle = Res.id.llTitle;
			activity.findViewById(id_llTitle).setVisibility(View.GONE);
			int id_llSearch = Res.id.llSearch;
			activity.findViewById(id_llSearch).setVisibility(View.VISIBLE);
			etSearch.requestFocus();
			etSearch.getText().clear();
		} else if (id == id_iv_clear) {
			etSearch.getText().clear();
		}
	}

	// 获取联系人列表
	@SuppressWarnings("unchecked")
	private void refreshContactList() {
		// 造一个“电话号码-联系人”映射表，加速查询
		ArrayList<ContactEntry> phone2Contact = new ArrayList<ContactEntry>();
		for (HashMap<String, Object> contact : contactsInMobile) {
			ArrayList<HashMap<String, Object>> phones
					= (ArrayList<HashMap<String, Object>>) contact.get("phones");
			if (phones != null && phones.size() > 0) {
				for (HashMap<String, Object> phone : phones) {
					String pn = (String) phone.get("phone");
					ContactEntry ent = new ContactEntry(pn, contact);
					phone2Contact.add(ent);
				}
			}
		}

		// 组织应用内好友分组
		ArrayList<HashMap<String,Object>> tmpFia = new ArrayList<HashMap<String,Object>>();
		int p2cSize = phone2Contact.size();
		for (HashMap<String, Object> friend : friendsInApp) {
			String phone = String.valueOf(friend.get("phone"));
			if (phone != null) {
				for (int i = 0; i < p2cSize; i++) {
					ContactEntry ent = phone2Contact.get(i);
					String cp = ent.getKey();
					if (phone.equals(cp)) {
						friend.put("contact", ent.getValue());
						friend.put("fia", true);
						tmpFia.add((HashMap<String, Object>) friend.clone());
					}
				}
			}
		}
		friendsInApp = tmpFia;

		// 移除本地联系人列表中，包含已加入APP的联系人
		HashSet<HashMap<String, Object>> tmpCon = new HashSet<HashMap<String,Object>>();
		for (ContactEntry ent : phone2Contact) {
			String cp = ent.getKey();
			HashMap<String, Object> con = ent.getValue();
			if (cp != null && con != null) {
				boolean shouldAdd = true;
				for (HashMap<String, Object> friend : friendsInApp) {
					String phone = String.valueOf(friend.get("phone"));
					if (cp.equals(phone)) {
						shouldAdd = false;
						break;
					}
				}
				if (shouldAdd) {
					tmpCon.add(con);
				}
			}
		}
		contactsInMobile.clear();
		contactsInMobile.addAll(tmpCon);

		// 删除非应用内好友分组联系人电话列表中已经注册了的电话号码
		for (HashMap<String, Object> friend : friendsInApp) {
			HashMap<String, Object> contact = (HashMap<String, Object>) friend.remove("contact");
			if (contact != null) {
				String phone = String.valueOf(friend.get("phone"));
				if (phone != null) {
					ArrayList<HashMap<String, Object>> phones
							= (ArrayList<HashMap<String, Object>>) contact.get("phones");
					if (phones != null && phones.size() > 0) {
						ArrayList<HashMap<String, Object>> tmpPs = new ArrayList<HashMap<String,Object>>();
						for (HashMap<String, Object> p : phones) {
							String cp = (String) p.get("phone");
							if (!phone.equals(cp)) {
								tmpPs.add(p);
							}
						}
						contact.put("phones", tmpPs);
					}
				}

				// 添加本地联系人名称
				friend.put("displayname", contact.get("displayname"));
			}
		}

		// 更新listview
		runOnUIThread(new Runnable() {
			public void run() {
				if (pd != null && pd.isShowing()) {
					pd.dismiss();
				}

				adapter = new ContactsAdapter(listView, friendsInApp, contactsInMobile);
				adapter.setContactItemMaker(itemMaker);
				listView.setAdapter(adapter);
			}
		});


//		if (pd != null && pd.isShowing()) {
//			pd.dismiss();
//		}
//
//		try {
//
//			// 造一个“电话号码-联系人”映射表，加速查询
//			HashMap<String, HashMap<String, Object>> phone2Contact = new HashMap<String, HashMap<String,Object>>();
//			for (HashMap<String, Object> contact : contactsInMobile) {
//				ArrayList<HashMap<String, Object>> phones = (ArrayList<HashMap<String, Object>>) contact.get("phones");
//				if (phones != null && phones.size() > 0) {
//					for (HashMap<String, Object> phone : phones) {
//						String pn = (String) phone.get("phone");
//						//有号码，木有名字；名字  = 号码
//						if(!contact.containsKey("displayname")){
//							contact.put("displayname", pn);
//						}
//						phone2Contact.put(pn, contact);
//					}
//				}
//			}
//
//			// 移除本地联系人列表中，包含已加入APP的联系人
//			ArrayList<HashMap<String, Object>> tmpList = new ArrayList<HashMap<String,Object>>();
//			for (int i = 0; i < friendsInApp.size(); i++) {
//				HashMap<String, Object> friend = friendsInApp.get(i);
//				String phoneNum = String.valueOf(friend.get("phone"));
//				HashMap<String, Object> contact = phone2Contact.remove(phoneNum);
//				if (contact != null) {
//					String namePhone = String.valueOf(contact.get("displayname"));
//					if(TextUtils.isEmpty(namePhone)){
//						namePhone = phoneNum;
//					}
//					// 已加入应用的联系人，显示contact name, 否则显示 phoneNumber
//					friend.put("displayname", namePhone);
//					tmpList.add(friend);
//				}
//			}
//			friendsInApp = tmpList;
//			//重新对号码进行过滤，排除重复的contact(一人多码)
//			HashSet<HashMap<String, Object>> contactsSet = new HashSet<HashMap<String,Object>>(phone2Contact.values());
//			contactsInMobile = new ArrayList<HashMap<String,Object>>(contactsSet);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		//TODO 更新listview
//		adapter = new ContactsAdapter(listView, friendsInApp, contactsInMobile);
//		adapter.setContactItemMaker(itemMaker);
//		//adapter.setOnItemClickListener(this);
//		listView.setAdapter(adapter);

	}

}
