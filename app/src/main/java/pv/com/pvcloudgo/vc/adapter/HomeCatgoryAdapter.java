package pv.com.pvcloudgo.vc.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.vc.adapter.holder.ViewHolderFunc;
import pv.com.pvcloudgo.model.bean.Campaign;
import pv.com.pvcloudgo.model.bean.HomeCampaign;

/**
 * Created by Ivan on 15/9/30.
 */
public class HomeCatgoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static int VIEW_TYPE_0 = 0;
    private static int VIEW_TYPE_1 = 1;
    private static int VIEW_TYPE_2 = 2;
    private static int VIEW_TYPE_3 = 3;

    private LayoutInflater mInflater;


    private List<HomeCampaign> mDatas;

    private Context mContext;


    private OnCampaignClickListener mListener;


    public HomeCatgoryAdapter(List<HomeCampaign> datas, Context context) {
        mDatas = datas;
        this.mContext = context;
    }


    public void setOnCampaignClickListener(OnCampaignClickListener listener) {

        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {


        mInflater = LayoutInflater.from(viewGroup.getContext());
        if (type == VIEW_TYPE_1) {//会生活
            return new ViewHolder(mInflater.inflate(R.layout.template_life, viewGroup, false));
        } else if (type == VIEW_TYPE_0) {//分类
            return new ViewHolderFunc(mInflater.inflate(R.layout.type_main_layout, viewGroup, false));
        } else if (type == VIEW_TYPE_2) {//精彩主题
            return new ViewHolder(mInflater.inflate(R.layout.template_theme, viewGroup, false));
        } else if (type == VIEW_TYPE_3) {//168定制
            return new ViewHolder(mInflater.inflate(R.layout.template_custom, viewGroup, false));
        }

        return new ViewHolder(mInflater.inflate(R.layout.template_theme, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
//        if (viewHolder instanceof HomeCatgoryAdapter.ViewHolder) {
//            HomeCatgoryAdapter.ViewHolder holder = (ViewHolder) viewHolder;
////            HomeCampaign homeCampaign = mDatas.get(i);
////            holder.textTitle.setText(homeCampaign.getTitle());
////
////            Picasso.with(mContext).load(homeCampaign.getCpOne().getImgUrl()).into(holder.imageViewBig);
////            Picasso.with(mContext).load(homeCampaign.getCpTwo().getImgUrl()).into(holder.imageViewSmallTop);
////            Picasso.with(mContext).load(homeCampaign.getCpThree().getImgUrl()).into(holder.imageViewSmallBottom);
//        } else if (viewHolder instanceof ViewHolder0) {
//            ViewHolder0 ViewHolder0 = (ViewHolder0) viewHolder;
//        }
    }

    @Override
    public int getItemCount() {


//        if (mDatas == null || mDatas.size() <= 0)
//            return 0;

        return 4;
    }


    @Override
    public int getItemViewType(int position) {

        return position;

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textTitle;
        ImageView imageViewBig;
        ImageView imageViewSmallTop;
        ImageView imageViewSmallBottom;

        public ViewHolder(View itemView) {
            super(itemView);

//
//            textTitle = (TextView) itemView.findViewById(R.id.text_title);
//            imageViewBig = (ImageView) itemView.findViewById(R.id.imgview_big);
//            imageViewSmallTop = (ImageView) itemView.findViewById(R.id.imgview_small_top);
//            imageViewSmallBottom = (ImageView) itemView.findViewById(R.id.imgview_small_bottom);
//
//
//            imageViewBig.setOnClickListener(this);
//            imageViewSmallTop.setOnClickListener(this);
//            imageViewSmallBottom.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


            if (mListener != null) {

                anim(v);

            }


        }

        private void anim(final View v) {

            ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotationX", 0.0F, 360.0F)
                    .setDuration(200);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    HomeCampaign campaign = mDatas.get(getLayoutPosition());

                    switch (v.getId()) {

                        case R.id.imgview_big:
                            mListener.onClick(v, campaign.getCpOne());
                            break;

                        case R.id.imgview_small_top:
                            mListener.onClick(v, campaign.getCpTwo());
                            break;

                        case R.id.imgview_small_bottom:
                            mListener.onClick(v, campaign.getCpThree());
                            break;

                    }

                }
            });
            animator.start();
        }
    }

    class ViewHolder0 extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ViewHolder0(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {


            if (mListener != null) {

                anim(v);

            }


        }

        private void anim(final View v) {

            ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotationX", 0.0F, 360.0F)
                    .setDuration(200);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    HomeCampaign campaign = mDatas.get(getLayoutPosition());

                    switch (v.getId()) {

                        case R.id.imgview_big:
                            mListener.onClick(v, campaign.getCpOne());
                            break;

                        case R.id.imgview_small_top:
                            mListener.onClick(v, campaign.getCpTwo());
                            break;

                        case R.id.imgview_small_bottom:
                            mListener.onClick(v, campaign.getCpThree());
                            break;

                    }

                }
            });
            animator.start();
        }
    }


    public interface OnCampaignClickListener {


        void onClick(View view, Campaign campaign);

    }

}
