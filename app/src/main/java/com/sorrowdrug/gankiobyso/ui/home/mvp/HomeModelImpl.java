package com.sorrowdrug.gankiobyso.ui.home.mvp;

import com.sorrowdrug.gankiobyso.ui.home.bean.HomeTabBean;
import com.sorrowdrug.gankiobyso.ui.home.bean.HomeTypeBean;
import com.sorrowdrug.gankiobyso.net.BaseResult;
import com.sorrowdrug.gankiobyso.net.NetResponse;
import com.sorrowdrug.gankiobyso.net.NetUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chentaikang on 2017/5/29 00:35.
 */

public class HomeModelImpl implements HomeConstraint.HomeModel {
    @Override
    public void loadTab(int size, final NetResponse response) {
        Observable<BaseResult<List<HomeTabBean>>> observable =
                NetUtils.getInstance().getApi().getHomeTab(size);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResult<List<HomeTabBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (response != null) {
                            response.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(BaseResult<List<HomeTabBean>> listBaseResult) {
                        if (!listBaseResult.isError()) {
                            if (response != null) {
                                response.onResponse(listBaseResult.getResults());
                            }
                        } else {
                            if (response != null) {
                                response.onError(listBaseResult.getMsg());
                            }
                        }
                    }
                });
    }

    @Override
    public void loadContent(String date, final NetResponse netResponse) {
        Observable<BaseResult<HomeTypeBean>> observable =
                NetUtils.getInstance().getApi().getHome(date);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResult<HomeTypeBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (netResponse != null) {
                            netResponse.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(BaseResult<HomeTypeBean> homeTypeBeanBaseResult) {
                        if (!homeTypeBeanBaseResult.isError()) {
                            if (netResponse != null) {
                                netResponse.onResponse(homeTypeBeanBaseResult.getResults());
                            }
                        } else {
                            if (netResponse != null) {
                                netResponse.onError(homeTypeBeanBaseResult.getMsg());
                            }
                        }
                    }
                });
    }
}
