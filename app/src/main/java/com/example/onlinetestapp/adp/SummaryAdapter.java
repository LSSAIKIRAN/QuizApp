package com.example.onlinetestapp.adp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinetestapp.R;
import com.example.onlinetestapp.databinding.SummaryListBinding;

import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {

    private ArrayList<Boolean> arrayList;
    Context context;
    private OnNumberClickListener clickListener;


    public  interface  OnNumberClickListener{
        void onNumberClick(int position);
    }

    public SummaryAdapter(ArrayList<Boolean> arrayList, OnNumberClickListener clickListener) {
        this.arrayList = arrayList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        return new SummaryViewHolder(SummaryListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        boolean value = arrayList.get(position);

        int currentNumber = position + 1;
        holder.binding.questionNumber.setText(String.valueOf(currentNumber));

        if (value){
            holder.binding.questionNumber.setBackground(context.getDrawable(R.drawable.greencircle));
        }
        else {
            holder.binding.questionNumber.setBackground(context.getDrawable(R.drawable.circle));
        }

        holder.binding.questionNumber.setOnClickListener(v -> {
            clickListener.onNumberClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SummaryViewHolder extends RecyclerView.ViewHolder{

        SummaryListBinding binding;
        public SummaryViewHolder(@NonNull SummaryListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
