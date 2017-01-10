package pv.com.pvcloudgo.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import pv.com.pvcloudgo.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.bean.Category;


/**
 */
public class WaresAdapter extends SimpleAdapter<Category> {



    public WaresAdapter(Context context, List<Category> datas) {
        super(context, R.layout.item_category, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Category item) {

        viewHoder.getTextView(R.id.text_title).setText(item.getName());
        SimpleDraweeView draweeView = (SimpleDraweeView) viewHoder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(Contants.API.BASE_URL+item.getWrap_ptlist_rootImage()));
    }



}
