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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BackVerifyDialogLayout {

	/**验证返回对话框布局*/
	public static LinearLayout create(Context context) {
		SizeHelper.prepare(context);

		LinearLayout root = new LinearLayout(context);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		root.setLayoutParams(params);
		root.setOrientation(LinearLayout.VERTICAL);

		TextView dialogHint = new TextView(context);
		dialogHint.setId(Res.id.tv_dialog_hint);
		LinearLayout.LayoutParams hintParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		hintParams.topMargin = SizeHelper.fromPxWidth(32);
		hintParams.bottomMargin = SizeHelper.fromPxWidth(32);
		dialogHint.setLayoutParams(hintParams);
		dialogHint.setPadding(SizeHelper.fromPxWidth(18), 0, SizeHelper.fromPxWidth(18), 0);
		dialogHint.setLineSpacing(SizeHelper.fromPxWidth(8), 1);
		int resid = R.getStringRes(context, "smssdk_make_sure_mobile_detail");
		dialogHint.setText(resid);
		dialogHint.setTextColor(0xffffffff);
		dialogHint.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeHelper.fromPxWidth(26));
		dialogHint.setGravity(Gravity.CENTER);
		root.addView(dialogHint);

		View line = new View(context);
		LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(1));
		line.setLayoutParams(lineParams);
		line.setBackgroundColor(0xff737373);
		root.addView(line);

		LinearLayout wrapper = new LinearLayout(context);
		LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		wrapper.setLayoutParams(wrapperParams);


		Button ok = new Button(context);
		ok.setId(Res.id.btn_dialog_ok);
		LinearLayout.LayoutParams okParams = new LinearLayout.LayoutParams(0,SizeHelper.fromPxWidth(78),1);
		okParams.leftMargin = SizeHelper.fromPxWidth(3);
		ok.setLayoutParams(okParams);
		resid = R.getBitmapRes(context, "smssdk_dialog_btn_back");
		ok.setBackgroundResource(resid);
		int padding = SizeHelper.fromPxWidth(8);
		ok.setPadding(padding, padding, padding, padding);
		resid = R.getStringRes(context, "smssdk_ok");
		ok.setText(resid);
		ok.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(22));
		ok.setTextColor(0xffffffff);
		wrapper.addView(ok);

		View line2 = new View(context);
		LinearLayout.LayoutParams line2Params = new LinearLayout.LayoutParams(SizeHelper.fromPxWidth(1),LinearLayout.LayoutParams.MATCH_PARENT);
		line2.setLayoutParams(line2Params);
		line2.setBackgroundColor(0xff737373);
		wrapper.addView(line2);

		Button cancel = new Button(context);
		cancel.setId(Res.id.btn_dialog_cancel);
		LinearLayout.LayoutParams cancelParams = new LinearLayout.LayoutParams(0,SizeHelper.fromPxWidth(78),1);
		cancelParams.rightMargin = SizeHelper.fromPxWidth(3);
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
