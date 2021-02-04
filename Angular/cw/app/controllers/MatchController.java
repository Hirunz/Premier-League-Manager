package controllers;

import classes.*;
import classes.Console;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static classes.Console.manager;

public class MatchController extends Controller {
    // creating a Premier league manager to access methods which provide results for the backend functions.
    private static final PremierLeagueManager manager=new PremierLeagueManager();
    // another separate list to hold list of matches
    public List<Match> matchesPlayed= manager.getMatchesPlayed();

    public Result getMatchTable(){
        // reads the file to load data into backend.
        manager.readFromFile();
        // JsonNode is the tree model of JSON.
        // using the convertValue method in ObjectMapper(A generic method),
        // can simply convert the array of clubs directly into JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(manager.getMatchesPlayed(), JsonNode.class);
        //Returning status of 200 OK result which says the http request is successful.
        return ok(jsonData);
    }

    public Result getMatchByDate(String date){
        manager.readFromFile();
        if (date.length()==10){
            String day=date.substring(0,2);
            String month=date.substring(3,5);
            String year=date.substring(6,10);

            if (Date.isAValidDate(day,month,year)){
                Date date1 = new Date(day, month, year);
                List<Match> matchesInADate=manager.matchesInADate(date1);

                if (matchesInADate.size()==0){
                    return badRequest(ConsoleLog.warn("No matches with the given date"));
                }

                // JsonNode is the tree model of JSON.
                // using the convertValue method in ObjectMapper(A generic method),
                // can simply convert the array of clubs directly into JsonNode
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonData = mapper.convertValue(matchesInADate, JsonNode.class);
                //Returning status of 200 OK result which says the http request is successful.
                return ok(jsonData);
            }

        }

        return badRequest(ConsoleLog.warn("Invalid date."));
    }
//
    public Result getRandomMatch(){
        // reads the file to load data.
        manager.readFromFile();
        // in a premier league, num of matches= ( (club count)^2-(club count)) *2
        // for 5 clubs => (5*5 -5)*2=40
        // one club plays with every other club two times (both locations).
        if (manager.getMatchesPlayed().size()
                == (manager.getLeagueClubs().size()) * (manager.getLeagueClubs().size() - 1)) {
            return badRequest(ConsoleLog.warn("All the matches played for the season."));
        }
        else {
//             generate a random match
            Match randomMatch = randomMatch();


//             add the match to played matches and sort by points and finally update the table again.
            manager.addPlayedMatch(randomMatch);
            manager.sortByPoints();
            manager.sortMatchesByDate();
            manager.saveInFile();

            // JsonNode is the tree model of JSON.
            // using the convertValue method in ObjectMapper(A generic method),
            // can simply convert the array of clubs directly into JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonData = mapper.convertValue(randomMatch, JsonNode.class);
            //Returning status of 200 OK result which says the http request is successful.
            return ok(jsonData);
        }
    }

    public static Match randomMatch() {
        manager.readFromFile();
        Match match;
//         getting maximum size to get a random number.
        int numOfClubs = manager.getLeagueClubs().size();
        Random random = new Random();

        int randomNum1;
        int randomNum2;
//         generates two random numbers for goals.
//         the highest number of goals scored in a match is 36 according to google.
//         it happened in 1885 with a win of 36-0
        int randomGoals1 = random.nextInt(36) + 1;
        int randomGoals2 = random.nextInt(36) + 1;
//        System.out.println(randomGoals1 + " " + randomGoals2);

//         a do while loop to ensure the generated clubs are not equal.
        do {
            randomNum1 = random.nextInt(numOfClubs);
            randomNum2 = random.nextInt(numOfClubs);
//            System.out.println(randomNum1 + " " + randomNum2 + " " + numOfClubs);
        } while (randomNum1 == randomNum2);

//         gets the respective club id from the manager.
        int club1 = manager.getLeagueClubs().get(randomNum1).getClubId();
        int club2 = manager.getLeagueClubs().get(randomNum2).getClubId();

//         this checks whether both matches are played as described in the bothMatchesPlayed method
//         in PremierLeagueManager
//         if true, then it will recurse to find another match if possible.
//         this makes sure that each club gets same number of matches.
        if (manager.bothMatchesPlayed(club1, club2)) {
            match = randomMatch();
        } else {
            match = new Match(club1, club2, randomGoals1, randomGoals2, Date.randomDate());
        }

        return match;
    }







}
