package com.sorrowdrug.gankiobyso.ui.home.mvp;

import com.sorrowdrug.gankiobyso.net.NetResponse;

/**
 * Created by chentaikang on 2017/5/27 14:14.
 */

public class HomeConstraint {
    public interface HomeView{
        void showLoading();
        void showContent();
        void showError(String msg);
        void fillData(Object data);
    }
    public interface HomePresenter{
        void loadTab(int size);
        void loadContent(String data,String title);
    }
    public interface HomeModel{
        void loadTab(int size,NetResponse response);
        void loadContent(String date,NetResponse response);
    }
}
