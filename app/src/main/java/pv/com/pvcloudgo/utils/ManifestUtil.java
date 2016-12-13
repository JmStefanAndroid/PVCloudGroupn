package pv.com.pvcloudgo.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * ProjectName:YayaShop
 * Autor： <a href="http://www.cniao5.com">菜鸟窝</a>
 * Description：
 * <p/>
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 */
public class ManifestUtil {


    public static String getMetaDataValue(Context context,String name, String def) {

        String value = getMetaDataValue(context,name);
        return (value == null) ? def : value;

    }

    public static String getMetaDataValue(Context context, String name) {

        Object value = null;

        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;

        try {

            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                value = applicationInfo.metaData.get(name);
            }

        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not read the name in the manifest file.", e);
        }

        if (value == null) {
            throw new RuntimeException("The name '" + name+ "' is not defined in the manifest file's meta data.");
        }

        return value.toString();

    }
}
