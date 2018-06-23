package com.example.test1;

import java.util.List;

/**
 *
 */

public class ItemInfo {
    private String wapUrl;
    private List<DataInfo> data;

    public ItemInfo() {
    }

    public String getWapUrl() {
        return wapUrl;
    }

    public void setWapUrl(String wapUrl) {
        this.wapUrl = wapUrl;
    }

    public List<DataInfo> getData() {
        return data;
    }

    public void setData(List<DataInfo> data) {
        this.data = data;
    }
}
