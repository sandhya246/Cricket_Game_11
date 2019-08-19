public class Player
{
    enum PlayerRole
    {
        BATSMAN,BOWLER,WICKETKEEPER,ALLROUNDER
    }

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
    public PlayerRole getPlayerRole()
    {
       return this.playerRole;
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

    public int getBallPlayed()
    {
       return this.ballPlayed;
    }

    @Override
    public String toString()
    {
      return "this.playerRole";
    }
    public void runScored(int run)
    {
        switch(run)
        {
            case 0:
                this.ballPlayed++;
                break;
            case 1:
                this.ballPlayed++;
                this.runScored+=1;
                break;
            case 2:
                 this.ballPlayed++;
                 this.runScored+=2;
                 break;

            case 3:
                 this.ballPlayed++;
                 this.runScored+=3;
                 break;
            case 4:
                 this.ballPlayed++;
                 this.runScored+=4;
                 this.noOfFour++;
                 break;
            case 5:
                 this.ballPlayed++;
                 this.runScored+=5;
                 break;
            case 6:
                 this.ballPlayed++;
                 this.runScored+=6;
                 this.noOfSix++;
                 break;
            case 7:
                this.ballPlayed++;
                this.out=1;


        }
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
    public void resetPlayerInfo()
    {
        ballThrown=0;
        runScored=0;
        wicketTaken=0;
        overBowled=0;
        ballPlayed=0;
        runGiven=0;
        noOfFour=0;
        noOfSix=0;
        out=0;
    }




}
