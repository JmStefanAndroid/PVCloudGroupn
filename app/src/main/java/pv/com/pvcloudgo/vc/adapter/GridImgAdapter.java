package pv.com.pvcloudgo.vc.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import pv.com.pvcloudgo.R;

/**
 * 主页面中GridView的适配器
 *
 * @author hanj
 */

public class GridImgAdapter extends BaseAdapter {
    public static String NULL_IMG_PREFIX="NULL_IMG_PREFIX";
    private Context context;
    private ArrayList<String> imagePathList = null;


    final int mGridWidth;
//    HashMap<String,Bitmap> mImages;

    public GridImgAdapter(Context context, ArrayList<String> imagePathList, int column) {
        this.context = context;
        this.imagePathList = imagePathList;
        int width = 0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            width = size.x;
        } else {
            width = wm.getDefaultDisplay().getWidth();
        }
        mGridWidth = width / column;
//        mImages=new HashMap<>();
    }

    @Override
    public int getCount() {
        return imagePathList == null ? 0 : imagePathList.size();
    }

    @Override
    public Object getItem(int position) {
        return imagePathList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final String filePath = (String) getItem(position);

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_img, null);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.main_gridView_item_photo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setScaleX(0);
        holder.imageView.setScaleY(0);


        holder.imageView.setTag(filePath);
        if (filePath.equals(NULL_IMG_PREFIX)) {
            Picasso.with(context).load(R.drawable.empty_photo).resize(mGridWidth, mGridWidth)
                    .centerCrop().into(holder.imageView, new Callback() {
                @Override
                public void onSuccess() {
                    holder.imageView.animate()
                            .scaleX(1.f).scaleY(1.f)
                            .setInterpolator(new OvershootInterpolator())
                            .setDuration(400)
                            .setStartDelay(200)
                            .start();
                }

                @Override
                public void onError() {

                }
            });
        } else{
            Picasso.with(context).load(new File(filePath)).resize(mGridWidth, mGridWidth)
                    .centerCrop().placeholder(R.drawable.empty_photo).into(holder.imageView, new Callback() {
                @Override
                public void onSuccess() {
                    holder.imageView.animate()
                            .scaleX(1.f).scaleY(1.f)
                            .setInterpolator(new OvershootInterpolator())
                            .setDuration(400)
                            .setStartDelay(200)
                            .start();

                }

                @Override
                public void onError() {

                }
            });
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        mImages.put("img" + position, Picasso.with(context).load(new File(filePath)).get());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

        }
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }

    public void bind(ArrayList<String> imagePathList) {
        if (imagePathList == null) return;
        this.imagePathList = imagePathList;
        notifyDataSetChanged();
//        mImages.clear();
    }

//    public Bitmap getDrawable(int position){
//       return mImages.get("img"+position);
//    }
}
