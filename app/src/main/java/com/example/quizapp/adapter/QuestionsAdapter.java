package com.example.quizapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.QuizModel;
import com.example.quizapp.ui.questions.OnItemClickListener;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;
        Button answer1, answer2, answer3, answer4, boolean_answer1, boolean_answer2;
        LinearLayout multiplyButtons, booleanButtons;
        RecyclerView rv;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.question_txt);
            multiplyButtons = itemView.findViewById(R.id.multiply_buttons);
            booleanButtons = itemView.findViewById(R.id.boolean_buttons);
            answer1 = itemView.findViewById(R.id.answer1);
            answer2 = itemView.findViewById(R.id.answer2);
            answer3 = itemView.findViewById(R.id.answer3);
            answer4 = itemView.findViewById(R.id.answer4);
            rv = itemView.findViewById(R.id.recycler_questions);
            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });
            answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });
            answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });
            answer4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });
            boolean_answer1 = itemView.findViewById(R.id.boolean_answer1);
            boolean_answer2 = itemView.findViewById(R.id.boolean_answer2);
            boolean_answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });
            boolean_answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });
        }

        public void onBind(QuizModel quizModel, int position) {
            this.position = position;
            questionTextView.setText(quizModel.getQuestion());
        }
    }
}
