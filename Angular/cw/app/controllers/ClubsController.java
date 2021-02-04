package controllers;

import classes.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;



public class ClubsController extends Controller {

    // creating a Premier league manager to access methods which provide results for the backend functions.
    private static final PremierLeagueManager manager=new PremierLeagueManager();
    // another separate list to hold list of football clubs
    public List<FootballClub> clubs= manager.getLeagueClubs();


    public Result getLeagueTable(){
        // since there is no main method, read and sort functions are called to retrieve the data from the txt file.
        manager.readFromFile();
        manager.sortByPoints();

        // using an object mapper in the jackson library,
        // converted the club data into JSON tree model, a JsonNode.

        ObjectMapper mapper = new ObjectMapper();

        // no need to convert to JSON if the clubs are empty.
        // Therefore, simply returning a bad request in that case.
        if (clubs.size()==0){
            return badRequest(ConsoleLog.error("No league Clubs to show.",
                    "ClubsController.getLeagueTable()"));
        }

        // JsonNode is the tree model of JSON.
        // using the convertValue method in ObjectMapper(A generic method),
        // can simply convert the array of clubs directly into JsonNode
        JsonNode jsonData = mapper.convertValue(clubs, JsonNode.class);
        //Returning status of 200 OK result which says the http request is successful.
        return ok(jsonData);
    }

    // This method is for future purpose. This is used to search a specific club
    // and then return it as a json object.
    // here, since we only have a single object, can simply convert to JSON using toJson method in Json class.
    public Result getClubData(int clubId){
        for (FootballClub c: clubs){
            if (c.getClubId()==clubId){
               JsonNode json=Json.toJson(c);

                return ok(json);
            }
        }
        return badRequest(ConsoleLog.error("club not found.","ClubsController.getClubData()"));
    }






}
