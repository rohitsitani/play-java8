package com.rohit.restapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FootballMatchRestAPI {

	private static final String url = "https://jsonmock.hackerrank.com/api/football_matches";
	
	public static void main(String[] args) {
		System.out.println("start");
		FootballMatchRestAPI api = new FootballMatchRestAPI();
		int year = 2011;
		String team = "Barcelona";
		int goals1 = api.totalGoals(year, team, "team1", 1, 0);
		int goals2 = api.totalGoals(year, team, "team2", 1, 0);
		System.out.println("total goals:" + goals1 + ":" + goals2);
		
		int winnergoals1 = api.totalWinnerGoals(year, team, "team1", 1, 0);
		int winnergoals2 = api.totalWinnerGoals(year, team, "team2", 1, 0);
		System.out.println("total winning goals: "+winnergoals1 + ":" + winnergoals2);
		
		int drawMatches1 = api.totalDrawMatches(year, team, "team1", 1, 0);
		int drawMatches2 = api.totalDrawMatches(year, team, "team2", 1, 0);
		System.out.println("total draw Matches:"+drawMatches1 + ":" + drawMatches2);


	}

	private int totalGoals(int year, String teamName, String teamtype, int page, int teamGoals) {
		String computedURL = String.format(url+"?year=%s&"+teamtype+"=%s",year,teamName);
		System.out.println(computedURL);
		String response = this.getResponsePerPage(computedURL,page);
		System.out.println(response);
		JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
		int totalPage = jsonObject.get("total_pages").getAsInt();
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");
		for(JsonElement jsonElement : jsonArray) {
			teamGoals = teamGoals + jsonElement.getAsJsonObject().get(teamtype+"goals").getAsInt();
		}
		if (totalPage == page) {
			return teamGoals;
		} else {
			return totalGoals(year, teamName, teamtype, page+1, teamGoals);
		}
	}
	
	private int totalWinnerGoals(int year, String teamName, String teamtype, int page, int teamGoals) {
		String computedURL = String.format(url+"?year=%d&"+teamtype+"=%s",year,teamName);
		System.out.println(computedURL);
		String response = this.getResponsePerPage(computedURL,page);
		System.out.println(response);
		JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
		int totalPage = jsonObject.get("total_pages").getAsInt();
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");
		for(JsonElement jsonElement : jsonArray) {
			int team1 = jsonElement.getAsJsonObject().get("team1goals").getAsInt();
			int team2 = jsonElement.getAsJsonObject().get("team2goals").getAsInt();
			if(teamtype.equalsIgnoreCase("team1") && team1 > team2) {
					teamGoals = teamGoals + team1;
			} else if(teamtype.equalsIgnoreCase("team2") && team1 < team2) {
				teamGoals = teamGoals + team2;
		}
		}
		if (totalPage == page) {
			return teamGoals;
		} else {
			return totalWinnerGoals(year, teamName, teamtype, page+1, teamGoals);
		}
	}
	
	private int totalDrawMatches(int year, String teamName, String teamtype, int page, int teamGoals) {
		String computedURL = String.format(url+"?year=%d&"+teamtype+"=%s",year,teamName);
		System.out.println(computedURL);
		String response = this.getResponsePerPage(computedURL,page);
		System.out.println(response);
		JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
		int totalPage = jsonObject.get("total_pages").getAsInt();
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");
		for(JsonElement jsonElement : jsonArray) {
			int team1 = jsonElement.getAsJsonObject().get("team1goals").getAsInt();
			int team2 = jsonElement.getAsJsonObject().get("team2goals").getAsInt();
			if(teamtype.equalsIgnoreCase("team1") && team1 == team2) {
					teamGoals = teamGoals + team1;
			} else if(teamtype.equalsIgnoreCase("team2") && team1 == team2) {
				teamGoals = teamGoals + team2;
		}
		}
		if (totalPage == page) {
			return teamGoals;
		} else {
			return totalDrawMatches(year, teamName, teamtype, page+1, teamGoals);
		}
	}

	private String getResponsePerPage(String computedURL, int page) {
		try {
			URL url = new URL(computedURL+"&page="+page);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			int status = connection.getResponseCode();
			if (status >= 200 && status < 300) {
				//success
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder builder = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				return builder.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
