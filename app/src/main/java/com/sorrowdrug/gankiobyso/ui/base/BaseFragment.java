package com.sorrowdrug.gankiobyso.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.bumptech.glide.Glide;

/**
 * Created by chentaikang on 2017/5/20 23:51.
 */

public abstract class BaseFragment extends Fragment{

    protected View mRootView;

    /**
     * 设置Fragment显示的布局
     *
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutResource();

    /**
     * 初始化 UI
     *
     * @param view  RootView
     * @param savedInstanceState  销毁前缓存的数据
     */
    protected abstract void  onInitView(View view, Bundle savedInstanceState);

    /**
     * 加载数据
     */
    protected abstract void onInitData();

    /**
     * 用于在初始化Data之前做一些事
     */
    protected void onInitPreData(View view, Bundle savedInstanceState){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getLayoutResource()!=0){
            mRootView = inflater.inflate(getLayoutResource(),container,false);
        }else {
            mRootView = super.onCreateView(inflater,container,savedInstanceState);
        }

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onInitView(view,savedInstanceState);
        onInitPreData(view,savedInstanceState);

        //在布局加载完成之后，再加载数据
        //自动加载
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //在布局加载完成之后，才能执行，不然看不到效果
                onInitData();
                mRootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    //在系统内存不足，所有后台程序（优先级为background的进程，
    // 不是指后台运行的进程）都被杀死时，系统会调用OnLowMemory。
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getContext()).clearMemory();
    }

    public boolean onBackPressed(){
        return false;
    }

    //    private BaseActivity mActivity;
//
//    /**
//     * <b>输入法管理器</b>
//     * <p>
//     * 由{@link BaseFragment}中定义的protected成员变量，子类可用。<br/>
//     */
//    protected InputMethodManager inputMethodManager;
//
//    /** 等同于{@link Activity#RESULT_OK}。值为-1 */
//    protected static final int RESULT_OK = Activity.RESULT_OK;
//    /** 等同于{@link Activity#RESULT_CANCELED}。值为0 */
//    protected static final int RESULT_CANCELED = Activity.RESULT_CANCELED;
//    /** 等同于{@link Activity#RESULT_FIRST_USER}。值为1 */
//    protected static final int RESULT_FIRST_USER = Activity.RESULT_FIRST_USER;
//
//    @SuppressWarnings("deprecation")
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//
//        if (activity instanceof BaseActivity) {
//            mActivity = (BaseActivity) activity;
//        }
//
//        inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//
//    }
//
////    @Override
////    public void onException(String api, Exception e) {
////        if (mActivity != null) {
////            mActivity.onException(api, e);
////            return;
////        }
//////        Toasty.error(getActivity(), getString(R.string.ppc_comm_exception) + ":" + e.getClass().getSimpleName(), Toast.LENGTH_SHORT, true).show();
////        Toast.makeText(getActivity(), getString(R.string.ppc_comm_exception) + ":" + e.getClass().getSimpleName(), Toast.LENGTH_SHORT, true).show();
////
////    }
////
////    @Override
////    public void onApiError(String api, ApiError error) {
////        if (mActivity != null) {
////            mActivity.onApiError(api, error);
////            return;
////        }
////        switch (error.getErrorCode()) {
////            case 1052:
////                AccountManager.getInstance(getActivity()).clearLoginInfo();
////                ImagePathManager.getInstance(getActivity()).clear();
////                DataSupport.deleteAll(Lesoon.class);
////                DataSupport.deleteAll(FreeDate.class);
////                Toasty.error(getActivity() ,AppResUtil.getApiErrorMsg(getActivity(), error.getErrorCode()), Toast.LENGTH_SHORT, true).show();
////                AccountsActivity.showLogin(getActivity());
////                break;
////            default:
////                Toasty.error(getActivity(), AppResUtil.getApiErrorMsg(getActivity(), error.getErrorCode()), Toast.LENGTH_SHORT, true).show();
////                break;
////        }
////    }
//
//    /**
//     * 封装了{@link Activity#onBackPressed()}<br>
//     * 当Attached Activity的onBackPressed()触发时被调用
//     *
//     * @return 如果处理了Back事件，返回true
//     */
//    public boolean onBackPressed() {
//        return false;
//    }
//
//    /**
//     * 封装了对{@link Activity#setResult(int, Intent)}的调用
//     *
//     * @param resultCode
//     * @param data
//     * @see Activity#setResult(int, Intent)
//     */
//    protected void setResult(int resultCode, Intent data) {
//        getActivity().setResult(resultCode, data);
//    }
//
//    /**
//     * 封装了对{@link Activity#getIntent()}的调用
//     *
//     * @return 当前Activity的Intent对象
//     * @see Activity#getIntent()
//     */
//    protected Intent getIntent() {
//        return getActivity().getIntent();
//    }
//
//    /**
//     * 隐藏输入法软键盘
//     */
//    protected void hideSoftKeyboard() {
//        int softInputMode = getActivity().getWindow().getAttributes().softInputMode;
//        if (softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
//            if (getActivity().getCurrentFocus() != null) {
//                IBinder windowToken = getActivity().getCurrentFocus().getWindowToken();
//                inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        }
//    }
//
//    /**
//     * 封装了对{@link Activity#finish()}的调用
//     *
//     * @see Activity#finish()
//     */
//    protected void finish() {
//        getActivity().finish();
//    }
//
//    /**
//     * <b>显示Toast</b>
//     * <p style="text-indent:2em">
//     * 默认显示短时长。
//     *
//     * @param msg
//     *            需要显示的信息
//     */
//    protected void showToast(CharSequence msg) {
//        showToast(msg, Toast.LENGTH_SHORT);
//    }
//
//	/**
//	 * <b>显示Toast</b>
//	 * <p style="text-indent:2em">
//	 * 默认显示短时长。
//	 *
//	 * @param rId
//	 *            需要显示的信息的资源id
//	 */
//    protected void showToast(int rId) {
//        showToast(rId, Toast.LENGTH_SHORT);
//    }
//
//	/**
//	 * <b>显示Toast</b>
//	 *
//	 * @param msg
//	 *            需要显示的信息
//	 * @param duration
//	 *            显示的时长
//	 */
//    protected void showToast(CharSequence msg, int duration) {
//        Toast.makeText(getActivity(), msg, duration).show();
//    }
//
//	/**
//	 * <b>显示Toast</b>
//	 *
//	 * @param rId
//	 *            需要显示的信息的资源id
//	 * @param duration
//	 *            显示的时长
//	 */
//    protected void showToast(int rId, int duration) {
//        Toast.makeText(getActivity(), rId, duration).show();
//    }
}
