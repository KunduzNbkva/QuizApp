package com.example.quizapp.ui.history;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private  RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<String> list;
    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initViews(){
        list=new ArrayList<>();
        list.add("Category");
       recyclerView=getView().findViewById(R.id.history_rv);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
       adapter=new HistoryAdapter(list);
       recyclerView.setAdapter(adapter);
    }


}