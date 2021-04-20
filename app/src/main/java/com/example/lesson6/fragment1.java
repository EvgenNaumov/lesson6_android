package com.example.lesson6;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class fragment1 extends Fragment {

    public static final String CURRENT_PHOTO = "Current_Photo";
    private int currentPosition = 0;
    private boolean isLand;

    public static fragment1 newInstance(String param1, String param2) {
        fragment1 fragment = new fragment1();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLand = getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE;

        if(savedInstanceState!=null){
            currentPosition = savedInstanceState.getInt(CURRENT_PHOTO);
        }
        if (isLand){
            showFragmentPhoto(currentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_PHOTO,currentPosition);
        super.onSaveInstanceState(outState);
    }

    private void showFragmentPhoto(int index) {
       if (isLand){
           showLandFragmentPhoto(index);
       } else {
           showPortFragmentPhoto(index);
       }
    }

    private void showLandFragmentPhoto(int index) {
//        Photo photo = Photo.newInstance(index);
/*
        FragmentManager fragmentManager =
                requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.photo_of_famaly, photo);
        // замена  фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
*/
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.photo_of_famaly,  Photo.newInstance(index))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    private void showPortFragmentPhoto(int index) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), ActivityPhoto.class);
        intent.putExtra(Photo.ARG_INDEX,index);
        startActivity(intent);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout)view;
        String[] famaly = getResources().getStringArray(R.array.семья);
        for (int i = 0; i<famaly.length ; i++) {
          String member =  famaly[i];
            TextView tv = new TextView(getContext());
            tv.setText(member);
            tv.setTextSize(30);
            layoutView.addView(tv);

            final int index = i;
            tv.setOnClickListener(v -> {
                //запуск активити из фрагмента
                currentPosition = index;
                showPortFragmentPhoto(currentPosition);
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }
}