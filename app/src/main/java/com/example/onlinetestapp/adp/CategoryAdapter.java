package com.example.onlinetestapp.adp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinetestapp.cls.CategoryData;
import com.example.onlinetestapp.databinding.CategoryListBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private ArrayList<CategoryData> arrayList;
    private Context context;
    private onCategoryClickListener categoryClickListener;

    public CategoryAdapter(ArrayList<CategoryData> arrayList,  onCategoryClickListener categoryClickListener) {
        this.arrayList = arrayList;

        this.categoryClickListener = categoryClickListener;
    }

    public interface onCategoryClickListener{
        void onCategoryClick(int position,View view);
    }

//    public onCategoryClickListener getCategoryClickListener() {
//        return categoryClickListener;
//    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context = parent.getContext();
        }
        return new CategoryViewHolder(CategoryListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        CategoryData data = arrayList.get(position);
        if (data== null){
            return;
        }

        holder.binding.name.setText(data.getName());
        Glide.with(context).load(data.getMainImage()).into(holder.binding.categoryImage);
        holder.binding.categoryCard.setOnClickListener(v -> {
            categoryClickListener.onCategoryClick(position,v);
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CategoryViewHolder extends  RecyclerView.ViewHolder{
        CategoryListBinding binding;
        public CategoryViewHolder(@NonNull  CategoryListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
