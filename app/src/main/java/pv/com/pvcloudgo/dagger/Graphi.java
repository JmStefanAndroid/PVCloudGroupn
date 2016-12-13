package pv.com.pvcloudgo.dagger;

import pv.com.pvcloudgo.BaseActivity;
import pv.com.pvcloudgo.fragment.BaseFragment;
import pv.com.pvcloudgo.utils.Pager;

/**
 * Dagger2的图接口
 * <p/>
 * Created by stefan on 16/11/2.
 */
public interface Graphi {

    void inject(BaseActivity mainActivity); // 注入BaseActivity

    void inject(BaseFragment baseFragment); // 注入BaseFragment

    void inject(Pager pager); // 注入BaseFragment
}