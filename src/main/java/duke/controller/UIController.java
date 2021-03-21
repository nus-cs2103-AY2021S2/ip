package duke.controller;

import duke.Duke;
import duke.model.Command;
import duke.model.task.*;
import duke.view.Window;

import java.util.Optional;

public class UIController {
    private TaskList tasks;
    private Duke duke;

    public UIController(Duke inputDuke, TaskList inputTaskList) {
        this.duke = inputDuke;
        this.tasks = inputTaskList;
    }

    public void updateUIDialog(String input, Window window) {
        Parser parserForNewInput = Parser.createParser(input, tasks);
        Optional<? extends ListItem> optionalTask = duke.getListController().operateListByCommand(parserForNewInput);
        String textToUse = this.generateTextForUpdate(parserForNewInput, optionalTask);
        window.updateDialogContainer(input, textToUse);
    }

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
                return task.map(x -> "Nice! I've marked this task as done: \n" + x.toString()).
                        orElse("Error, task cannot be found");
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
     * creates a standardised string that is common for all the tasks type to be printed
     *
     * @param typeOfTask
     * @param inputList  - to get the size of the list
     * @return a string to be printed by UI
     */
    public String printPredefinedMessage(String typeOfTask, TaskList inputList) {
        return "Got it. I've added this task: \n" + typeOfTask + "\nNow you have "
                + inputList.getListItems().size() + " tasks in the list";
    }
}
