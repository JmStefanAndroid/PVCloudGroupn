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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

/**TitleLayout*/
public class TitleLayout {

	static final int inHeight = 74;
	static final int lineHeight = 2;

	/**根据是否带搜索功能，创建头部标题布局*/
	static LinearLayout create(Context context,boolean isSearch) {
		SizeHelper.prepare(context);

		//init base LinearLayout
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout titleLayout = new LinearLayout(context);
		titleLayout.setLayoutParams(params);
		titleLayout.setOrientation(LinearLayout.VERTICAL);

		View topLine = new View(context);
		LinearLayout.LayoutParams topLineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(1));
		topLine.setLayoutParams(topLineParams);
		topLine.setBackgroundColor(0xff454a4b);
		titleLayout.addView(topLine);

		if(isSearch) {
			createSearch(titleLayout,context);
		}
		else {
			createNormal(titleLayout,context);
		}

		View bottomLine = new View(context);
		LinearLayout.LayoutParams bottomLineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(2));
		bottomLine.setLayoutParams(bottomLineParams);
		bottomLine.setBackgroundColor(0xff1a1c1d);
		titleLayout.addView(bottomLine);

		return titleLayout;
	}

	private static void createNormal(LinearLayout titleLayout,Context context) {

		//build the inside linearLayout
		int height = SizeHelper.fromPx(inHeight);

		LinearLayout.LayoutParams inParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);
		LinearLayout inLayout = new LinearLayout(context);
		inLayout.setLayoutParams(inParams);
		inLayout.setBackgroundColor(0xff303537);

				LinearLayout.LayoutParams backParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
				LinearLayout backLayout = new LinearLayout(context);
				backLayout.setLayoutParams(backParams);
				backLayout.setId(Res.id.ll_back);
				backLayout.setPadding(SizeHelper.fromPx(14), 0, SizeHelper.fromPx(26), 0);

				LinearLayout.LayoutParams arrowParams = new LinearLayout.LayoutParams(SizeHelper.fromPx(15),SizeHelper.fromPx(25));
				arrowParams.gravity = Gravity.CENTER_VERTICAL;
				arrowParams.rightMargin = SizeHelper.fromPx(14);
				ImageView backArrow = new ImageView(context);
				backArrow.setLayoutParams(arrowParams);
				int res = R.getBitmapRes(context, "smssdk_back_arrow");
				backArrow.setBackgroundResource(res);

				LinearLayout.LayoutParams logoParams = new LinearLayout.LayoutParams(SizeHelper.fromPx(30),SizeHelper.fromPx(44));
				logoParams.gravity = Gravity.CENTER_VERTICAL;
				logoParams.rightMargin = SizeHelper.fromPx(14);
				ImageView backLogo = new ImageView(context);
				backLogo.setLayoutParams(logoParams);
				res = R.getBitmapRes(context, "smssdk_sharesdk_icon");
				backLogo.setBackgroundResource(res);

				backLayout.addView(backArrow);
				backLayout.addView(backLogo);

				inLayout.addView(backLayout);

				LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
				titleParams.gravity = Gravity.CENTER_VERTICAL;
				TextView title = new TextView(context);
				title.setLayoutParams(titleParams);
				title.setId(Res.id.tv_title);
				title.setTextColor(0xffcfcfcf);
				title.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeHelper.fromPx(32));
				inLayout.addView(title);

				titleLayout.addView(inLayout);
	}

	private static void createSearch(LinearLayout titleLayout,Context context) {
		//build the inside linearLayout
		int height = SizeHelper.fromPx(inHeight);
		LinearLayout.LayoutParams inParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height);
		LinearLayout inLayout = new LinearLayout(context);
		inLayout.setLayoutParams(inParams);
		inLayout.setBackgroundColor(0xff303537);
		inLayout.setBaselineAligned(false);

		LinearLayout.LayoutParams backParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
		LinearLayout backLayout = new LinearLayout(context);
		backLayout.setLayoutParams(backParams);
		backLayout.setId(Res.id.ll_back);
		backLayout.setPadding(SizeHelper.fromPx(14), 0, SizeHelper.fromPx(26), 0);

		LinearLayout.LayoutParams arrowParams = new LinearLayout.LayoutParams(SizeHelper.fromPx(15),SizeHelper.fromPx(25));
		arrowParams.gravity = Gravity.CENTER_VERTICAL;
		arrowParams.rightMargin = SizeHelper.fromPxWidth(14);
		ImageView backArrow = new ImageView(context);
		backArrow.setLayoutParams(arrowParams);
		int res = R.getBitmapRes(context, "smssdk_back_arrow");
		backArrow.setBackgroundResource(res);

		LinearLayout.LayoutParams logoParams = new LinearLayout.LayoutParams(SizeHelper.fromPx(30),SizeHelper.fromPx(44));
		logoParams.gravity = Gravity.CENTER_VERTICAL;
		logoParams.rightMargin = SizeHelper.fromPx(14);
		ImageView backLogo = new ImageView(context);
		backLogo.setLayoutParams(logoParams);
		res = R.getBitmapRes(context, "smssdk_sharesdk_icon");
		backLogo.setBackgroundResource(res);

		backLayout.addView(backArrow);
		backLayout.addView(backLogo);

		inLayout.addView(backLayout);

		LinearLayout.LayoutParams innerTitleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT,1);
		LinearLayout innerTitleLayout = new LinearLayout(context);
		innerTitleLayout.setLayoutParams(innerTitleParams);
		innerTitleLayout.setId(Res.id.llTitle);
		inLayout.addView(innerTitleLayout);

		LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1);
		titleParams.gravity = Gravity.CENTER_VERTICAL;
		TextView title = new TextView(context);
		title.setLayoutParams(titleParams);
		title.setId(Res.id.tv_title);
		res = R.getStringRes(context, "smssdk_choose_country");
		title.setText(res);
		title.setTextColor(0xffcfcfcf);
		title.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeHelper.fromPx(32));
		innerTitleLayout.addView(title);

		LinearLayout.LayoutParams searchImageParams = new LinearLayout.LayoutParams(SizeHelper.fromPx(70),LinearLayout.LayoutParams.WRAP_CONTENT);
		searchImageParams.gravity = Gravity.CENTER_VERTICAL;
		ImageView searchImage = new ImageView(context);
		searchImage.setLayoutParams(searchImageParams);
		searchImage.setId(Res.id.ivSearch);
		res = R.getBitmapRes(context, "smssdk_search_icon");
		searchImage.setImageResource(res);
		searchImage.setScaleType(ScaleType.CENTER_INSIDE);
		searchImage.setPadding(SizeHelper.fromPx(15), 0, SizeHelper.fromPx(15), 0);
		innerTitleLayout.addView(searchImage);

		LinearLayout.LayoutParams innerSearchParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1);
		innerSearchParams.gravity = Gravity.CENTER_VERTICAL;
		innerSearchParams.rightMargin = SizeHelper.fromPx(18);
		LinearLayout innerSearchLayout = new LinearLayout(context);
		innerSearchLayout.setLayoutParams(innerSearchParams);
		innerSearchLayout.setId(Res.id.llSearch);
		res = R.getBitmapRes(context, "smssdk_input_bg_focus");
		innerSearchLayout.setBackgroundResource(res);
		innerSearchLayout.setPadding(SizeHelper.fromPx(14), 0, SizeHelper.fromPx(14), 0);
		innerSearchLayout.setVisibility(View.GONE);
		inLayout.addView(innerSearchLayout);

		LinearLayout.LayoutParams searchIconParams = new LinearLayout.LayoutParams(SizeHelper.fromPx(36),SizeHelper.fromPx(36));
		searchIconParams.gravity = Gravity.CENTER_VERTICAL;
		searchIconParams.rightMargin = SizeHelper.fromPx(8);
		ImageView searchIcon = new ImageView(context);
		searchIcon.setLayoutParams(searchIconParams);
		res = R.getBitmapRes(context, "smssdk_search_icon");
		searchIcon.setImageResource(res);
		searchIcon.setScaleType(ScaleType.CENTER_INSIDE);
		innerSearchLayout.addView(searchIcon);

		LinearLayout.LayoutParams identifyParams = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1);
		identifyParams.gravity = Gravity.CENTER_VERTICAL;
		EditText identify = new EditText(context);
		identify.setLayoutParams(identifyParams);
		identify.setId(Res.id.et_put_identify);
		res = R.getStringRes(context, "smssdk_search");
		identify.setHint(res);
		identify.setTextColor(0xffffffff);
		identify.setBackgroundDrawable(null);
		identify.setSingleLine(true);
		innerSearchLayout.addView(identify);

		LinearLayout.LayoutParams clearParams = new LinearLayout.LayoutParams(SizeHelper.fromPx(30),SizeHelper.fromPx(30));
		clearParams.gravity = Gravity.CENTER_VERTICAL;
		clearParams.rightMargin = SizeHelper.fromPxWidth(5);
		ImageView clear = new ImageView(context);
		clear.setLayoutParams(clearParams);
		clear.setId(Res.id.iv_clear);
		res = R.getBitmapRes(context, "smssdk_clear_search");
		clear.setImageResource(res);
		clear.setScaleType(ScaleType.FIT_CENTER);
		innerSearchLayout.addView(clear);

		titleLayout.addView(inLayout);
	}
}
