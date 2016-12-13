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

/**页面布局基类*/
public abstract class BasePageLayout {
	LinearLayout layout = null;
	Context context = null;

	public BasePageLayout(Context c,boolean isSearch) {
		context = c;
		layout = new LinearLayout(context);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundColor(0xffffffff);

		LinearLayout title = TitleLayout.create(context,isSearch);
		layout.addView(title);
		onCreateContent(layout);
	}

	/**获取生成的布局*/
	public LinearLayout getLayout() {
		return layout;
	}

	protected abstract void onCreateContent(LinearLayout parent);
}
