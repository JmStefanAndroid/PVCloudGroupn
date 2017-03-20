package pv.com.pvcloudgo.vc.view.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import pv.com.pvcloudgo.utils.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.model.bean.Banner;
import pv.com.pvcloudgo.model.bean.HomeCampaign;
import pv.com.pvcloudgo.vc.view.ui.fragment.dummy.TabEntity;
import pv.com.pvcloudgo.http.BaseCallback;
import pv.com.pvcloudgo.http.SpotsCallBack;

/**
 * 云商圈
 */
public class CloudCirFragment extends BaseFragment {


    @Bind(R.id.slider)
    SliderLayout mSliderLayout;
    @Bind(R.id.common_layout)
    CommonTabLayout mCommonTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;


    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"关注", "推荐", "热门"};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private static final String TAG = "HomeFragment";



    private List<Banner> mBanner;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cloudcir, container, false);
        return view;
    }

    @Override
    public void init() {

        requestImages();

        initRecyclerView();

        initTab();
    }

    private void initTab() {

        for (String title : mTitles) {
            mFragments.add(new ShopFragment());
        }

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        mViewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));

        tl_2();

    }


    private void requestImages() {

        String url = "http://112.124.22.238:8081/course_api/banner/query?type=1";
        mHttpHelper.get(url, new SpotsCallBack<List<Banner>>(getActivity()) {


            @Override
            public void onSuccess(Response response, List<Banner> banners) {

                mBanner = banners;
                initSlider();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }
        });


    }


    private void initRecyclerView() {


        mHttpHelper.get(Contants.API.CAMPAIGN_HOME, new BaseCallback<List<HomeCampaign>>(getActivity()) {
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
            public void onSuccess(Response response, List<HomeCampaign> homeCampaigns) {

                initData(homeCampaigns);
            }


            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }

            @Override
            public void onTokenError(Response response, int code) {

            }
        });

    }


    private void initData(List<HomeCampaign> homeCampaigns) {



    }


    private void initSlider() {


        if (mBanner != null) {

            for (Banner banner : mBanner) {


                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(banner.getImgUrl());
                textSliderView.description(banner.getName());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(textSliderView);

            }
        }


        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(3000);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mSliderLayout!=null)
        mSliderLayout.stopAutoCycle();
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

    protected int dp2px(float dp) {
        final float scale = getActivity().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
