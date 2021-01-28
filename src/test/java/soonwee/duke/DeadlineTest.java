package soonwee.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
	@Test
	public void createDeadline() {
		Deadline deadLineTask = new Deadline("Buy coffee", LocalDateTime.parse("2010-01-19T15:00:00"));
		assertEquals("[D][ ] Buy coffee(by: Jan 19 2010 15:00)", deadLineTask.toString());
	}
}
