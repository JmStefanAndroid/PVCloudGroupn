/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2014年 mob.com. All rights reserved.
 */
package cn.smssdk.gui.layout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableHelper {
	/**创建圆角背景*/
	public static Drawable createCornerBg(Context c) {
		StateListDrawable sd = new StateListDrawable();
		sd.addState(new int[]{android.R.attr.state_pressed}, createCornerBgPressed(c));
		sd.addState(new int[]{}, createCornerBgNormal(c));
		return sd;
	}

	/**一般状态下的背景*/
	public static Drawable createCornerBgNormal(Context c) {
		SizeHelper.prepare(c);

		// prepare
	    int strokeWidth = SizeHelper.fromPxWidth(1);
	    int roundRadius = SizeHelper.fromPxWidth(6);
	    int strokeColor = Color.parseColor("#ffc9c9cb");
	    int fillColor = Color.parseColor("#ffffffff");

	    GradientDrawable gd = new GradientDrawable();
	    gd.setColor(fillColor);
	    gd.setCornerRadius(roundRadius);
	    gd.setStroke(strokeWidth, strokeColor);

		return gd;
	}

	/**被按下时的背景*/
	public static Drawable createCornerBgPressed(Context c) {
		SizeHelper.prepare(c);

		// prepare
	    int strokeWidth = SizeHelper.fromPxWidth(1);
	    int roundRadius = SizeHelper.fromPxWidth(6);
	    int strokeColor = Color.parseColor("#ffc9c9cb");
	    int fillColor = Color.parseColor("#afc9c9cb");

	    GradientDrawable gd = new GradientDrawable();
	    gd.setColor(fillColor);
	    gd.setCornerRadius(roundRadius);
	    gd.setStroke(strokeWidth, strokeColor);

		return gd;
	}
}
