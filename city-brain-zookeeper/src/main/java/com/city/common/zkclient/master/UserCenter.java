package com.city.common.zkclient.master;

import java.io.Serializable;

//用户中心
public class UserCenter implements Serializable {

    private int pcId;
    private String pcName;
    private boolean flag = true;

    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "UserCenter{" +
                "pcId=" + pcId +
                ", pcName='" + pcName + '\'' +
                ", flag=" + flag +
                '}';
    }
}
