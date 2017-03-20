package pv.com.pvcloudgo.vc.view.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.okhttp.Response;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import pv.com.pvcloudgo.utils.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.model.bean.Param;
import pv.com.pvcloudgo.model.bean.User;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.model.base.BaseRespMsg;
import pv.com.pvcloudgo.model.msg.MineMsg;
import pv.com.pvcloudgo.vc.view.ui.activity.mine.OrderActivity;
import pv.com.pvcloudgo.vc.view.ui.activity.mine.PersonalInfoActivity;
import pv.com.pvcloudgo.vc.view.ui.activity.mine.SettingActivity;
import pv.com.pvcloudgo.utils.ToastUtils;
import pv.com.pvcloudgo.utils.Utils;
import pv.com.pvcloudgo.vc.widget.pulldownview.PullToZoomScrollViewEx;


public class MineFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.pull_scroll)
    PullToZoomScrollViewEx pullScrollView;
    TextView mineFunAllOrder;
    TextView mineFunWaitPay;
    TextView mineFunWaitReceive;
    TextView mineFunPayback;
    TextView mineFunEvaluate;
    TextView mTxtUserName;
    TextView attenCountTv;
    TextView fansTv;
    TextView salecardTv;
    TextView bankNoTv;
    TextView frozenMoneyTv;
    TextView availableMoneyTv;
    CircleImageView imgHead;
    LinearLayout mTBoardBottom;
    LinearLayout contentContainer;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }


    @Override
    public void init() {


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
        initView();
        showUser();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_fun_all_order:
            case R.id.mine_fun_evaluate:
            case R.id.mine_fun_payback:
            case R.id.mine_fun_wait_pay:
            case R.id.mine_fun_wait_receive:
                next(OrderActivity.class);
                break;
            case R.id.mine_tt_settings_iv:
                next(SettingActivity.class);
                break;
            case R.id.txt_username:
            case R.id.img_head:
                next(PersonalInfoActivity.class);
                break;
        }
    }


    private void showUser() {

        User user = App.getInstance().getUser();
        if (user == null) {
            mTxtUserName.setText(R.string.to_login);
        } else {
            mTxtUserName.setText(user.getNicheng());

            loadMineData();
        }

    }


    public void next(Class cls) {


        Intent intent = new Intent(getActivity(), cls);

        startActivityForResult(intent, true, Contants.REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Contants.REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            showUser();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initView() {
        mineFunAllOrder = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_fun_all_order);
        mineFunWaitPay = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_fun_wait_pay);
        mineFunWaitReceive = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_fun_wait_receive);
        mineFunPayback = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_fun_payback);
        mineFunEvaluate = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_fun_evaluate);
        mTBoardBottom = (LinearLayout) pullScrollView.getPullRootView().findViewById(R.id.m_t_board_bottom);
        contentContainer = (LinearLayout) pullScrollView.getPullRootView().findViewById(R.id.content_container);
        mTxtUserName = (TextView) pullScrollView.getPullRootView().findViewById(R.id.txt_username);

        attenCountTv = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_atten_shop_tv);
        fansTv = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_fansno_tv);
        salecardTv = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_quan_tv);
        bankNoTv = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_hb_bankno_tv);
        availableMoneyTv = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_hb_availablemoney_tv);
        frozenMoneyTv = (TextView) pullScrollView.getPullRootView().findViewById(R.id.mine_hb_frozenmoney_tv);

        imgHead = (CircleImageView) pullScrollView.getPullRootView().findViewById(R.id.img_head);

        mineFunAllOrder.setOnClickListener(this);
        mineFunWaitPay.setOnClickListener(this);
        mineFunWaitReceive.setOnClickListener(this);
        mineFunPayback.setOnClickListener(this);
        mineFunEvaluate.setOnClickListener(this);
        mTxtUserName.setOnClickListener(this);
        imgHead.setOnClickListener(this);
        pullScrollView.getPullRootView().findViewById(R.id.mine_tt_settings_iv).setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void loadMineData() {

        HashMap<String, Object> param = new Param();
        param.put("token", App.getInstance().getToken());
        mHttpHelper.get(Contants.API.MINE, param, new SpotsCallBack<MineMsg>(getActivity()) {


            @Override
            public void onSuccess(Response response, MineMsg respMsg) {
                if (respMsg != null && respMsg.getStatus().equals(BaseRespMsg.STATUS_SUCCESS)) {
                    MineMsg.Result result = respMsg.getResult();
                    Utils.bindStrText(attenCountTv, result.getSccount() + "");
                    Utils.bindStrText(fansTv, result.getAllFensiCount() + "");
                    Utils.bindStrText(salecardTv, result.getYhqwaituse() + "");
                    Utils.bindStrText(bankNoTv, result.getBrankcount() + "");
                    Utils.bindStrText(availableMoneyTv, result.getAccount().getAvailablePrice() + "");
                    Utils.bindStrText(frozenMoneyTv, result.getAccount().getFrozenPrice() + "");
                } else {
                    showNormalErr(respMsg);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                showFail();
            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {
                ToastUtils.show(errmsg);
            }
        });


    }
}
