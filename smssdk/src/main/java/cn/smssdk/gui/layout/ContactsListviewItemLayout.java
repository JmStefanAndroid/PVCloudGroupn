/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2014年 mob.com. All rights reserved.
 */
package cn.smssdk.gui.layout;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.utils.R;

/**联系人列表表项布局*/
public class ContactsListviewItemLayout {

	public static RelativeLayout create(Context context) {
		SizeHelper.prepare(context);

		RelativeLayout root = new RelativeLayout(context);
		root.setId(Res.id.rl_lv_item_bg);
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, SizeHelper.fromPxWidth(95));
		root.setLayoutParams(params);
		int padding = SizeHelper.fromPxWidth(14);
		root.setPadding(padding, padding, padding, padding);
		root.setGravity(Gravity.CENTER_VERTICAL);
		root.setBackgroundColor(0xffffffff);

		AsyncImageView contactImage = new AsyncImageView(context);
		contactImage.setId(Res.id.iv_contact);
		RelativeLayout.LayoutParams contactImageParams = new RelativeLayout.LayoutParams(SizeHelper.fromPxWidth(64),SizeHelper.fromPxWidth(64));
		contactImageParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		contactImage.setLayoutParams(contactImageParams);
		contactImage.setScaleType(ScaleType.FIT_CENTER);
		root.addView(contactImage);

		LinearLayout wrapper = new LinearLayout(context);
		RelativeLayout.LayoutParams wrapperParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		wrapperParams.addRule(RelativeLayout.RIGHT_OF, Res.id.iv_contact);
		wrapperParams.addRule(RelativeLayout.CENTER_VERTICAL);
		wrapperParams.leftMargin = SizeHelper.fromPxWidth(12);
		wrapper.setLayoutParams(wrapperParams);
		wrapper.setOrientation(LinearLayout.VERTICAL);
		root.addView(wrapper);

		TextView name = new TextView(context);
		name.setId(Res.id.tv_name);
		LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		name.setLayoutParams(nameParams);
		name.setTextColor(0xff333333);
		name.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(28));
		wrapper.addView(name);

		TextView contact = new TextView(context);
		contact.setId(Res.id.tv_contact);
		LinearLayout.LayoutParams contactParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		contact.setLayoutParams(contactParams);
		contact.setTextColor(0xff999999);
		contact.setTextSize(TypedValue.COMPLEX_UNIT_PX,SizeHelper.fromPxWidth(22));
		wrapper.addView(contact);

		Button add = new Button(context);
		add.setId(Res.id.btn_add);
		RelativeLayout.LayoutParams addParams = new RelativeLayout.LayoutParams(SizeHelper.fromPxWidth(92),SizeHelper.fromPxWidth(46));
		addParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		addParams.addRule(RelativeLayout.CENTER_VERTICAL);
		add.setLayoutParams(addParams);
		int resid = R.getStringRes(context, "smssdk_add_contact");
		add.setText(resid);
		add.setTextSize(TypedValue.COMPLEX_UNIT_PX, SizeHelper.fromPxWidth(22));
		add.setTextColor(0xff797979);
		//resid = R.getBitmapRes(context, "smssdk_corners_bg");
		add.setBackgroundDrawable(DrawableHelper.createCornerBg(context));
		add.setPadding(0, 0, 0, 0);
		root.addView(add);

		return root;
	}
}
