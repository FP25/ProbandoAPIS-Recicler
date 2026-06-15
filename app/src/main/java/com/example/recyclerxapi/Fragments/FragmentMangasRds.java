package com.example.recyclerxapi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerxapi.R;

public class FragmentMangasRds extends Fragment {
    public FragmentMangasRds() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_mangas_rds, container, false);
    }
}