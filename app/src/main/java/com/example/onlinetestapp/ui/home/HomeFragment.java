package com.example.onlinetestapp.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.onlinetestapp.MyApplication;
import com.example.onlinetestapp.adp.CategoryAdapter;
import com.example.onlinetestapp.adp.ImageSliderAdapter;
import com.example.onlinetestapp.cls.CategoryData;
import com.example.onlinetestapp.cls.SliderData;
import com.example.onlinetestapp.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements CategoryAdapter.onCategoryClickListener {

    private FragmentHomeBinding binding;
    private ImageSliderAdapter adapter;
    private ArrayList<String> arrayList;

    private CategoryAdapter categoryAdapter;
    private ArrayList<CategoryData> categoryDataArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        init();
        loadImageSliderData();
        loadCategoryData();

        return root;
    }

    private void init(){
        arrayList = new ArrayList<>();
        adapter = new ImageSliderAdapter(arrayList);
        binding.imageSlider.setSliderAdapter(adapter);

        categoryDataArrayList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryDataArrayList,this);
        binding.rvView.setAdapter(categoryAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        binding.rvView.setLayoutManager(layoutManager);

    }

    private void loadImageSliderData(){
        MyApplication.firestore.collection("sliders").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (queryDocumentSnapshots != null){
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                SliderData data = documentSnapshot.toObject(SliderData.class);
                                if (data.getUrl() != null){
                                    arrayList.add(data.getUrl());
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }

                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Something went Wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void loadCategoryData(){
       MyApplication.firestore.collection("quizCategory").get()
               .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                   @Override
                   public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                       if (queryDocumentSnapshots !=null){
                           for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                               CategoryData data = documentSnapshot.toObject(CategoryData.class);
                               data.setId(documentSnapshot.getId());
                               categoryDataArrayList.add(data);
                               categoryAdapter.notifyDataSetChanged();
                           }
                       }

                   }
               }).addOnFailureListener(e -> {
                   Toast.makeText(getContext(), "Something Went Wrong "+e.getMessage(), Toast.LENGTH_SHORT).show();
               });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override

    public void onCategoryClick(int position,View view) {
        CategoryData data = categoryDataArrayList.get(position);
        NavDirections action =
                HomeFragmentDirections.actionNavHomeToSubCategoryRootFragment(data,data.getName());
        Navigation.findNavController(view).navigate(action);
    }
}