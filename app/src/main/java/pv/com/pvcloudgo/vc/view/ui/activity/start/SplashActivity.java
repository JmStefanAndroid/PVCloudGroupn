package pv.com.pvcloudgo.vc.view.ui.activity.start;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.vc.base.BaseActivity;
import pv.com.pvcloudgo.vc.view.ui.activity.main.MainActivity;


public class SplashActivity extends BaseActivity {

    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_splash, null);
        setContentView(rootView);
        ButterKnife.bind(this);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> rootView.animate().scaleXBy(0.1f).scaleYBy(0.1f).alphaBy(0.1f).setDuration(1000).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startActivity(new Intent(mContext, MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start());

            }
        }, 3000);
    }


}
