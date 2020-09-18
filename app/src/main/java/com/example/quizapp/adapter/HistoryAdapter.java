package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>  {
    List<String> list;

    public HistoryAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
       holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
}
    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryText,difficultyText,correctAnswers;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryText=itemView.findViewById(R.id.category_txt);
            difficultyText=itemView.findViewById(R.id.difficulty_txt);
            correctAnswers=itemView.findViewById(R.id.correct_answers_txt);
        }

        public void onBind(int position) {

        }
    }
}
