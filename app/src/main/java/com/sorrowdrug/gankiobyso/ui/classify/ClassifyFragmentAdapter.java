package com.sorrowdrug.gankiobyso.ui.classify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by chentaikang on 2017/6/9 21:37.
 */

public class ClassifyFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<ClassifyItemFragment> fragments;
    private String[] titles;

    public ClassifyFragmentAdapter(FragmentManager fm, ArrayList<ClassifyItemFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
