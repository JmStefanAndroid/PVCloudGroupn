package pv.com.pvcloudgo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import pv.com.pvcloudgo.LoginActivity;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.bean.User;
import pv.com.pvcloudgo.http.OkHttpHelper;


public abstract class BaseFragment extends Fragment {

    @Inject
    protected OkHttpHelper mHttpHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = createView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);

        App.component().inject(this);

        initToolBar();

        init();

        return view;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initToolBar() {

    }


    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void init();


    public void startActivity(Intent intent, boolean isNeedLogin) {


        if (isNeedLogin) {

            User user = App.getInstance().getUser();
            if (user != null) {
                super.startActivity(intent);
            } else {

                App.getInstance().putIntent(intent);
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                super.startActivity(loginIntent);

            }

        } else {
            super.startActivity(intent);
        }

    }


}
