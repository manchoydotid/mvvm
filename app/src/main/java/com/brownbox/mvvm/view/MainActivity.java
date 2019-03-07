package com.brownbox.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.brownbox.mvvm.Injection;
import com.brownbox.mvvm.R;
import com.brownbox.mvvm.TeamNavigator;
import com.brownbox.mvvm.adapter.RVAdapter;
import com.brownbox.mvvm.databinding.ActivityMainBinding;
import com.brownbox.mvvm.model.TeamDetail;
import com.brownbox.mvvm.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TeamNavigator {

    private TeamViewModel teamViewModel;
    private RecyclerView recTeam;
    private RVAdapter adapter;
    private List<TeamDetail> dataListTeamBola;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        teamViewModel = new TeamViewModel(Injection.provideTeamRepository(this),
                getApplicationContext());

        dataListTeamBola = new ArrayList<>();
        teamViewModel.setTeamNavigator(this);
        teamViewModel.getListTeam();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new RVAdapter(this, dataListTeamBola);
        recTeam = binding.rvTeamBola;
        recTeam.setLayoutManager(new LinearLayoutManager(this));
        //create line betweem itemrow
        recTeam.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recTeam.setAdapter(adapter);
    }

    @Override
    public void loadListTeam(List<TeamDetail> listTeam) {
        dataListTeamBola.addAll(listTeam);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void errorLoadListTeam(String message) {
        Log.e("ERROR", message);
    }
}
