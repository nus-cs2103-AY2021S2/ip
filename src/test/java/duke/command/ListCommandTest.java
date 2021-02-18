package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class ListCommandTest {
    @Test
    public void emptyListTest() {
        Command cmd = new ListCommand();
        try {
            cmd.execute(new Ui(), new Storage(), new TaskList());
            assertEquals("Here are the tasks in your list:\n" + "\tYour list is empty!", cmd.reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void todoListTest() {
        Command cmd = new ListCommand();
        try {
            TaskList list = new TaskList();
            list.addTask(new Todo("Homework"));
            cmd.execute(new Ui(), new Storage(), list);
            assertEquals("Here are the tasks in your list:\n" + "\t1. [T][ ] Homework", cmd.reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
