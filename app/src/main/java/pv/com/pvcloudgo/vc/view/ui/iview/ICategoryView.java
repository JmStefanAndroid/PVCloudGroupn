package pv.com.pvcloudgo.vc.view.ui.iview;

import java.util.List;

import pv.com.pvcloudgo.model.bean.CRoot2;
import pv.com.pvcloudgo.model.bean.Category;
import pv.com.pvcloudgo.vc.base.IBaseView;

/**
 * Created by Stefan on 2017/3/15.
 */

public interface ICategoryView extends IBaseView {

     void initRefreshLayout() ;

    void showCategoryData(List<Category> categories);

    void showWaresData(List<CRoot2> mValues);

}
