package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.datahandler.DukeTaskHandler;
import duke.datahandler.Parser;
import duke.datahandler.Storage;
import duke.datahandler.TaskList;
import duke.datahandler.Ui;
import duke.enums.Command;
import duke.exception.TaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import javafx.scene.image.Image;

/**
 * A chat bot that accepts certain commands and keeps track of them in a
 * seperate file. The commands are similar to a calendar where the user would
 * like to keep track of todo events and deadlines that are upcoming. Tasks can
 * be marked as done or pending, time can be specified for time sensitive
 * events.
 */
public class Duke {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private TaskList taskList;

    /**
     * Construct duke for input
     */
    public Duke() {
        taskList = new TaskList();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        Command taskType = parser.getCommand();
        String output = "";

        try {
            switch (taskType) {
                case LIST:
                    output = Ui.execute(Command.LIST, taskList, null);
                    break;
                case DELETE:
                    Task deletedTask = taskList.get(parser.getTaskIndex() - 1);
                    taskList.delete(parser.getTaskIndex());
                    output = Ui.execute(Command.DELETE, taskList, deletedTask.getStatus());
                    break;
                case DONE:
                    Task doneTask = taskList.get(parser.getTaskIndex() - 1);
                    doneTask.setDone(true);
                    output = Ui.execute(Command.DONE, taskList, doneTask.getStatus());
                    break;
                case DEADLINE:
                case EVENT:
                case TODO:
                    DukeTaskHandler dukeTaskHandler = new DukeTaskHandler(parser, taskType);
                    Task task = dukeTaskHandler.getNewTask();
                    taskList.add(task);
                    output = Ui.execute(Command.TODO, taskList, task.getStatus());
                    break;
                case FIND:
                    output = Ui.execute(Command.FIND, taskList, parser.getDescription());
                    break;
                case NONE:
                    throw new TaskException("    OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                case BYE:
                    output = Ui.execute(Command.BYE, taskList, null);
                    break;

                default:
                    break;

            }

        } catch (TaskException e) {
            return (e.getMessage());
        } catch (IllegalArgumentException e) {
            return ("    OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        Storage.createAndWrite("savedTasks.txt", taskList);

        return output;

    }

    public static String greeting() {
        return "    Hey yo, I'm Travis.\n    I make you work. \n";
    }
}
