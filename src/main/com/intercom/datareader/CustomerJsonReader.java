package main.com.intercom.datareader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import main.com.intercom.model.Customer;

public class CustomerJsonReader {

	/**
	 * get the list of customers by reading the text json file provided
	 * customers.txt
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Customer> getCustomerList() throws IOException {
		ArrayList<Customer> customerList = new ArrayList();
		readFile("resources/customers.txt", customerList);
		return customerList;
	}

	/**
	 * Read the file and add them into list provided
	 * 
	 * @param fileName
	 * @param customerList
	 * @throws IOException
	 */
	public void readFile(String fileName, ArrayList<Customer> customerList) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		try {

			while (line != null) {
				line = br.readLine();
				Gson gson = new Gson();
				Customer customer = gson.fromJson(line, Customer.class);
				// add this to list of customer objects
				customerList.add(customer);

			}
		} finally {
			br.close();
		}
	}
}
