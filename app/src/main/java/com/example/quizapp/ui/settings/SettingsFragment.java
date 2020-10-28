package com.example.quizapp.ui.settings;

import android.os.Bundle;
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

import java.util.List;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

public class SettingsFragment extends BaseFragment {
    private LinearLayout clearHistory;
    private SettingsViewModel mViewModel;
    private RecyclerView themes_rv;
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
        list.add(R.drawable.dark_icon);
        clearHistory = view.findViewById(R.id.clear_history_linear);
        clearHistory.setOnClickListener(onClickListener);
        themes_rv=view.findViewById(R.id.themes_rv);
        themes_rv.setLayoutManager(new LinearLayoutManager(requireActivity(), HORIZONTAL,true));
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