package pv.com.pvcloudgo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.Bind;
import pv.com.pvcloudgo.R;

/**
 * 套餐分类
 */
public class TcTypeFragment extends BaseFragment implements OnTabSelectListener {


    @Bind(R.id.sliding_tab_layout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "全部分类", "净水器套装", "化妆品套装"
            , "日用品", "后端", "设计", "工具资源"
    };
    private MyPagerAdapter mAdapter;


    public TcTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tc_type, container, false);

        return view;
    }

    @Override
    public void init() {
        initTab();
    }

    private void initTab() {
        for (String title : mTitles) {
            mFragments.add(new TcListFragment());
        }


        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(mViewPager);
//        /** 默认 */
//        SlidingTabLayout tabLayout_1 = ViewFindUtils.find(decorView, R.id.tl_1);
//        /**自定义部分属性*/
//        SlidingTabLayout tabLayout_2 = ViewFindUtils.find(decorView, R.id.tl_2);
//        /** 字体加粗,大写 */
//        SlidingTabLayout tabLayout_3 = ViewFindUtils.find(decorView, R.id.tl_3);
//        /** tab固定宽度 */
//        SlidingTabLayout tabLayout_4 = ViewFindUtils.find(decorView, R.id.tl_4);
//        /** indicator固定宽度 */
//        SlidingTabLayout tabLayout_5 = ViewFindUtils.find(decorView, R.id.tl_5);
//        /** indicator圆 */
//        SlidingTabLayout tabLayout_6 = ViewFindUtils.find(decorView, R.id.tl_6);
//        /** indicator矩形圆角 */
//        final SlidingTabLayout tabLayout_7 = ViewFindUtils.find(decorView, R.id.tl_7);
//        /** indicator三角形 */
//        SlidingTabLayout tabLayout_8 = ViewFindUtils.find(decorView, R.id.tl_8);
//        /** indicator圆角色块 */
//        SlidingTabLayout tabLayout_9 = ViewFindUtils.find(decorView, R.id.tl_9);
//        /** indicator圆角色块 */
//        SlidingTabLayout tabLayout_10 = ViewFindUtils.find(decorView, R.id.tl_10);
//
//        tabLayout_1.setViewPager(vp);
//        tabLayout_2.setViewPager(vp);
//        tabLayout_2.setOnTabSelectListener(this);
//        tabLayout_3.setViewPager(vp);
//        tabLayout_4.setViewPager(vp);
//        tabLayout_5.setViewPager(vp);
//        tabLayout_6.setViewPager(vp);
//        tabLayout_7.setViewPager(vp, mTitles);
//        tabLayout_8.setViewPager(vp, mTitles, this, mFragments);
//        tabLayout_9.setViewPager(vp);
//        tabLayout_10.setViewPager(vp);
//
//        vp.setCurrentItem(4);
//
//        tabLayout_1.showDot(4);
//        tabLayout_3.showDot(4);
//        tabLayout_2.showDot(4);
//
//        tabLayout_2.showMsg(3, 5);
//        tabLayout_2.setMsgMargin(3, 0, 10);
//        MsgView rtv_2_3 = tabLayout_2.getMsgView(3);
//        if (rtv_2_3 != null) {
//            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
//        }
//
//        tabLayout_2.showMsg(5, 5);
//        tabLayout_2.setMsgMargin(5, 0, 10);

    }

    @Override
    public void onTabSelect(int position) {
        Toast.makeText(getActivity(), "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselect(int position) {
        Toast.makeText(getActivity(), "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
