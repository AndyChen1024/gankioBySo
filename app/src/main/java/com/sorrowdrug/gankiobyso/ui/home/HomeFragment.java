package com.sorrowdrug.gankiobyso.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.bean.HomeTabBean;
import com.sorrowdrug.gankiobyso.ui.base.BaseFragment;
import com.sorrowdrug.gankiobyso.ui.home.mvp.HomeConstraint;
import com.sorrowdrug.gankiobyso.ui.home.mvp.HomePresenterImpl;
import com.sorrowdrug.gankiobyso.widget.StatusViewLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chentaikang on 2017/5/21 18:06.
 */

public class HomeFragment extends BaseFragment implements HomeConstraint.HomeView {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private StatusViewLayout statusView;

    public static final int TAB_COUNT = 5;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public void showLoading() {
        statusView.showLoading();
    }

    @Override
    public void showContent() {
        statusView.showContent();
    }

    @Override
    public void showError(String msg) {
        statusView.showError(msg);
    }

    @Override
    public void fillData(Object data) {
        List<HomeTabBean> tabBeans = (List<HomeTabBean>) data;
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        for(int i=0;i<tabBeans.size();i++){
            HomeTabBean tabBean = tabBeans.get(i);
            fragments.add(HomeItemFragment
                    .getInstance(tabBean.getTitle(),tabBean.getPublishedAt()));
        }
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(
                getChildFragmentManager(),fragments);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView(View view, Bundle savedInstanceState) {
        tabLayout = (TabLayout) view.findViewById(R.id.home_tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.home_viewPager);
        statusView = (StatusViewLayout) view.findViewById(R.id.statusView);
    }

    @Override
    protected void onInitData() {
        HomeConstraint.HomePresenter presenter = new HomePresenterImpl(this);
        presenter.loadTab(TAB_COUNT);
    }
}
