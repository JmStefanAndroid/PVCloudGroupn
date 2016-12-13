package pv.com.pvcloudgo.dagger;

import javax.inject.Singleton;

import dagger.Component;
import pv.com.pvcloudgo.app.App;

/**
 * Created by stefan on 16/12/3.
 */
@Singleton
@Component(modules = {AppModule.class,ApiModule.class})
public interface AppComponent extends Graphi{


    final class Initializer {
        private Initializer() {
        } // No instances.

        // 初始化组件
        public static AppComponent init(App app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app)).apiModule(new ApiModule())
                    .build();
        }
    }
}
