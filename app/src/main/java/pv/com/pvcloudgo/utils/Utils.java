package pv.com.pvcloudgo.utils;


import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {

    //弹出键盘
    public static void showInputMethod(Context context , View view )
    {
        InputMethodManager imm = ( InputMethodManager ) context
                .getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm != null )
        {
            imm.showSoftInput( view , 0 );
        }
    }

    /** dip转px */
    public static int dipToPx(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;

        return (int) (dip * density + 0.5f);
    }

}
