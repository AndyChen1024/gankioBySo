package com.sorrowdrug.gankiobyso.ui.classify.mvp;

import android.content.Context;

import com.sorrowdrug.gankiobyso.net.BaseResult;
import com.sorrowdrug.gankiobyso.net.ExceptionHandle;
import com.sorrowdrug.gankiobyso.net.IApi;
import com.sorrowdrug.gankiobyso.net.MySubscribe;
import com.sorrowdrug.gankiobyso.net.NetHelper;
import com.sorrowdrug.gankiobyso.net.NetResponse;
import com.sorrowdrug.gankiobyso.net.NetUtils;
import com.sorrowdrug.gankiobyso.ui.classify.bean.ClassifyBean;

import java.util.List;

import rx.Observable;

/**
 * Created by chentaikang on 2017/6/10 07:24.
 */

class ClassifyModelImpl implements ClassifyContaint.IClassifyModel {
    @Override
    public void loadData(Context context, String type, int page, final NetResponse callback) {
        IApi api = NetUtils.getInstance().getApi();
        Observable<BaseResult<List<ClassifyBean>>> observable =
                api.getClassify(type,page);
        observable
                .compose(NetHelper.schedulersTransformer())
                .compose(NetHelper.transformer())
                .subscribe(new MySubscribe<List<ClassifyBean>>(context){

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        if(callback!=null){
                            callback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(List<ClassifyBean> classifyBeen) {
                        super.onNext(classifyBeen);
                        if(callback!=null){
                            callback.onResponse(classifyBeen);
                        }
                    }
                });
    }
}
