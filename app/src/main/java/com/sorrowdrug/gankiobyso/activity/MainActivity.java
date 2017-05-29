package com.sorrowdrug.gankiobyso.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.sorrowdrug.gankiobyso.R;
import com.sorrowdrug.gankiobyso.ui.base.BaseActivity;
import com.sorrowdrug.gankiobyso.ui.classify.ClassifyFragment;
import com.sorrowdrug.gankiobyso.ui.home.HomeFragment;
import com.sorrowdrug.gankiobyso.ui.mine.MineFragment;
import com.sorrowdrug.gankiobyso.ui.read.ReadFragment;

/**
 * <b>app主页Activity</b>
 */
public class MainActivity extends BaseActivity {

    /**
     * 当前显示的Fragment
     */
    private Fragment mCurrentFragment;
    /**
     * 将要显示的Fragment
     */
    private Fragment mShowFragment;

    /**
     * 选择的下标
     */
    private int selIndex = 0;

    public static final String SELINDEX = "INDEX";

    private FragmentManager fm;

    private String[] tags = {"HOME", "CLASSIFY", "READ", "MINE"};

    private Fragment mHomeFragment, mClassifyFragment, mReadFragment, mMineFragment;

    private BottomNavigationView mTab;

    //为启动页做准备
    public static void show(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mTab = (BottomNavigationView) findViewById(R.id.bottom_bar);
        mTab.setOnNavigationItemSelectedListener(new SelectedListener());
        fm = getSupportFragmentManager();
        if (savedInstanceState != null) {
            //读取上一次界面save的时候tab选中的状态
            selIndex = savedInstanceState.getInt(SELINDEX, selIndex);
            mHomeFragment = fm.findFragmentByTag(tags[0]);
            mClassifyFragment = fm.findFragmentByTag(tags[1]);
            mReadFragment = fm.findFragmentByTag(tags[2]);
            mMineFragment = fm.findFragmentByTag(tags[3]);
            restoreSelect();
        } else {
            mTab.findViewById(R.id.action_home).performClick();
        }

    }

    private void restoreSelect() {
        //选中index
        View view = null;
        switch (selIndex) {
            case 0:
                view = mTab.findViewById(R.id.action_home);
                break;
            case 1:
                view = mTab.findViewById(R.id.action_classify);
                break;
            case 2:
                view = mTab.findViewById(R.id.action_read);
                break;
            case 3:
                view = mTab.findViewById(R.id.action_mine);
                break;
            default:
                break;
        }
        view.performClick();
    }

    class SelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.action_home:
                    selIndex = 0;
                    hint(transaction, 1, 2, 3);
                    if(mHomeFragment == null){
                        mHomeFragment= HomeFragment.newInstance();
                        transaction.add(R.id.fragment_container,mHomeFragment,tags[0]);
                    }else {
                        transaction.show(mHomeFragment);
                    }
                    break;
                case R.id.action_classify:
                    selIndex = 1;
                    hint(transaction, 0, 2, 3);
                    if(mClassifyFragment == null){
                        mClassifyFragment= ClassifyFragment.newInstance();
                        transaction.add(R.id.fragment_container,mClassifyFragment,tags[1]);
                    }else {
                        transaction.show(mClassifyFragment);
                    }
                    break;
                case R.id.action_read:
                    selIndex = 2;
                    hint(transaction, 0, 1, 3);
                    if(mReadFragment == null){
                        mReadFragment= ReadFragment.newInstance();
                        transaction.add(R.id.fragment_container,mReadFragment,tags[2]);
                    }else {
                        transaction.show(mReadFragment);
                    }
                    break;
                case R.id.action_mine:
                    selIndex = 3;
                    hint(transaction, 0, 1, 2);
                    if(mMineFragment == null){
                        mMineFragment= MineFragment.newInstance();
                        transaction.add(R.id.fragment_container,mMineFragment,tags[3]);
                    }else {
                        transaction.show(mMineFragment);
                    }
                    break;

            }
            transaction.commit();
            return true;
        }


    }

    private void hint(FragmentTransaction transaction, Integer... fs) {
        for (int j = 0; j < fs.length; j++) {
            switch (fs[j]) {
                case 0:
                    if (mHomeFragment != null)
                        transaction.hide(mHomeFragment);
                    break;
                case 1:
                    if (mClassifyFragment != null)
                        transaction.hide(mClassifyFragment);
                    break;
                case 2:
                    if(mReadFragment!=null)
                        transaction.hide(mReadFragment);
                    break;
                case 3:
                    if(mMineFragment!=null)
                        transaction.hide(mMineFragment);
                    break;


            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELINDEX, selIndex);
    }

    //当前时间
    long current;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - current > 2000) {
            current = System.currentTimeMillis();
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
            System.exit(0);
        }
    }
}
