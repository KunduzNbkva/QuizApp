package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.QuizModel;
import com.example.quizapp.ui.questions.OnItemClickListener;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<QuizModel> list;
    OnItemClickListener itemClickListener;


    public QuestionsAdapter(OnItemClickListener itemClickListener, List<QuizModel> list) {
        this.itemClickListener = itemClickListener;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
