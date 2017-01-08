package pv.com.pvcloudgo.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.BaseActivity;
import pv.com.pvcloudgo.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.adapter.ChargeRecordAdapter;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.bean.User;
import pv.com.pvcloudgo.fragment.dummy.DummyContent;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.msg.LoginRespMsg;
import pv.com.pvcloudgo.utils.DESUtil;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by stefan on 16/12/13.
 */

public class PhoneChargeRecordActivity extends BaseActivity {
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
    @Bind(R.id.ac_p_charge_record_recycler)
    XRecyclerView mXRecylcer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_charge_record);
        ButterKnife.bind(this);


        initToolBar();

        initRecylcer();
    }

    private void initRecylcer() {
        mXRecylcer.setLayoutManager(new LinearLayoutManager(context));
        mXRecylcer.setAdapter(new ChargeRecordAdapter(DummyContent.ITEMS, position -> {

        }));
        mXRecylcer.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXRecylcer.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                // load more data here
                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXRecylcer.loadMoreComplete();
                    }
                },3000);
            }
        });
        mXRecylcer.setRefreshProgressStyle(ProgressStyle.BallBeat);
        mXRecylcer.setLoadingMoreProgressStyle(ProgressStyle.BallPulseSync);
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setupToolbar(toolbar, true);

        toolbarTitle.setText("充值记录");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
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


}
