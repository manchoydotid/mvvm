package com.brownbox.mvvm.data.local;

import android.content.Context;

import com.brownbox.mvvm.data.TeamDataSource;
import com.brownbox.mvvm.model.Team;
import com.brownbox.mvvm.model.TeamDetail;

import java.util.List;

public class TeamLocalDataSource implements TeamDataSource {
    private Context context;
    private TeamDao teamDao;

    public TeamLocalDataSource(Context context) {
        this.context = context;
        //calling instance function from TeamDatabase class
        teamDao = TeamDatabase.getInstance(context).teamDao();
    }

    //get data from Local Database
    @Override
    public void getListTeams(final GetTeamsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TeamDetail> team = teamDao.getTeams();
                if(team.isEmpty()){
                    callback.onDataNotAvailable(
                            "Data di Database SQLite kosong");
                }else {
                    Team teams = new Team(team);
                    callback.onTeamLoaded(teams);
                }
            }
        };
        new Thread(runnable).start();
    }

    //save data from internet to local
    public void saveDataTeam(final List<TeamDetail> teamDetail){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                teamDao.insertTeam(teamDetail);
            }
        };
        new Thread(runnable).start();
    }
}
