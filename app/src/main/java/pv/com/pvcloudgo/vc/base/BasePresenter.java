package pv.com.pvcloudgo.vc.base;

import android.content.Context;

import javax.inject.Inject;

import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.http.OkHttpHelper;

/**
 * Created by Stefan on 2017/3/15.
 */

public class BasePresenter {

    @Inject
    protected OkHttpHelper mHttpHelper;

    protected  Context mContext;
    public BasePresenter(Context mContext) {
        App.component().inject(this);
        this.mContext=mContext;

    }
}
