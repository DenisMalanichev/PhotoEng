package com.example.photoeng.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.photoeng.MainActivity;
import com.example.photoeng.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFour extends Fragment {

    private TextView back;
    private ViewPager mViewPager;
    private TextView done;

    public FragmentFour() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four2, container, false);
        back = view.findViewById(R.id.slide_four_back);
        done = view.findViewById(R.id.slide_done);
        mViewPager = getActivity().findViewById(R.id.viewPager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
