
package pv.com.pvcloudgo.model.msg;


import pv.com.pvcloudgo.model.base.BaseRespMsg;
import pv.com.pvcloudgo.model.bean.User;

public class LoginResp extends BaseRespMsg {

    Result results;


    public Result getResults() {
        return results;
    }


    public class Result {
        User myUser;
        String token;

        public User getMyUser() {
            return myUser;
        }

        public String getToken() {
            return token;
        }
    }
}
