package pv.com.pvcloudgo.vc.view.presenter;

import android.content.Context;

import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pv.com.pvcloudgo.http.SimpleCallback;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.model.base.IBaseModel;
import pv.com.pvcloudgo.model.bean.Category;
import pv.com.pvcloudgo.model.bean.Param;
import pv.com.pvcloudgo.model.msg.CategoryResp;
import pv.com.pvcloudgo.model.msg.CategoryRootResp;
import pv.com.pvcloudgo.utils.Contants;
import pv.com.pvcloudgo.utils.ToastUtils;
import pv.com.pvcloudgo.vc.base.BasePresenter;
import pv.com.pvcloudgo.vc.view.ui.iview.ICategoryView;

/**
 * Created by Stefan on 2017/3/15.
 */

public class CategoryPresenter extends BasePresenter {
    private ICategoryView mBaseView;
    private IBaseModel mBaseModel;

    private int currPage = 1;
    private int totalPage = 1;
    private long category_id = 0;


    public static final int STATE_NORMAL = 0;
    public static final int STATE_REFREH = 1;
    public static final int STATE_MORE = 2;

    public int state = STATE_NORMAL;

    public int getCurrPage() {
        return currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public long getCategory_id() {
        return category_id;
    }

    public int getState() {
        return state;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public CategoryPresenter(ICategoryView view, Context mContext) {
        super(mContext);
        mBaseView = view;
    }

    public void init() {
        load();
        mBaseView.initRefreshLayout();
    }

    public void load() {

        Map<String, Object> params = new Param(1);
        mHttpHelper.get(Contants.API.productTypeList, params, new SpotsCallBack<CategoryResp>(mContext) {


            @Override
            public void onSuccess(Response response, CategoryResp mCategoryResp) {
                if (mCategoryResp != null && mCategoryResp.getResults() != null) {
                    long category_id = 0;
                    List<Category> categories = mCategoryResp.getResults().getPtTypeList();
                    mBaseView.showCategoryData(categories);

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

    public void requestWares(long ptId) {
        HashMap<String, Object> params = new Param(2);
        params.put("ptId", ptId);

        mHttpHelper.get(Contants.API.findByRoot, params, new SimpleCallback<CategoryRootResp>(mContext) {


            @Override
            public void onSuccess(Response response, CategoryRootResp resp) {


                if (resp != null && resp.getResults() != null && resp.getResults().getProductType() != null &&
                        resp.getResults().getProductType().getChilds() != null &&
                        resp.getResults().getProductType().getChilds().size() != 0) {
                    mBaseView.showWaresData(resp.getResults().getProductType().getChilds());
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

    public void refreshData() {

        currPage = 1;

        state = STATE_REFREH;
        requestWares(category_id);

    }
    public void loadMoreData() {

        currPage = ++currPage;
        state = STATE_MORE;
        requestWares(category_id);

    }


}
