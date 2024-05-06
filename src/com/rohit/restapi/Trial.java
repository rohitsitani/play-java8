package com.rohit.restapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Trial {
	
	private static String URL = "https://jsonmock.hackerrank.com/api/football_matches";
	
	private String getResultGivenPage (String url, int page) {
		try {
			java.net.URL ur = new java.net.URL(String.format(url+"&page=%s", page));
			HttpURLConnection connection = (HttpURLConnection) ur.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			if (connection.getResponseCode() < 300 && connection.getResponseCode() >=200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String sr = null;
				while ((sr = br.readLine()) != null) {
					sb.append(sr);
				}
				return sb.toString();
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	private int total(String url, int page, int year, String teamType, String teamName, int total) {
		url = String.format(url+"?year=%s&%s=%s", year, teamType, teamName);
		String response = this.getResultGivenPage(url, page);
		Gson gson = new Gson();
		JsonObject jo = gson.fromJson(response, JsonObject.class);
		int pages = jo.get("total_pages").getAsInt();
		JsonArray ja = jo.getAsJsonArray("data");
		for (JsonElement je : ja) {
			total = total + je.getAsJsonObject().get("team1goals").getAsInt(); 
		}
		if (page < pages) {
			return this.total(url, page+1, year, teamType, teamName, total);
		}
		return total;
		
	}
	
	
	public static void main(String[] args) {
		Trial trial = new Trial();
		System.out.println(trial.total(URL, 1, 2012, "team1", "Barcelona", 0));
		
	}

}
