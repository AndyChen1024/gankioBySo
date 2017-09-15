package com.sorrowdrug.gankiobyso.ui.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.sorrowdrug.gankiobyso.App;
import com.sorrowdrug.gankiobyso.ui.base.BaseListFragment;
import com.sorrowdrug.gankiobyso.ui.classify.bean.ClassifyBean;
import com.sorrowdrug.gankiobyso.ui.classify.mvp.ClassifyContaint;
import com.sorrowdrug.gankiobyso.ui.classify.mvp.ClassifyPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentaikang on 2017/6/8 15:28.
 */

public class ClassifyItemFragment extends BaseListFragment
        implements ClassifyContaint.IClassifyView {

    private static final String BUNDLE_KEY = "CLASSIFY";
    private String type;
    private ArrayList<ClassifyBean> mData = new ArrayList<>();
    ClassifyContaint.IClassifyPresenter presenter;

    public ClassifyItemFragment() {
    }

    public static ClassifyItemFragment newInstance(String type) {
        ClassifyItemFragment fragment = new ClassifyItemFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(BUNDLE_KEY);
        }
        presenter = new ClassifyPresenterImpl(this, type, App.sContext);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new ClassifyAdapter(getContext(), mData);
    }

    @Override
    protected void loadData() {
        presenter.pull();
    }

    @Override
    protected void addData() {
        presenter.push();
    }

    @Override
    public void onPull(List<ClassifyBean> data) {
        mData.clear();
        mData.addAll(data);
    }

    @Override
    public void onPush(List<ClassifyBean> data) {
        mData.addAll(data);
    }

    @Override
    public void loadError(String msg) {
        mStatusView.showError(msg);
    }

    @Override
    public void loadFinish() {
        super.loadFinish();
        mStatusView.showContent();
    }
}
