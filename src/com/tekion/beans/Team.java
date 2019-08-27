package com.tekion.beans;

import com.tekion.beans.Player;
import com.tekion.util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;

public class Team
{
    private int team_id;
    private String team_name;
    public List<Player> playerList = new ArrayList<>();
    private int runScored=0;
    private int wicketLost=0;
    private int wicketTaken=0;
    private int runsGiven=0;
    private int overBowled=0;
    private Player striker1=null;
    private Player striker2=null;
    private Player currStriker=null;


    public Team(int team_id,String team_name)
    {
       this.team_id=team_id;
       this.team_name=team_name;
    }


    public void setStriker1(Player striker1)
    {
        this.striker1=striker1;
    }

    public Player getStriker1()
    {
        return striker1;
    }

    public void setStriker2(Player striker2)
    {
       this.striker2=striker2;
    }

    public Player getStriker2()
    {
        return this.striker2;
    }

    public void setRunScored(int runScored)
    {
        this.runScored+=runScored;
    }

    public void setRunScoredZero(int runScored)
    {
      this.runScored=runScored;

    }
    public Player getCurrStriker()
    {
       return this.currStriker;
    }

    public int getTeam_id()
    {
      return this.team_id;
    }

    public String getTeam_name()
    {
       return this.team_name;
    }
    public void setWicketLost(int wicketLost)
    {
        this.wicketLost=wicketLost;
    }
    public void setWicketTaken(int wicketTaken)
    {
        this.wicketTaken=wicketTaken;
    }

    public int getRunScored()
    {
       return this.runScored;
    }

    public int getWicketLost()
    {
        return this.wicketLost;
    }

    public int getWicketTaken()
    {
        return this.wicketTaken;
    }

    public int  getRunsGiven()
    {
       return this.runsGiven;
    }

    public int getOverBowled()
    {
       return this.overBowled;
    }

    public void addPlayer(Player player)
    {
       playerList.add(player);

    }

    public List<Player> getPlayerList()
    {
        return this.playerList;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void setRunsGiven(int runsGiven) {
        this.runsGiven = runsGiven;
    }

    public void setOverBowled(int overBowled) {
        this.overBowled = overBowled;
    }

    public void setCurrStriker(Player currStriker) {
        this.currStriker = currStriker;
    }
}
