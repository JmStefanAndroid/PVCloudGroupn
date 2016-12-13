package pv.com.pvcloudgo.dagger;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pv.com.pvcloudgo.app.App;

/**
 * Created by stefan on 16/12/3.
 */
@Module
public class AppModule {

    private final App mApp;

    public AppModule(App application) {
        mApp = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return mApp;
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return mApp.getResources();
    }

}
