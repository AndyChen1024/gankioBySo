package com.sorrowdrug.gankiobyso.ui.history.mvp;

import android.content.Context;

import com.sorrowdrug.gankiobyso.ui.history.HistoryBean;
import com.sorrowdrug.gankiobyso.net.NetResponse;

import java.util.List;

/**
 * Created by chentaikang on 2017/6/12 08:36.
 */

public class HistoryConstraint {
    public interface HistoryView{
        void onPull(List<HistoryBean> data);//下拉刷新
        void onPush(List<HistoryBean> data);//上拉加载
        void loadFinish();
        void loadError(String msg);
    }
    public interface HistoryPresenter{

        void pull();
        void push();
    }
    public interface HistoryModel{

        void loadContent(Context context, int page, NetResponse response);
    }
}
