
package pv.com.pvcloudgo.model.msg;


import java.io.Serializable;

import pv.com.pvcloudgo.model.base.BaseRespMsg;
import pv.com.pvcloudgo.model.bean.Account;
import pv.com.pvcloudgo.model.bean.GradeRule;
import pv.com.pvcloudgo.model.bean.Integration;
import pv.com.pvcloudgo.model.bean.User;

public class MineMsg extends BaseRespMsg {
    Result results;

    public Result getResult() {
        return results;
    }

    public void setResult(Result results) {
        this.results = results;
    }

    public class Result implements Serializable{
        User myUser;
        Account account;
        Integration myIntegration;
        GradeRule myGradeRule;
        int sccount;
        int allFensiCount;
        int yhqwaituse;
        int brankcount;
        int order_waitpay_count;
        int order_waitconfirm_count;


        public User getMyUser() {
            return myUser;
        }

        public Account getAccount() {
            return account;
        }

        public Integration getMyIntegration() {
            return myIntegration;
        }

        public GradeRule getMyGradeRule() {
            return myGradeRule;
        }

        public int getSccount() {
            return sccount;
        }

        public int getAllFensiCount() {
            return allFensiCount;
        }

        public int getYhqwaituse() {
            return yhqwaituse;
        }

        public int getBrankcount() {
            return brankcount;
        }

        public int getOrder_waitpay_count() {
            return order_waitpay_count;
        }

        public int getOrder_waitconfirm_count() {
            return order_waitconfirm_count;
        }
    }

}
