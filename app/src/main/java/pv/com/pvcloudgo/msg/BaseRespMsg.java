
package pv.com.pvcloudgo.msg;

import java.io.Serializable;


public class BaseRespMsg implements Serializable {

    public final static String STATUS_SUCCESS = "ok";
    public final static String STATUS_ERROR = "error";
    public final static String MSG_SUCCESS = "success";

    protected String status = STATUS_SUCCESS;
    protected String errorMessage;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return errorMessage;
    }

    public void setMessage(String message) {
        this.errorMessage = message;
    }
}
