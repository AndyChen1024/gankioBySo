package com.sorrowdrug.gankiobyso.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.widget.StatusViewLayout;

/**
 * Created by chentaikang on 2017/5/27 23:26.
 */

public abstract class BaseListFragment extends LazyFragment {

    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected StatusViewLayout mStatusView;
    protected RecyclerView.Adapter mAdapter;

    /**
     * 设置显示的Adapter
     *
     * @return
     */
    protected abstract RecyclerView.Adapter getAdapter();

    /**
     * 下拉刷新,加载数据
     */
    protected abstract void loadData();

    /**
     * 上拉加载,加载数据
     */
    protected abstract void addData();

    /**
     * 加载完成,取消加载状态
     */
    public void loadFinish() {
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    protected boolean enableRefresh() {
        return true;
    }

    protected boolean isAddItemDecoration() {
        return true;
    }

    @Override
    protected void onInitView(View v) {

        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_red_light),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_blue_light)
        );
        if (isAddItemDecoration()) {
            //分割线
            DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(decoration);
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = getAdapter();
        if (mAdapter != null)
            mRecyclerView.setAdapter(mAdapter);
        if (!enableRefresh()) {
            mSwipeRefreshLayout.setEnabled(false);
            loadData();
            return;
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //滑到底部,加载新数据
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    if (!mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        addData();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    int last = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    if (last != -1) {
                        lastVisibleItem = last;
                    }
                }
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);
        loadData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = super.onCreateView(inflater, container, savedInstanceState);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swiprefreshView);
        mStatusView = (StatusViewLayout) mRootView.findViewById(R.id.statusView);

        return mRootView;
    }

    @Override
    protected void onInitData() {

    }

    public void showLoading() {
        mStatusView.showLoading();
    }

    public void showContent() {
        mStatusView.showContent();
    }

    public void showError(String msg) {
        mStatusView.showError();
    }
}
