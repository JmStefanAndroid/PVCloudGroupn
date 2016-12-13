/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2014年 mob.com. All rights reserved.
 */
package cn.smssdk.gui.layout;

import com.mob.tools.utils.R;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

/**联系人详情页面布局*/
public class ContactDetailPageLayout extends BasePageLayout {

	public ContactDetailPageLayout(Context c) {
		super(c,false);
	}

	protected void onCreateContent(LinearLayout parent) {
		SizeHelper.prepare(context);

		LinearLayout wrapperLayout =  new LinearLayout(context);
		LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		wrapperParams.setMargins(SizeHelper.fromPxWidth(25), SizeHelper.fromPxWidth(30), SizeHelper.fromPxWidth(25), 0);
		wrapperLayout.setLayoutParams(wrapperParams);

		wrapperLayout.setBackgroundDrawable(DrawableHelper.createCornerBgNormal(context));
		wrapperLayout.setOrientation(LinearLayout.HORIZONTAL);
		parent.addView(wrapperLayout);

		ImageView contactIcon = new ImageView(context);
		contactIcon.setId(Res.id.iv_contact_icon);
		LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(SizeHelper.fromPxWidth(88),SizeHelper.fromPxWidth(88));
		int margin = SizeHelper.fromPxWidth(16);
		imageParams.setMargins(margin, margin, margin, margin);
		contactIcon.setLayoutParams(imageParams);
		contactIcon.setScaleType(ScaleType.FIT_CENTER);
		int resid = R.getBitmapRes(context, "smssdk_default_avatar");
		contactIcon.setBackgroundResource(resid);
		wrapperLayout.addView(contactIcon);

		LinearLayout innerLayout =  new LinearLayout(context);
		LinearLayout.LayoutParams innerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		innerParams.setMargins(SizeHelper.fromPxWidth(4), SizeHelper.fromPxWidth(10), 0, SizeHelper.fromPxWidth(15));
		innerLayout.setLayoutParams(innerParams);
		innerLayout.setOrientation(LinearLayout.VERTICAL);
		wrapperLayout.addView(innerLayout);

		TextView contactName = new TextView(context);
		contactName.setId(Res.id.tv_contact_name);
		LinearLayout.LayoutParams contactNameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		contactNameParams.topMargin = SizeHelper.fromPxWidth(10);
		contactName.setLayoutParams(contactNameParams);
		contactName.setTextColor(0xff000000);
		contactName.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(24));
		innerLayout.addView(contactName);

		LinearLayout nestLayout =  new LinearLayout(context);
		LinearLayout.LayoutParams nestParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		nestParams.setMargins(0, SizeHelper.fromPxWidth(10), SizeHelper.fromPxWidth(15), 0);
		nestLayout.setLayoutParams(nestParams);
		nestLayout.setOrientation(LinearLayout.HORIZONTAL);
		innerLayout.addView(nestLayout);

		TextView tvPhone = new TextView(context);
		LinearLayout.LayoutParams tvPhoneParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		tvPhone.setLayoutParams(tvPhoneParams);
		resid = R.getStringRes(context, "smssdk_contacts_phones");
		tvPhone.setText(resid);
		tvPhone.setTextColor(0xff000000);
		tvPhone.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(20));
		nestLayout.addView(tvPhone);

		TextView contactPhone = new TextView(context);
		contactPhone.setId(Res.id.tv_contact_phones);
		LinearLayout.LayoutParams contactPhoneParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		contactPhoneParams.leftMargin = SizeHelper.fromPxWidth(10);
		contactPhone.setLayoutParams(contactPhoneParams);
		contactPhone.setTextColor(0xff000000);
		contactPhone.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(20));
		nestLayout.addView(contactPhone);

		TextView tv = new TextView(context);
		tv.setId(Res.id.tv_invite_hint);
		LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		tvParams.setMargins(SizeHelper.fromPxWidth(26), SizeHelper.fromPxWidth(24), SizeHelper.fromPxWidth(26), 0);
		tvParams.gravity = Gravity.CENTER_HORIZONTAL;
		tv.setLayoutParams(tvParams);
		tv.setTextColor(0xff5c5c5c);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(28));
		parent.addView(tv);

		Button inviteBtn = new Button(context);
		inviteBtn.setId(Res.id.btn_invite);
		LinearLayout.LayoutParams inviteParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(72));
		inviteParams.setMargins(SizeHelper.fromPxWidth(26), SizeHelper.fromPxWidth(22), SizeHelper.fromPxWidth(26), 0);
		inviteBtn.setLayoutParams(inviteParams);
		resid = R.getBitmapRes(context, "smssdk_btn_enable");
		inviteBtn.setBackgroundResource(resid);
		resid = R.getStringRes(context, "smssdk_send_invitation");
		inviteBtn.setText(resid);
		inviteBtn.setTextColor(0xffffffff);
		inviteBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(28));
		inviteBtn.setPadding(0, 0, 0, 0);
		parent.addView(inviteBtn);
	}

}
