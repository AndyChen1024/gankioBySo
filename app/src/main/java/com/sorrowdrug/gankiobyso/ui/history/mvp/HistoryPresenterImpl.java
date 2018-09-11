package com.sorrowdrug.gankiobyso.ui.history.mvp;

import android.content.Context;

import com.sorrowdrug.gankiobyso.ui.history.HistoryBean;
import com.sorrowdrug.gankiobyso.net.NetResponse;

import java.util.List;

/**
 * Created by chentaikang on 2017/6/12 10:28.
 */

public class HistoryPresenterImpl implements HistoryConstraint.HistoryPresenter {

    private HistoryConstraint.HistoryModel model;
    private HistoryConstraint.HistoryView view;

    private Context context;
    private int page;

    public HistoryPresenterImpl(HistoryConstraint.HistoryView view, Context context) {
        this.view = view;

        this.context = context;
        this.model = new HistoryModelImpl();
    }

    @Override
    public void pull() {
        page = 1;
        model.loadContent(context, page, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                List<HistoryBean> list = (List<HistoryBean>) data;
                view.onPull(list);
                view.loadFinish();
            }

            @Override
            public void onError(String msg) {
                view.loadError(msg);
            }
        });
    }

    @Override
    public void push() {
        page++;
        model.loadContent(context, page, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                List<HistoryBean> list = (List<HistoryBean>) data;
                view.onPush(list);
                view.loadFinish();
            }

            @Override
            public void onError(String msg) {
                view.loadError(msg);
            }
        });
    }
}
