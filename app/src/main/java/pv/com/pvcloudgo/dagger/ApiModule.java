package pv.com.pvcloudgo.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pv.com.pvcloudgo.http.OkHttpHelper;

/**
 * Created by stefan on 16/12/3.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    protected  OkHttpHelper provideOkHttpHelper() {
        return new OkHttpHelper();
    }

}
