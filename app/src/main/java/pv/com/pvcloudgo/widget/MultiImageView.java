package pv.com.pvcloudgo.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.utils.Utils;

public class MultiImageView  extends LinearLayout {


    // 照片的Url列表
    private ArrayList<String> imagesList;

    /** 长度 单位为Pixel **/
    private int pxOneWidth = Utils.dipToPx(getContext(), 115);// 单张图时候的宽
    private int pxOneHeight = Utils.dipToPx(getContext(), 150);// 单张图时候的高
    private int pxMoreWandH = 0;// 多张图的宽高
    private int pxImagePadding = Utils.dipToPx(getContext(), 3);// 图片间的间距

    private final int MAX_PER_ROW_COUNT = 3;// 每行显示最大数

    private LayoutParams onePicPara;
    private LayoutParams morePara;
    private LayoutParams rowPara;

    public MultiImageView(Context context) {
        super(context);

    }

    public MultiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setList(ArrayList<String> lists, int width) {
        initVariable();
        imagesList = lists;
//        pxMoreWandH = width/3;
        pxMoreWandH = 1280/3;
        initView();
    }

    private void initVariable() {


        onePicPara = new LayoutParams(pxOneWidth, pxOneHeight);

        morePara = new LayoutParams(pxMoreWandH, pxMoreWandH);
        morePara.setMargins(0, 0, pxImagePadding, 0);

        int wrap = LayoutParams.WRAP_CONTENT;
        int match = LayoutParams.MATCH_PARENT;
        rowPara = new LayoutParams(match, wrap);
        rowPara.setMargins(0, 0, 0, pxImagePadding);
    }

    // 根据imageView的数量初始化不同的View布局,还要为每一个View作点击效果
    private void initView()
    {
        this.setOrientation(VERTICAL);
        this.removeAllViews();
        if(imagesList == null || imagesList.size() == 0)
        {
            return;
        }

        if(imagesList.size() == 1)
        {
            for(String url : imagesList)
            {
                ImageView imageView = createImageView(url,0,onePicPara);
                addView(imageView);

                Picasso.with(getContext()).load(url).into(imageView);
            }

        }else
        {
            int allCount = imagesList.size();

            int rowCount = allCount/MAX_PER_ROW_COUNT + (allCount%MAX_PER_ROW_COUNT > 0 ? 1 : 0);//行数
            for(int rowCursor = 0; rowCursor < rowCount; rowCursor ++)
            {
                LinearLayout rowLayout = new LinearLayout(getContext());
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);

                rowLayout.setLayoutParams(rowPara);
                if(rowCursor < 2)
                {
                    rowLayout.setPadding(0, pxImagePadding, 0, 0);
                }

                int columnCount = allCount%MAX_PER_ROW_COUNT == 0 ? MAX_PER_ROW_COUNT : allCount%MAX_PER_ROW_COUNT;
                if(rowCursor != rowCount -1)
                {
                    columnCount = MAX_PER_ROW_COUNT;
                }
                addView(rowLayout);

                int rowOffset = rowCursor * MAX_PER_ROW_COUNT;//行偏移
                for(int columnCursor = 0; columnCursor < columnCount; columnCursor ++)
                {
                    int position = columnCursor + rowOffset;
                    String thumbUrl = imagesList.get(position);

                    ImageView imageView = createImageView(thumbUrl,position,morePara);

                    imageView.setImageResource(R.drawable.default_head);
                    rowLayout.addView(imageView);
//                    Picasso.with(getContext()).load(thumbUrl).into(imageView);
                }
            }
        }
    }


    private ImageView createImageView(String thumbUrl,int position,LayoutParams params){

        ImageView imageView = new ImageView(getContext());
        imageView.setId(thumbUrl.hashCode());//指定id

        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                    imageView.setImageResource(thumbUrl);



        imageView.setTag(position);
        imageView.setOnClickListener(ImageViewOnClickListener);

        return  imageView;

    }



    // 图片点击事件
    private OnClickListener ImageViewOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            int position = (Integer) arg0.getTag();

        }
    };

}
