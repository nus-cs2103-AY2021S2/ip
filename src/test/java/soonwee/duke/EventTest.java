package soonwee.duke;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
	@Test
	public void createEvent_validInput_success() {
		Event eventTask = new Event("Sleep", LocalDateTime.parse("2010-01-19T23:00:00"));
		assertEquals("[E][ ] Sleep(at: Jan 19 2010 23:00)", eventTask.toString());
	}

	@Test
	public void createEvent_invalidDate_exceptionThrown() {
		assertThrows(java.time.format.DateTimeParseException.class, () ->
				new Event("wxyz", LocalDateTime.parse("1234")));
	}
}