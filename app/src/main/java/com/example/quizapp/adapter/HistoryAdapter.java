package com.example.quizapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.models.ResultModel;
import com.example.quizapp.ui.history.OnItemClick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>  {
    List<ResultModel> list = new ArrayList<>();
    public OnItemClick onItemClick;
    public HistoryAdapter(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void updateList(List<ResultModel> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
       holder.onBind(list.get(position),onItemClick);
    }

    @Override
    public int getItemCount() {
        return list.size();
}

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryText,difficultyText,correctAnswers,date_txt,total;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryText=itemView.findViewById(R.id.category_txt);
            difficultyText=itemView.findViewById(R.id.difficulty_txt);
            correctAnswers=itemView.findViewById(R.id.correct_answers_txt);
            date_txt=itemView.findViewById(R.id.date);
            total=itemView.findViewById(R.id.total_questions_txt);

        }

        public void onBind(ResultModel resultModel,OnItemClick onItemClick) {
            Log.e("TAG", "onBind: ");
            categoryText.setText(resultModel.getCategory());
            difficultyText.setText(resultModel.getDifficulty());
            correctAnswers.setText(String.valueOf(resultModel.getCorrectAnswer()));
            String date=new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(resultModel.getDate());
            date_txt.setText(date);
            total.setText(String.valueOf(resultModel.getQuestions().size()));
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClick.onItemClick(resultModel.getId());
                    return false;
                }
            });
        }
    }
}
