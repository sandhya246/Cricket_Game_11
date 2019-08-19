import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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

  public Match(int series_Id,int match_Id,Team team1,Team team2,int max_over,int noOfMatch)
  {
    this.series_Id=series_Id;
    this.match_Id=match_Id;
    this.team1=team1;
    this.team2=team2;
    this.max_over=max_over;
    this.noOfMatch=noOfMatch;
  }


  public void doToss()
  {
    int toss= ThreadLocalRandom.current().nextInt(0,2);

    if(toss==0)
    {
      this.toss_won=team1;
      System.out.println(toss_won.getTeam_name()+ " won toss");
      int batt= ThreadLocalRandom.current().nextInt(0,2);
      if(batt==0)
      {
        this.batFirst=team1;
        this.bowlFirst=team2;
      }
      else
      {
        this.batFirst=team2;
        this.bowlFirst=team1;
      }

    }
    else
    {
      this.toss_won=team2;
      System.out.println(toss_won.getTeam_name());
      int batt= ThreadLocalRandom.current().nextInt(0,2);
      if(batt==0)
      {
        this.batFirst=team2;
        this.bowlFirst=team1;
      }
      else
      {
        this.batFirst=team1;
        this.bowlFirst=team2;
      }

    }
    System.out.println(batFirst.getTeam_name() +" will be batting");
    System.out.println(bowlFirst.getTeam_name() + " will be bowling");
  }



  public Player getBowler(Team team)
  {
      while(true)
      {
        int t = ThreadLocalRandom.current().nextInt(6, 11);
        Player player = team.playerList.get(t);
        if (player.getOverBowled() < Player.MAX_OVERS)
        {
          return team.playerList.get(t);
        }
      }
  }



  public int randomRun()
  {
     int probab = ThreadLocalRandom.current().nextInt(0,100);

     if(probab<15)
     {
         return 0;
     }
     else if(probab<25)
     {
         return 1;
     }
     else if(probab<35)
     {
        return 2;
     }
     else if(probab<50)
     {
         return 3;
     }
     else if(probab<70)
     {
         return 4;
     }
     else if(probab<90)
     {
         return 6;
     }
     else
     {
         return 7;
     }
  }


  public void changeStriker()
  {
    int temp=this.currStriker1;
    this.currStriker1=this.currStriker2;
    this.currStriker2=temp;
  }


  public void startOver(Team batting,Team bowling)
  {
    currBowler=getBowler(bowling);

    for(int i=0;i<6 && batting.getWicketLost()<10 ;i++)
    {
      int run=randomRun();
      ball_no++;
      batting.getStriker1().runScored(run);
      currBowler.runGiven(run);
      if((batting.getStriker1().getOut()==1) && nextBatsman<10 )
      {
          
        batting.setWicketLost(batting.getWicketLost()+1);
        bowling.setWicketTaken(bowling.getWicketTaken()+1);
        DBConnection.updateBallData(ball_no,batting.getTeam_id(),batting.getStriker1().getPlayerName(),currBowler.getPlayerName(),0,batting.getRunScored(),1,batting.getWicketLost());
        currStriker1=nextBatsman++;
        batting.setStriker1(batting.playerList.get(currStriker1));

        currBowler.setWicketTaken(currBowler.getWicketTaken()+1);


      }
      else
      {

         if(run!=7 && inning1 == 0)
         {
           targetRun=targetRun+run;
           batting.setRunScored(run);
         }
         else if(run!=7 && inning1 == 1)
         {
           batting.setRunScored(run);
         }
         DBConnection.updateBallData(ball_no,batting.getTeam_id(),batting.getStriker1().getPlayerName(),currBowler.getPlayerName(),run,batting.getRunScored(),0,batting.getWicketLost());
         if(run==1||run==3||run==5)
         {
           changeStriker();
         }

      }
      if(inning1==1 && batting.getRunScored()>=targetRun)
      {
        return;
      }

    }
    changeStriker();
    currBowler.setOverBowled(currBowler.getOverBowled()+1);
  }

  public void resetInningInfo()
  {
    currStriker1=0;
    currStriker2=1;
    nextBatsman=2;
    ball_no=0;
  }

  public void resetMatchInfo()
  {

      toss_won=null;
      batFirst=null;
      bowlFirst=null;
      currBowler=null;
      targetRun=0;
      currStriker1=0;
      currStriker2=1;
      nextBatsman=2;
      inning1=0;
      noOfMatch=0;
  }


  public void startInning()
  {
      System.out.println("1st Inning  started");
      Team batting = batFirst;
      Team bowling = bowlFirst;

      batting.setStriker1(batting.playerList.get(currStriker1));
      batting.setStriker2(batting.playerList.get(currStriker2));

      for(int i=0;i<max_over && batting.getWicketLost()<10;i++)
      {
          startOver(batting,bowling);
      }

      System.out.println(batting.getTeam_name()+" scored  targetRun "+ batting.getRunScored());
      targetRun=targetRun+1;
      System.out.println("Target  Set "+ targetRun);
      inning1=1;

      resetInningInfo();


      System.out.println("2nd Inning started");
      batting=bowlFirst;
      bowling=batFirst;

      batting.setStriker1(batting.playerList.get(currStriker1));
      batting.setStriker2(batting.playerList.get(currStriker2));
      for(int i=0;(i<max_over && batting.getWicketLost()<10 && batting.getRunScored()<targetRun);i++)
      {
          startOver(batting,bowling);
      }

      resetInningInfo();
      if(batting.getRunScored()>=bowling.getRunScored())
      {
         System.out.println(batting.getTeam_name()+"  scored "+ batting.getRunScored());
         System.out.println(bowling.getTeam_name()+"  scored "+ bowling.getRunScored());
         match_won=batting;
      }
      else
      {
         System.out.println(batting.getTeam_name()+"  scored "+ batting.getRunScored());
         System.out.println(bowling.getTeam_name()+"  scored "+ bowling.getRunScored());
         match_won=bowling;
      }




  }


  public void DisplayResult()
  {
    if(match_won!=null)
      System.out.println(match_won.getTeam_name()+ " won "+ " scored " + match_won.getRunScored());

  }

  public void DisplayePlayerPerformance()
  {
       System.out.println("################################################################----Team1-----#############################################");
       System.out.println("PlayerId "+ " PlayerName "+ " playerRole "+ "RunScored "+ "Overbowled " + "wicketTaken "+" NoOfFour "+ " NoOfSix");
       for(int i=0;i<11;i++)
       {
         Player player=team1.playerList.get(i);
         System.out.println(player.getPlayerId()+" "+player.getPlayerName()+" "+player.getPlayerRole()+" "+player.getRunScored()+" "+player.getOverBowled()+" "+player.getWicketTaken()
         +" "+player.getNoOfFour()+" "+player.getNoOfSix());


       }

        System.out.println("################################################################----Team1-----#############################################");
        System.out.println("Player_id "+ " Player_Name "+ " player Role "+ "Run scored "+ "Over bowled " + "wicket taken "+" No of four "+ " No Of Six");
        for(int i=0;i<11;i++)
        {
          Player player=team2.playerList.get(i);
          System.out.println(player.getPlayerId()+" "+player.getPlayerName()+" "+player.getPlayerRole()+" "+player.getRunScored()+" "+player.getOverBowled()+" "+player.getWicketTaken()
                  +" "+player.getNoOfFour()+" "+player.getNoOfSix());


        }
  }

  public void resetData()
  {
      ArrayList<Player> playerList1= team1.getPlayerList();
      ArrayList<Player> playerList2=team2.getPlayerList();
      for(int i=0;i<11;i++)
      {
           playerList1.get(i).resetPlayerInfo();
           playerList2.get(i).resetPlayerInfo();
      }
     team1.resetTeamInfo();
     team2.resetTeamInfo();
     resetMatchInfo();


  }

  public  void updateDatabase()
  {

       DBConnection.updateMatchData(match_Id,series_Id,team1,team2,match_won);

       DBConnection.updatePlayerScore(match_Id,series_Id,team1,team2);

  }

  public void startMatch()
  {
    doToss();
    startInning();
    updateDatabase();
    DisplayResult();
    DisplayePlayerPerformance();
    resetData();

  }




}
