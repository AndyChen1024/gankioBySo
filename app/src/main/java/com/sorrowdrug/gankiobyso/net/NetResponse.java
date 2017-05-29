package com.sorrowdrug.gankiobyso.net;

/**
 * Created by chentaikang on 2017/5/27 14:19.
 */

public interface NetResponse {
    void onResponse(Object data);
    void onError(String msg);
}
