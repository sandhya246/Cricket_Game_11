package com.tekion.beans;


import com.tekion.util.PlayerRole;

public class Player
{
    public static final int MAX_OVERS = 5;
    private int playerId;
    private String playerName;
    private PlayerRole playerRole;
    private int runScored=0;
    private int wicketTaken=0;
    private int overBowled=0;
    private int ballPlayed=0;
    private int runGiven=0;
    private int ballThrown=0;
    private int noOfFour=0;
    private int noOfSix=0;
    private int out=0;


    public Player(int id,String name,PlayerRole playerRole)
    {
        this.playerId=id;
        this.playerName=name;
        this.playerRole=playerRole;
    }

    public void setOverBowled(int overBowled)
    {
       this.overBowled=overBowled;
    }
    public void setWicketTaken(int wicketTaken)
    {
        this.wicketTaken=wicketTaken;
    }
    public int getWicketTaken()
    {
         return this.wicketTaken;
    }
    public String getPlayerRole()
    {
       return this.playerRole.toString();
    }
    public int getBallThrown()
    {
        return this.ballThrown;
    }

    public int getRunGiven(){return this.runGiven;}
    public int getPlayerId()
    {
       return this.playerId;
    }
    public String getPlayerName()
    {
        return this.playerName;
    }
    public int getOverBowled()
    {
      return this.overBowled;
    }

    public int getOut()
    {
       return this.out;
    }
    public int getRunScored()
    {
       return this.runScored;
    }
    public int getNoOfFour()
    {
       return this.noOfFour;
    }
    public int getNoOfSix()
    {
        return this.noOfSix;
    }
    public static int getMaxOvers() {
        return MAX_OVERS;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerRole(PlayerRole playerRole) {
        this.playerRole = playerRole;
    }

    public void setRunScored(int runScored) {
        this.runScored = runScored;
    }

    public void setBallPlayed(int ballPlayed) {
        this.ballPlayed = ballPlayed;
    }

    public void setRunGiven(int runGiven) {
        this.runGiven = runGiven;
    }

    public void setBallThrown(int ballThrown) {
        this.ballThrown = ballThrown;
    }

    public void setNoOfFour(int noOfFour) {
        this.noOfFour = noOfFour;
    }

    public void setNoOfSix(int noOfSix) {
        this.noOfSix = noOfSix;
    }

    public void setOut(int out) {
        this.out = out;
    }


    public int getBallPlayed()
    {
       return this.ballPlayed;
    }

    @Override
    public String toString()
    {
      return "this.playerRole";
    }


    public void runGiven(int run)
    {
        switch(run)
        {
            case 0:
                this.ballThrown++;
                break;
            case 1:
                this.ballThrown++;
                this.runGiven++;
                break;
            case 2:
                this.ballThrown++;
                this.runGiven+=2;
                break;
            case 3:
                this.ballThrown++;
                this.runGiven+=3;
                break;
            case 4:
                this.ballThrown++;
                this.runGiven+=4;
                break;
            case 5:
                this.ballThrown++;
                this.runGiven+=5;
                break;
            case 6:
                this.ballThrown++;
                this.runGiven+=6;
                break;
            case 7:
                this.ballThrown++;
                this.wicketTaken++;
                break;


        }

    }
}
