package com.brownbox.mvvm.data.remote;

import com.brownbox.mvvm.data.TeamDataSource;
import com.brownbox.mvvm.model.Team;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamRemoteDataSource implements TeamDataSource {

    private ApiInterface apiInterface =
            ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void getListTeams(final GetTeamsCallback callback) {

        //get data from API
        Call<Team> call = apiInterface.getAllTeams("English Premier League");
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                callback.onTeamLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                callback.onDataNotAvailable(t.toString());
            }
        });
    }
}
