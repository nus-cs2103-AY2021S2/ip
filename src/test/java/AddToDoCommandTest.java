
import duke.command.AddToDoCommand;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class AddToDoCommandTest {
    @org.junit.jupiter.api.Test
    void executeTest1() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        AddToDoCommand c1 = new AddToDoCommand("todo go to school");
        try{
            c1.execute(taskList);
        }
        catch (DukeException e){
            System.out.println(e.getMessage());
        }

        LinkedList<Task> outputList = taskList.getTasks();
        Task output = outputList.getFirst();
        assertEquals("[T]go to school",output.toString());
    }


}