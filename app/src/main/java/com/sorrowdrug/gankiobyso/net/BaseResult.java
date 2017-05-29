package com.sorrowdrug.gankiobyso.net;

/**
 * Created by chentaikang on 2017/5/28 23:04.
 */

public class BaseResult<T> {
    private boolean error;
    private T results;
    private String msg;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
