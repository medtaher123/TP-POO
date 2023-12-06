package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class DatabaseService {
	public static final String BASE_API_URL = "http://localhost:3000";
	public static final String USERS_API_URL = BASE_API_URL + "/users";
	public static final String BOOKS_API_URL = BASE_API_URL + "/books";

	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	public static String sendHttpRequest(String requestMethod, String apiUrl, String requestBody) {

		
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);
			
			if (requestBody != null && !requestBody.isEmpty()) {
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setDoOutput(true);
				try (OutputStream os = conn.getOutputStream()) {
					byte[] input = requestBody.getBytes("utf-8");
					os.write(input, 0, input.length);
				}
			}

			int responseCode = conn.getResponseCode();
			if (responseCode<400) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// Gson gson = new Gson();
				System.out.println("responssse: " + response);
				// return gson.fromJson(response.toString(), JsonElement.class);
				return response.toString();
			} else {
				System.out.println("HTTP "+ requestMethod +" request failed: " + responseCode + " " + conn.getResponseMessage());
			}
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String sendHttpRequest(String requestMethod, String apiUrl, Object requestBody) {
		return sendHttpRequest(requestMethod, apiUrl, (requestBody != null) ? gson.toJson(requestBody) : null);
	}

	public static String sendHttpRequest(String requestMethod, String apiUrl) {
		return sendHttpRequest(requestMethod,apiUrl,(String) null);
	}

}