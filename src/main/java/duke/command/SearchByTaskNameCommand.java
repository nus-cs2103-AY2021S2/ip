package duke.command;
import duke.exception.DukeException;
import duke.task.*;
import duke.ui.Ui;

import java.util.LinkedList;


public class SearchByTaskNameCommand extends Command{

    public SearchByTaskNameCommand(String userMessage){
        super(userMessage);
    }


    public void execute(TaskList taskList, Ui ui) throws DukeException {
        String[] info;
        String name;

        // prevent input mistakes.
        try{
            info = userMessage.split(" ",2);
            name = info[1];
        } catch (Exception e){
            throw new DukeException("The search input format is wrong, the format should be: \n" +
                    "search name <The task name>");
        }

        LinkedList<Task> tasks = taskList.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the search results: \n");
        int numOfTasksFound = 0;

        // Search by loop
        for (Task single : tasks) {
            String singleName = single.getTaskName();
            if (singleName.contains(name)){
                builder.append("[" + single.getStatusIcon() + "]" + single.toString() + "\n");
                numOfTasksFound++;
            }
        }
        if (numOfTasksFound == 0){
            throw new DukeException("OOPS! There is no task that matches the name.");
        }

        String botMessage = builder.toString();
        ui.display(botMessage);

    }
}
