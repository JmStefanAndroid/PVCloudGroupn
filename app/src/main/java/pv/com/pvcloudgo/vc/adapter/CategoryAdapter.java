package pv.com.pvcloudgo.vc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.model.bean.Category;

/**
 */
public class CategoryAdapter extends SimpleAdapter<Category> {

    private int index = 0;

    public CategoryAdapter(Context context, List<Category> datas) {
        super(context, R.layout.template_single_text, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Category item) {

        if (index == viewHoder.getLayoutPosition()) {

            viewHoder.getView(R.id.parentPanel).
                    setBackgroundColor(Color.WHITE);

            viewHoder.getView(R.id.divider).
                    setVisibility(View.VISIBLE);

            ((TextView)viewHoder.getView(R.id.textView)).
                    setTextColor(viewHoder.getView(R.id.parentPanel)
                            .getResources().getColor(R.color.purple));
        } else {
            viewHoder.getView(R.id.parentPanel).
                    setBackgroundColor(viewHoder.getView(R.id.parentPanel)
                            .getResources().getColor(R.color.gainsboro));
            viewHoder.getView(R.id.divider).
                    setVisibility(View.GONE);
            ((TextView)viewHoder.getView(R.id.textView)).
                    setTextColor(viewHoder.getView(R.id.parentPanel)
                            .getResources().getColor(R.color.black));
        }
        viewHoder.getTextView(R.id.textView).setText(item.getName());


    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }
}
