package pv.com.pvcloudgo.model.bean;

import java.io.Serializable;

/**
 * Created by stefan on 17/1/4.
 */

public class Account implements Serializable {

    String whoclassName;
    String id;
    String whoId;
    String whoName;
    String withdrawalsPwd;
    float totalPrice;
    float frozenPrice;
    float availablePrice;
    String isQueren;
    String addressId;
    String addressTreeIds;

    public String getWhoclassName() {
        return whoclassName;
    }

    public String getId() {
        return id;
    }

    public String getWhoId() {
        return whoId;
    }

    public String getWhoName() {
        return whoName;
    }

    public String getWithdrawalsPwd() {
        return withdrawalsPwd;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getFrozenPrice() {
        return frozenPrice;
    }

    public float getAvailablePrice() {
        return availablePrice;
    }

    public String getIsQueren() {
        return isQueren;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getAddressTreeIds() {
        return addressTreeIds;
    }
}
