package com.example.quizapp.ui.history;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.adapter.HistoryAdapter;
import com.example.quizapp.core.BaseFragment;
import com.example.quizapp.models.ResultModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends BaseFragment implements OnItemClick {
    private  RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<String> list;
    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.history_fragment;
    }

    @Override
    protected void init(View view) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        initViews();
    }

    private void initViews(){
        observeHistory();
        list=new ArrayList<>();
        list.add("Category");
       recyclerView=getView().findViewById(R.id.history_rv);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
       adapter=new HistoryAdapter(this);
       recyclerView.setAdapter(adapter);
    }
    private void observeHistory(){
        mViewModel.history.observe(getViewLifecycleOwner(), new Observer<List<ResultModel>>() {
            @Override
            public void onChanged(List<ResultModel> resultModels) {
                Log.e("log","");
                adapter.updateList(resultModels);
            }
        });
    }

    @Override
    public void onItemClick(int id) {
        App.historyStorage.delete(id);
        showToast("Deleted");
    }


}