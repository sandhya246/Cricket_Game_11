package com.tekion.util;

import com.tekion.beans.Player;
import com.tekion.beans.Team;

import java.util.List;

public class TeamUtil {

    // private constructor
    private TeamUtil()
    {

    }

    public static void resetTeamInfo(Team team)
    {
        PlayerUtil.resetPlayers(team.getPlayerList());
        team.setRunScoredZero(0);
        team.setWicketLost(0);
        team.setWicketTaken(0);
        team.setRunsGiven(0);
        team.setOverBowled(0);
        team.setStriker1(null);
        team.setStriker2(null);
        team.setCurrStriker(null);

    }

}
