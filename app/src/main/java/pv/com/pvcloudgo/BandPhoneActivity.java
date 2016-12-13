package pv.com.pvcloudgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import pv.com.pvcloudgo.utils.ToastUtils;
import pv.com.pvcloudgo.widget.ClearEditText;

public class BandPhoneActivity extends BaseActivity {

    private static final String TAG = "BandPhoneActivity";
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
    ClearEditText edittxtPhone;
    @Bind(R.id.edittxt_code)
    ClearEditText edittxtCode;
    @Bind(R.id.ac_reg_virifycode_tv)
    TextView acRegVirifycodeTv;
    @Bind(R.id.btn_complete)
    Button btnComplete;


    private SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandphone);
        ButterKnife.bind(this);

        initToolBar();

    }



    private void initToolBar() {
        setupToolbar(toolbar, true);
        toolbarTitle.setText("绑定手机");
        toolbarRightTitle.setText("跳过");
        toolbarRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             startActivity(new Intent(mContext,MainActivity.class));

            }
        });


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

}
