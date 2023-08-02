package com.example.assigment_and2.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assigment_and2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_gioithieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_gioithieu extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gioithieu, container, false);
        return view;
    }
}