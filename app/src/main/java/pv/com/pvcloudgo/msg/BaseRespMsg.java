
package pv.com.pvcloudgo.msg;

import java.io.Serializable;


public class BaseRespMsg implements Serializable {

    public final static int STATUS_SUCCESS=1;
    public final static int STATUS_ERROR=0;
    public final static String MSG_SUCCESS="success";

    protected  int status=STATUS_SUCCESS;
    protected  String errorMessage;



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return errorMessage;
    }

    public void setMessage(String message) {
        this.errorMessage = message;
    }
}
