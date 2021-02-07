package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * Represents the execution of Add Command
 */
public class AddCmd extends Command {
    private final String cmdArgs;
    private final TaskType taskType;

    public AddCmd(String cmdArgs, TaskType taskType) {
        this.cmdArgs = cmdArgs;
        this.taskType = taskType;
    }

    private void validateNotEmpty(String str, String msg) {
        if (str.equals("")) {
            throw new DukeException(msg);
        }
    }

    private String[] trimStrArr(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = strArr[i].trim();
        }

        return strArr;
    }

    /**
     * Returns the response of the bot after execution of command
     *
     * @param lst TaskList
     * @return response
     */
    @Override
    public String execute(TaskList lst) {
        Task task;
        String[] words;

        validateNotEmpty(cmdArgs, "OOPS!!! The description of a task cannot be empty");

        switch (taskType) {
        case TODO:
            task = new Todo(cmdArgs);
            break;
        case EVENT:
            words = cmdArgs.split("/at");
            trimStrArr(words);
            task = new Event(words[0], words[1]);
            break;
        case DEADLINE:
            words = cmdArgs.split("/by");
            trimStrArr(words);
            task = new Deadline(words[0], words[1]);
            break;
        default:
            throw new DukeException(String.format("Processing of %s taskType is not implemented in AddCmd", taskType));
        }

        lst.add(task);

        String resp = "Got it. I've added this task:\n";
        resp += String.format("\t%s\n", task.toString());
        resp += String.format("Now you have %d tasks in the list\n", lst.size());
        return resp;
    }
}
