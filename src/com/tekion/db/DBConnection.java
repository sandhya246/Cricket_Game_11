package com.tekion.db;

import java.sql.*;
import java.util.List;

import com.tekion.beans.Player;
import com.tekion.beans.Team;

public class DBConnection
{
    static Connection conn;
    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CricketGame", "root","tekion@123");

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.getMessage();
        }
    }



    static PreparedStatement preparedStatement = null;
    static ResultSet rst = null;

    public static void closeConnection()
    {
        try
        {
          conn.close();
        }
        catch (Exception e)
        {

        }
    }

    public static void clearPreviosSeries()
    {
       try
       {
          preparedStatement=conn.prepareStatement("Delete from Player");
          preparedStatement.executeUpdate();

          preparedStatement=conn.prepareStatement("Delete from ball_data");
          preparedStatement.executeUpdate();

          preparedStatement=conn.prepareStatement("delete from CricketGame.Match");
          preparedStatement.executeUpdate();

          preparedStatement=conn.prepareStatement("delete from Series");
          preparedStatement.executeUpdate();

          preparedStatement=conn.prepareStatement("delete from Team");
          preparedStatement.executeUpdate();

          preparedStatement=conn.prepareStatement("delete from Player_score");
          preparedStatement.executeUpdate();
       }
       catch(Exception e)
       {
         e.printStackTrace();
       }
    }

    public static void  updatePlayerData(Team team1, Team team2)
    {
        try
        {
            List<Player> playerList1=team1.getPlayerList();
            int team1_id=team1.getTeam_id();

            for(int i=0;i<11;i++)
            {
                Player player=playerList1.get(i);
                int player_id=player.getPlayerId();
                String player_name=player.getPlayerName();
                String player_role= player.getPlayerRole();
                preparedStatement= conn.prepareStatement("Insert into Player(player_id,player_name,team_id,player_role) values("+player_id+",'"+player_name+"',"+team1_id+",'"+player_role+"')");
                preparedStatement.executeUpdate();
            }

            List<Player> playerList2=team2.getPlayerList();
            int team2_id=team2.getTeam_id();

            for(int i=0;i<11;i++)
            {
                Player player=playerList2.get(i);
                int player_id=player.getPlayerId();
                String player_name=player.getPlayerName();
                String player_role= player.getPlayerRole();

                preparedStatement= conn.prepareStatement("Insert into Player(player_id,player_name,team_id,player_role) values("+player_id+",'"+player_name+"',"+team2_id+",'"+player_role+"')");
                preparedStatement.executeUpdate();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public static void updatePlayerScore(int match_id, int series_id, Team team1, Team team2)
    {

        try
        {
            List<Player> playerList1=team1.getPlayerList();
            int team1_id=team1.getTeam_id();

            for(int i=0;i<11;i++)
            {
                Player player = playerList1.get(i);


                preparedStatement=conn.prepareStatement("Insert into Player_Score(player_id,match_id,series_id,ball_played,run_scored,over_bowled,wicket_taken,runs_given,no_of_four,no_of_six,ball_thrown,is_out) " +
                        "values("+player.getPlayerId()+","+match_id+","+series_id+","+player.getBallPlayed()+","+player.getRunScored()+","+player.getOverBowled()+","+player.getWicketTaken()+","+player.getRunGiven()+","+player.getNoOfFour()+","+player.getNoOfSix()+","+player.getBallThrown()+","+player.getOut()+") ");

                preparedStatement.executeUpdate();




            }

            List<Player> playerList2=team1.getPlayerList();
            int team2_id=team1.getTeam_id();

            for(int i=0;i<11;i++)
            {
                Player player = playerList1.get(i);


                preparedStatement=conn.prepareStatement("Insert into Player_Score(player_id,match_id,series_id,ball_played,run_scored,over_bowled,wicket_taken,runs_given,no_of_four,no_of_six,ball_thrown,is_out) " +
                        "values("+player.getPlayerId()+","+match_id+","+series_id+","+player.getBallPlayed()+","+player.getRunScored()+","+player.getOverBowled()+","+player.getWicketTaken()+","+player.getRunGiven()+","+player.getNoOfFour()+","+player.getNoOfSix()+","+player.getBallThrown()+","+player.getOut()+") ");

                preparedStatement.executeUpdate();




            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void updateBallData(int ball_no,int team_id,String striker,String bowler,int run,int total_run,int wicket,int total_wicket)
    {
      try
      {
        preparedStatement=conn.prepareStatement("Insert into ball_data(ball_no,team_id,striker,bowler,run,total_run,wicket,total_wicket) values("+ball_no+","+team_id+",'"+striker+"','"+bowler+"',"+run+","+total_run+","+wicket+","+total_wicket+")");
        preparedStatement.executeUpdate();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
    }

    public static void updateTeamData(Team team)
    {
        try
        {
            int team_id=team.getTeam_id();
            String team_name=team.getTeam_name();
            preparedStatement = conn.prepareStatement("Insert into Team(team_id,team_name) values("+team_id+",'"+team_name+"')" );
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


    public static void updateMatchData(int match_id, int series_id, Team team1, Team team2, Team match_won)
    {
        try
        {

         preparedStatement = conn.prepareStatement("Insert into CricketGame.Match(match_id,series_id,team1_id,team2_id,run_scoredby_team1,wicket_lostby_team1,run_scoredby_team2,wicket_lostby_team2,winner_team_id) values("+match_id+","+series_id+","+team1.getTeam_id()+","+team2.getTeam_id()+","+team1.getRunScored()+","+team1.getWicketLost()+","+team2.getRunScored()+","+team2.getWicketLost()+","+match_won.getTeam_id()+")" );
         preparedStatement.executeUpdate();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void updateSeriesData(int series_id, Team team1, Team team2)
    {
        try
        {   int team1_id=team1.getTeam_id();
            int team2_id=team2.getTeam_id();
            preparedStatement = conn.prepareStatement("Insert into Series(series_id,team1_id,team2_id) values("+series_id+","+team1_id+","+team2_id+")" );
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateSeriesWinner(int series_id, Team team1, Team team2)
    {
        try
        {
            preparedStatement = conn.prepareStatement("Select * from CricketGame.Match where  winner_team_id= "+ team1.getTeam_id() +" ");
            rst=preparedStatement.executeQuery();
            int team1_won_match=0;

            while(rst.next())
            {
              team1_won_match++;
            }


            preparedStatement = conn.prepareStatement("Select * from CricketGame.Match where  winner_team_id= " + team2.getTeam_id() +" ");
            rst=preparedStatement.executeQuery();
            int team2_won_match = 0;

            while(rst.next())
            {
               team2_won_match++;


            }

            preparedStatement = conn.prepareStatement("Select * from CricketGame.Match where  series_id=" + series_id + " ");
            rst=preparedStatement.executeQuery();
            int noOfMatchPlayed = 0;

            while(rst.next())
            {
                noOfMatchPlayed++;
            }
            System.out.println("team1 winner "+team1_won_match);
            System.out.println("team2 winner  "+team2_won_match);
            System.out.println("no of match "+noOfMatchPlayed);

            int seriesWinner = team1_won_match > team2_won_match ? team1.getTeam_id() : team2.getTeam_id();
            preparedStatement = conn.prepareStatement("Insert into CricketGame.Series(series_id,team1_id,team2_id,total_match_played,team1_won,team2_won,series_winner) values("+series_id+","+team1.getTeam_id()+","+team2.getTeam_id()+"," + noOfMatchPlayed + "," + team1_won_match + "," + team2_won_match + "," + seriesWinner + ") ");
            preparedStatement.executeUpdate();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
