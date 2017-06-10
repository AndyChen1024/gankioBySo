package com.sorrowdrug.gankiobyso.ui.classify.mvp;

import android.content.Context;

import com.sorrowdrug.gankiobyso.net.NetResponse;
import com.sorrowdrug.gankiobyso.ui.classify.bean.ClassifyBean;

import java.util.List;

/**
 * Created by chentaikang on 2017/6/8 15:30.
 */

public class ClassifyContaint {
    public interface IClassifyView{
        void onPull(List<ClassifyBean> data);//下拉刷新
        void onPush(List<ClassifyBean> data);//上拉加载
        void loadFinish();
        void loadError(String msg);
    }

    public interface IClassifyPresenter{
        void pull();
        void push();
    }

    public interface IClassifyModel{
        void loadData(Context context, String type, int page, NetResponse callback);
    }
}
