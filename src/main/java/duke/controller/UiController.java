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
    private TaskList tasks;
    private Duke duke;

    /**
     * Constructor, takes in Duke and TaskList
     * @param inputDuke
     * @param inputTaskList
     */
    public UiController(Duke inputDuke, TaskList inputTaskList) {
        this.duke = inputDuke;
        this.tasks = inputDuke.getTasks();
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
            return "Bye. Hope to see you again soon!";
        case LIST:
            String initStr = "Here are the tasks in your list:";
            for (int i = 0; i < tasks.getListItems().size(); i++) {
                initStr += "\n" + ((i + 1) + "." + tasks.getListItems().get(i));
            }
            return initStr;
        case DONE:
            return task.map(x -> "Nice! I've marked this task as done: \n" + x.toString())
                    .orElse("Error, task cannot be found");
        case EVENT:
        case DEADLINE:
        case TODO:
            String temp = task.map(ListItem::toString).get();
            return printPredefinedMessage(temp, tasks);
        case ERROR:
            return inputParser.getArgument();
        case DELETE:
            return "Noted. I've removed this task: " + task.map(ListItem::toString).get()
                    + "\nNow you have " + tasks.getListItems().size() + " tasks in the list";
        case FIND:
            String matchedStr = "Here are the tasks in your list that fulfills your requirement:";
            TaskList tempList = tasks.findMatchingItems(inputParser.getArgument());
            for (int i = 0; i < tempList.getListItems().size(); i++) {
                matchedStr += "\n" + ((i + 1) + "." + tempList.getListItems().get(i));
            }
            return matchedStr;
        case TAG:
            return "Nice! I've marked task " + inputParser.getArgument() + " with the tag: \n#"
                    + inputParser.getOptionalArgument();
        default:
            return "";
        }
    }

    /**
     * creates a standardised string that is common for similar commands to be printed
     * @param typeOfTask
     * @param inputList  - to get the size of the list
     * @return the predefined string
     */
    public String printPredefinedMessage(String typeOfTask, TaskList inputList) {
        return "Got it. I've added this task: \n" + typeOfTask + "\nNow you have "
                + inputList.getListItems().size() + " tasks in the list";
    }
}
