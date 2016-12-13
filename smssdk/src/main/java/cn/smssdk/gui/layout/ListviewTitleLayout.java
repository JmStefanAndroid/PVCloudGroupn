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
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**ListView的标题布局*/
public class ListviewTitleLayout {

	static RelativeLayout create(Context context) {
		SizeHelper.prepare(context);

		RelativeLayout root = new RelativeLayout(context);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		root.setLayoutParams(params);

		TextView title = new TextView(context);
		title.setId(Res.id.tv_title);
		RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(40));
		titleParams.topMargin = SizeHelper.fromPxWidth(-20);
		title.setLayoutParams(titleParams);
		title.setPadding(SizeHelper.fromPxWidth(20), 0, 0, 0);
		title.setLineSpacing(SizeHelper.fromPxWidth(8), 1);
		int resid = R.getStringRes(context, "smssdk_regist");
		title.setText(resid);
		title.setTextColor(0xff999999);
		title.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(26));
		title.setGravity(Gravity.CENTER_VERTICAL);
		title.setBackgroundColor(0xffeae8ee);
		root.addView(title);

		return root;
	}
}
