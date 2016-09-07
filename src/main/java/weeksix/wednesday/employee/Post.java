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

public class Post {

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

			// String value for firstName - required
			String firstName = "Heather";

			// String value for lastName - required
			String lastName = "Mills";
			
			// String value for email - required
			String email = "hmills365@gmail.com";
			
			// String value for homePhone - formatted as ###-###-#### || ###.###.#### || ##########, not required
			String homePhone = "123-456-7890";

			// String value for homePhone - formatted as ###-###-#### || ###.###.#### || ##########, not required
			String cellPhone = "987-654-3210";
			
			// String value for password - required, must contain 1 uppercase, 1 lowercase, 1 number, and be a max length of 8 characters
			String password = "Passw0rd";
			
			// String value for if employee is active on webserver - required, can only be a 1 or 0
			String active = "1";
									
			// Creates the url parameters as a string encoding them with the
			// defined charset
			String queryString = String.format("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s", 
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset));

			// Creates a new URL out of the endpoint and queryString
			
			URL addEmployee = new URL(endpoint + "?" + queryString);
			
			HttpURLConnection connection = (HttpURLConnection) addEmployee.openConnection();
			connection.setRequestMethod("POST");

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
