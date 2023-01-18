package com.example.onlinetestapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinetestapp.MainActivity;
import com.example.onlinetestapp.R;
import com.example.onlinetestapp.adp.SummaryAdapter;
import com.example.onlinetestapp.databinding.FragmentSummaryBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class SummaryFragment extends BottomSheetDialogFragment implements SummaryAdapter.OnNumberClickListener {

  private FragmentSummaryBinding binding;
  private SummaryAdapter adapter;

    public SummaryFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentSummaryBinding.inflate(inflater,container,false);

        init();
       return binding.getRoot();
    }

    private void init(){

        adapter = new SummaryAdapter(MainActivity.summaryList,this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),5);
        binding.rvView.setLayoutManager(layoutManager);
        binding.rvView.setAdapter(adapter);
    }

    @Override
    public void onNumberClick(int position) {

        MainActivity.summaryViewModel.setSelectedPosition(position);
        dismiss();
    }
}