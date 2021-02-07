package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import data.Event;
import data.TagList;
import data.TaskList;
import ui.TextUi;

public class AddEventCommandTest {

    private TaskList tasks;
    private TextUi ui;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new TextUi();
    }

    @Test
    @DisplayName("AddEventCommand should add to TaskList")
    void testOutput() throws IOException {
        Event event = new Event("a", new TagList(), LocalDate.parse("2020-01-01"));
        Command command = new AddEventCommand(event);

        command.execute(tasks, ui);

        assertEquals(event, tasks.get(0));
    }
}
