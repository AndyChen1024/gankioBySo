package com.sorrowdrug.gankiobyso.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.ui.base.BaseActivity;

public class HistoryInfoActivity extends BaseActivity {

  private String img_url;
  private ImageView ivInfo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history_info);

    ivInfo = findViewById(R.id.iv_info_history);
    if(getIntent()!=null){
      img_url = getIntent().getStringExtra("img_url");
    }
    initView();
  }

  private void initView() {
    //Glide.with(this).load(img_url).into(ivInfo);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      postponeEnterTransition();
    }
    RequestOptions options = new RequestOptions();
    options.centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);
    Glide.with(this)
        .asBitmap()
        .load(img_url)
        .apply(options)
        .into(new SimpleTarget<Bitmap>() {
          @Override
          public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
            ivInfo.setImageBitmap(resource);
            scheduleStartPostponedTransition(ivInfo);
          }

          //@Override
          //
          //public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
          //  ivInfo.setImageBitmap(resource);
          //  scheduleStartPostponedTransition(ivInfo);
          //}
        });
  }

  private void scheduleStartPostponedTransition(final View sharedElement) {
    sharedElement.getViewTreeObserver().addOnPreDrawListener(
        new ViewTreeObserver.OnPreDrawListener() {
          @TargetApi(Build.VERSION_CODES.LOLLIPOP)
          @Override
          public boolean onPreDraw() {
            sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
            startPostponedEnterTransition();
            return true;
          }
        });
  }

}
