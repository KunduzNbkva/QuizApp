package com.example.quizapp.ui.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ThemeItemBinding;
import com.example.quizapp.ui.history.OnItemClick;
import com.example.quizapp.ui.questions.OnItemClickListener;
import java.util.List;


public class ThemesAdapter extends RecyclerView.Adapter<ThemesAdapter.ViewHolder>{
List<Integer> list;
OnItemClick listener;

public ThemesAdapter(List<Integer> list) {
        this.list = list;
    }

public void setListener(OnItemClick listener){
    this.listener=listener;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ThemeItemBinding.bind(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.theme_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        ThemeItemBinding themeItemBinding;
        public ViewHolder( @NonNull ThemeItemBinding binding) {
            super(binding.getRoot());
            themeItemBinding=binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.radioButton.setChecked(true);
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void onBind(Integer integer) {
          themeItemBinding.imageTheme.setImageDrawable(ContextCompat.
                  getDrawable(themeItemBinding.getRoot().getContext(), integer));
        }
    }
}
