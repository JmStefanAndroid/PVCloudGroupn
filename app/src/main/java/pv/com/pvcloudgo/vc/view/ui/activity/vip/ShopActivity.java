package pv.com.pvcloudgo.vc.view.ui.activity.vip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.vc.base.BaseActivity;
import pv.com.pvcloudgo.utils.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.model.bean.User;
import pv.com.pvcloudgo.vc.view.ui.fragment.ComboSelectedFragment;
import pv.com.pvcloudgo.vc.view.ui.fragment.dummy.TabEntity;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.model.msg.LoginRespMsg;
import pv.com.pvcloudgo.utils.DESUtil;


public class ShopActivity extends BaseActivity {


    @Bind(R.id.common_tab_layout)
    CommonTabLayout mCommonTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;


    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"店铺首页", "全部商品","优惠券","限时促销"};
    private int[] mIconUnselectIds = {
            R.drawable.ic_v_shop,  R.drawable.ic_v_mall,
            R.drawable.ic_v_quan, R.drawable.ic_saling};
    private int[] mIconSelectIds = {
            R.drawable.ic_v_shop_p, R.drawable.ic_v_mall_p,
            R.drawable.ic_v_quan_p, R.drawable.ic_v_clock_p};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);

        initTab();

    }


    public void login(View view) {


        String phone = null;

        String pwd = null;


        Map<String, Object> params = new HashMap<>(2);
        params.put("phone", phone);
        params.put("password", DESUtil.encode(Contants.DES_KEY, pwd));

        mHttpHelper.post(Contants.API.LOGIN, params, new SpotsCallBack<LoginRespMsg<User>>(this) {


            @Override
            public void onSuccess(Response response, LoginRespMsg<User> userLoginRespMsg) {


                App application = App.getInstance();
                application.putUser(userLoginRespMsg.getData(), userLoginRespMsg.getToken());

                if (application.getIntent() == null) {
                    setResult(RESULT_OK);
                    finish();
                } else {

                    application.jumpToTargetActivity(mContext);
                    finish();

                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }
        });


    }

    private void initTab() {

        mFragments.add(new ComboSelectedFragment());
        mFragments.add(new ComboSelectedFragment());
        mFragments.add(new ComboSelectedFragment());
        mFragments.add(new ComboSelectedFragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tl_2();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    private void tl_2() {
        mCommonTabLayout.setTabData(mTabEntities);
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCommonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
