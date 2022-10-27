package com.lorenzo.mytabapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lorenzo.mytabapp.Fragment1;
import com.lorenzo.mytabapp.Fragment2;
import com.lorenzo.mytabapp.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2}; // 2 o + fragment
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
    Fragment fragment = null;

    switch (position){
        case 0:
            fragment = new Fragment1();
            break;
        case 1:
            fragment = new Fragment2();
            break;
    }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]); //qui conta le position
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2; // 2 o +
    }
}