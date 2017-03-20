
package pv.com.pvcloudgo.model.bean;

import java.io.Serializable;

public class Favorites implements Serializable {


    private Long id;
    private Long createTime;
    private Wares wares;



    public Favorites(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Wares getWares() {
        return wares;
    }

    public void setWares(Wares wares) {
        this.wares = wares;
    }
}
