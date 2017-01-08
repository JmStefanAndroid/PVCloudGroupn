package pv.com.pvcloudgo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Response;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import pv.com.pvcloudgo.bean.Param;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.msg.BaseRespMsg;
import pv.com.pvcloudgo.utils.CountTimerView;
import pv.com.pvcloudgo.utils.ToastUtil;
import pv.com.pvcloudgo.utils.ToastUtils;
import pv.com.pvcloudgo.widget.ClearEditText;

public class RegActivity extends BaseActivity {

    private static final String TAG = "RegActivity";

    // 默认使用中国区号
    private static final String DEFAULT_COUNTRY_ID = "42";
    @Bind(R.id.edittxt_phone)
    ClearEditText mEtxtPhone;
    @Bind(R.id.edittxt_code)
    ClearEditText edittxtCode;
    @Bind(R.id.edittxt_pwd)
    ClearEditText mEtxtPwd;
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
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ac_reg_virifycode_tv)
    TextView mVirifycodeTv;


    private SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);

        initToolBar();

    }

    @OnClick(R.id.ac_reg_btn)
    public void goReg() {
        toolbarTitle.setText("注册");
        String code = edittxtCode.getText().toString().trim();
        String phone = mEtxtPhone.getText().toString().trim().replaceAll("\\s*", "");
        verifyCode(code, phone);
    }


    private void initToolBar() {
        setupToolbar(toolbar, true);

        mVirifycodeTv.setText("获取验证码");
        mVirifycodeTv.setOnClickListener(v -> verifyPhone(mEtxtPhone.getText().toString().trim()));
    }

    /**
     * 获取验证码
     */
    private void getCode() {

        String phone = mEtxtPhone.getText().toString().trim().replaceAll("\\s*", "");
        Map<String, Object> params = new Param(2);
        params.put("telPhone", phone);
        mHttpHelper.post(Contants.API.GETCODE, params, new SpotsCallBack<BaseRespMsg>(this) {


            @Override
            public void onSuccess(Response response, BaseRespMsg respMsg) {


                if (respMsg != null && respMsg.getStatus().equals(BaseRespMsg.STATUS_SUCCESS)) {
                    CountTimerView countTimerView = new CountTimerView(mVirifycodeTv);
                    countTimerView.start();
                    ToastUtils.show("验证码已发送至您的手机");
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

            }
        });

    }


    private void checkPhoneNum(String phone, String code, String password) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.show("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.show("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show("请输入登录密码");
            return;
        }

        String rule = "^1(3|5|7|8|4)\\d{9}";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(phone);

        if (!m.matches()) {
            ToastUtils.show(this, "您输入的手机号码格式不正确");
            return;
        }
        if (code.length() != 6) {
            ToastUtils.show("请输入6位验证码");
            return;
        }
        if (password.length() > 12 || password.length() < 6) {
            ToastUtils.show("请输入6-12位登录密码");
            return;
        }

        Map<String, Object> params = new Param(4);
        params.put("name", phone);
        params.put("smsYanzhengma", code);
        params.put("password", password);
        mHttpHelper.post(Contants.API.REG, params, new SpotsCallBack<BaseRespMsg>(this) {


            @Override
            public void onSuccess(Response response, BaseRespMsg respMsg) {


                if (respMsg != null && respMsg.getStatus().equals(BaseRespMsg.STATUS_SUCCESS)) {
//                    startActivity(new Intent(mContext, BandPhoneActivity.class));
                    ToastUtil.showToast(mContext, R.drawable.ic_opt_suc, "注册成功");
                    finish();
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

            }
        });


    }

    public void verifyPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.show("请输入手机号码");
            return;
        }
        String rule = "^1(3|5|7|8|4)\\d{9}";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(phone);

        if (!m.matches()) {
            ToastUtils.show(this, "您输入的手机号码格式不正确");
            return;
        }


        Map<String, Object> params = new Param(2);
        params.put("name", phone);
        mHttpHelper.post(Contants.API.VERIFYPHONE, params, new SpotsCallBack<BaseRespMsg>(this) {


            @Override
            public void onSuccess(Response response, BaseRespMsg respMsg) {


                if (respMsg != null && respMsg.getStatus().equals(BaseRespMsg.STATUS_SUCCESS)) {
                    getCode();
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

            }
        });
    }

    public void verifyCode(String code, String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.show("请输入手机号码");
            return;
        }
        String rule = "^1(3|5|7|8|4)\\d{9}";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(phone);

        if (!m.matches()) {
            ToastUtils.show(this, "您输入的手机号码格式不正确");
            return;
        }

        if (TextUtils.isEmpty(code)) {
            ToastUtils.show("请输入验证码");
            return;
        }
        if (code.length() != 6) {
            ToastUtils.show("请输入6位验证码");
            return;
        }


        Map<String, Object> params = new Param(2);
        params.put("smsYanzhengma", code);
        params.put("telPhone", phone);
        mHttpHelper.post(Contants.API.VERIFYCODE, params, new SpotsCallBack<BaseRespMsg>(this) {


            @Override
            public void onSuccess(Response response, BaseRespMsg respMsg) {
                if (respMsg != null && respMsg.getStatus().equals(BaseRespMsg.STATUS_SUCCESS)) {
                    String phone = mEtxtPhone.getText().toString().trim().replaceAll("\\s*", "");
                    String code = edittxtCode.getText().toString().trim();
                    String pwd = mEtxtPwd.getText().toString().trim();
                    checkPhoneNum(phone, code, pwd);
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

            }
        });
    }


}
