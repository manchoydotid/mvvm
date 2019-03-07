package com.brownbox.mvvm;

import com.brownbox.mvvm.model.TeamDetail;

import java.util.List;

public interface TeamNavigator {

    void loadListTeam(List<TeamDetail> listTeam);
    void errorLoadListTeam(String message);

}
