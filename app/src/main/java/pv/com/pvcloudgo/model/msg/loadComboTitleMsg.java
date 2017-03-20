package pv.com.pvcloudgo.model.msg;

import java.util.List;

import pv.com.pvcloudgo.model.base.BaseRespMsg;
import pv.com.pvcloudgo.model.bean.Combo;

/**
 * Created by Administrator on 2017/1/12.
 */
public class loadComboTitleMsg extends BaseRespMsg {

    public Result results;

    public Result getResults() {
        return results;
    }

    public class Result {

        List<Combo> list;

        public List<Combo> getList() {
            return list;
        }
    }
}
