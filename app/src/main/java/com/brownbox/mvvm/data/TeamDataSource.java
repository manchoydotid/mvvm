package com.brownbox.mvvm.data;

import com.brownbox.mvvm.model.Team;

/*
* Blue print fungsi atau method apa yang kita pakai untuk callback
*/

public interface TeamDataSource {

    void getListTeams(GetTeamsCallback callback);

    interface GetTeamsCallback {
        void onTeamLoaded(Team data);
        void onDataNotAvailable(String errorMessage);
    }

}
