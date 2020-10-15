package com.example.quizapp.ui.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.core.BaseFragment;

public class SettingsFragment extends BaseFragment {
    private LinearLayout clearHistory;
    private SettingsViewModel mViewModel;

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
        clearHistory = view.findViewById(R.id.clear_history_linear);
        clearHistory.setOnClickListener(onClickListener);
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