package com.example.lesson6;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Photo extends Fragment {

    public static final String ARG_INDEX = "index";
    private int index;

    public Photo() {
        // Required empty public constructor
    }

    public static Photo newInstance(int index) {
        Photo fragment = new Photo();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @SuppressLint("Recycle")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        AppCompatImageView imageView = view.findViewById(R.id.photo_of_famaly);
        TypedArray photoFamaly = getResources().obtainTypedArray(R.array.famaly_photo);
        imageView.setImageResource(photoFamaly.getResourceId(index, -1));
        return view;
    }
}