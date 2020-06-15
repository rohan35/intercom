package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import main.com.intercom.DisplayCustomers;

public class TestDisplayCustomers {

	private String getCorrectExpectedOutput() {
		return "Name Ian Kehoe UserId 4\n" + "Name Nora Dempsey UserId 5\n" + "Name Theresa Enright UserId 6\n"
				+ "Name Eoin Ahearn UserId 8\n" + "Name Richard Finnegan UserId 11\n" + "Name Olive Ahearn UserId 13\n"
				+ "Name Michael Ahearn UserId 15\n" + "Name Patricia Cahill UserId 17\n"
				+ "Name Eoin Gallagher UserId 23\n" + "Name Rose Enright UserId 24\n"
				+ "Name Stephen McArdle UserId 26\n" + "Name Oliver Ahearn UserId 29\n"
				+ "Name Nick Enright UserId 30\n" + "Name Alan Behan UserId 31\n" + "Name Lisa Ahearn UserId 39\n";
	}

	/**
	 * processCustomers() method of class {DisplayCustomers} is called to get
	 * successfull result by matching console output
	 */
	@Test
	public void a_succesTestProcessCustomers() {
		DisplayCustomers displayCustomers = new DisplayCustomers();
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		displayCustomers.processCustomers();
		String actualOutput = outContent.toString().replaceAll("\\r|\\n", "").replaceAll(" ", "");
		assertEquals(getCorrectExpectedOutput().replaceAll("\\r|\\n", "").replaceAll(" ", ""), actualOutput);
	}

	/**
	 * calculateDistance method of class {DisplayCustomers} is called to get Success
	 * result by matching returned output
	 */
	@Test
	public void b_SuccessTestCalculateDistance() {
		DisplayCustomers displayCustomers = new DisplayCustomers();
		double dist = displayCustomers.calculateDistance("52.986375","-6.043701");
		assertEquals(41.768725500836176, dist, 0);
	}

	/**
	 * calculateDistance method of class {DisplayCustomers} is called to get Failure
	 * result by matching returned output
	 */
	@Test
	public void c_FailureTestCalculateDistance() {
		DisplayCustomers displayCustomers = new DisplayCustomers();
		double dist = 0 ;
		try {
			dist = displayCustomers.calculateDistance(null,null);
		}catch (NullPointerException exception) {
			assertEquals(-1,dist,0);
		
		} catch (NumberFormatException exception) {
			assertEquals(-1,dist,0);
		}
		
		
	}
}
