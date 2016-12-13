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
import android.graphics.Typeface;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

/**验证码输入页面布局*/
public class IdentifyNumPageLayout extends BasePageLayout {

	public IdentifyNumPageLayout(Context c) {
		super(c,false);
	}

	protected void onCreateContent(LinearLayout parent) {
		LinearLayout wrapperLayout =  new LinearLayout(context);
		LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		wrapperLayout.setLayoutParams(wrapperParams);
		wrapperLayout.setBackgroundColor(0xffffffff);
		wrapperLayout.setOrientation(LinearLayout.VERTICAL);
		wrapperLayout.setPadding(SizeHelper.fromPxWidth(26), 0, SizeHelper.fromPxWidth(26), 0);
		parent.addView(wrapperLayout);

		TextView identifyNotify = new TextView(context);
		identifyNotify.setId(Res.id.tv_identify_notify);
		LinearLayout.LayoutParams identifyNotifyParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		identifyNotifyParams.topMargin = SizeHelper.fromPxWidth(32);
		identifyNotify.setGravity(Gravity.CENTER);
		identifyNotify.setLayoutParams(identifyNotifyParams);
		int resid = R.getStringRes(context, "smssdk_make_sure_mobile_detail");
		identifyNotify.setText(resid);
		identifyNotify.setTextColor(0xff999999);
		identifyNotify.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(24));
		wrapperLayout.addView(identifyNotify);

		TextView phone = new TextView(context);
		phone.setId(Res.id.tv_phone);
		LinearLayout.LayoutParams phoneParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		phoneParams.topMargin = SizeHelper.fromPxWidth(50);
		phone.setGravity(Gravity.CENTER);
		phone.setLayoutParams(identifyNotifyParams);
		phone.setTextColor(0xff353535);
		phone.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(26));
		phone.setTypeface(Typeface.DEFAULT_BOLD);
		wrapperLayout.addView(phone);

		RelativeLayout inputBg = new RelativeLayout(context);
		LinearLayout.LayoutParams inputBgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(72));
		inputBgParams.topMargin = SizeHelper.fromPxWidth(38);
		inputBg.setLayoutParams(inputBgParams);
		resid = R.getBitmapRes(context, "smssdk_input_bg_focus");
		inputBg.setBackgroundResource(resid);
		wrapperLayout.addView(inputBg);

		RelativeLayout.LayoutParams putIdentifyParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
		putIdentifyParams.leftMargin = SizeHelper.fromPxWidth(18);
		EditText putIdentify = new EditText(context);
		putIdentify.setLayoutParams(putIdentifyParams);
		putIdentify.setId(Res.id.et_put_identify);
		resid = R.getStringRes(context, "smssdk_write_identify_code");
		putIdentify.setHint(resid);
		putIdentify.setBackgroundColor(0xffffffff);
		putIdentify.setSingleLine(true);
		putIdentify.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(24));
		putIdentify.setInputType(InputType.TYPE_CLASS_NUMBER);
		inputBg.addView(putIdentify);

		RelativeLayout.LayoutParams clearImageParams = new RelativeLayout.LayoutParams(SizeHelper.fromPxWidth(34),SizeHelper.fromPxWidth(34));
		clearImageParams.addRule(RelativeLayout.CENTER_VERTICAL);
		clearImageParams.addRule(RelativeLayout.ALIGN_RIGHT, Res.id.et_put_identify);
		ImageView clearImage = new ImageView(context);
		clearImage.setLayoutParams(clearImageParams);
		clearImage.setId(Res.id.iv_clear);
		resid = R.getBitmapRes(context, "smssdk_clear_search");
		clearImage.setImageResource(resid);
		clearImage.setScaleType(ScaleType.FIT_CENTER);
		clearImage.setVisibility(View.GONE);
		inputBg.addView(clearImage);

		//add sound button
		Button soundBtn = new Button(context);
		soundBtn.setId(Res.id.btn_sounds);
		RelativeLayout.LayoutParams soundParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,SizeHelper.fromPxWidth(54));
		soundParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		soundParams.addRule(RelativeLayout.ALIGN_RIGHT, Res.id.et_put_identify);
		soundBtn.setLayoutParams(soundParams);
		resid = R.getBitmapRes(context, "smssdk_btn_disenable");
		soundBtn.setBackgroundResource(resid);
		resid = R.getStringRes(context, "smssdk_send_sounds");
		soundBtn.setText(resid);
		soundBtn.setTextColor(Res.color.smssdk_white);
		soundBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(20));
		int padding = SizeHelper.fromPxWidth(18);
		soundBtn.setPadding(padding, 0, padding, 0);
		soundBtn.setVisibility(View.GONE);
		inputBg.addView(soundBtn);

		TextView unreceive = new TextView(context);
		unreceive.setId(Res.id.tv_unreceive_identify);
		LinearLayout.LayoutParams unreceiveParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		unreceiveParams.setMargins(0, SizeHelper.fromPxWidth(34), 0, SizeHelper.fromPxWidth(30));
		unreceive.setLayoutParams(unreceiveParams);
		unreceive.setGravity(Gravity.CENTER);
		resid = R.getStringRes(context, "smssdk_receive_msg");
		unreceive.setText(resid);
		unreceive.setTextColor(0xff5c5c5c);
		unreceive.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(22));
		wrapperLayout.addView(unreceive);

		//add submit button
		Button submitBtn = new Button(context);
		submitBtn.setId(Res.id.btn_submit);
		LinearLayout.LayoutParams submitParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,SizeHelper.fromPxWidth(72));
		submitBtn.setLayoutParams(submitParams);
		resid = R.getBitmapRes(context, "smssdk_btn_disenable");
		submitBtn.setBackgroundResource(resid);
		resid = R.getStringRes(context, "smssdk_next");
		submitBtn.setText(resid);
		submitBtn.setTextColor(0xffffffff);
		submitBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(24));
		submitBtn.setPadding(SizeHelper.fromPxWidth(10), 0, SizeHelper.fromPxWidth(10), 0);
		wrapperLayout.addView(submitBtn);

	}

}
