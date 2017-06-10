package com.sorrowdrug.gankiobyso.ui.classify;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by chentaikang on 2017/5/21 18:08.
 */

public class ClassifyFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ArrayList<ClassifyItemFragment> fragments;

    private String[] titles = {"全部分类", "Android", "iOS", "休息视频", "拓展资源", "前端"};
    private String[] type = {"all", "Android", "iOS", "休息视频", "拓展资源", "前端"};


    public ClassifyFragment() {
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void onInitView(View view, Bundle savedInstanceState) {

        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        fragments = new ArrayList<>();
        for (int i = 0, len = titles.length; i < len; i++) {
            fragments.add(ClassifyItemFragment.newInstance(type[i]));
        }

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        ClassifyFragmentAdapter adapter = new ClassifyFragmentAdapter(getChildFragmentManager(), fragments, titles);
        //预加载界面的个数
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onInitData() {

    }

    @Override
    protected void onInitPreData(View view, Bundle savedInstanceState) {
        super.onInitPreData(view, savedInstanceState);
    }
}
