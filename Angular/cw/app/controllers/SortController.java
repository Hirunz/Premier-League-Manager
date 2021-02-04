package controllers;

import classes.ConsoleLog;
import classes.FootballClub;
import classes.Match;
import classes.PremierLeagueManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class SortController extends Controller {
    // creating a Premier league manager to access methods which provide results for the backend functions.
    private static final PremierLeagueManager manager=new PremierLeagueManager();
    // another separate list to hold list of football clubs
    public List<FootballClub> clubs= manager.getLeagueClubs();

    public Result getSortByGoals(){
        manager.readFromFile();
        manager.sortByGoalsScored();
        return getJson(clubs);
    }

    public Result getSortByWins(){
        manager.readFromFile();
        manager.sortByWins();
        return getJson(clubs);
    }

    public Result getDefaultSort(){
        manager.readFromFile();
        manager.sortByPoints();
        return getJson(clubs);

    }

    public Result getJson(List<FootballClub> clubs){
        // JsonNode is the tree model of JSON.
        // using the convertValue method in ObjectMapper(A generic method),
        // can simply convert the array of clubs directly into JsonNode
        ObjectMapper mapper = new ObjectMapper();
        if (clubs.size()==0){
            return badRequest(ConsoleLog.error("Sorting Error","SortController"));
        }
        JsonNode jsonData = mapper.convertValue(clubs, JsonNode.class);
        // sending a ok result which tells the http response was successful
        return ok(jsonData);
    }


}
