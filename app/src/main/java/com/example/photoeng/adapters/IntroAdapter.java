package com.example.photoeng.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.photoeng.fragments.FirstFragment;
import com.example.photoeng.fragments.SecondFragment;
import com.example.photoeng.fragments.ThirdFragment;

public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
