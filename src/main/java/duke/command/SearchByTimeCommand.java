package duke.command;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


public class SearchByTimeCommand extends Command{

    public SearchByTimeCommand(String userMessage){
        super(userMessage);
    }


    public void execute(TaskList taskList, Ui ui) throws DukeException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] info;
        LocalDateTime time;

        // prevent input mistakes.
        try{
            info = userMessage.split(" ",3);
            time = LocalDateTime.parse(info[2],df);
        } catch (Exception e){
            throw new DukeException("The search input format is wrong, the format should be: \n" +
                    "search time yyyy-MM-dd HH:mm!");
        }

        LinkedList<Task> tasks = taskList.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the search results: \n");
        int numOfTasksFound = 0;

        // Search by loop
        for (Task single : tasks) {
            if (single instanceof ToDo){
                continue;
            } else if (single instanceof Event){
                LocalDateTime eventTime = ((Event) single).getAt();
                if (eventTime.isEqual(time)){
                    builder.append("[" + single.getStatusIcon() + "]" + single.toString() + "\n");
                    numOfTasksFound++;
                }
            } else if (single instanceof Deadline){
                LocalDateTime deadlineTime = ((Deadline) single).getBy();
                if (deadlineTime.isEqual(time)){
                    builder.append("[" + single.getStatusIcon() + "]" + single.toString() + "\n");
                    numOfTasksFound++;
                }
            }
        }
        if (numOfTasksFound == 0){
            throw new DukeException("OOPS! There is no task that matches the time.");
        }

        String botMessage = builder.toString();
        ui.display(botMessage);

    }
}
