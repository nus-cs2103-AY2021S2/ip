package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import data.TagList;
import data.TaskList;
import data.Todo;
import ui.TextUi;

public class AddTodoCommandTest {

    private TaskList tasks;
    private TextUi ui;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new TextUi();
    }

    @Test
    @DisplayName("AddToDoCommand should add to TaskList")
    void testOutput() throws IOException {
        Todo todo = new Todo("a", new TagList());
        Command command = new AddTodoCommand(todo);

        command.execute(tasks, ui);

        assertEquals(todo, tasks.get(0));
    }
}
