package duke.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateAndTimeTest {

	@Test
	void converterTest() {
		String date = "2019-10-22";
		String actualOutput = DateAndTime.converter(date);
		String expectedOutput = "Oct 22 2019";
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	void converterTest_WrongDateFormat() {
		String date = "2019";
		String actualOutput = DateAndTime.converter(date);
		String expectedOutput = "!!!Err, wrong date format.. (yyyy-mm-dd)!!!";
		assertEquals(expectedOutput, actualOutput);
	}

}
