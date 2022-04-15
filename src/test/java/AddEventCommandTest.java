
import duke.command.AddEventCommand;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class AddEventCommandTest {

    @org.junit.jupiter.api.Test
    void executeTest1() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        AddEventCommand c1 = new AddEventCommand("event go to school /at 2020-01-01 19:00");
        try{
            c1.execute(taskList);
        }
        catch (DukeException e){
            System.out.println(e.getMessage());
        }

        LinkedList<Task> outputList = taskList.getTasks();
        Task output = outputList.getFirst();
        assertEquals("[E]go to school (at: Jan 01 2020 19:00)",output.toString());
    }

    @org.junit.jupiter.api.Test
    void executeTest2() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        AddEventCommand c2 = new AddEventCommand("event go to school /at 2020-01-1 19:02");

        try{
            c2.execute(taskList);
        }
        catch (DukeException e){
            assertEquals("OOPS! The input format is wrong! Should be YYYY-MM-DD HH:MM",e.getMessage());
            ui.display(e.getMessage());
        }

    }
}