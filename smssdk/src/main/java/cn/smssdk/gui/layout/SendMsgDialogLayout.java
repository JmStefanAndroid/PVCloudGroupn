/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2014年 mob.com. All rights reserved.
 */
package cn.smssdk.gui.layout;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mob.tools.utils.R;

/**确定发送短信对话框*/
public class SendMsgDialogLayout {

	public static LinearLayout create(Context context) {
		SizeHelper.prepare(context);
		LinearLayout root = new LinearLayout(context);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		root.setLayoutParams(params);
		root.setOrientation(LinearLayout.VERTICAL);

		TextView dialogTitle = new TextView(context);
		dialogTitle.setId(Res.id.tv_dialog_title);
		LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,SizeHelper.fromPxWidth(92));
		dialogTitle.setLayoutParams(titleParams);
		dialogTitle.setPadding(SizeHelper.fromPxWidth(20), SizeHelper.fromPxWidth(20),SizeHelper.fromPxWidth(20), SizeHelper.fromPxWidth(20));
		int resid = R.getStringRes(context, "smssdk_make_sure_mobile_num");
		dialogTitle.setText(resid);
		dialogTitle.setTextColor(0xff3cac17);
		dialogTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(32));
		dialogTitle.setGravity(Gravity.CENTER_VERTICAL);
		dialogTitle.setTypeface(Typeface.DEFAULT_BOLD);
		root.addView(dialogTitle);

		View line1 = new View(context);
		LinearLayout.LayoutParams line1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(1));
		line1.setLayoutParams(line1Params);
		line1.setBackgroundColor(0xff3cac17);
		root.addView(line1);

		TextView dialogHint = new TextView(context);
		dialogHint.setId(Res.id.tv_dialog_hint);
		LinearLayout.LayoutParams hintParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		hintParams.topMargin = SizeHelper.fromPxWidth(28);
		dialogHint.setLayoutParams(hintParams);
		dialogHint.setPadding(SizeHelper.fromPxWidth(18), 0, SizeHelper.fromPxWidth(18), 0);
		resid = R.getStringRes(context, "smssdk_make_sure_mobile_detail");
		dialogHint.setText(resid);
		dialogHint.setTextColor(0xffffffff);
		dialogHint.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(24));
		root.addView(dialogHint);

		TextView phone = new TextView(context);
		phone.setId(Res.id.tv_phone);
		LinearLayout.LayoutParams phoneParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		phoneParams.bottomMargin = SizeHelper.fromPxWidth(26);
		phone.setLayoutParams(phoneParams);
		phone.setPadding(SizeHelper.fromPxWidth(18), 0, SizeHelper.fromPxWidth(18), 0);
		phone.setTextColor(0xffffffff);
		phone.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(24));
		root.addView(phone);

		View line2 = new View(context);
		LinearLayout.LayoutParams line2Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(1));
		line2.setLayoutParams(line2Params);
		line2.setBackgroundColor(0xff737373);
		root.addView(line2);

		LinearLayout wrapper = new LinearLayout(context);
		LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		wrapper.setLayoutParams(wrapperParams);


		Button ok = new Button(context);
		ok.setId(Res.id.btn_dialog_ok);
		LinearLayout.LayoutParams okParams = new LinearLayout.LayoutParams(0,SizeHelper.fromPxWidth(80),1);
		okParams.rightMargin = SizeHelper.fromPxWidth(1);
		ok.setLayoutParams(okParams);
		resid = R.getBitmapRes(context, "smssdk_dialog_btn_back");
		ok.setBackgroundResource(resid);
		int padding = SizeHelper.fromPxWidth(18);
		ok.setPadding(padding, padding, padding, padding);
		resid = R.getStringRes(context, "smssdk_ok");
		ok.setText(resid);
		ok.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(22));
		ok.setTextColor(0xffffffff);
		wrapper.addView(ok);

		View line3 = new View(context);
		LinearLayout.LayoutParams line3Params = new LinearLayout.LayoutParams(SizeHelper.fromPxWidth(1),LinearLayout.LayoutParams.MATCH_PARENT);
		line3.setLayoutParams(line3Params);
		line3.setBackgroundColor(0xff737373);
		wrapper.addView(line3);

		Button cancel = new Button(context);
		cancel.setId(Res.id.btn_dialog_cancel);
		LinearLayout.LayoutParams cancelParams = new LinearLayout.LayoutParams(0,SizeHelper.fromPxWidth(80),1);
		cancelParams.rightMargin = SizeHelper.fromPxWidth(1);
		cancel.setLayoutParams(cancelParams);
		resid = R.getBitmapRes(context, "smssdk_dialog_btn_back");
		cancel.setBackgroundResource(resid);
		cancel.setPadding(padding, padding, padding, padding);
		resid = R.getStringRes(context, "smssdk_cancel");
		cancel.setText(resid);
		cancel.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(22));
		cancel.setTextColor(0xffffffff);
		wrapper.addView(cancel);

		root.addView(wrapper);
		return root;
	}
}
