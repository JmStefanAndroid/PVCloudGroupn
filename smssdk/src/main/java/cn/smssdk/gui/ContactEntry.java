/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2014年 mob.com. All rights reserved.
 */
package cn.smssdk.gui;

import java.util.HashMap;
import java.util.Map.Entry;

public class ContactEntry implements Entry<String, HashMap<String, Object>> {
	private String key;
	private HashMap<String, Object> value;

	public ContactEntry(String key, HashMap<String, Object> value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public HashMap<String, Object> getValue() {
		return value;
	}

	public HashMap<String, Object> setValue(HashMap<String, Object> object) {
		value = object;
		return value;
	}

	public String toString() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return map.toString();
	}

}
