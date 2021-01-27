package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import data.Event;
import data.TaskList;
import ui.TextUi;

public class AddEventCommandTest {

    private TaskList tasks;
    private TextUi ui;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new TextUi(new ByteArrayInputStream(new byte[1024]), new ByteArrayOutputStream());
    }

    @Test
    @DisplayName("AddEventCommand should add to TaskList")
    void testOutput() throws IOException {
        Event event = new Event("a", LocalDate.parse("2020-01-01"));
        Command command = new AddEventCommand(event);

        command.execute(tasks, ui);

        assertEquals(event, tasks.get(0));
    }
}
