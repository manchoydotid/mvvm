package com.brownbox.mvvm;


import android.content.Context;

import com.brownbox.mvvm.data.TeamRepository;
import com.brownbox.mvvm.data.local.TeamLocalDataSource;
import com.brownbox.mvvm.data.remote.TeamRemoteDataSource;

/*
  Menyediakan resource dari kelas Repository
 */
public class Injection {

    public static TeamRepository provideTeamRepository(Context context){
        return new TeamRepository(new TeamRemoteDataSource(),
                new TeamLocalDataSource(context));
    }

}
