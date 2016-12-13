/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2014年 mob.com. All rights reserved.
 */
package cn.smssdk.gui.layout;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**进度dialog*/
public class ProgressDialogLayout {

	public static LinearLayout create(Context context) {
		LinearLayout root = new LinearLayout(context);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		root.setLayoutParams(params);
		root.setOrientation(LinearLayout.VERTICAL);

		ProgressBar bar = new ProgressBar(context);
		LinearLayout.LayoutParams barParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		bar.setLayoutParams(barParams);
		SizeHelper.prepare(context);
		int padding = SizeHelper.fromPxWidth(20);
		bar.setPadding(padding, padding, padding, padding);

		root.addView(bar);

		return root;
	}
}
