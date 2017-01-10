package pv.com.pvcloudgo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pv.com.pvcloudgo.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.WareDetailActivity;
import pv.com.pvcloudgo.bean.CRoot2;
import pv.com.pvcloudgo.bean.Category;
import pv.com.pvcloudgo.fragment.interf.OnItemClickListener;

public class CategoryThingsAdapter extends RecyclerView.Adapter<CategoryThingsAdapter.ViewHolder> {

    private List<CRoot2> mValues;
    private Context mContext;

    public CategoryThingsAdapter(Context mContext, List<CRoot2> mValues) {
        this.mContext = mContext;
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_c_thins, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public CRoot2 mItem;
        public RecyclerView recyclerView;
        private WaresAdapter mWaresAdatper;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            recyclerView = (RecyclerView) view.findViewById(R.id.item_c_thins_recyclerview);


            if (mWaresAdatper == null) {
                mWaresAdatper = new WaresAdapter(view.getContext(), mItem.getChilds());
                mWaresAdatper.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Category wares = mWaresAdatper.getItem(position);

                        Intent intent = new Intent(view.getContext(), WareDetailActivity.class);

                        intent.putExtra(Contants.WARE, wares);
                        view.getContext().startActivity(intent);

                    }
                });

                recyclerView.setAdapter(mWaresAdatper);

                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                    mRecyclerviewWares.addItemDecoration(new DividerGridItemDecoration(getContext()));
            } else {
                mWaresAdatper.clear();
                mWaresAdatper.addData(mItem.getChilds());
            }


        }

    }

    public void clear() {
        mValues.clear();
    }

    public void bindNew(List<CRoot2> items) {
        this.mValues = items;
        notifyDataSetChanged();
    }
}
