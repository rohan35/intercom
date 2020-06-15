package main.com.intercom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.com.intercom.datareader.CustomerJsonReader;
import main.com.intercom.model.Customer;
import main.com.intercom.utils.Constants;

public class DisplayCustomers {

	/**
	 * entry point to execute the process of calculating the distance between 2
	 * points
	 */
	public void processCustomers() {
		// create an object for reading the file
		CustomerJsonReader reader = new CustomerJsonReader();
		// create an list for storing the customer details
		ArrayList<Customer> customerList = new ArrayList();
		// create an list for storing the range within 100 km
		ArrayList<Customer> customerWithinRangeList = new ArrayList();
		try {
			// get list
			customerList = reader.getCustomerList();
			/*
			 * loop through the list and find the distance between point from office and the
			 * customer location
			 */
			for (int i = 0; i < customerList.size() - 1; i++) {
				// calculate distance
				double distance = calculateDistance(customerList.get(i).getLatitude(),
						customerList.get(i).getLongitude());
				// check if distance is within 100 km then add them to range list
				if (distance < 100 && distance >= 0) {
					customerWithinRangeList.add(customerList.get(i));
				}
			}
			// sort the list based on user ids.
			Collections.sort(customerWithinRangeList, (c1, c2) -> c1.getUser_id() - c2.getUser_id());
			// print the list
			printCustomerList(customerWithinRangeList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// catch exception if excetion occurs
			e.printStackTrace();
		}
	}

	/**
	 * Print the list of customers within 100 kms
	 * 
	 * @param customerWithinRangeList
	 */
	private void printCustomerList(ArrayList<Customer> customerWithinRangeList) {
		for (Customer customer : customerWithinRangeList) {
			System.out.println("Name " + customer.getName() + " UserId " + customer.getUser_id());
		}
	}

	/**
	 * Calculate distance between 2 points dublin office and provided customers
	 * 
	 * @param lat
	 * @param lng
	 * @return
	 */
	public double calculateDistance(String latString, String lngString) {
		// convert string to double and return -1 if some exception occurs
		double lat, lng;
		try {
			lat = Double.parseDouble(latString);
			lng = Double.parseDouble(lngString);
		} catch (NullPointerException exception) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "Null pointer exception", exception);
			return -1;
		} catch (NumberFormatException exception) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "Number format exception", exception);
			return -1;
		}
		// convert degree to radians
		double latitude = Math.toRadians(lat);
		double longitude = Math.toRadians(lng);
		// find difference between them
		double longitudeDistnace = Constants.OFFICE_LONGITUDE - longitude;
		double latitudeDistance = Constants.OFFICE_LATITUDE - latitude;

		// Haversine formula
		double distance = Math.pow(Math.sin(latitudeDistance / 2), 2) + Math.cos(Constants.OFFICE_LATITUDE)
				* Math.cos(latitude) * Math.pow(Math.sin(longitudeDistnace / 2), 2);

		double accurateDistance = 2 * Math.asin(Math.sqrt(distance));

		// calculate the result
		return (accurateDistance * Constants.RADIUS);

	}
}
