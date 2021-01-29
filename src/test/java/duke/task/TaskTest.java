package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

	@Test
	void done() {
		Task.clearAllTask();
		Todo task = new Todo("Scratch back", " ");
		Task.done(1);
		assertEquals("X", task.getDoneStatus());
	}

	@Test
	void delete() {
		Task.clearAllTask();
		Todo task = new Todo("Scratch back", " ");
		Task.delete(1);
		assertEquals(0, Task.getTaskList().size());
	}
}