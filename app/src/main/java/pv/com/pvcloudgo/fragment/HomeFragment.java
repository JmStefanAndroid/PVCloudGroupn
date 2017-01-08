package pv.com.pvcloudgo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.WareListActivity;
import pv.com.pvcloudgo.adapter.HomeCatgoryAdapter;
import pv.com.pvcloudgo.adapter.decoration.CardViewtemDecortion;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.bean.Banner;
import pv.com.pvcloudgo.bean.Campaign;
import pv.com.pvcloudgo.bean.HomeCampaign;
import pv.com.pvcloudgo.bean.Param;
import pv.com.pvcloudgo.http.BaseCallback;
import pv.com.pvcloudgo.http.SpotsCallBack;


public class HomeFragment extends BaseFragment {


    private static final String TAG = "HomeFragment";
    @Bind(R.id.slider)
    SliderLayout mSliderLayout;
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private HomeCatgoryAdapter mAdatper;
    private Gson mGson = new Gson();

    private List<Banner> mBanner;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void init() {

        requestImages();
        loadHomeData();

        initRecyclerView();
        initData(new ArrayList<>());
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


        mHttpHelper.get(Contants.API.CAMPAIGN_HOME, new BaseCallback<List<HomeCampaign>>() {
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


        mAdatper = new HomeCatgoryAdapter(homeCampaigns, getActivity());

        mAdatper.setOnCampaignClickListener(new HomeCatgoryAdapter.OnCampaignClickListener() {
            @Override
            public void onClick(View view, Campaign campaign) {


                Intent intent = new Intent(getActivity(), WareListActivity.class);
                intent.putExtra(Contants.COMPAINGAIN_ID, campaign.getId());

                startActivity(intent);


            }
        });

        mRecyclerView.setAdapter(mAdatper);

        mRecyclerView.addItemDecoration(new CardViewtemDecortion());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
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
        if (mSliderLayout != null)
            mSliderLayout.stopAutoCycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private void loadHomeData() {

        HashMap<String, Object> param = new Param();
        param.put("token", App.getInstance().getToken());
//        mHttpHelper.get(Contants.API.HOME, param, new SpotsCallBack<List<Banner>>(getActivity()) {
//
//
//            @Override
//            public void onSuccess(Response response, List<Banner> respMsg) {
//                if (respMsg != null && respMsg.getStatus().equals(BaseRespMsg.STATUS_SUCCESS)) {
//                } else {
//                    showNormalErr(respMsg);
//                }
//            }
//
//            @Override
//            public void onError(Response response, int code, Exception e) {
//                showFail();
//            }
//        });


    }
}
