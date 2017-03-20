package pv.com.pvcloudgo.model.bean;

import java.io.Serializable;

/**
 * Created by stefan on 17/1/4.
 */

public class GradeRule implements Serializable {

    String id;
    String name;
    int grade;
    double beginv;
    double endv;
    float jinhuoPrice;
    float jinhuoDjPrice;
    float vticheng;
    String tuangouKaoheBiaozhunType;
    float gouwuxingweiBiaozhunV;
    float meiyueGouwuxiaofeiV;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public double getBeginv() {
        return beginv;
    }

    public double getEndv() {
        return endv;
    }

    public float getJinhuoPrice() {
        return jinhuoPrice;
    }

    public float getJinhuoDjPrice() {
        return jinhuoDjPrice;
    }

    public float getVticheng() {
        return vticheng;
    }

    public String getTuangouKaoheBiaozhunType() {
        return tuangouKaoheBiaozhunType;
    }

    public float getGouwuxingweiBiaozhunV() {
        return gouwuxingweiBiaozhunV;
    }

    public float getMeiyueGouwuxiaofeiV() {
        return meiyueGouwuxiaofeiV;
    }
}
