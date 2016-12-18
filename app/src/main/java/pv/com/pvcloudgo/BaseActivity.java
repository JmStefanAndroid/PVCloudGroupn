package pv.com.pvcloudgo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.bean.User;
import pv.com.pvcloudgo.http.OkHttpHelper;

/**
 */
public class BaseActivity extends AppCompatActivity {


    private View.OnClickListener onBackListener;

    protected static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    public OkHttpHelper mHttpHelper;

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        App.component().inject(this);
    }

    public void startActivity(Intent intent, boolean isNeedLogin) {


        if (isNeedLogin) {

            User user = App.getInstance().getUser();
            if (user != null) {
                super.startActivity(intent);
            } else {

                App.getInstance().putIntent(intent);
                Intent loginIntent = new Intent(this
                        , LoginActivity.class);
                super.startActivity(intent);

            }

        } else {
            super.startActivity(intent);
        }

    }

    protected void setupToolbar(Toolbar mToolbar, boolean homeIconVisible) {
        if (mToolbar == null) {
            throw new RuntimeException("toolbar cannot be null!");
        }
        if (null == getSupportActionBar()) {
            setSupportActionBar(mToolbar);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(homeIconVisible);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.ic_arr_left_3);
    }

    protected void setupToolbar(Toolbar mToolbar, boolean homeIconVisible, View.OnClickListener onBackListener) {
        setupToolbar(mToolbar, homeIconVisible);
        this.onBackListener = onBackListener;
    }

    /**
     * 重写Actionbar返回上一级的动画,以及避免重复创建实例
     *
     * @param item 菜单项
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (onBackListener != null) {
                onBackListener.onClick(null);
            } else {
                finish();
            }
        }
        return false;
    }

}
