import alice.AliceException;
import alice.task.Task;
import alice.task.TaskBuilder;
import alice.task.TaskDeadline;
import alice.task.TaskEvent;
import alice.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import alice.task.TaskTodo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

	static LocalDate testDate1 = LocalDate.of(2020, 1, 30);

	static List<Task> tl1 = new ArrayList<>();
	static List<Task> tl2 = new ArrayList<>();

	static {
		tl1.add(new TaskDeadline("deadline a", false, testDate1));
		tl1.add(new TaskEvent("event a", false, testDate1));
		tl1.add(new TaskTodo("todo a", false));
		tl2 = tl1.stream().map(Task::clone).collect(Collectors.toList());
	}

	@Test
	public void compareTaskTodo_sameTask_equal() {
		assertEquals(new TaskTodo("a", false), new TaskTodo("a", false));
	}

	@Test
	public void compareTaskDeadline_sameTask_equal() {
		assertEquals(
				new TaskDeadline("a", false, testDate1),
				new TaskDeadline("a", false, testDate1));
	}

	@Test
	public void compareTaskDeadline_differentTask_notEqual() {
		assertNotEquals(
				new TaskDeadline("a", true, testDate1),
				new TaskDeadline("a", false, testDate1));
	}

	@Test
	public void compareTaskDeadline_changedTask_equal() {
		assertEquals(
				new TaskDeadline("a", true, testDate1),
				new TaskDeadline("a", false, testDate1).setDone(true));
	}

	@Test
	void compareTaskList_sameTaskList_equal() {
		assertEquals(new TaskList(tl1), new TaskList(tl2));
	}

	@Test
	void compareTaskList_changedTaskList_notEqual() {
		List<Task> tl3 = tl1.stream().map(Task::clone).collect(Collectors.toList());
		tl3.set(0, tl3.get(0).setDone(true));
		assertNotEquals(new TaskList(tl1), new TaskList(tl3));
	}

	@Test
	void buildTask_invalidArgument_exceptionThrown() {
		assertThrows(IllegalArgumentException.class, () -> TaskBuilder.buildTask(new String[]{}));
	}

	@Test
	void buildTask_invalidTime_exceptionThrown() {
		Exception exception = assertThrows(
				AliceException.class,
				() -> TaskBuilder.buildTask(new String[]{"deadline", "deadline a", "abcdefg"}));
		assertTrue(exception.getMessage().contains("Invalid date supplied"));
	}

	@Test
	void buildTask_emptyArguments_exceptionThrown() {
		assertThrows(
				IllegalArgumentException.class,
				() -> TaskBuilder.buildTask(new String[]{"", "", ""}));
	}

	@Test
	void buildDeadline_invalidArgument_exceptionThrown() {
		assertThrows(
				IllegalArgumentException.class,
				() -> TaskBuilder.buildTask(new String[]{"deadline", "", ""}));
	}

	@Test
	void buildEvent_invalidArgument_exceptionThrown() {
		assertThrows(
				IllegalArgumentException.class,
				() -> TaskBuilder.buildTask(new String[]{"event", "", ""}));
	}

	@Test
	void buildTask_invalidTask_exceptionThrown() {
		assertThrows(
				IllegalStateException.class,
				() -> TaskBuilder.buildTask(new String[]{"state", "abcdefg"})
		);
	}

	@Test
	void compareBuiltTodo_sameTask_equal() {
		assertEquals(
				new TaskTodo("abcdefg", false),
				assertDoesNotThrow(() -> TaskBuilder.buildTask(new String[]{"todo", "abcdefg"})));
	}

	@Test
	void compareBuiltDeadline_sameTask_equal() {
		Task t = assertDoesNotThrow(() -> TaskBuilder.buildTask(new String[]{"deadline", "abcdefg", "2020/1/30"}));
		assertEquals(new TaskDeadline("abcdefg", testDate1), t);
		assertEquals(t.toString(), "[D][\u2718] abcdefg (by: 2020 1 30, Thu)");
	}

	@Test
	void compareBuiltEvent_sameTask_equal() {
		Task t = assertDoesNotThrow(() -> TaskBuilder.buildTask(new String[]{"event", "abcdefg", "2020/1/30"}));
		assertEquals(new TaskEvent("abcdefg", testDate1), t);
		assertEquals(t.toString(), "[E][\u2718] abcdefg (at: 2020 1 30, Thu)");
	}
}
