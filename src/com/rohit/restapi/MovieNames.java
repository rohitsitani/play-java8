package com.rohit.restapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MovieNames {
	
	private static final String URL = "https://jsonmock.hackerrank.com/api/movies/search/"; 
	
	private String getResponseGivenURL(String url, int page) {
		try {
			url = String.format(url+"&page=%s", page);
			System.out.println(url);
			URL netURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)netURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			
			if(connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
				//success
				StringBuilder builder = new StringBuilder();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					builder.append(line);
				}
				System.out.println(builder.toString());
				return builder.toString();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	private void get(String url, String subtitle, int page, List<String> titles) {
		url = String.format(url+"?Title=%s", subtitle);
		String response = this.getResponseGivenURL(url, page);
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
		int totalPages = jsonObject.get("total_pages").getAsInt();
		if (totalPages == 0) {
			//empty result
			return;
		}
		JsonArray array = jsonObject.get("data").getAsJsonArray();
		for (JsonElement element : array) {
			titles.add(element.getAsJsonObject().get("Title").getAsString());
		}
		if (totalPages > page) {
			//more pages to read
			this.get(url, subtitle, page+1, titles);
		}
		return;
	}
	
	
	public static void main(String[] args) {
		String title = "Waterworld";
		List<String> titles = new ArrayList<String>();
		MovieNames names = new MovieNames();
		names.get(URL, title, 1, titles);
		Collections.sort(titles);
		for (String str : titles)
		System.out.println(str);
		
	}

}
