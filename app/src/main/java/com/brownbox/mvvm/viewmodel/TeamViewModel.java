package com.brownbox.mvvm.viewmodel;

import android.content.Context;

import com.brownbox.mvvm.TeamNavigator;
import com.brownbox.mvvm.data.TeamDataSource;
import com.brownbox.mvvm.data.TeamRepository;
import com.brownbox.mvvm.model.Team;

public class TeamViewModel {

    private TeamRepository teamRepository;
    private TeamNavigator teamNavigator;

    public TeamViewModel(TeamRepository teamRepository, Context context) {
        this.teamRepository = teamRepository;
    }

    public void setTeamNavigator(TeamNavigator teamNavigator) {
        this.teamNavigator = teamNavigator;
    }

    public void getListTeam(){
        teamRepository.getListTeams(new TeamDataSource.GetTeamsCallback() {
            @Override
            public void onTeamLoaded(Team data) {
                teamNavigator.loadListTeam(data.getTeams());
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                teamNavigator.errorLoadListTeam(errorMessage);
            }
        });
    }
}
