package com.example.photoeng.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.photoeng.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private TextView next;
    private ViewPager mViewPager;
    private TextView back;
    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        next = view.findViewById(R.id.slide_two_next);
        back = view.findViewById(R.id.slide_two_back);
        mViewPager = getActivity().findViewById(R.id.viewPager);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });
        return view;
    }
}
