package com.sorrowdrug.gankiobyso.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.history.HistoryAdapter;
import com.sorrowdrug.gankiobyso.history.HistoryBean;
import com.sorrowdrug.gankiobyso.history.mvp.HistoryConstraint;
import com.sorrowdrug.gankiobyso.history.mvp.HistoryPresenterImpl;
import com.sorrowdrug.gankiobyso.ui.base.BaseActivity;

import com.sorrowdrug.gankiobyso.utils.WaterUtil;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity implements HistoryConstraint.HistoryView {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private HistoryConstraint.HistoryPresenter presenter;
    private ArrayList<HistoryBean> mData = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //使状态栏透明
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();
        presenter = new HistoryPresenterImpl(this, getApplicationContext());
        initView();

    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiprefreshView_history);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_red_light),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_blue_light)
        );

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_history);
        recyclerView.setHasFixedSize(true);
        staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new HistoryAdapter(this, mData);
        recyclerView.setAdapter(mAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.pull();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //滑到底部,加载新数据
                Log.i("TAG","当前newState:"+newState+"....以及lastVisibleItem+1 = :"+(lastVisibleItem+1));
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisibleItem + 1 == mAdapter.getItemCount()||lastVisibleItem+2 == mAdapter.getItemCount()) {
                    if (!swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(true);
                        presenter.push();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager instanceof StaggeredGridLayoutManager) {
//                    int last = ((StaggeredGridLayoutManager) layoutManager).;
//                    if (last != -1) {
//                        lastVisibleItem = last;
//                    }
                    int columnCount = layoutManager.getColumnCountForAccessibility(null,null);
                    int[] positions = new int[columnCount];
                    layoutManager.findLastVisibleItemPositions(positions);
                    int last = positions[positions.length-1];
                    if(last!=-1){
                        lastVisibleItem = last;
                    }
                }
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        presenter.pull();
    }


    @Override
    public void onPull(List<HistoryBean> data) {
        mData.clear();
        mData.addAll(data);
    }

    @Override
    public void onPush(List<HistoryBean> data) {

        mData.addAll(data);
    }

    @Override
    public void loadFinish() {
        mAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadError(String msg) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
