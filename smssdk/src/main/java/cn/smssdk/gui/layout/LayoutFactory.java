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

/**布局工厂*/
public class LayoutFactory
{

	static ViewGroup create(Context context,String name) {
		ViewGroup v = null;
		if(name.equals("smssdk_back_verify_dialog"))
		{
			v = BackVerifyDialogLayout.create(context);
		}
		else if(name.equals("smssdk_contact_detail_page"))
		{
			ContactDetailPageLayout page = new ContactDetailPageLayout(context);
			v = page.getLayout();
		}
		else if(name.equals("smssdk_contact_list_page"))
		{
			ContactListPageLayout page = new ContactListPageLayout(context);
			v = page.getLayout();
		}
		else if(name.equals("smssdk_contacts_listview_item"))
		{
			v = ContactsListviewItemLayout.create(context);
		}
		else if(name.equals("smssdk_country_list_page"))
		{
			CountryListPageLayout page = new CountryListPageLayout(context);
			v = page.getLayout();
		}
		else if(name.equals("smssdk_input_identify_num_page"))
		{
			IdentifyNumPageLayout page = new IdentifyNumPageLayout(context);
			v = page.getLayout();
		}
		else if(name.equals("smssdk_progress_dialog"))
		{
			v = ProgressDialogLayout.create(context);
		}
		else if(name.equals("smssdk_register_page"))
		{
			RegisterPageLayout layout = new RegisterPageLayout(context);
			v = layout.getLayout();
		}
		else if(name.equals("smssdk_send_msg_dialog"))
		{
			v = SendMsgDialogLayout.create(context);
		}

		return v;
	}

}
