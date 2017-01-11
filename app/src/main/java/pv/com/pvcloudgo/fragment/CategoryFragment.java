package pv.com.pvcloudgo.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import pv.com.pvcloudgo.Contants;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.adapter.BaseAdapter;
import pv.com.pvcloudgo.adapter.CategoryAdapter;
import pv.com.pvcloudgo.adapter.CategoryThingsAdapter;
import pv.com.pvcloudgo.adapter.decoration.DividerItemDecoration;
import pv.com.pvcloudgo.bean.CRoot2;
import pv.com.pvcloudgo.bean.Category;
import pv.com.pvcloudgo.bean.Param;
import pv.com.pvcloudgo.http.SimpleCallback;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.msg.CategoryResp;
import pv.com.pvcloudgo.msg.CategoryRootResp;
import pv.com.pvcloudgo.utils.ToastUtils;


public class CategoryFragment extends BaseFragment {


    @Bind(R.id.recyclerview_category)
    RecyclerView mRecyclerView;
    @Bind(R.id.recyclerview_wares)
    RecyclerView mRecyclerviewWares;
    @Bind(R.id.refresh_layout)
    MaterialRefreshLayout mRefreshLaout;


    private CategoryAdapter mCategoryAdapter;
    private CategoryThingsAdapter mCategoryThingsAdapter;


    private int currPage = 1;
    private int totalPage = 1;
    private int pageSize = 10;
    private long category_id = 0;


    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREH = 1;
    private static final int STATE_MORE = 2;

    private int state = STATE_NORMAL;
    private List<Category> mCategories;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void init() {

//        requestCategoryData();
        load();
        initRefreshLayout();
    }


    private void initRefreshLayout() {

        mRefreshLaout.setLoadMore(true);
        mRefreshLaout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                refreshData();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                if (currPage <= totalPage)
                    loadMoreData();
                else {
//                    Toast.makeText()
                    mRefreshLaout.finishRefreshLoadMore();
                }
            }
        });
    }


    private void refreshData() {

        currPage = 1;

        state = STATE_REFREH;
        requestWares(category_id);

    }

    private void loadMoreData() {

        currPage = ++currPage;
        state = STATE_MORE;
        requestWares(category_id);

    }


    private void requestCategoryData() {


        mHttpHelper.get(Contants.API.CATEGORY_LIST, new SpotsCallBack<List<Category>>(getActivity()) {


            @Override
            public void onSuccess(Response response, List<Category> categories) {

                showCategoryData(categories);

                if (categories != null && categories.size() > 0)
                    category_id = categories.get(0).getId();
                requestWares(category_id);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }
        });

    }

    private void showCategoryData(List<Category> categories) {

        this.mCategories = categories;
        mCategoryAdapter = new CategoryAdapter(getActivity(), categories);

        mCategoryAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Category category = mCategoryAdapter.getItem(position);

                category_id = category.getId();
                currPage = 1;
                state = STATE_NORMAL;

                requestWares(category_id);
                mCategoryAdapter.setIndex(position);
            }
        });

        mRecyclerView.setAdapter(mCategoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));


    }


    private void requestWares(long ptId) {
        HashMap<String, Object> params = new Param(2);
        params.put("ptId", ptId);

        mHttpHelper.get(Contants.API.findByRoot,params, new SimpleCallback<CategoryRootResp>(getActivity()) {


            @Override
            public void onSuccess(Response response, CategoryRootResp resp) {


                if (resp != null && resp.getResults() != null && resp.getResults().getProductType() != null &&
                        resp.getResults().getProductType().getChilds() != null &&
                        resp.getResults().getProductType().getChilds().size() != 0) {
                    showWaresData(resp.getResults().getProductType().getChilds());
                } else ToastUtils.show("暂无数据");

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }


        });

    }


    private void showWaresData(List<CRoot2> mValues) {


        switch (state) {

            case STATE_NORMAL:

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

            case STATE_REFREH:
                mCategoryThingsAdapter.clear();
                mCategoryThingsAdapter.bindNew(mValues);

                mRecyclerviewWares.scrollToPosition(0);
                mRefreshLaout.finishRefresh();
                break;

            case STATE_MORE:
//                mWaresAdatper.addData(mWaresAdatper.getDatas().size(), wares);
//                mRecyclerviewWares.scrollToPosition(mWaresAdatper.getDatas().size());
                mRefreshLaout.finishRefreshLoadMore();
                break;


        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    public void load() {

        Map<String, Object> params = new Param(1);
        mHttpHelper.get(Contants.API.productTypeList, params, new SpotsCallBack<CategoryResp>(getActivity()) {


            @Override
            public void onSuccess(Response response, CategoryResp mCategoryResp) {
                if (mCategoryResp != null && mCategoryResp.getResults() != null) {
                    List<Category> categories = mCategoryResp.getResults().getPtTypeList();
                    showCategoryData(categories);

                    if (categories != null && categories.size() > 0)
                        category_id = categories.get(0).getId();
                    requestWares(category_id);
                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }
        });


    }

}



