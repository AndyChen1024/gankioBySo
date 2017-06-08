package com.sorrowdrug.gankiobyso.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.sorrowdrug.gankiobyso.App;
import com.sorrowdrug.gankiobyso.utils.MD5Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chentaikang on 2017/6/3 13:27.
 */

public class PhotoPresentImpl implements PhotoContract.PhotoPresenter {

    PhotoContract.PhotoView view;
    String mUrl;
    private Subscription mSubscribe;

    public PhotoPresentImpl(PhotoContract.PhotoView view) {
        this.view = view;
    }

    @Override
    public void setPhoto(Intent intent, ImageView view) {
        mUrl = intent.getStringExtra("url");
        //带上的后缀是请求宽度:500的图片,非原图.为节约流量
        DrawableTypeRequest<String> load = Glide.with(view.getContext()).load(mUrl+"?imageView2/0/w/500");
        //渐入效果
        load.crossFade();
        load.asBitmap().into(view);
        this.view.showPhoto();
    }

    @Override
    public void savePhoto(ImageView view) {
        final BitmapDrawable drawable = (BitmapDrawable) view.getDrawable();
        if (drawable != null) {
            //做出提示
            Toast.makeText(view.getContext(), "开始保存", Toast.LENGTH_SHORT).show();

            //保存图片
            mSubscribe = Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {

                    FileOutputStream fileOutputStream = null;
                    try {
                        Bitmap bitmap = drawable.getBitmap();
                        File pics = App.picDirectory;
                        //文件名以时间为名
                        String fileName = MD5Util.md5(mUrl) + ".png";
                        File file = new File(pics, fileName);
                        fileOutputStream = new FileOutputStream(file);
                        //保存
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                        subscriber.onNext(file.getAbsolutePath());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }finally {
                        try{
                            if(fileOutputStream!=null)
                                fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        subscriber.onCompleted();
                    }
                }
            }).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(App.sContext,"哎哟,保存失败,可能内存不足哟~",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(String s) {
                            Toast.makeText(App.sContext,"保存在:"+s,Toast.LENGTH_SHORT).show();

                        }
                    });
        }else {
            Toast.makeText(view.getContext(),"保存失败,可能未加载",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void destroy() {
        if(mSubscribe!=null&&!mSubscribe.isUnsubscribed()){
            mSubscribe.unsubscribe();
        }
    }
}
