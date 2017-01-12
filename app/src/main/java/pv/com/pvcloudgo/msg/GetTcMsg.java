package pv.com.pvcloudgo.msg;

import java.util.List;

import pv.com.pvcloudgo.bean.Pager;
import pv.com.pvcloudgo.bean.ProductPackage;
import pv.com.pvcloudgo.bean.ProductPackageType;

/**
 * Created by Administrator on 2017/1/12.
 */
public class GetTcMsg extends BaseRespMsg {

    public Result results;

    public Result getResults() {
        return results;
    }

    public class Result {
        String canDingjin;

        ProductPackageType productPackageType;

        List<ProductPackage> list;

        Pager pager;

        public ProductPackageType getProductPackageType() {
            return productPackageType;
        }

        public List<ProductPackage> getList() {
            return list;
        }

        public Pager getPager() {
            return pager;
        }

        public String getCanDingjin() {
            return canDingjin;
        }
    }
}
