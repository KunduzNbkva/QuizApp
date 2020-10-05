package com.example.quizapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.models.CategoriesListModel;
import com.example.quizapp.models.TriviaCategoryModel;
import com.example.quizapp.ui.questions.QuestionsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private static final String EXTRA_AMOUNT = "amount";
    private static final String EXTRA_DIFFICULTY = "difficulty";
    private static final String EXTRA_CATEGORY = "category";
    private static final int EXTRA_RCODE = 1;
    private TextView questionsAmountTxt, countText;
    private SeekBar questionsSeekbar;
    private Spinner categoriesSpinner, difficultySpinner;
    private MainViewModel mViewModel;
    private Button plus, minus, startBtn;
    private int amount, category;
    private String difficulty;


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
        categoriesSpinner = view.findViewById(R.id.categories_spinner);
        difficultySpinner = view.findViewById(R.id.difficulty_spinner);
        questionsAmountTxt = view.findViewById(R.id.text_amount);
        questionsSeekbar = view.findViewById(R.id.questions_slider);
        amount = 10;
        getQuestionsAmount();
        questionsAmountTxt.setText("0");
        startBtn = view.findViewById(R.id.start_btn);
        countText = view.findViewById(R.id.count_txt);
        plus = view.findViewById(R.id.plus_btn);
        minus = view.findViewById(R.id.minus_btn);
        setListeners();
    }

    public void setListeners() {
        plus.setOnClickListener(view1 -> mViewModel.plus());
        minus.setOnClickListener(view12 -> mViewModel.minus());
        questionsSeekbar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                questionsAmountTxt.setText(String.valueOf(i));
            }
        });
        startBtn.setOnClickListener(view13 -> getParamsOfQuiz());


        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                difficulty = getResources().getStringArray(R.array.spinner_difficulty)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);//тут удалятся данные во вьюмодел
        mViewModel.result.observeForever(integer -> countText.setText(String.valueOf(integer)));
        observeGetCategories();
        getParamsOfQuiz();
    }

    public void getQuestionsAmount() {
        questionsSeekbar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                super.onProgressChanged(seekBar, i, b);
                amount = seekBar.getProgress();
                countText.setText(amount);
            }
        });
    }

    public void observeGetCategories() {
        mViewModel.getCategories();
        mViewModel.listCategories.observe(getViewLifecycleOwner(), new Observer<CategoriesListModel>() {
            @Override
            public void onChanged(CategoriesListModel categoriesListModel) {
                List<TriviaCategoryModel> categoryList;
                categoryList = (List<TriviaCategoryModel>) categoriesListModel.getTriviaCategories();
                List<String> categoriesName = new ArrayList<>();
                for (TriviaCategoryModel triviaCategoryModel : categoryList) {
                    categoriesName.add(triviaCategoryModel.getName());
                }
                Log.e("spinner", "name " + categoriesName.get(0));
                ArrayAdapter<String> adapter = new
                        ArrayAdapter<>(requireContext(), R.layout.support_simple_spinner_dropdown_item, categoriesName);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                categoriesSpinner.setAdapter(adapter);
                categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        category = categoriesListModel.getTriviaCategories().get(i).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        });
    }

    public void getParamsOfQuiz() {
        Intent intent = new Intent(requireContext(),QuestionsActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(EXTRA_CATEGORY, category);
        startActivity(intent);
        Log.e("intent", "result: " + amount + " " + difficulty + " " + category);
    }
}