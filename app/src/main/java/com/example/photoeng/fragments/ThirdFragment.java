package com.example.photoeng.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.photoeng.DictionaryActivity;
import com.example.photoeng.MainActivity;
import com.example.photoeng.MainScreen;
import com.example.photoeng.R;
import com.example.photoeng.StartActivity;


public class ThirdFragment extends Fragment {
    private TextView back;
    private ViewPager mViewPager;
    private TextView next;
    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        back = view.findViewById(R.id.slide_three_back);
        next = view.findViewById(R.id.slide_third_next);
        mViewPager = getActivity().findViewById(R.id.viewPager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(3);
            }
        });
        return view;
    }
}
