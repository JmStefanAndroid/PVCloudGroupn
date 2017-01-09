package pv.com.pvcloudgo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.squareup.okhttp.Response;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.bean.Param;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.msg.CategoryResp;

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

    public void load() {




        Map<String, Object> params = new Param(1);

        mHttpHelper.post(Contants.API.productTypeList, params, new SpotsCallBack<CategoryResp>(this) {


            @Override
            public void onSuccess(Response response, CategoryResp mCategoryResp) {




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
