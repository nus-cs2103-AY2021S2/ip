package duke.controller;

import java.util.Optional;

import duke.Duke;
import duke.model.task.ListItem;
import duke.model.task.TaskList;
import duke.view.Window;

/**
 * Controller responsible for UI-related logic, controlling what content to be displayed
 * Has access to Duke to interact with other controller
 */
public class UiController {
    // list of predefined string to show users
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task: %s\nNow you have %d tasks in the list";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done: \n";
    public static final String MESSAGE_FIND = "Here are the tasks in your list that fulfills your requirement:";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_LIST_IF_EMPTY = "You currently do not have anything in the list!";
    public static final String MESSAGE_TASK_NOT_FOUND = "Error, task cannot be found";
    public static final String MESSAGE_TAG = "Nice! I've marked task %s with the tag: \n#%s";
    public static final String MESSAGE_PREDEFINED_FOR_TASKS = "Got it. I've added this task: \n%s\nNow you have %d tasks in the list";

    private TaskList tasks;
    private Duke duke;
    /**
     * Constructor, takes in Duke and TaskList
     * @param inputDuke
     * @param inputTaskList
     */
    public UiController(Duke inputDuke, TaskList inputTaskList) {
        this.duke = inputDuke;
        this.tasks = inputTaskList;
    }

    /**
     * update UI with <code>Window</code> and <code>DialogBox</code> in view
     * takes in user input and interact with different controller to decide what to show on UI
     * @param input user's input
     * @param window
     */
    public void updateUiDialog(String input, Window window) {
        Parser parserForNewInput = Parser.createParser(input, tasks);
        Optional<? extends ListItem> optionalTask = duke.getListController().operateListByCommand(parserForNewInput);
        String textToUse = this.generateTextForUpdate(parserForNewInput, optionalTask);
        window.updateDialogContainer(input, textToUse);
    }

    /**
     * Generate a String based on the <code>ListItem</code> operating on and what user input is
     * to be showed on UI
     * @param inputParser for extracting command, optionalArgument and argument
     * @param task the optional item that associates with the current command
     * @return a string to be showed to user
     */
    public String generateTextForUpdate(Parser inputParser, Optional<? extends ListItem> task) {
        switch (inputParser.getCommand()) {
        case BYE:
            return MESSAGE_BYE;
        case LIST:
            String strBuilder = MESSAGE_LIST;
            for (int i = 0; i < tasks.getListItems().size(); i++) {
                strBuilder += "\n" + ((i + 1) + "." + tasks.getListItems().get(i));
            }
            if (tasks.getListItems().size() == 0) {
                return MESSAGE_LIST_IF_EMPTY;
            } else {
                return strBuilder;
            }
        case DONE:
            return task.map(x -> MESSAGE_DONE + x.toString()).orElse(MESSAGE_TASK_NOT_FOUND);
        case EVENT:
        case DEADLINE:
        case TODO:
            String temp = task.map(ListItem::toString).get();
            return printPredefinedMessage(temp, tasks);
        case ERROR:
            return inputParser.getArgument();
        case DELETE:
            return String.format(MESSAGE_DELETE, task.map(ListItem::toString).get(), tasks.getListItems().size());
        case FIND:
            String matchedStrBuilder = MESSAGE_FIND;
            TaskList tempList = tasks.findMatchingItems(inputParser.getArgument());
            for (int i = 0; i < tempList.getListItems().size(); i++) {
                matchedStrBuilder += "\n" + ((i + 1) + "." + tempList.getListItems().get(i));
            }
            return matchedStrBuilder;
        case TAG:
            return String.format(MESSAGE_TAG, inputParser.getArgument(), inputParser.getOptionalArgument());
        default:
            return "";
        }
    }

    /**
     * creates a standardised string that is common for tasks-related commands to be printed
     * @param typeOfTask
     * @param inputList  - to get the size of the list
     * @return the predefined string
     */
    public String printPredefinedMessage(String typeOfTask, TaskList inputList) {
        return String.format(MESSAGE_PREDEFINED_FOR_TASKS, typeOfTask, inputList.getListItems().size());
    }
}
