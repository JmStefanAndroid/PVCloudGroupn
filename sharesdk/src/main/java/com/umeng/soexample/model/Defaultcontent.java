package com.umeng.soexample.model;

import android.os.Environment;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;

/**
 * Created by wangfei on 16/7/12.
 */
public class Defaultcontent {
   public static String url = "http://t.cn/RVsZt1Z";
    public static String text = "友盟微社区sdk，多终端一社区，为您的app添加社区就是这么简单";
    public static String title = "友盟微社区";
    public static String imageurl = "http://dev.umeng.com/images/tab2_1.png";
    public static String videourl = "http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html";
    public static String musicurl = "http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3";
 public static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
}
