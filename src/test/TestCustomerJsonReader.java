package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.com.intercom.datareader.CustomerJsonReader;
import main.com.intercom.model.Customer;

public class TestCustomerJsonReader {

	private CustomerJsonReader customerJsonReader;

	@Before
	public void setUp() throws Exception {
		customerJsonReader = new CustomerJsonReader();
	}

	@After
	public void tearDown() throws Exception {
		customerJsonReader = null;
	}

	/**
	 * readFile method is called of class CustomJsonReader to successfully read the
	 * file and return the expected list size
	 */
	@Test
	public void a_testSuccessReadFile() {
		ArrayList<Customer> customerList = new ArrayList();
		try {
			customerJsonReader.readFile("resources/customers.txt", customerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(customerList);
		assertEquals(32, customerList.size());
	}

	/**
	 * readFile method is called of class CustomJsonReader to successfully read the
	 * file and return the unexpected list size
	 */
	@Test
	public void b_testFailureReadFile() {
		ArrayList<Customer> customerList = new ArrayList();
		try {
			customerJsonReader.readFile("", customerList);
		} catch (IOException e) {
			assertEquals(0, customerList.size());
		}
	}

	/**
	 * getCustomerList method is called of class CustomJsonReader to successfully
	 * get the list of customers
	 */
	@Test
	public void c_testSuccessCustomerList() {
		try {
			assertNotNull(customerJsonReader.getCustomerList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
