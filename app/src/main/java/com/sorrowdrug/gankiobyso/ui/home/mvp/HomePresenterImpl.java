package com.sorrowdrug.gankiobyso.ui.home.mvp;

import android.text.TextUtils;

import com.sorrowdrug.gankiobyso.bean.HomeBean;
import com.sorrowdrug.gankiobyso.bean.HomeTypeBean;
import com.sorrowdrug.gankiobyso.bean.ItemType;
import com.sorrowdrug.gankiobyso.net.NetResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentaikang on 2017/5/27 20:52.
 */

public class HomePresenterImpl implements HomeConstraint.HomePresenter {

    private HomeConstraint.HomeView homeView;
    private HomeConstraint.HomeModel homeModel;

    public HomePresenterImpl(HomeConstraint.HomeView homeView) {
        this.homeView = homeView;
        this.homeModel = new HomeModelImpl();
    }

    @Override
    public void loadTab(int size) {
        homeView.showLoading();
        homeModel.loadTab(size, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                homeView.showContent();
                homeView.fillData(data);
            }

            @Override
            public void onError(String msg) {
                homeView.showError(msg);
            }
        });

    }

    @Override
    public void loadContent(String date, final String title) {

        //原有的格式为:2017-05-26T13:43:32.138Z
        date = TextUtils.substring(date,0,10);
        date = date.replace("-","/");

        homeView.showLoading();
        homeModel.loadContent(date, new NetResponse() {
            @Override
            public void onResponse(Object data) {
                HomeTypeBean typeBean = (HomeTypeBean) data;
                ArrayList<ItemType> items = new ArrayList<ItemType>();

                //title
                ItemType titleItem = new ItemType(ItemType.TYPE_TITLE,title);
                items.add(titleItem);

                //image
                List<HomeBean> girls = typeBean.get福利();
                if(girls.size()>0){
                    for(int i =0;i<girls.size();i++){
                        ItemType imageType = new ItemType(ItemType.TYPE_IMAGE,girls.get(i));
                        items.add(imageType);
                    }
                }

                //视频
                List<HomeBean> videos = typeBean.get休息视频();
                handlerTypeData(items,"休息视频",videos);

                //Android
                List<HomeBean> android = typeBean.getAndroid();
                handlerTypeData(items,"Android",android);

                //ios
                List<HomeBean> ios = typeBean.getIOS();
                handlerTypeData(items,"iOS",ios);

                //前端
                List<HomeBean> front = typeBean.get前端();
                handlerTypeData(items,"前端",front);

                //推荐
                List<HomeBean> recommend = typeBean.get瞎推荐();
                handlerTypeData(items,"瞎推荐",recommend);

                //拓展资源
                List<HomeBean>  expand = typeBean.get拓展资源();
                handlerTypeData(items,"拓展资源",expand);

                homeView.showContent();
                homeView.fillData(items);
            }

            @Override
            public void onError(String msg) {
                homeView.showError(msg);
            }
        });
    }

    private void handlerTypeData(ArrayList<ItemType> data,
                                 String subtitle,
                                 List<HomeBean> beans){

        if(beans !=null && beans.size()>0){
            ItemType subTitle = new ItemType(ItemType.TYPE_SUB_TITLE,subtitle);
            data.add(subTitle);
            for(int i = 0;i<beans.size();i++){
                ItemType content = new ItemType(ItemType.TYPE_CONTENT,beans.get(i));
                data.add(content);
            }
        }

    }
}
