package com.sorrowdrug.gankiobyso.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.photo.PhotoContract;
import com.sorrowdrug.gankiobyso.photo.PhotoPresentImpl;
import com.sorrowdrug.gankiobyso.ui.base.BaseActivity;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoActivity extends BaseActivity implements PhotoContract.PhotoView{

    private PhotoView mPvPhoto;
    private TextView mTvBack;
    private TextView mTvDownload;
    private RelativeLayout mRlBottomLayout;
    private PhotoPresentImpl present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mPvPhoto = (PhotoView) findViewById(R.id.pv_photo);
        mTvBack = (TextView) findViewById(R.id.tv_back);
        mTvDownload = (TextView) findViewById(R.id.tv_download);
        mRlBottomLayout = (RelativeLayout) findViewById(R.id.rl_bottom_layout);
    }

    private void initListener(){
        //退出事件
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //保存事件
        mTvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                present.savePhoto(mPvPhoto);
            }
        });
        //给mRlBottomLayout打上标记标识开关
        mRlBottomLayout.setTag(true);
        mPvPhoto.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float v, float v1) {
                //控制动画渐渐检出的动画,这里没有做动画中的判断,因为photoView会收集用户快递点击的事件
                boolean open = (boolean) mRlBottomLayout.getTag();
                if(open){
                    mRlBottomLayout.animate().translationY(300f).start();
                    mRlBottomLayout.setTag(false);
                }else {
                    mRlBottomLayout.animate().translationY(0).start();
                }
                mRlBottomLayout.setTag(!open);
            }
        });
    }

    private void initData(){
        present = new PhotoPresentImpl(this);
        present.setPhoto(getIntent(),mPvPhoto);
    }

    @Override
    public void showPhoto() {


    }

    public void setPresent(PhotoPresentImpl present) {
        this.present = present;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        present.destroy();
    }
}
