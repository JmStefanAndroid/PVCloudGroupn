
package pv.com.pvcloudgo.msg;


public class LoginResp<T> extends BaseRespMsg {

    Result results;


    public Result getResults() {
        return results;
    }


  public  class Result {
        T myUser;
        String token;

        public T getMyUser() {
            return myUser;
        }

        public String getToken() {
            return token;
        }
    }
}
