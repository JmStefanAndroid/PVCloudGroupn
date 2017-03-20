package pv.com.pvcloudgo.model.msg;


import pv.com.pvcloudgo.model.base.BaseRespMsg;

public class CreateOrderRespMsg extends BaseRespMsg {



    private OrderRespMsg data;

    public OrderRespMsg getData() {
        return data;
    }

    public void setData(OrderRespMsg data) {
        this.data = data;
    }



}


