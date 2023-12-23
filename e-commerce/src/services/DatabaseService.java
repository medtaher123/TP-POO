package services;

import adapters.GsonInstance;
import helpers.IniFileReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.function.Predicate;

//Services are the classes that communicate with the database json-server
public abstract class DatabaseService {	
	//I chose to define the API_URLs in the DatabaseService parent class instead of the subclasses (each subclass with it's own URL) for easier manipulation (constants are grouped together)
	public static final String BASE_API_URL =  IniFileReader.getBaseApiUrl(); //I don't think this is the best way to initialize this;
	public static final String TEST_API_URL = BASE_API_URL + "/ECommerceProjectTestEndPoint";
	public static final String USERS_API_URL = BASE_API_URL + "/users";
	protected static final String PRODUCTS_API_URL = BASE_API_URL + "/products";
	public static final String INVENTORY_API_URL = BASE_API_URL + "/inventory";
	public static final String SETTINGS_API_URL = BASE_API_URL + "/settings";


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
		return sendHttpRequest(requestMethod, apiUrl, (requestBody != null) ? GsonInstance.gson.toJson(requestBody) : null);
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

	/*//TODO: to test this
	protected static <T> T[] filter(T[] elements, Predicate<? super T> predicate) {
		List<T> filteredList = new ArrayList<>();

		for (T element : elements) {
			if (predicate.test(element)) {
				filteredList.add(element);
			}
		}

		@SuppressWarnings("unchecked")
		T[] filteredElements = (T[]) Array.newInstance(elements.getClass().getComponentType(), filteredList.size());
		return filteredList.toArray(filteredElements);
	}*/

}
