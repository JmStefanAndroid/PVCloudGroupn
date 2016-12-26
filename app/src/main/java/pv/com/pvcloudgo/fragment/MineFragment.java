package pv.com.pvcloudgo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pv.com.pvcloudgo.Contants;
import pv.com.pvcloudgo.LoginActivity;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.bean.User;
import pv.com.pvcloudgo.ui.mine.OrderActivity;
import pv.com.pvcloudgo.widget.pulldownview.PullToZoomScrollViewEx;


public class MineFragment extends BaseFragment {

    @Bind(R.id.pull_scroll)
    PullToZoomScrollViewEx pullScrollView;
    @Bind(R.id.mine_fun_all_order)
    TextView mineFunAllOrder;
    @Bind(R.id.mine_fun_wait_pay)
    TextView mineFunWaitPay;
    @Bind(R.id.mine_fun_wait_receive)
    TextView mineFunWaitReceive;
    @Bind(R.id.mine_fun_payback)
    TextView mineFunPayback;
    @Bind(R.id.mine_fun_evaluate)
    TextView mineFunEvaluate;
    @Bind(R.id.m_t_board_bottom)
    LinearLayout mTBoardBottom;
    @Bind(R.id.content_container)
    LinearLayout contentContainer;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void init() {

        showUser();
//        scrollView.getPullRootView().findViewById(R.id.tv_test1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 14.0F)));
        pullScrollView.setHeaderLayoutParams(localObject);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.mine_tt, null, false);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_content_view, null, false);
        pullScrollView.setHeaderView(headView);
        pullScrollView.setZoomView(zoomView);
        pullScrollView.setScrollContentView(contentView);
        pullScrollView.setParallax(true);
//        pullScrollView.setOnTurnListener(new PullScrollView.OnTurnListener() {
//            @Override
//            public void onTurn() {
//
//            }
//        });
//        pullScrollView.init(poster);
        bindListener();
    }

    private void bindListener() {
    }

    @OnClick({R.id.mine_fun_all_order, R.id.mine_fun_evaluate, R.id.mine_fun_payback, R.id.mine_fun_wait_pay, R.id.mine_fun_wait_receive})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_fun_all_order:
            case R.id.mine_fun_evaluate:
            case R.id.mine_fun_payback:
            case R.id.mine_fun_wait_pay:
            case R.id.mine_fun_wait_receive:
                startActivity(new Intent(getActivity(), OrderActivity.class));
                break;
        }
    }


    private void showUser() {

        User user = App.getInstance().getUser();
        if (user == null) {
//            mbtnLogout.setVisibility(View.GONE);
//            mTxtUserName.setText(R.string.to_login);

        } else {

//            mbtnLogout.setVisibility(View.VISIBLE);
//            if (!TextUtils.isEmpty(user.getLogo_url()))
//                Picasso.with(getActivity()).load(Uri.parse(user.getLogo_url())).into(mImageHead);

//            mTxtUserName.setText(user.getUsername());

        }

    }


    @OnClick({R.id.img_head, R.id.txt_username})
    public void toLoginActivity(View view) {


        Intent intent = new Intent(getActivity(), LoginActivity.class);

        startActivityForResult(intent, Contants.REQUEST_CODE);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
