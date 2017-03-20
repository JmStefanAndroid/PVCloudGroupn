package pv.com.pvcloudgo.vc.view.ui.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Response;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.vc.base.BaseActivity;
import pv.com.pvcloudgo.utils.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.vc.base.BaseAdapter;
import pv.com.pvcloudgo.vc.adapter.ChargeItemAdapter;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.model.bean.ChargeRule;
import pv.com.pvcloudgo.model.bean.Param;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.model.msg.LoadChargeMsg;
import pv.com.pvcloudgo.utils.ToastUtils;

/**
 * Created by stefan on 16/12/13.
 */

public class PhoneChargeActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_left_logo)
    ImageView toolbarLeftLogo;
    @Bind(R.id.toolbar_logo)
    ImageView toolbarLogo;
    @Bind(R.id.toolbar_left_title)
    TextView toolbarLeftTitle;
    @Bind(R.id.toolbar_right_title)
    TextView toolbarRightTitle;
    @Bind(R.id.image_right)
    ImageView imageRight;
    @Bind(R.id.image_exit)
    ImageView imageExit;
    @Bind(R.id.ac_p_c_phone_edt)
    EditText mPhoneEdt;
    @Bind(R.id.ac_c_p_recyclerview)
    RecyclerView mRecyclerview;

    private ChargeItemAdapter mChargeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_charge);
        ButterKnife.bind(this);


        initToolBar();
        init();
        load();
    }

    private void init() {

        mPhoneEdt.setText(App.getInstance().getUser().getTelPhone());





    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setupToolbar(toolbar, true);

        toolbarTitle.setText("手机充值");
        toolbarRightTitle.setText("充值记录");
        toolbarRightTitle.setTextColor(Color.BLACK);
        toolbarRightTitle.setVisibility(View.VISIBLE);
        toolbarRightTitle.setOnClickListener(v -> startActivity(new Intent(mContext, PhoneChargeRecordActivity.class)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    public void load() {
        HashMap params = new Param();
        mHttpHelper.get(Contants.API.TELCHONGZHI, params, new SpotsCallBack<LoadChargeMsg>(mContext) {
            @Override
            public void onSuccess(Response response, LoadChargeMsg resp) {
                if (resp != null&&resp.getResults()!=null&&resp.getResults().getRuleList()!=null&&
                        resp.getResults().getRuleList().size()!=0) {

                    if (mChargeAdapter == null) {
                        mChargeAdapter = new ChargeItemAdapter(mContext, resp.getResults().getRuleList());
                        mChargeAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                ChargeRule rule = mChargeAdapter.getItem(position);


                            }
                        });

                        mRecyclerview.setAdapter(mChargeAdapter);

                        mRecyclerview.setLayoutManager(new GridLayoutManager(mContext, 3,GridLayoutManager.VERTICAL,false));
                        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
//                    mRecyclerviewWares.addItemDecoration(new DividerGridItemDecoration(getContext()));
                    } else {
                        mChargeAdapter.clear();
                        mChargeAdapter.addData(resp.getResults().getRuleList());
                    }
                }else ToastUtils.show("暂无数据");

            }

            @Override
            public void onError(Response response, int code, Exception e) {
                ToastUtils.show("获取数据失败");
            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }
        });


    }
}
