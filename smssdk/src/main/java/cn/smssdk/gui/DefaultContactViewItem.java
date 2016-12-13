/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2014年 mob.com. All rights reserved.
 */
package cn.smssdk.gui;

import static com.mob.tools.utils.R.getBitmapRes;
import static com.mob.tools.utils.R.getStringRes;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.smssdk.gui.layout.ContactsListviewItemLayout;
import cn.smssdk.gui.layout.Res;

import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.gui.BitmapProcessor;

public class DefaultContactViewItem implements ContactItemMaker {

	@Override
	public View getView(final HashMap<String, Object> user, View convertView, final ViewGroup parent) {

		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();

			convertView = ContactsListviewItemLayout.create(parent.getContext());

			int resId = Res.id.iv_contact;
			viewHolder.ivContact = (AsyncImageView) convertView.findViewById(resId);
			resId = Res.id.tv_name;
			viewHolder.tvName = (TextView) convertView.findViewById(resId);
			resId = Res.id.tv_contact;
			viewHolder.tvContact = (TextView) convertView.findViewById(resId);
			resId = Res.id.btn_add;
			viewHolder.btnAdd = (Button) convertView.findViewById(resId);
			resId = Res.id.rl_lv_item_bg;
			viewHolder.bg = convertView.findViewById(resId);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if(user != null){
			// 如果user包含“fia”，则为应用内好友
			if (user.containsKey("fia")) {
				viewHolder.tvName.setText(String.valueOf(user.get("nickname")));
				viewHolder.tvContact.setVisibility(View.VISIBLE);
				String dspName = (String) user.get("displayname");
				if (TextUtils.isEmpty(dspName)) {
					viewHolder.tvContact.setText(String.valueOf(user.get("phone")));
				} else {
					viewHolder.tvContact.setText(dspName);
				}
				int resId = getStringRes(parent.getContext(), "smssdk_add_contact");
				if (resId > 0) {
					viewHolder.btnAdd.setText(resId);
				}
			} else {
				String dspName = (String) user.get("displayname");
				if (TextUtils.isEmpty(dspName)) {
					@SuppressWarnings("unchecked")
					ArrayList<HashMap<String, Object>> phones
							= (ArrayList<HashMap<String, Object>>) user.get("phones");
					if (phones != null && phones.size() > 0) {
						String cp = (String) phones.get(0).get("phone");
						viewHolder.tvName.setText(cp);
					}
				} else {
					viewHolder.tvName.setText(dspName);
				}
				viewHolder.tvContact.setVisibility(View.GONE);
				int resId = getStringRes(parent.getContext(), "smssdk_invite");
				if (resId > 0) {
					viewHolder.btnAdd.setText(resId);
				}
			}

			viewHolder.bg.setBackgroundColor(0xffffffff);
			// 是否有新好友，如有，改变背景颜色
			if(user.containsKey("isnew")){
				boolean isNew = Boolean.valueOf(String.valueOf(user.get("isnew")));
				if(isNew){
					viewHolder.bg.setBackgroundColor(0xfff7fcff);
				}
			}

			String iconUrl = user.containsKey("avatar")? (String) user.get("avatar") : null;
			// 设置默认头像，如果有url，就去下载
			int resId = getBitmapRes(parent.getContext(), "smssdk_cp_default_avatar");
			if (resId > 0) {
				viewHolder.ivContact.execute(null, resId);
			}
			if(!TextUtils.isEmpty(iconUrl)){
				Log.w(String.valueOf(user.get("displayname")) + " icon url ==>> ", iconUrl);
				Bitmap bm = BitmapProcessor.getBitmapFromCache(iconUrl);
				if (bm != null && !bm.isRecycled()) {
					viewHolder.ivContact.setImageBitmap(bm);
				} else{
					viewHolder.ivContact.execute(iconUrl, resId);
				}
			}

			viewHolder.btnAdd.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(user.containsKey("fia")){
						// 在这里添加第一组的按钮事件
						Toast.makeText(parent.getContext(), String.valueOf(user), Toast.LENGTH_SHORT).show();
					} else{
						ContactDetailPage contactDetailPage = new ContactDetailPage();
						contactDetailPage.setContact(user);
						contactDetailPage.show(parent.getContext(), null);
					}
				}
			});
		}
		return convertView;
	}

	public class ViewHolder{
		public View bg;
		public AsyncImageView ivContact;
		public TextView tvName;
		public TextView tvContact;
		public Button btnAdd;
	}

}
