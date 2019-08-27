package com.tekion.beans;

public class Match
{
  private int series_Id;
  private int noOfMatch;
  private int match_Id;
  private Team team1;
  private Team team2;
  private int max_over;
  private Team toss_won=null;
  private Team match_won=null;
  private Team batFirst=null;
  private Team bowlFirst=null;
  private Player currBowler=null;
  private int targetRun=0;
  private int currStriker1=0;
  private int inning1=0;
  private int currStriker2=1;
  private int nextBatsman=2;
  private int ball_no=0;

  public Match(int series_Id, int match_Id, Team team1, Team team2, int max_over, int noOfMatch)
  {
    this.series_Id=series_Id;
    this.match_Id=match_Id;
    this.team1=team1;
    this.team2=team2;
    this.max_over=max_over;
    this.noOfMatch=noOfMatch;
  }



    public int getSeries_Id() {
        return series_Id;
    }

    public void setSeries_Id(int series_Id) {
        this.series_Id = series_Id;
    }

    public int getNoOfMatch() {
        return noOfMatch;
    }

    public void setNoOfMatch(int noOfMatch) {
        this.noOfMatch = noOfMatch;
    }

    public int getMatch_Id() {
        return match_Id;
    }

    public void setMatch_Id(int match_Id) {
        this.match_Id = match_Id;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getMax_over() {
        return max_over;
    }

    public void setMax_over(int max_over) {
        this.max_over = max_over;
    }

    public Team getToss_won() {
        return toss_won;
    }

    public void setToss_won(Team toss_won) {
        this.toss_won = toss_won;
    }

    public Team getMatch_won() {
        return match_won;
    }

    public void setMatch_won(Team match_won) {
        this.match_won = match_won;
    }

    public Team getBatFirst() {
        return batFirst;
    }

    public void setBatFirst(Team batFirst) {
        this.batFirst = batFirst;
    }

    public Team getBowlFirst() {
        return bowlFirst;
    }

    public void setBowlFirst(Team bowlFirst) {
        this.bowlFirst = bowlFirst;
    }

    public Player getCurrBowler() {
        return currBowler;
    }

    public void setCurrBowler(Player currBowler) {
        this.currBowler = currBowler;
    }

    public int getTargetRun() {
        return targetRun;
    }

    public void setTargetRun(int targetRun) {
        this.targetRun = targetRun;
    }

    public int getCurrStriker1() {
        return currStriker1;
    }

    public void setCurrStriker1(int currStriker1) {
        this.currStriker1 = currStriker1;
    }

    public int getInning1() {
        return inning1;
    }

    public void setInning1(int inning1) {
        this.inning1 = inning1;
    }

    public int getCurrStriker2() {
        return currStriker2;
    }

    public void setCurrStriker2(int currStriker2) {
        this.currStriker2 = currStriker2;
    }

    public int getNextBatsman() {
        return nextBatsman;
    }

    public void setNextBatsman(int nextBatsman) {
        this.nextBatsman = nextBatsman;
    }

    public int getBall_no() {
        return ball_no;
    }

    public void setBall_no(int ball_no) {
        this.ball_no = ball_no;
    }
}
