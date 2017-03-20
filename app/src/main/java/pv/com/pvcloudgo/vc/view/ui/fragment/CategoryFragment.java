package pv.com.pvcloudgo.vc.view.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.List;

import butterknife.Bind;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.model.bean.CRoot2;
import pv.com.pvcloudgo.model.bean.Category;
import pv.com.pvcloudgo.vc.adapter.CategoryAdapter;
import pv.com.pvcloudgo.vc.adapter.CategoryThingsAdapter;
import pv.com.pvcloudgo.vc.adapter.decoration.DividerItemDecoration;
import pv.com.pvcloudgo.vc.view.presenter.CategoryPresenter;
import pv.com.pvcloudgo.vc.view.ui.iview.ICategoryView;


public class CategoryFragment extends BaseFragment implements ICategoryView {


    @Bind(R.id.recyclerview_category)
    RecyclerView mRecyclerView;
    @Bind(R.id.recyclerview_wares)
    RecyclerView mRecyclerviewWares;
    @Bind(R.id.refresh_layout)
    MaterialRefreshLayout mRefreshLaout;


    private CategoryAdapter mCategoryAdapter;
    private CategoryThingsAdapter mCategoryThingsAdapter;


    protected CategoryPresenter mPresenter;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new CategoryPresenter(this, getActivity());
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    @Override
    public void init() {
        mPresenter.init();
    }

    @Override
    public void initRefreshLayout() {

        mRefreshLaout.setLoadMore(true);
        mRefreshLaout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mPresenter. refreshData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                if (mPresenter.getCurrPage() <= mPresenter.getTotalPage())
                    mPresenter.loadMoreData();
                else {
                    mRefreshLaout.finishRefreshLoadMore();
                }
            }
        });
    }


    @Override
    public void showCategoryData(List<Category> categories) {

        mCategoryAdapter = new CategoryAdapter(getActivity(), categories);

        mCategoryAdapter.setOnItemClickListener((view, position) -> {

            Category category = mCategoryAdapter.getItem(position);

            mPresenter.setCategory_id(category.getId());
            mPresenter.setState(CategoryPresenter.STATE_NORMAL);
            mPresenter.setCurrPage(1);

            mPresenter.requestWares(mPresenter.getCategory_id());
            mCategoryAdapter.setIndex(position);
        });

        mRecyclerView.setAdapter(mCategoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));


    }


    @Override
    public void showWaresData(List<CRoot2> mValues) {


        switch (mPresenter.getState()) {

            case CategoryPresenter.STATE_NORMAL:

                if (mCategoryThingsAdapter == null) {
                    mCategoryThingsAdapter = new CategoryThingsAdapter(getActivity(), mValues);

                    mRecyclerviewWares.setAdapter(mCategoryThingsAdapter);

                    mRecyclerviewWares.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerviewWares.setItemAnimator(new DefaultItemAnimator());
//                    mRecyclerviewWares.addItemDecoration(new DividerGridItemDecoration(getContext()));
                } else {
                    mCategoryThingsAdapter.clear();
                    mCategoryThingsAdapter.bindNew(mValues);
                }


                break;

            case CategoryPresenter.STATE_REFREH:
                mCategoryThingsAdapter.clear();
                mCategoryThingsAdapter.bindNew(mValues);

                mRecyclerviewWares.scrollToPosition(0);
                mRefreshLaout.finishRefresh();
                break;

            case CategoryPresenter.STATE_MORE:
                mRefreshLaout.finishRefreshLoadMore();
                break;


        }


    }


}



