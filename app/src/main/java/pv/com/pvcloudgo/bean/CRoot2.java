package pv.com.pvcloudgo.bean;


import java.util.List;

public class CRoot2 extends BaseBean {

    String orderIndex;
    String webTiCheng;
    String isQuanqiugou;
    String quanqiugouShuiType;
    float omoy_root_backbill;
    String status;
    String wrap_ptlist_rootImage;
    String wrap_ptlist_threeImage;
    String name;
    int rootTypeId;
    int isRoot;
    int isLeaf;
    int jibie;
    String url;
    String treeId;
    int fatherId;
    String allName;
    List<Category> childs;


    public List<Category> getChilds() {
        return childs;
    }

    public CRoot2() {
    }

    public CRoot2(String name) {

        this.name = name;
    }

    public CRoot2(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getOrderIndex() {
        return orderIndex;
    }

    public String getWebTiCheng() {
        return webTiCheng;
    }

    public String getIsQuanqiugou() {
        return isQuanqiugou;
    }

    public String getQuanqiugouShuiType() {
        return quanqiugouShuiType;
    }

    public float getOmoy_root_backbill() {
        return omoy_root_backbill;
    }

    public String getStatus() {
        return status;
    }

    public String getWrap_ptlist_rootImage() {
        return wrap_ptlist_rootImage;
    }

    public String getWrap_ptlist_threeImage() {
        return wrap_ptlist_threeImage;
    }

    public int getRootTypeId() {
        return rootTypeId;
    }

    public int getIsRoot() {
        return isRoot;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public int getJibie() {
        return jibie;
    }

    public String getUrl() {
        return url;
    }

    public String getTreeId() {
        return treeId;
    }

    public int getFatherId() {
        return fatherId;
    }

    public String getAllName() {
        return allName;
    }
}
