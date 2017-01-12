package pv.com.pvcloudgo.http;

import android.content.Context;
import android.content.Intent;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.LoginActivity;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.utils.ToastUtils;


/**
 */
public abstract class SimpleCallback<T> extends BaseCallback<T> {


    public SimpleCallback(Context context) {
        super(context);

    }

    @Override
    public void onBeforeRequest(Request request) {

    }

    @Override
    public void onFailure(Request request, Exception e) {

    }

    @Override
    public void onResponse(Response response) {

    }

    @Override
    public void onTokenError(Response response, int code) {
        ToastUtils.show(mContext, mContext.getString(R.string.token_error));

        Intent intent = new Intent();
        intent.setClass(mContext, LoginActivity.class);
        mContext.startActivity(intent);

        App.getInstance().clearUser();

    }


}
