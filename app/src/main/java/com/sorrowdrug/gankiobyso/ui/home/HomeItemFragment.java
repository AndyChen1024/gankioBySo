package com.sorrowdrug.gankiobyso.ui.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.sorrowdrug.gankiobyso.bean.ItemType;
import com.sorrowdrug.gankiobyso.ui.base.BaseListFragment;
import com.sorrowdrug.gankiobyso.ui.home.mvp.HomeConstraint;
import com.sorrowdrug.gankiobyso.ui.home.mvp.HomePresenterImpl;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by chentaikang on 2017/5/27 23:25.
 */

public class HomeItemFragment extends BaseListFragment implements HomeConstraint.HomeView {

    private ArrayList<ItemType> datas = new ArrayList<>();

    public HomeItemFragment(){

    }
    
    public static HomeItemFragment getInstance(String title,String data){
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("data",data);
        HomeItemFragment fragment = new HomeItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void showContent() {
        super.showContent();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
    }

    @Override
    public void fillData(Object data) {
        datas.addAll((Collection<? extends  ItemType>)data);
        loadFinish();
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new HomeAdapter(getContext(),datas);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean isAddItemDecoration() {
        return false;
    }

    @Override
    protected void loadData() {
        Bundle args = getArguments();
        String title = args.getString("title");
        String data = args.getString("data");

        HomeConstraint.HomePresenter presenter = new HomePresenterImpl(this);
        presenter.loadContent(data,title);
    }

    @Override
    protected void addData() {

    }


}
