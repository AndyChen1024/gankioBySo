package com.sorrowdrug.gankiobyso.ui.home.bean;

import java.util.List;

/**
 * Created by chentaikang on 2017/5/27 21:14.
 */

public class HomeTypeBean {
    private List<HomeBean> Android;
    private List<HomeBean> iOS;
    private List<HomeBean> 休息视频;
    private List<HomeBean> 拓展资源;
    private List<HomeBean> 瞎推荐;
    private List<HomeBean> 福利;
    private List<HomeBean> 前端;

    public List<HomeBean> getAndroid() {
        return Android;
    }

    public void setAndroid(List<HomeBean> Android) {
        this.Android = Android;
    }

    public List<HomeBean> getIOS() {
        return iOS;
    }

    public void setIOS(List<HomeBean> iOS) {
        this.iOS = iOS;
    }

    public List<HomeBean> get休息视频() {
        return 休息视频;
    }

    public void set休息视频(List<HomeBean> 休息视频) {
        this.休息视频 = 休息视频;
    }

    public List<HomeBean> get拓展资源() {
        return 拓展资源;
    }

    public void set拓展资源(List<HomeBean> 拓展资源) {
        this.拓展资源 = 拓展资源;
    }

    public List<HomeBean> get瞎推荐() {
        return 瞎推荐;
    }

    public void set瞎推荐(List<HomeBean> 瞎推荐) {
        this.瞎推荐 = 瞎推荐;
    }

    public List<HomeBean> get福利() {
        return 福利;
    }

    public void set福利(List<HomeBean> 福利) {
        this.福利 = 福利;
    }


    public List<HomeBean> get前端() {
        return 前端;
    }

    public void set前端(List<HomeBean> 前端) {
        this.前端 = 前端;
    }
}
