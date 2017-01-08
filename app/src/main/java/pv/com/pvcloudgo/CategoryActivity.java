package pv.com.pvcloudgo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.bean.User;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.msg.LoginRespMsg;
import pv.com.pvcloudgo.utils.DESUtil;

public class CategoryActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);


        initToolBar();

    }


    private void initToolBar() {
        setupToolbar(toolbar, true);

        toolbarTitle.setText("全部分类");

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
