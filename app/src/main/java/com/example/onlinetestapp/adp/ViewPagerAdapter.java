package com.example.onlinetestapp.adp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.onlinetestapp.cls.SubCategoryData;
import com.example.onlinetestapp.fragments.QuizFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private ArrayList<SubCategoryData> arrayList;

    public ViewPagerAdapter(@NonNull Fragment fragment,ArrayList<SubCategoryData> arrayList) {
        super(fragment);
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new QuizFragment();
        SubCategoryData data = arrayList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id", data.getId());
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
