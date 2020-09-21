package com.example.quizapp.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;

public class MainFragment extends Fragment {
    TextView questionsAmountTxt,countText;
    SeekBar questionsSeekbar;
    private MainViewModel mViewModel;
    Button plus,minus;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        questionsAmountTxt=view.findViewById(R.id.text_amount);
        questionsSeekbar=view.findViewById(R.id.questions_slider);
        questionsAmountTxt.setText("0");
        countText=view.findViewById(R.id.count_txt);
        plus=view.findViewById(R.id.plus_btn);
        minus=view.findViewById(R.id.minus_btn);
        questionsSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                questionsAmountTxt.setText(String.valueOf(i));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);//тут удалятся данные во вьюмодел
        mViewModel.result.observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                countText.setText(String.valueOf(integer));
            }
        });
        Log.e("fragment name","Main fragment");
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.plus();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.minus();
            }
        });
    }


}