import duke.command.AddEventCommand;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.command.DeleteCommand;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {
    @org.junit.jupiter.api.Test
    void executeTest1() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        AddEventCommand c1 = new AddEventCommand("event go to school /at 2020-01-01 19:00");
        DeleteCommand c2 = new DeleteCommand("delete 1");
        try{
            c1.execute(taskList);
            c2.execute(taskList);
        }
        catch (DukeException e){
            System.out.println(e.getMessage());
        }

        assertEquals(0,taskList.getNumOfTasks());
    }

    @org.junit.jupiter.api.Test
    void executeTest2() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        AddEventCommand c1 = new AddEventCommand("event go to school /at 2020-01-01 19:00");
        DeleteCommand c2 = new DeleteCommand("delete 2");
        try{
            c1.execute(taskList);
            c2.execute(taskList);
        }
        catch (DukeException e){
            ui.display(e.getMessage());
            assertEquals("OOPS!!! The event index of a delete is wrong.",e.getMessage());
        }


    }


}