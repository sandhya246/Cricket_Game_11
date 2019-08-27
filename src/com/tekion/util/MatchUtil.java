package com.tekion.util;

import com.tekion.beans.Match;
import com.tekion.beans.Player;
import com.tekion.beans.Team;
import com.tekion.db.DBConnection;

import java.util.concurrent.ThreadLocalRandom;

public class MatchUtil
{
    public MatchUtil()
    {

    }

    public  static  void doToss(Match match)
    {
        int toss= ThreadLocalRandom.current().nextInt(0,2);

        if(toss==0)
        {
            match.setToss_won(match.getTeam1());
            System.out.println(match.getToss_won().getTeam_name()+ " won toss");

            int batt= ThreadLocalRandom.current().nextInt(0,2);
            if(batt==0)
            {
                match.setBatFirst(match.getTeam1());
                match.setBowlFirst(match.getTeam2());

            }
            else
            {
                match.setBatFirst(match.getTeam2());
                match.setBowlFirst(match.getTeam1());
            }

        }
        else
        {
            match.setToss_won(match.getTeam2());
            System.out.println(match.getToss_won().getTeam_name());
            int batt= ThreadLocalRandom.current().nextInt(0,2);
            if(batt==0)
            {
                match.setBatFirst(match.getTeam2());
                match.setBowlFirst(match.getTeam1());
            }
            else
            {
                match.setBatFirst(match.getTeam1());
                match.setBowlFirst(match.getTeam2());
            }

        }
        System.out.println(match.getBatFirst().getTeam_name() +" will be batting");
        System.out.println(match.getBowlFirst().getTeam_name() + " will be bowling");
    }


    public  static void resetData(Match match)
    {
        resetMatchInfo(match);
        TeamUtil.resetTeamInfo(match.getTeam1());
        TeamUtil.resetTeamInfo(match.getTeam2());
        resetInningInfo(match);


    }


    public  static   Player getBowler(Team team)
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

    public static int randomRun()
    {
        int probab = ThreadLocalRandom.current().nextInt(0,100);

        if(probab<10)
        {
            return 0;
        }
        else if(probab<20)
        {
            return 1;
        }
        else if(probab<35)
        {
            return 2;
        }
        else if(probab<45)
        {
            return 3;
        }
        else if(probab<65)
        {
            return 4;
        }
        else if(probab<85)
        {
            return 6;
        }
        else
        {
            return 7;
        }
    }

    public static void changeStriker(Match match,Team team)
    {
        int t1 = match.getCurrStriker1();
        int t2 = match.getCurrStriker2();
        match.setCurrStriker2(t1);
        match.setCurrStriker1(t2);

        Player player1=team.getStriker1();
        Player player2=team.getStriker2();

        team.setStriker1(player2);
        team.setStriker2(player1);

    }

    public static void resetInningInfo(Match match)
    {
        match.setCurrStriker1(0);
        match.setCurrStriker2(1);
        match.setNextBatsman(2);
        match.setBall_no(0);

    }

    public static void resetMatchInfo(Match match)
    {
        match.setToss_won(null);
        match.setBatFirst(null);
        match.setBowlFirst(null);
        match.setCurrBowler(null);
        match.setTargetRun(0);
        match.setCurrStriker1(0);
        match.setCurrStriker2(1);
        match.setNextBatsman(2);
        match.setInning1(0);
        match.setNoOfMatch(0);

    }

    public static void startOver(Match match,Team batting, Team bowling)
    {
        match.setCurrBowler(getBowler(bowling));


        for(int i=0;i<6 && batting.getWicketLost()<10 ;i++)
        {
            int run=randomRun();

            match.setBall_no(match.getBall_no()+1);
            PlayerUtil.runScored(batting.getStriker1(),run);

            match.getCurrBowler().runGiven(run);

            if((batting.getStriker1().getOut()==1) && match.getNextBatsman()<10 )
            {
                batting.setWicketLost(batting.getWicketLost()+1);
                bowling.setWicketTaken(bowling.getWicketTaken()+1);
                DBConnection.updateBallData(match.getBall_no(),batting.getTeam_id(),batting.getStriker1().getPlayerName(),match.getCurrBowler().getPlayerName(),0,batting.getRunScored(),1,batting.getWicketLost());
                match.setCurrStriker1(match.getNextBatsman());

                batting.setStriker1(batting.playerList.get(match.getCurrStriker1()));
                match.setNextBatsman(match.getNextBatsman()+1);
                match.getCurrBowler().setWicketTaken(match.getCurrBowler().getWicketTaken()+1);


            }
            else
            {

                if(run!=7 && match.getInning1() == 0)
                {
                    match.setTargetRun(match.getTargetRun()+run);
                    batting.setRunScored(run);
                }
                else if(run!=7 && match.getInning1() == 1)
                {
                    batting.setRunScored(run);
                }
                DBConnection.updateBallData(match.getBall_no(),batting.getTeam_id(),batting.getStriker1().getPlayerName(),match.getCurrBowler().getPlayerName(),run,batting.getRunScored(),0,batting.getWicketLost());
                if(run==1||run==3||run==5)
                {
                    changeStriker(match,batting);
                }

            }
            if(match.getInning1()==1 && batting.getRunScored()>=match.getTargetRun())
            {
                return;
            }

        }
      //  System.out.println(batting.getStriker1().getRunScored()+ " "+batting.getStriker1().getPlayerName());
     //   System.out.println(batting.getStriker2().getRunScored()+ " "+batting.getStriker2().getPlayerName());
        changeStriker(match,batting);

        match.getCurrBowler().setOverBowled(match.getCurrBowler().getOverBowled()+1);
    }

    public static void startInning(Match match)
    {
        Team batting =match.getBatFirst();
        Team bowling =match.getBowlFirst();

        batting.setStriker1(batting.playerList.get(match.getCurrStriker1()));
        batting.setStriker2(batting.playerList.get(match.getCurrStriker2()));



        for(int i=0;i<match.getMax_over() && batting.getWicketLost()<10;i++)
        {
            startOver(match,batting,bowling);
        }

        System.out.println(batting.getTeam_name()+" scored "+ batting.getRunScored());
        match.setTargetRun(match.getTargetRun()+1);

        System.out.println("Target  Set "+ match.getTargetRun());
        match.setInning1(1);

        resetInningInfo(match);


        System.out.println("2nd Inning started");

        batting=null;
        bowling=null;
        batting=match.getBowlFirst();
        bowling=match.getBatFirst();


        batting.setStriker1(batting.playerList.get(match.getCurrStriker1()));
        batting.setStriker2(batting.playerList.get(match.getCurrStriker2()));

        for(int i=0;(i<match.getMax_over() && batting.getWicketLost()<10 && batting.getRunScored()<match.getTargetRun());i++)
        {
            startOver(match,batting,bowling);
        }


        if(batting.getRunScored()>=bowling.getRunScored())
        {
            System.out.println(batting.getTeam_name()+"  scored "+ batting.getRunScored());
            System.out.println(bowling.getTeam_name()+"  scored "+ bowling.getRunScored());
            match.setMatch_won(batting);
        }
        else
        {
            System.out.println(batting.getTeam_name()+"  scored "+ batting.getRunScored());
            System.out.println(bowling.getTeam_name()+"  scored "+ bowling.getRunScored());
            match.setMatch_won(bowling);
        }



    }

    public static void DisplayResult(Match match)
    {
        if(match.getMatch_won()!=null)
            System.out.println(match.getMatch_won().getTeam_name()+ " won "+ " scored " +match.getMatch_won().getRunScored()+" wicket lost"+match.getMatch_won().getWicketLost());

    }
    public static void updateDatabase(Match match)
    {

        DBConnection.updateMatchData(match.getMatch_Id(),match.getSeries_Id(),match.getTeam1(),match.getTeam2(),match.getMatch_won());

        DBConnection.updatePlayerScore(match.getMatch_Id(),match.getSeries_Id(),match.getTeam1(),match.getTeam2());

    }

    public static void startMatch(Match match)
    {
        doToss(match);
        startInning(match);
        updateDatabase(match);
        DisplayResult(match);
        DisplayePlayerPerformance(match);
        resetData(match);

    }
    public static  void DisplayePlayerPerformance(Match match)
    {
        System.out.println("################################################################----Team1-----#############################################");
        System.out.println("PlayerId "+ " PlayerName "+ " playerRole "+ "RunScored "+ "Overbowled " + "wicketTaken "+" NoOfFour "+ " NoOfSix");
        for(int i=0;i<11;i++)
        {
            Player player=match.getTeam1().playerList.get(i);
            System.out.println(player.getPlayerId()+" "+player.getPlayerName()+" "+player.getPlayerRole()+" "+player.getRunScored()+" "+player.getOverBowled()+" "+player.getWicketTaken()
                    +" "+player.getNoOfFour()+" "+player.getNoOfSix());


        }

        System.out.println("################################################################----Team1-----#############################################");
        System.out.println("Player_id "+ " Player_Name "+ " player Role "+ "Run scored "+ "Over bowled " + "wicket taken "+" No of four "+ " No Of Six");
        for(int i=0;i<11;i++)
        {
            Player player=match.getTeam2().playerList.get(i);
            System.out.println(player.getPlayerId()+" "+player.getPlayerName()+" "+player.getPlayerRole()+" "+player.getRunScored()+" "+player.getOverBowled()+" "+player.getWicketTaken()
                    +" "+player.getNoOfFour()+" "+player.getNoOfSix());


        }
    }
}
