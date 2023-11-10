package com.TechnoWebDistributed.tp2;
import com.TechnoWebDistributed.tp2.model.Match;
import com.TechnoWebDistributed.tp2.model.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class FootBallService {

    private static final String API_URL = "https://jsonmock.hackerrank.com/api/football_matches";

//        https://jsonmock.hackerrank.com/api/football_matches?year=2011&team1=Barcelona&page=1
//        20 points
//        https://jsonmock.hackerrank.com/api/football_matches?year=2011&team2=Barcelona&page=1
//        15 points

    public static void main(String[] args) {
        System.out.println(getTotalGoalsByTeamAndYear("Barcelona", 2011));
    }



    /**
     * Get the total number of goals scored by a team in a given year.
     *
     * @param team the team name
     * @param year the year to consider
     * @return the total number of goals scored by the team in the given year
     */
    public static int getTotalGoalsByTeamAndYear(String team, int year) {
        return getTotalGoalsByTeamAndYearOnlyOneSide(team, year, 1)
                + getTotalGoalsByTeamAndYearOnlyOneSide(team, year, 2);
    }

    private static int getTotalGoalsByTeamAndYearOnlyOneSide(String team, int year, int side) {
        String apiUrl = String.format("%s?year=%d&team%d=%s", API_URL, year, side, team);

        // Create RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Make the HTTP GET request
        ResponseEntity<Page> response = restTemplate.getForEntity(apiUrl,Page.class);

        // Extract the total goals from the response
        Page pageBody = response.getBody();

        int totalGoals = 0;
        // if only one page, get it directly
        // else, get all pages and sum the goals
        int totalPages = pageBody.getTotal_pages();
        if (totalPages == 1) {
            return getGoalsFromListMatch(pageBody.getData(), side);
        } else {
            totalGoals += getGoalsFromListMatch(pageBody.getData(), side);
            for (int i = 2; i <= totalPages; i++) {
                apiUrl = String.format("%s?year=%d&team%d=%s&page=%d", API_URL, year, side, team);
                response = restTemplate.getForEntity(apiUrl,Page.class);
                pageBody = response.getBody();
                totalGoals += getGoalsFromListMatch(pageBody.getData(), side);
            }
            return totalGoals;
        }
    }

    /**
     * Get the total number of goals scored from a list of Match.
     * @param data the API response
     * @return the total number of goals scored
     */
    private static int getGoalsFromListMatch(List<Match> data, int side){
        // for each match, get the goal
        // sum all goals
        int totalGoals = 0;
        int parsedGoals;
        for (Match match : data) {
            try {
                if (side == 1) {
                    parsedGoals = Integer.parseInt(match.getTeam1goals());
                } else {
                    parsedGoals = Integer.parseInt(match.getTeam2goals());
                }
            } catch (NumberFormatException e) {
                parsedGoals = 0;
            }
            totalGoals += parsedGoals;
        }
        return totalGoals;
    }

}
