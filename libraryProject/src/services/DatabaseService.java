package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import helpers.IniFileReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.function.Predicate;

public abstract class DatabaseService {
	//TODO: add to doc(done): services are the classes that communicate with the database json-server
	//TODO: add to doc: chose to define the API_URLs in the DatabaseService parent class instead of the subclasses (each subclass with it's own URL) for easier manipulation (constants are grouped together)  
	public static final String BASE_API_URL =  IniFileReader.getBaseApiUrl(); //I don't think this is the best way to initialize this;
	public static final String TEST_API_URL = BASE_API_URL + "/libraryProjectTestEndPoint";
	public static final String USERS_API_URL = BASE_API_URL + "/users";
	public static final String BOOKS_API_URL = BASE_API_URL + "/books";
	public static final String BOOK_COPIES_API_URL = BASE_API_URL + "/bookCopies";
	public static final String BOOKINGS_API_URL = BASE_API_URL + "/bookings";
	public static final String EVENTS_API_URL = BASE_API_URL + "/events";
	public static final String SETTINGS_API_URL = BASE_API_URL + "/settings";
	
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
				//System.out.println("responssse: " + response);
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
	
	protected static <T> T[] filter(T[] elements, Predicate<? super T> predicate) {
	    Object[] filtered = Arrays.stream(elements)
	                              .filter(predicate)
	                              .toArray();

	    @SuppressWarnings("unchecked")
	    T[] filteredElements = (T[]) Arrays.copyOf(filtered, filtered.length, elements.getClass());
	    return filteredElements;
	}


}
