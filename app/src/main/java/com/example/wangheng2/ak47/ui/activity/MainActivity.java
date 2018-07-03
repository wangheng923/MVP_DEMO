package com.example.wangheng2.ak47.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangheng2.ak47.R;
import com.example.wangheng2.ak47.ui.fragment.BFragment;
import com.example.wangheng2.ak47.ui.fragment.CFragment;
import com.example.wangheng2.ak47.ui.fragment.HomeFragment;
import com.example.wangheng2.ak47.ui.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.tabbar)
    TabLayout tabbar;

    private List<Fragment> fragments;

    @Inject
    HomeFragment fragmentA;
    Fragment fragmentB;
    Fragment fragmentC;
    @Inject
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewpage.addOnPageChangeListener(new MyOnPageChangeListener());
        tabbar.addOnTabSelectedListener(new MyTabSelectedListener());
        initView();
    }


    private void initView() {
        if (null == fragments) {
            fragments = new ArrayList<>();
        }
        System.out.println("Afragment->" + fragmentA);
        if (null == fragmentA) {
            fragmentA = new HomeFragment();
        }

        fragments.add(fragmentA);
        if (null == fragmentB) {
            fragmentB = new BFragment();
        }
        fragments.add(fragmentB);
        if (null == fragmentC) {
            fragmentC = new CFragment();
        }
        fragments.add(fragmentC);
        viewpage.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

        }
    }

    private class MyFragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragements = new ArrayList<Fragment>();

        public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragements) {
            super(fm);
            this.fragements = fragements;
        }


        @Override
        public Fragment getItem(int position) {
            return fragements.get(position);
        }

        @Override
        public int getCount() {
            return fragements.size();
        }
    }

    private class MyTabSelectedListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewpage.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tabbar.getTabAt(position).select();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public class MyFragmentPagerAdapter extends PagerAdapter {
        private List<Fragment> fragments;
        private FragmentManager manager;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super();
            manager = fm;
            this.fragments = fragments;
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = fragments.get(position);
            //判断当前的fragment是否已经被添加进入Fragmentanager管理器中
            if (!fragment.isAdded()) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, fragment.getClass().getSimpleName());
                //不保存系统参数，自己控制加载的参数
                transaction.commitAllowingStateLoss();
                //手动调用,立刻加载Fragment片段
                manager.executePendingTransactions();
            }
            if (fragment.getView().getParent() == null) {
                //添加布局
                container.addView(fragment.getView());
            }
            return fragment.getView();
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //移除布局
            container.removeView(fragments.get(position).getView());
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
