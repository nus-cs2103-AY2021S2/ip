package duke.controller;

import java.util.Arrays;
import java.util.Optional;

import duke.Duke;
import duke.model.Command;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.ListItem;
import duke.model.task.TaskList;
import duke.model.task.Todo;

/**
 * Controller responsible for providing Duke the ability to operate on the TaskList the application is using
 * dealing with the logic associates with TaskList
 * <code>tasks</code> stores the TaskList that Duke is current using in order to perform action
 */
public class ListController {
    private Duke duke;
    private TaskList tasks;

    /**
     * standard constructor that takes in Duke's TaskList and store for later use
     * @param list TaskList from Duke
     */
    public ListController(Duke duke, TaskList list) {
        this.tasks = list;
        this.duke = duke;
    }

    /**
     * Check if the command that will perform operations on TaskList has a valid index
     *
     * @param command  take in for checking if the current command requires a valid index first
     *                 (e.g. LIST or FIND would not require)
     * @param argument the argument that user provides - if the command is one of the command that requires valid index,
     *                 then this should be an integer in a string form for Integer.parseInt
     * @param list     TaskList for checking the size to know if index is valid
     * @return a boolean
     */
    public static boolean checkValidIndexForListOperation(Command command, String argument, TaskList list) {
        Command[] commandList = {Command.DONE, Command.DELETE, Command.TAG};
        boolean commandRequiresIndexCheck = Arrays.asList(commandList).contains(command);
        if (commandRequiresIndexCheck) {
            try {
                int index = Integer.parseInt(argument);
                int listSize = list.getListItems().size();
                if (index <= listSize) {
                    return true;
                }
                return false;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }

    /**
     * Take in a Parser and operate on Duke's TaskList accordingly depending on Command
     *
     * @param inputParser - used for extracting argument, optionalArgument and command to decide what operation to be
     *                    performed on TaskList
     * @return an optional ListItem (Todo/Event/Deadline) for showing user with UIController
     */
    public Optional<? extends ListItem> operateListByCommand(Parser inputParser) {
        int index = -1;
        String argument = inputParser.getArgument();
        String optionalArgument = inputParser.getOptionalArgument();
        Command command = inputParser.getCommand();
        boolean isValidIndex = checkValidIndexForListOperation(command, argument, this.tasks);
        if (isValidIndex) {
            switch (command) {
            case DONE:
                index = Integer.parseInt(argument);
                tasks.markItemAsDone(index);
                return Optional.of(tasks.getListItems().get(index - 1));
            case EVENT:
                Event newEvent = new Event(argument, optionalArgument);
                tasks.addCommandMutable(newEvent);
                return Optional.of(newEvent);
            case DEADLINE:
                Deadline newDeadline = new Deadline(argument, optionalArgument);
                tasks.addCommandMutable(newDeadline);
                return Optional.of(newDeadline);
            case TODO:
                Todo newTodo = new Todo(argument);
                tasks.addCommandMutable(newTodo);
                return Optional.of(newTodo);
            case DELETE:
                index = Integer.parseInt(argument);
                ListItem deletedItem = tasks.getListItems().get(index - 1);
                tasks.deleteCommandMutable(index);
                return Optional.of(deletedItem);
            case TAG:
                index = Integer.parseInt(argument);
                tasks.updateItemTag(index, optionalArgument);
                return Optional.of(tasks.getListItems().get(index - 1));
            default:
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * print the current list as string for saving to local file
     * @return a concatenated string
     */
    public String printListAsStringForIO() {
        if (tasks.getListItems().size() == 0) {
            return "";
        } else {
            return tasks.toString();
        }
    }
}
