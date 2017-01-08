
package pv.com.pvcloudgo.bean;

import java.io.Serializable;


public class User implements Serializable {


    private Long id;
    private String logo_url;
    private String username;
    private String mobi;

    String wxId;
    String bianhan;
    String name;
    String nicheng, password, telPhone, email, touxiang, touxiangMin, qq, weibo, addressTreeIds, selfInfo, status, vo_sex;
    String zhiye, aihao, jiatingzhuzhi, vo_gradeName, salt, laiyuan;
    int addressId, sex, age, shopId, vckDataId;//普通-0，商家-！0
    String zhuceDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobi() {
        return mobi;
    }

    public void setMobi(String mobi) {
        this.mobi = mobi;
    }

    public String getWxId() {
        return wxId;
    }

    public String getBianhan() {
        return bianhan;
    }

    public String getName() {
        return name;
    }

    public String getNicheng() {
        return nicheng;
    }

    public String getPassword() {
        return password;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public String getTouxiangMin() {
        return touxiangMin;
    }

    public String getQq() {
        return qq;
    }

    public String getWeibo() {
        return weibo;
    }

    public String getAddressTreeIds() {
        return addressTreeIds;
    }

    public String getSelfInfo() {
        return selfInfo;
    }

    public String getStatus() {
        return status;
    }

    public String getVo_sex() {
        return vo_sex;
    }

    public String getZhiye() {
        return zhiye;
    }

    public String getAihao() {
        return aihao;
    }

    public String getJiatingzhuzhi() {
        return jiatingzhuzhi;
    }

    public String getVo_gradeName() {
        return vo_gradeName;
    }

    public String getSalt() {
        return salt;
    }

    public String getLaiyuan() {
        return laiyuan;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public int getShopId() {
        return shopId;
    }

    public int getVckDataId() {
        return vckDataId;
    }

    public String getZhuceDate() {
        return zhuceDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", logo_url='" + logo_url + '\'' +
                ", username='" + username + '\'' +
                ", mobi='" + mobi + '\'' +
                ", wxId='" + wxId + '\'' +
                ", bianhan='" + bianhan + '\'' +
                ", name='" + name + '\'' +
                ", nicheng='" + nicheng + '\'' +
                ", password='" + password + '\'' +
                ", telPhone='" + telPhone + '\'' +
                ", email='" + email + '\'' +
                ", touxiang='" + touxiang + '\'' +
                ", touxiangMin='" + touxiangMin + '\'' +
                ", qq='" + qq + '\'' +
                ", weibo='" + weibo + '\'' +
                ", addressTreeIds='" + addressTreeIds + '\'' +
                ", selfInfo='" + selfInfo + '\'' +
                ", status='" + status + '\'' +
                ", vo_sex='" + vo_sex + '\'' +
                ", zhiye='" + zhiye + '\'' +
                ", aihao='" + aihao + '\'' +
                ", jiatingzhuzhi='" + jiatingzhuzhi + '\'' +
                ", vo_gradeName='" + vo_gradeName + '\'' +
                ", salt='" + salt + '\'' +
                ", laiyuan='" + laiyuan + '\'' +
                ", addressId=" + addressId +
                ", sex=" + sex +
                ", age=" + age +
                ", shopId=" + shopId +
                ", vckDataId=" + vckDataId +
                ", zhuceDate='" + zhuceDate + '\'' +
                '}';
    }
}
