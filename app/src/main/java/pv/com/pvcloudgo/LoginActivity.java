package pv.com.pvcloudgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Response;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.bean.Param;
import pv.com.pvcloudgo.bean.User;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.msg.BaseRespMsg;
import pv.com.pvcloudgo.msg.LoginResp;
import pv.com.pvcloudgo.utils.ToastUtils;
import pv.com.pvcloudgo.widget.ClearEditText;


public class LoginActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
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
    @Bind(R.id.etxt_phone)
    ClearEditText mEtxtPhone;
    @Bind(R.id.etxt_pwd)
    ClearEditText mEtxtPwd;
    @Bind(R.id.txt_toReg)
    TextView txtToReg;
    @Bind(R.id.find_pass_tv)
    TextView findPassTv;
    @Bind(R.id.btn_login)
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        initToolBar();

        txtToReg.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegActivity.class)));
        findPassTv.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, FindPassActivity.class)));
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setupToolbar(toolbar, true);

        toolbarTitle.setText("登录");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


    public void testlogin() {
        App application = App.getInstance();
        String token = "testtest";
        User user = new User();
        application.putUser(user, token);
        setResult(RESULT_OK);
        finish();
    }

    /**
     * TODO
     * 测试登录，目前服务器不可用了，可以更改为自己的服务器地址进行测试
     *
     * @param view
     */
    @OnClick(R.id.btn_login)
    public void login(View view) {


        String phone = mEtxtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.show(this, "请输入手机号码");
            return;
        }

        String pwd = mEtxtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.show(this, "请输入密码");
            return;
        }


        Map<String, Object> params = new Param(3);
        params.put("name", phone);
        params.put("password", pwd);

        mHttpHelper.post(Contants.API.LOGIN, params, new SpotsCallBack<LoginResp>(this) {


            @Override
            public void onSuccess(Response response, LoginResp userLoginRespMsg) {
                if (userLoginRespMsg != null && userLoginRespMsg.getStatus().equals(BaseRespMsg.STATUS_SUCCESS)) {


                    App application = App.getInstance();
                    application.putUser(userLoginRespMsg.getResults().getMyUser(), userLoginRespMsg.getResults().getToken());
                    setResult(RESULT_OK);
                    finish();
                } else {
                    showNormalErr(userLoginRespMsg);
                }


            }

            @Override
            public void onError(Response response, int code, Exception e) {
//                showFail();
                testlogin();
            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {
                ToastUtils.show(errmsg);
            }
        });


    }


}
