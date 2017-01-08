package pv.com.pvcloudgo.bean;

import java.util.List;

/**
 * Created by stefan on 16/12/28.
 */

public class Floor {

    String id;
    String pageName;
    String name;
    String minName;
    String floorIndex;
    String topimage;
    String bitimage;
    String topimageurl;
    String botimageurl;
    String appType;
    List<Row> rowSet;

    public String getId() {
        return id;
    }

    public String getPageName() {
        return pageName;
    }

    public String getName() {
        return name;
    }

    public String getMinName() {
        return minName;
    }

    public String getFloorIndex() {
        return floorIndex;
    }

    public String getTopimage() {
        return topimage;
    }

    public String getBitimage() {
        return bitimage;
    }

    public String getTopimageurl() {
        return topimageurl;
    }

    public String getBotimageurl() {
        return botimageurl;
    }

    public String getAppType() {
        return appType;
    }

    public List<Row> getRowSet() {
        return rowSet;
    }
}
