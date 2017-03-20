package pv.com.pvcloudgo.model.bean;

import java.io.Serializable;

/**
 * Created by stefan on 17/1/4.
 */

public class Integration implements Serializable{
    String id;
    float historyTotal;
    String who;
    String whoId;
    float total;
    float available;
    float available2RMB;
    float mySelfBuyGet;
    String lastShengjiDate;
    String addressId;
    String addressTreeIds;

    public String getId() {
        return id;
    }

    public float getHistoryTotal() {
        return historyTotal;
    }

    public String getWho() {
        return who;
    }

    public String getWhoId() {
        return whoId;
    }

    public float getTotal() {
        return total;
    }

    public float getAvailable() {
        return available;
    }

    public float getAvailable2RMB() {
        return available2RMB;
    }

    public float getMySelfBuyGet() {
        return mySelfBuyGet;
    }

    public String getLastShengjiDate() {
        return lastShengjiDate;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getAddressTreeIds() {
        return addressTreeIds;
    }
}
