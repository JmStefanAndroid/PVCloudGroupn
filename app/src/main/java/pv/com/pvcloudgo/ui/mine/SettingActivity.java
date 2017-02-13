package pv.com.pvcloudgo.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.BaseActivity;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.utils.ToastUtils;


public class SettingActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.person_ll)
    LinearLayout personLl;
    @Bind(R.id.logout_btn)
    Button logoutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);


        initToolBar();

        personLl.setOnClickListener(v -> startActivity(new Intent(mContext, PersonalInfoActivity.class)));
        logoutBtn.setOnClickListener(v -> logout(v));
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setupToolbar(toolbar, true);

        toolbarTitle.setText("设置");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    public void logout(View view) {
/**
 *
 * TODO 服务器已关闭，退出改为本地退出方式
 */
        App application = App.getInstance();
        application.clearUser();
        finish();
        System.exit(0);
        ToastUtils.show("注销成功！");

//        Map<String, Object> params = new HashMap<>(2);
//        params.put("token", App.getInstance().getToken());
//
//        mHttpHelper.post(Contants.API.LOGOUT, params, new SpotsCallBack<LoginRespMsg<User>>(this) {
//
//
//            @Override
//            public void onSuccess(Response response, LoginRespMsg<User> userLoginRespMsg) {
//                if (userLoginRespMsg != null && userLoginRespMsg.getStatus().equals(BaseRespMsg.STATUS_SUCCESS)) {
//                    App application = App.getInstance();
//                    application.clearUser();
//                    finish();
//                    System.exit(0);
//                    ToastUtils.show("注销成功！");
//                }else{
//                    ToastUtils.show("注销失败！");
//                }
//
//                }
//
//                @Override
//                public void onError (Response response,int code, Exception e){
//
//                }
//
//                @Override
//                public void onServerError (Response response,int code, String errmsg){
//
//                }
//            }
//
//            );
//
//
        }

    }
