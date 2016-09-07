package weeksix.wednesday.employee;

import java.io.*;
import java.net.*;

/**
 * 
 * Sample class that makes a simple request to the employee API
 * 
 * @author Kavita Somavarapu
 *
 */

public class GetAll {

	/**
	 * The URL of the API we want to connect to
	 */
	public static String endpoint = "http://localhost:1337/employee";

	/**
	 * The character set to use when encoding URL parameters
	 */
	protected static String charset = "UTF-8";

	/**
	 * API key used for making requests to API
	 */

	public static void main(String[] args) {

		try {
			
			// Creates a new URL out of the endpoint and queryString
			
			URL allEmployees = new URL(endpoint);
			
			HttpURLConnection connection = (HttpURLConnection) allEmployees.openConnection();
			connection.setRequestMethod("GET");

			// If we did not get a 200 (success), throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			// Read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			// Loop of buffer line by line until it returns null meaning there
			// are no more lines
			while (br.readLine() != null) {
				// print out each line to the screen
				System.out.println(br.readLine());
			}

			// Close connection to API

			connection.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
