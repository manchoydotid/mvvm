package com.brownbox.mvvm.data;

import android.support.annotation.Nullable;

import com.brownbox.mvvm.data.local.TeamLocalDataSource;
import com.brownbox.mvvm.data.remote.TeamRemoteDataSource;
import com.brownbox.mvvm.model.Team;

public class TeamRepository implements TeamDataSource {

    TeamRemoteDataSource teamRemoteDataSource;
    TeamLocalDataSource teamLocalDataSource;

    public TeamRepository(TeamRemoteDataSource teamRemoteDataSource,
                          TeamLocalDataSource teamLocalDataSource) {
        this.teamRemoteDataSource = teamRemoteDataSource;
        this.teamLocalDataSource = teamLocalDataSource;
    }

    @Override
    public void getListTeams(final GetTeamsCallback callback) {
        teamLocalDataSource.getListTeams(new GetTeamsCallback() {
            @Override
            public void onTeamLoaded(Team data) {
                callback.onTeamLoaded(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                getTeamsFromRemote(callback);
            }
        });

    }


    private void getTeamsFromRemote(@Nullable final GetTeamsCallback callback){
        teamRemoteDataSource.getListTeams(new GetTeamsCallback() {
            @Override
            public void onTeamLoaded(Team data) {
                //insert into database
                teamLocalDataSource.saveDataTeam(data.getTeams());
                callback.onTeamLoaded(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }
}
