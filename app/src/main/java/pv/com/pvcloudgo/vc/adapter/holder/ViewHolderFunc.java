package pv.com.pvcloudgo.vc.adapter.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;

import pv.com.pvcloudgo.vc.view.ui.activity.other.CategoryActivity;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.vc.view.ui.activity.home.AboutUsActivity;
import pv.com.pvcloudgo.vc.view.ui.activity.home.CloudClubActivity;
import pv.com.pvcloudgo.vc.view.ui.activity.home.CloudPointActivity;
import pv.com.pvcloudgo.vc.view.ui.activity.home.CloudTcActivity;
import pv.com.pvcloudgo.vc.view.ui.activity.home.CloudWorldGoActivity;
import pv.com.pvcloudgo.vc.view.ui.activity.home.OMYOActivity;
import pv.com.pvcloudgo.vc.view.ui.activity.home.PhoneChargeActivity;
import pv.com.pvcloudgo.utils.Utils;

public class ViewHolderFunc extends RecyclerView.ViewHolder implements View.OnClickListener {


    public ViewHolderFunc(View itemView) {
        super(itemView);

        LinearLayout root = (LinearLayout) itemView.findViewById(R.id.content_container);
        for (int i = 0; i < root.getChildCount(); i++) {
            for (int z = 0; z < ((ViewGroup) root.getChildAt(i)).getChildCount(); z++) {
                ((ViewGroup) root.getChildAt(i)).getChildAt(z).setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {

        anim(v);
    }

    private void anim(final View v) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotationX", 0.0F, 360.0F)
                .setDuration(200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                if (!Utils.login()) {
                    return;
                }
                switch (v.getId()) {

                    case R.id.fun_all:
                        v.getContext().startActivity(new Intent(v.getContext(), CategoryActivity.class));
                        break;
                    case R.id.fun_combo:
                        v.getContext().startActivity(new Intent(v.getContext(), CloudTcActivity.class));
                        break;
                    case R.id.fun_charge:
                        v.getContext().startActivity(new Intent(v.getContext(), PhoneChargeActivity.class));
                        break;
                    case R.id.fun_wolrdgo:
                        v.getContext().startActivity(new Intent(v.getContext(), CloudWorldGoActivity.class));
                        break;
                    case R.id.fun_cloud_p:
                        v.getContext().startActivity(new Intent(v.getContext(), CloudPointActivity.class));
                        break;
                    case R.id.fun_omyo:
                        v.getContext().startActivity(new Intent(v.getContext(), OMYOActivity.class));
                        break;
                    case R.id.fun_cloud_club:
                        v.getContext().startActivity(new Intent(v.getContext(), CloudClubActivity.class));
                        break;
                    case R.id.fun_aboutus:
                        v.getContext().startActivity(new Intent(v.getContext(), AboutUsActivity.class));
                        break;

                }

            }
        });
        animator.start();
    }
}