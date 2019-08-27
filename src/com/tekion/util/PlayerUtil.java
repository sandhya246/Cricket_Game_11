package com.tekion.util;

import com.tekion.beans.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtil {

    // private Constructor
    private PlayerUtil(){

    }

    /*
     * Reset list of players
     */
    public static void resetPlayers(List<Player> players)
    {
        List<Player> reset_players = new ArrayList<>();
        for(Player player:players){
            resetPlayer(player);
        }

    }
    public static void runScored(Player player,int run)
    {
        switch(run)
        {
            case 0:
                player.setBallPlayed(player.getBallPlayed()+1);
                break;
            case 1:
                player.setBallPlayed(player.getBallPlayed()+1);
                player.setRunScored(player.getRunScored()+1);
                break;
            case 2:
                player.setBallPlayed(player.getBallPlayed()+1);
                player.setRunScored(player.getRunScored()+2);
                break;

            case 3:
                player.setBallPlayed(player.getBallPlayed()+1);
                player.setRunScored(player.getRunScored()+3);
                break;
            case 4:
                player.setBallPlayed(player.getBallPlayed()+1);
                player.setRunScored(player.getRunScored()+4);
                player.setNoOfFour(player.getNoOfFour()+1);
                break;
            case 5:
                player.setBallPlayed(player.getBallPlayed()+1);
                player.setRunScored(player.getRunScored()+5);
                break;
            case 6:
                player.setBallPlayed(player.getBallPlayed()+1);
                player.setRunScored(player.getRunScored()+6);
                player.setNoOfSix(player.getNoOfSix()+1);
                break;
            case 7:
                player.setBallPlayed(player.getBallPlayed()+1);
                player.setOut(1);

        }
    }

    public void runGiven(Player player,int run)
    {
        switch(run)
        {
            case 0:
                player.setBallThrown(player.getBallThrown()+1);
                break;
            case 1:
                player.setBallThrown(player.getBallThrown()+1);
                player.setRunGiven(player.getRunGiven()+1);
                break;
            case 2:
                player.setBallThrown(player.getBallThrown()+1);
                player.setRunGiven(player.getRunGiven()+2);
                break;
            case 3:
                player.setBallThrown(player.getBallThrown()+1);
                player.setRunGiven(player.getRunGiven()+3);
                break;
            case 4:
                player.setBallThrown(player.getBallThrown()+1);
                player.setRunGiven(player.getRunGiven()+4);
                break;
            case 5:
                player.setBallThrown(player.getBallThrown()+1);
                player.setRunGiven(player.getRunGiven()+5);
                break;
            case 6:
                player.setBallThrown(player.getBallThrown()+1);
                player.setRunGiven(player.getRunGiven()+6);
                break;
            case 7:
                player.setBallThrown(player.getBallThrown()+1);
                player.setWicketTaken(player.getWicketTaken()+1);
                break;


        }

    }
    public static void resetPlayer(Player player)
    {
        player.setBallThrown(0);
        player.setRunScored(0);
        player.setWicketTaken(0);
        player.setOverBowled(0);
        player.setBallPlayed(0);
        player.setRunGiven(0);
        player.setNoOfFour(0);
        player.setNoOfSix(0);
        player.setOut(0);
    }

}
