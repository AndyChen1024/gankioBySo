package com.sorrowdrug.gankiobyso.bean;

/**
 * Created by chentaikang on 2017/5/27 21:21.
 */

public class ItemType {
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_SUB_TITLE = 2;
    public static final int TYPE_CONTENT = 3;

    private int type;
    private Object data;

    public ItemType() {
    }

    public ItemType(int type, Object data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
