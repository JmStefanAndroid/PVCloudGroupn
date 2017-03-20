package pv.com.pvcloudgo.vc.view.ui.activity.start;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.model.base.BaseRespMsg;
import pv.com.pvcloudgo.utils.Contants;
import pv.com.pvcloudgo.utils.ToastUtils;
import pv.com.pvcloudgo.vc.base.BaseActivity;
import pv.com.pvcloudgo.vc.widget.ClearEditText;

public class FindPassActivity extends BaseActivity {

    private static final String TAG = "FindPassActivity";

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
    @Bind(R.id.edittxt_phone)
    ClearEditText mEtxtPhone;
    @Bind(R.id.edittxt_code)
    ClearEditText mEditCode;
    @Bind(R.id.ac_reg_virifycode_tv)
    TextView acRegVirifycodeTv;
    @Bind(R.id.ac_find_code_content)
    LinearLayout acFindCodeContent;
    @Bind(R.id.edittxt_pswd1)
    ClearEditText edittxtPswd1;
    @Bind(R.id.edittxt_pswd2)
    ClearEditText edittxtPswd2;
    @Bind(R.id.ac_find_setpswd_content)
    LinearLayout acFindSetpswdContent;
    @Bind(R.id.ac_find_pass_next)
    Button acFindPassNext;


    private SpotsDialog dialog;

    private int curState = 0;//0 找回密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass);
        ButterKnife.bind(this);

        initToolBar();

        acFindPassNext.setOnClickListener(v -> {
            if (curState==0) {
                String phone = mEtxtPhone.getText().toString().trim().replaceAll("\\s*", "");
                sendCode(phone);
            }
        });
    }


    private void initToolBar() {
        setupToolbar(toolbar, true);
        toolbarTitle.setText("找回密码");
    }


    private void checkPhoneNum(String phone, String code) {
        if (code.startsWith("+")) {
            code = code.substring(1);
        }

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.show(this, "请输入手机号码");
            return;
        }

        if (code == "86") {
            if (phone.length() != 11) {
                ToastUtils.show(this, "手机号码长度不对");
                return;
            }

        }

        String rule = "^1(3|5|7|8|4)\\d{9}";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(phone);

        if (!m.matches()) {
            ToastUtils.show(this, "您输入的手机号码格式不正确");
            return;
        }

    }


//    /**
//     * 请求验证码后，跳转到验证码填写页面
//     */
//    private void afterVerificationCodeRequested(boolean smart) {
//
//
//        String phone = mEtxtPhone.getText().toString().trim().replaceAll("\\s*", "");
//        String code = edittxtCode.getText().toString().trim();
//        String pwd = mEtxtPwd.getText().toString().trim();
//
//        if (code.startsWith("+")) {
//            code = code.substring(1);
//        }
//
//        Intent intent = new Intent(this, RegSecondActivity.class);
//        intent.putExtra("phone", phone);
//        intent.putExtra("pwd", pwd);
//        intent.putExtra("countryCode", code);
//
//        startActivity(intent);
//    }


    public void sendCode(String phone) {
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


        Map<String, Object> params = new HashMap<>(1);
        params.put("telPhone", phone);
        mHttpHelper.post(Contants.API.FINDPSWDSENDCODE, params, new SpotsCallBack<BaseRespMsg>(this) {

            @Override
            public void onSuccess(Response response, BaseRespMsg respMsg) {
                if (respMsg != null && respMsg.getStatus().equals(BaseRespMsg.STATUS_SUCCESS)) {
                    ToastUtils.show("验证码已发送至您的手机");
                    mEtxtPhone.setVisibility(View.GONE);
                    acFindCodeContent.setVisibility(View.VISIBLE);
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
