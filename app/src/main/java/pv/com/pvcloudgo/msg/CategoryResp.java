package pv.com.pvcloudgo.msg;

import java.util.List;

import pv.com.pvcloudgo.bean.CRoot1;
import pv.com.pvcloudgo.bean.Category;

/**
 * Created by stefan on 17/1/9.
 */

public class CategoryResp extends BaseRespMsg {

    public Result results;

    public Result getResults() {
        return results;
    }

    public class Result {
        List<Category> ptTypeList;

        List<CRoot1> firstRoot;

        public List<Category> getPtTypeList() {
            return ptTypeList;
        }

        public List<CRoot1> getFirstRoot() {
            return firstRoot;
        }
    }
}
