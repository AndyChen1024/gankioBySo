package com.sorrowdrug.gankiobyso.net;

import com.sorrowdrug.gankiobyso.ui.history.HistoryBean;
import com.sorrowdrug.gankiobyso.ui.classify.bean.ClassifyBean;
import com.sorrowdrug.gankiobyso.ui.home.bean.HomeTabBean;
import com.sorrowdrug.gankiobyso.ui.home.bean.HomeTypeBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chentaikang on 2017/5/28 22:47.
 */

public interface IApi {

//    @GET("history/content/{size}/1")
//    Call<BaseResult<List<HomeTabBean>>> getHomeTab(@Path("size") int size);

    //首页相关

    @GET("history/content/{size}/1")
    Observable<BaseResult<List<HomeTabBean>>> getHomeTab(@Path("size") int size);

    //例如:gank.io/api/day/2017/05/26
    @GET("day/{date}")
    Observable<BaseResult<HomeTypeBean>> getHome(@Path("date") String date);

    //分类相关

    @GET("data/{type}/10/{page}")
    Observable<BaseResult<List<ClassifyBean>>> getClassify(
            @Path("type") String type,@Path("page") int page
    );

    @GET("data/福利/10/{page}")
    Observable<BaseResult<List<HistoryBean>>> getHistory(@Path("page") int page);
}
