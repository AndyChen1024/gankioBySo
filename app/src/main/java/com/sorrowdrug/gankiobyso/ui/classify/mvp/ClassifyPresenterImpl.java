package com.sorrowdrug.gankiobyso.ui.classify.mvp;

import android.content.Context;

import com.sorrowdrug.gankiobyso.net.NetResponse;
import com.sorrowdrug.gankiobyso.ui.classify.bean.ClassifyBean;

import java.util.List;

/**
 * Created by chentaikang on 2017/6/8 15:46.
 */

public class ClassifyPresenterImpl implements ClassifyContaint.IClassifyPresenter {

    private ClassifyContaint.IClassifyView view;
    private ClassifyContaint.IClassifyModel model;
    private String type;
    private Context context;
    private int page;

    public ClassifyPresenterImpl(ClassifyContaint.IClassifyView view,  String type, Context context) {
        this.view = view;
        this.type = type;
        this.context = context;

        this.model = new ClassifyModelImpl();
    }

    @Override
    public void pull() {
        page = 1;
        model.loadData(context, type, page, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                List<ClassifyBean> list = (List<ClassifyBean>) data;
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
        model.loadData(context, type, page, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                List<ClassifyBean> list = (List<ClassifyBean>) data;
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
