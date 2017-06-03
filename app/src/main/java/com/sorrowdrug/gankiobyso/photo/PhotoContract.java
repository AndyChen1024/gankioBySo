package com.sorrowdrug.gankiobyso.photo;

import android.content.Intent;
import android.widget.ImageView;

/**
 * Created by chentaikang on 2017/6/3 11:58.
 */

public interface PhotoContract {
    interface PhotoView{
        void showPhoto();//展示图片
    }
    interface PhotoModel{

    }
    interface PhotoPresenter{
        void setPhoto(Intent intent, ImageView view);//设置图片
        void savePhoto(ImageView view);//保存图片
        void destroy();//销毁图片
    }
}
