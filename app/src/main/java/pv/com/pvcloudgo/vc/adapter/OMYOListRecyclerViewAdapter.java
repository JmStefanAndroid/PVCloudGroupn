package pv.com.pvcloudgo.vc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.vc.view.ui.fragment.dummy.DummyContent.DummyItem;
import pv.com.pvcloudgo.vc.view.ui.fragment.interf.OnItemClickListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 */
public class OMYOListRecyclerViewAdapter extends RecyclerView.Adapter<OMYOListRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnItemClickListener mListener;

    public OMYOListRecyclerViewAdapter(List<DummyItem> items, OnItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_omyo_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

    }
}
