package com.rohit.restapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FootballMatchRestAPI {

	private static final String url = "https://jsonmock.hackerrank.com/api/football_matches";
	private static final String url_competetions = "https://jsonmock.hackerrank.com/api/football_competitions";

	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("start");
		FootballMatchRestAPI api = new FootballMatchRestAPI();
		int year = 2011;
		String teamName = "Barcelona";
		String competition = "UEFA Champions League";
		int goals1 = api.totalGoals(year, teamName, "team1", 1, 0);
		int goals2 = api.totalGoals(year, teamName, "team2", 1, 0);
		System.out.println("total goals:" + goals1 + ":" + goals2);
		
		teamName = api.getChampionshipTeam(year, competition);
		System.out.println("teamName:"+teamName);
		
		int winnergoals1 = api.totalWinnerGoals(year, teamName, competition, "team1", 1, 0);
		int winnergoals2 = api.totalWinnerGoals(year, teamName, competition, "team2", 1, 0);
		System.out.println("total winning goals: "+winnergoals1 + ":" + winnergoals2);
		
		//int drawMatches = api.totalDrawMatches(year, 1, 0);
		//System.out.println("total draw Matches:"+drawMatches);
		
		int totalDrawMatches = 0;
		for (int i=0; i<=10; i++) {
			int drawMatch = api.totalDrawMatchesNew(year, 1, i, 0);
			System.out.println("drawmatch:"+i+":"+drawMatch);
			totalDrawMatches = totalDrawMatches + drawMatch;
		}
		System.out.println("total draw Matches:"+totalDrawMatches);
	}

	private String getChampionshipTeam(int year, String competetion) throws UnsupportedEncodingException {
		String computedURL = String.format(url_competetions+"?year=%d&name=%s", year, URLEncoder.encode(competetion, "UTF-8"));
		System.out.println(computedURL);
		String response = this.getResponsePerPage(computedURL,1);
		System.out.println(response);
		JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");
		if (jsonArray.size() == 1) {
			JsonElement jsonElement = jsonArray.get(0);
			return jsonElement.getAsJsonObject().get("winner").getAsString();
		}
		return null;
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
		if (totalPage <= page) {
			return teamGoals;
		} else {
			return totalGoals(year, teamName, teamtype, page+1, teamGoals);
		}
	}
	
	private int totalWinnerGoals(int year, String teamName, String competition, String teamtype, int page, int teamGoals) throws UnsupportedEncodingException {
		String computedURL = String.format(url+"?year=%d&%s=%s&competition=%s", year, teamtype, teamName, URLEncoder.encode(competition, "UTF-8"));
		String response = this.getResponsePerPage(computedURL,page);
		System.out.println(response);
		JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
		int totalPage = jsonObject.get("total_pages").getAsInt();
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");
		for(JsonElement jsonElement : jsonArray) {
			int team1 = jsonElement.getAsJsonObject().get("team1goals").getAsInt();
			int team2 = jsonElement.getAsJsonObject().get("team2goals").getAsInt();
			if(teamtype.equalsIgnoreCase("team1")) {
					teamGoals = teamGoals + team1;
			} else if(teamtype.equalsIgnoreCase("team2")) {
				teamGoals = teamGoals + team2;
		}
		}
		if (totalPage <= page) {
			return teamGoals;
		} else {
			return totalWinnerGoals(year, teamName, competition, teamtype, page+1, teamGoals);
		}
	}
	
	private int totalDrawMatches(int year, int page, int teamGoals) {
		String computedURL = String.format(url+"?year=%d",year);
		System.out.println(computedURL);
		String response = this.getResponsePerPage(computedURL,page);
		System.out.println(response);
		JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
		int totalPage = jsonObject.get("total_pages").getAsInt();
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");
		for(JsonElement jsonElement : jsonArray) {
			int team1 = jsonElement.getAsJsonObject().get("team1goals").getAsInt();
			int team2 = jsonElement.getAsJsonObject().get("team2goals").getAsInt();
			if(team1 == team2) {
				teamGoals = teamGoals + 1;
			}
		}
		if (totalPage <= page) {
			return teamGoals;
		} else {
			return totalDrawMatches(year, page+1, teamGoals);
		}
	}
	
	private int totalDrawMatchesNew(int year, int page, int goal, int teamGoals) {
		String computedURL = String.format(url+"?year=%d&team1goals=%s&team2goals=%s",year,goal,goal);
		String response = this.getResponsePerPage(computedURL,page);
		System.out.println(response);
		JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
		int total = jsonObject.get("total").getAsInt();
		return total;
	}

	private String getResponsePerPage(String computedURL, int page) {
		try {
			String ur = computedURL+"&page="+page;
			URL url = new URL(ur);
			System.out.println(url);
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
