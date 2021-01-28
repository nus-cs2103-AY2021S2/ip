package soonwee.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
	@Test
	public void createToDo() {
		ToDo result = new ToDo("Sleep");
		assertEquals("[T][ ] Sleep", result.toString());
	}
}