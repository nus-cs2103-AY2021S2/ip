package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import data.Deadline;
import data.TaskList;
import ui.TextUi;

public class AddDeadlineCommandTest {

    private TaskList tasks;
    private TextUi ui;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new TextUi(new ByteArrayInputStream(new byte[1024]), new ByteArrayOutputStream());
    }

    @Test
    @DisplayName("AddDeadlineCommand should add to TaskList")
    void testOutput() throws IOException {
        Deadline deadline = new Deadline("a", LocalDate.parse("2020-01-01"));
        Command command = new AddDeadlineCommand(deadline);

        command.execute(tasks, ui);

        assertEquals(deadline, tasks.get(0));
    }
}
