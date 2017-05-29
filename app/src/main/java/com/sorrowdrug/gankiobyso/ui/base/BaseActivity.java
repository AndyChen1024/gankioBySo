package com.sorrowdrug.gankiobyso.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by chentaikang on 2017/5/20 23:25.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!isShowDefaultTitleBar()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof BaseFragment) {
            mCurrentFragment = (BaseFragment) fragment;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onBackPressed() {
        boolean fmBackResult = false;
        if (mCurrentFragment != null) {
            String fragmentName = mCurrentFragment.getClass().getSimpleName();
            Log.d(TAG + "[onBackPressed]", "^o^ -- 调用了" + fragmentName + ".onBackPressed()");
            fmBackResult = mCurrentFragment.onBackPressed();
        }
        if (!fmBackResult) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG + "[onActivityResult]", "^o^ -- 调用了BaseActivity.onActivityResult(int, int, Intent)");

        if (mCurrentFragment != null) {
            Log.d(TAG + "[onActivityResult]",
                    "^o^ -- 调用了" + mCurrentFragment.getClass().getSimpleName() + ".onActivityResult(int, int, Intent)");
            mCurrentFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

//    @Override
//    public void onException(String api, Exception e) {
//        showToast(getString(R.string.ppc_comm_exception) + ":" + e.getClass().getSimpleName());
//    }
//
//    @Override
//    public void onApiError(String api, ApiError error) {
//        switch (error.getErrorCode()) {
//            case 1052:
//                AccountManager.getInstance(this).clearLoginInfo();
//                ImagePathManager.getInstance(this).clear();
//                DataSupport.deleteAll(Lesoon.class);
//                DataSupport.deleteAll(FreeDate.class);
//                showToast(AppResUtil.getApiErrorMsg(this, error.getErrorCode()));
//                AccountsActivity.showLogin(this);
//                break;
//            default:
//                showToast(AppResUtil.getApiErrorMsg(this, error.getErrorCode()));
//                break;
//        }
//    }



    /**
     * 是否需要显示默认的Android ActionBar，默认不显示
     *
     * @return 需要显示ActionBar返回true
     */
    protected boolean isShowDefaultTitleBar() {
        return false;
    }

    /**
     * 设置当前显示的Fragment
     *
     * @param fragment
     *            继承自{@link BaseFragment}的子类
     */
    protected void setCurrentFragment(Fragment fragment) {
        if (fragment instanceof BaseFragment) {
            mCurrentFragment = (BaseFragment) fragment;
        }
    }

    /**
     * <b>显示Toast</b>
     * <p style="text-indent:2em">
     * 默认显示短时长。
     *
     * @param msg
     *            需要显示的信息
     */
    protected void showToast(CharSequence msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    /**
     * <b>显示Toast</b>
     * <p style="text-indent:2em">
     * 默认显示短时长。
     *
     * @param rId
     *            需要显示的信息的资源id
     */
    protected void showToast(int rId) {
        showToast(rId, Toast.LENGTH_SHORT);
    }

    /**
     * <b>显示Toast</b>
     *
     * @param msg
     *            需要显示的信息
     * @param duration
     *            显示的时长
     */
    protected void showToast(CharSequence msg, int duration) {
        Toast.makeText(this, msg, duration).show();
    }

    /**
     * <b>显示Toast</b>
     *
     * @param rId
     *            需要显示的信息的资源id
     * @param duration
     *            显示的时长
     */
    protected void showToast(int rId, int duration) {
        Toast.makeText(this, rId, duration).show();
    }
}
