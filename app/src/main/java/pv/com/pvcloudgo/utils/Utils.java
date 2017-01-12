package pv.com.pvcloudgo.utils;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import pv.com.pvcloudgo.LoginActivity;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.bean.User;

public class Utils {

    //弹出键盘
    public static void showInputMethod(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * dip转px
     */
    public static int dipToPx(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;

        return (int) (dip * density + 0.5f);
    }

    public static void bindStrText(TextView textView, String str) {
        if (textView == null || str == null) return;
        textView.setText(str);
    }

    public static boolean isLogin() {
        User user = App.getInstance().getUser();

        if (user != null)
            return true;
        else return false;
    }
    public static boolean login() {
        User user = App.getInstance().getUser();

        if (user != null)
            return true;
        else {
            Intent loginIntent = new Intent(App.getInstance(), LoginActivity.class);
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.getInstance().startActivity(loginIntent);

            return false;
        }
    }


}
