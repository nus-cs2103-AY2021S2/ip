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
import duke.task.Task;
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
    private String illegalArgumentString = "    Invalid command, work harder!!!\n";
    private TaskList taskList;

    /**
     * Construct duke for input
     */
    public Duke() {
        taskList = new TaskList();
    }

    /**
     * Get the users input and generates the relevant response based on the command
     * input and validity.
     * @param input input from the user in the ui
     * @return text display to the user
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        Command taskType = parser.getCommand();
        String output = "";

        try {
            switch (taskType) {
            case LIST:
                output = Ui.execute(Command.LIST, taskList, null);
                System.out.println(output);
                break;
            case DELETE:
                Task deletedTask = taskList.get(parser.getTaskIndex() - 1);
                taskList.delete(parser.getTaskIndex() - 1);
                output = Ui.execute(Command.DELETE, taskList, deletedTask.getStatus());
                break;
            case DONE:
                Task doneTask = taskList.get(parser.getTaskIndex() - 1);
                doneTask.setDone(true);
                output = Ui.execute(Command.DONE, taskList, doneTask.getStatus());
                break;
            case SNOOZE:
                Task changeTask = taskList.get(parser.getTaskIndex() - 1);
                LocalDate newDate = LocalDate.parse(parser.getDate(),
                        DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
                changeTask.changeEventTime(newDate);
                taskList.update(changeTask, parser.getTaskIndex() - 1);
                output = Ui.execute(Command.SNOOZE, taskList, changeTask.getStatus());
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
                throw new TaskException(illegalArgumentString);
            case BYE:
                output = Ui.execute(Command.BYE, taskList, null);
                break;

            default:
                break;

            }

        } catch (TaskException e) {
            return (e.getMessage());
        } catch (IllegalArgumentException e) {
            return (illegalArgumentString);
        }
        Storage.createAndWrite("savedTasks.txt", taskList);

        return output;

    }

    /**
     * sends the greeting from duke to the user
     * @return text shown to the user when they start duke
     */

    public static String greeting() {
        return "    Hey yo, I'm Travis.\n    I make you work. \n";
    }
}
