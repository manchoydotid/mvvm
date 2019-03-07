package com.brownbox.mvvm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.brownbox.mvvm.Injection;
import com.brownbox.mvvm.R;
import com.brownbox.mvvm.TeamNavigator;
import com.brownbox.mvvm.adapter.RVAdapter;
import com.brownbox.mvvm.model.TeamDetail;
import com.brownbox.mvvm.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TeamNavigator {

    private TeamViewModel teamViewModel;
    private RecyclerView recTeam;
    private RVAdapter adapter;
    private List<TeamDetail> dataListTeamBola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recTeam = (RecyclerView)findViewById(R.id.rvTeamBola);
        teamViewModel = new TeamViewModel(Injection.provideTeamRepository(this),
                getApplicationContext());

        dataListTeamBola = new ArrayList<>();
        teamViewModel.setTeamNavigator(this);
        teamViewModel.getListTeam();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new RVAdapter(getApplicationContext(), dataListTeamBola);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recTeam.setLayoutManager(layoutManager);
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
