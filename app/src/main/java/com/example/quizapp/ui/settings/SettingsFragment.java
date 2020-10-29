package com.example.quizapp.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.core.BaseFragment;
import com.example.quizapp.ui.history.OnItemClick;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

public class SettingsFragment extends BaseFragment {
    private LinearLayout clearHistory;
    private SettingsViewModel mViewModel;
    private RecyclerView themes_rv;
    ThemesAdapter adapter;
    private List<Integer> list;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.settings_fragment;
    }

    @Override
    protected void init(View view) {
        showToast("Android 4 is  supper");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list=new ArrayList<>();
        list.add(R.drawable.dark_icon);
        list.add(R.drawable.violet_icon);
        list.add(R.drawable.white_icon);
        list.add(R.drawable.green_icon);
        list.add(R.drawable.blue_icon);
        clearHistory = view.findViewById(R.id.clear_history_linear);
        clearHistory.setOnClickListener(onClickListener);
        themes_rv=view.findViewById(R.id.themes_rv);
        themes_rv.setLayoutManager(new LinearLayoutManager(requireActivity(), HORIZONTAL,true));
        adapter=new ThemesAdapter(list);
        themes_rv.setAdapter(adapter);
        adapter.setListener(new OnViewClick() {
            @Override
            public void onAdapterClick(int position) {
                switch (position){
                    case 0:
                        setThemes_rv(R.style.DarkTheme);

                        break;
                    case 1:
                        setThemes_rv(R.style.VioletTheme);
                        break;
                    case 2:
                        setThemes_rv(R.style.AppTheme);
                        break;
                    case 3:
                        setThemes_rv(R.style.GreenTheme);
                        break;
                    case 4:
                        setThemes_rv(R.style.BlueTheme);
                        break;
                }
                Intent intent=requireActivity().getIntent();
                requireActivity().finish();
                startActivity(intent);

            }
        });
    }
    public void setThemes_rv(int drawable){
        requireActivity().setTheme(drawable);
        App.prefs.setTheme(drawable);
        App.prefs.setChecked(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mViewModel.deleteAll();
            showToast("History is cleared");
        }
    };
}