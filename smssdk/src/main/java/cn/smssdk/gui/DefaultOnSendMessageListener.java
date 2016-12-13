package cn.smssdk.gui;

import cn.smssdk.OnSendMessageHandler;

public class DefaultOnSendMessageListener implements OnSendMessageHandler{

	public boolean onSendMessage(String country, String phone) {
		return false;
	}

}
