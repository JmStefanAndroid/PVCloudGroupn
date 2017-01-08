
package pv.com.pvcloudgo.msg;


import pv.com.pvcloudgo.bean.User;

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
