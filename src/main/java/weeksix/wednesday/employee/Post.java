package weeksix.wednesday.employee;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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
		
		Scanner sc = new Scanner(System.in);
		String firstName = null;
		String lastName = null;
		String email = null;
		String homePhone = null;
		String cellPhone = null;
		String password = null;
		String active = null;
		
		try {

			System.out.println("Welcome to the Employee Database!\n");
			System.out.println("Please enter your first name: ");
			firstName = sc.nextLine();
			System.out.println("Please enter your last name: ");
			lastName = sc.nextLine();
			System.out.println("Please enter your email address: ");
			email = sc.nextLine();
			System.out.println("Please enter your home phone number: ");
			homePhone = sc.nextLine();
			System.out.println("Please enter your cell phone number: ");
			cellPhone = sc.nextLine();
			System.out.println("Please enter your password: ");
			password = sc.nextLine();
			System.out.println("Are you active in the system? Enter 1 for yes or 0 for no: ");
			active = sc.nextLine();
			
			sc.close();
			
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

			// If we did not get a 201 (success), throw an exception
			if (connection.getResponseCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			// Read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			// Loop of buffer line by line until it returns null meaning there
			// are no more lines
			System.out.println();
			System.out.println("You have been added to the database!\n");
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
