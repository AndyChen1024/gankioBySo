package com.sorrowdrug.gankiobyso.ui.classify.bean;

import java.util.List;

/**
 * Created by chentaikang on 2017/6/8 14:27.
 */

public class ClassifyBean {

    /**
     *
     " "_id": "5938a2c9421aa92c769a8c32",
     "createdAt": "2017-06-08T09:05:13.875Z",
     "desc": "snackbar扩展，可以显示进度.",
     "images": [
     "http://img.gank.io/88fb40c1-e9fb-4ffd-9a55-0a822a04f961"
     ],
     "publishedAt": "2017-06-08T11:27:47.21Z",
     "source": "web",
     "type": "Android",
     "url": "https://github.com/tingyik90/snackprogressbar",
     "used": true,
     "who": "ShineYang"
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
