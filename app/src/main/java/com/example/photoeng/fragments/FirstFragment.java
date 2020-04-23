package com.example.photoeng.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.photoeng.R;


public class FirstFragment extends Fragment {
    private TextView next;
    private ViewPager mViewPager;
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        next = view.findViewById(R.id.slide_one_next);
        mViewPager = getActivity().findViewById(R.id.viewPager);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });
        return view;
    }
}
