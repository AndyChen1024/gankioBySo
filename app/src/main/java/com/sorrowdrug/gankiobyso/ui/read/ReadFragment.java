package com.sorrowdrug.gankiobyso.ui.read;

import android.os.Bundle;
import android.view.View;

import com.sorrowdrug.gankiobyso.ui.base.BaseFragment;

/**
 * Created by chentaikang on 2017/5/21 18:08.
 */

public class ReadFragment extends BaseFragment {
    public ReadFragment() {
    }
    public static ReadFragment newInstance(){
        ReadFragment fragment = new ReadFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void onInitView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void onInitData() {

    }
}
