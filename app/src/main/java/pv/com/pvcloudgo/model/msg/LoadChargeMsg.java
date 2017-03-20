package pv.com.pvcloudgo.model.msg;

import java.util.List;

import pv.com.pvcloudgo.model.base.BaseRespMsg;
import pv.com.pvcloudgo.model.bean.ChargeRule;

/**
 * Created by Administrator on 2017/1/12.
 */
public class LoadChargeMsg extends BaseRespMsg {

    public Result results;

    public Result getResults() {
        return results;
    }

    public class Result{
        List<ChargeRule> ruleList;

        ChargeRule rule;

        public List<ChargeRule> getRuleList() {
            return ruleList;
        }

        public ChargeRule getRule() {
            return rule;
        }
    }
}
