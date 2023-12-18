package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import helpers.ConsoleHelper;

public class ServerTestService extends DatabaseService {

	// TODO: add to doc: I run this test to check if the json server is running or
	// not. I chose to create the test endPoint with an uncommon name
	// "libraryProjectTestEndPoint" to identify my server to avoid having another
	// server running on the specified port instead.
	// I'm sure there are cleaner solutions, but this is the one I came up with!
	private static boolean pingServer() {
		try {

			URL url = new URL(TEST_API_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				System.out.println("JSON server is running.");
				return true;
			} else {
				// System.out.println("JSON server is not running. Response code: " +
				// responseCode);
			}
			connection.disconnect();
		} catch (Exception e) {
			// System.out.println("Exception occurred: " + e.getMessage());
		}
		return false;
	}

	public static void checkForServer() {
		while (!pingServer()) {
			System.out.println("Run the json server and try again.\n"
					+ "Check that the port is the same as the one in the .ini file (by default 3000)\n"
					+ "If not modify it and try again.");
			ConsoleHelper.input("enter anything to try again");
		}
	}
}
