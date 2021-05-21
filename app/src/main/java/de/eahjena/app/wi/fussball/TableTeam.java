package de.eahjena.app.wi.fussball;

public class TableTeam {
    //Erstellung der Attribute
    int place;
    int teamInfoId;
    String teamName;
    String shortName;
    int matches;
    int won;
    int draw;
    int lost;
    int goals;
    int opponentgoals;
    int points;
    String teamIconUrl;



    public TableTeam(int place, String teamName, int goals, int points, int matches, String teamIconUrl) {
        this.place = place;
        this.teamName = teamName;
        this.goals = goals;
        this.points = points;
        this.matches = matches;
        this.teamIconUrl = teamIconUrl;
    }
}
