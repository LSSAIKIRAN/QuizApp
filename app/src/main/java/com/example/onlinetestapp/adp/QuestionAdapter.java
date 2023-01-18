package com.example.onlinetestapp.adp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinetestapp.cls.QuestionData;
import com.example.onlinetestapp.databinding.QuestionListBinding;
import com.example.onlinetestapp.fragments.QuestionRootFragment;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private ArrayList<QuestionData> arrayList;
    private OnOptionClickListener clickListener;

    public QuestionAdapter(ArrayList<QuestionData> arrayList, OnOptionClickListener clickListener) {
        this.arrayList = arrayList;
        this.clickListener = clickListener;
    }

    public interface OnOptionClickListener{
        void onOptionClick(int position, String userAnswer);
        void onClearClick(int position);
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionViewHolder(QuestionListBinding.inflate(LayoutInflater.from(parent.getContext()),parent
                ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

        QuestionData data = arrayList.get(position);
        if (data == null){
            return;
        }

        int qno = position+1;
        String question = "[Q."+qno+"]  " + data.getQuestion() + " ?";

        holder.binding.question.setText(question);

        holder.binding.option1.setText(data.getOptions().get(0));
        holder.binding.option2.setText(data.getOptions().get(1));
        holder.binding.option3.setText(data.getOptions().get(2));
        holder.binding.option4.setText(data.getOptions().get(3));

        holder.binding.option1.setOnClickListener(v -> {
            clickListener.onOptionClick(position,data.getOptions().get(0));
        });

        holder.binding.option2.setOnClickListener(v -> {
            clickListener.onOptionClick(position,data.getOptions().get(1));
        });

        holder.binding.option3.setOnClickListener(v -> {
            clickListener.onOptionClick(position,data.getOptions().get(2));
        });

        holder.binding.option4.setOnClickListener(v -> {
            clickListener.onOptionClick(position,data.getOptions().get(3));
        });


        holder.binding.clearSelection.setOnClickListener(v -> {
            holder.binding.radioGroup.clearCheck();
            clickListener.onClearClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{

        QuestionListBinding binding;
        public QuestionViewHolder(@NonNull QuestionListBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }
}
