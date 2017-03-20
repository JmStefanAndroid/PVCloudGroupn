package pv.com.pvcloudgo.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import pv.com.pvcloudgo.model.bean.User;
import pv.com.pvcloudgo.dagger.AppComponent;
import pv.com.pvcloudgo.dagger.DaggerAppComponent;
import pv.com.pvcloudgo.utils.ToastUtils;
import pv.com.pvcloudgo.utils.UserLocalData;

public class App extends Application {

    private  final String TAG = this.getClass().getSimpleName();
    private static AppComponent appComponent;
    private User user;

    private static App mInstance;


    public static App getInstance() {

        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        buildComponentAndInject();


        initUser();

        ToastUtils.initialize(this);
        Fresco.initialize(this);

    }

    public static AppComponent component() {
        return appComponent;
    }

    public static void buildComponentAndInject() {
        appComponent = DaggerAppComponent.Initializer.init(mInstance);
    }


    private void initUser() {

        this.user = UserLocalData.getUser(this);
        if (this.user != null) Log.e(TAG, user.toString());
    }


    public User getUser() {

        return user;
    }


    public void putUser(User user, String token) {
        this.user = user;
        UserLocalData.putUser(this, user);
        UserLocalData.putToken(this, token);
    }

    public void clearUser() {
        this.user = null;
        UserLocalData.clearUser(this);
        UserLocalData.clearToken(this);


    }


    public String getToken() {

        return UserLocalData.getToken(this);
    }


    private Intent intent;

    public void putIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void jumpToTargetActivity(Context context) {

        context.startActivity(intent);
        this.intent = null;
    }

}
