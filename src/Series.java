public class Series
{

    private static int seriesId=1;

    public static void setSeriesId(int seriesId)
    {
        Series.seriesId++;
    }
    public static  int getSeriesId()
    {
       return Series.seriesId;
    }

    public static void startSeries(int team1_Id, String team1_name, int team2_Id, String team2_name, int noOfMatch)
    {
        Team team1=null;
        Team team2=null;


        team1=new Team(team1_Id,team1_name);
        team2=new Team(team2_Id,team2_name);

        AddPlayerList(team1,team2);

        DBConnection.clearPreviosSeries();
        DBConnection.updateTeamData(team1);
        DBConnection.updateTeamData(team2);

        DBConnection.updatePlayerData(team1,team2);

        for(int i=1;i<=noOfMatch;i++)
        {
            System.out.println(i + " Match started");
            startSeriesMatch(seriesId,i,team1,team2,noOfMatch);
        }
        DBConnection.updateSeriesWinner(seriesId,team1,team2);
        seriesId++;
        DBConnection.closeConnection();


    }

    public static  void AddPlayerList(Team team1,Team team2)
    {
        team1.addPlayer(new Player(1,"Rohit Sharma",Player.PlayerRole.BATSMAN));
        team1.addPlayer(new Player(2,"virat Kohli",Player.PlayerRole.BATSMAN));
        team1.addPlayer(new Player(3,"Lokesh Rahul",Player.PlayerRole.BATSMAN));
        team1.addPlayer(new Player(4,"MS Dhoni",Player.PlayerRole.WICKETKEEPER));
        team1.addPlayer(new Player(5,"Shikhar dhawan",Player.PlayerRole.BATSMAN));
        team1.addPlayer(new Player(6,"hardik pandya",Player.PlayerRole.ALLROUNDER));
        team1.addPlayer(new Player(7,"Mohammad Shami",Player.PlayerRole.BOWLER));
        team1.addPlayer(new Player(8,"Jasprit Bumrah",Player.PlayerRole.BOWLER));
        team1.addPlayer(new Player(9,"Bhuvneshwar Kumar",Player.PlayerRole.BOWLER));
        team1.addPlayer(new Player(10,"Yuzvendra Chahal",Player.PlayerRole.BOWLER));
        team1.addPlayer(new Player(11,"dinesh Kartilk",Player.PlayerRole.BOWLER));



        team2.addPlayer((new Player(12,"Joe Root",Player.PlayerRole.BATSMAN)));
        team2.addPlayer((new Player(13,"Buttler",Player.PlayerRole.WICKETKEEPER)));
        team2.addPlayer((new Player(14,"Ben Stokes",Player.PlayerRole.ALLROUNDER)));
        team2.addPlayer((new Player(15,"Moeen Ali",Player.PlayerRole.ALLROUNDER)));
        team2.addPlayer((new Player(16,"Eoin Morgan",Player.PlayerRole.BATSMAN)));
        team2.addPlayer((new Player(17,"Johny Bairstrow",Player.PlayerRole.WICKETKEEPER)));
        team2.addPlayer((new Player(18,"Aadil Rashid",Player.PlayerRole.BOWLER)));
        team2.addPlayer((new Player(19,"Jofra Archer",Player.PlayerRole.BOWLER)));
        team2.addPlayer((new Player(20,"Jason Roy",Player.PlayerRole.BOWLER)));
        team2.addPlayer((new Player(21,"Chris Wokes",Player.PlayerRole.BOWLER)));
        team2.addPlayer((new Player(22,"Tom Curran",Player.PlayerRole.BOWLER)));



    }


    public static void startSeriesMatch(int seriesId,int matchId,Team team1,Team team2,int noOfMatch)
    {
       Match  match = new Match(seriesId,matchId,team1,team2,20,noOfMatch);
       match.startMatch();

    }
}
