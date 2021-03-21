package duke.controller;

import duke.model.Command;
import duke.model.task.*;


import java.util.Arrays;
import java.util.Optional;

public class ListController {
    private TaskList list;

    public ListController(TaskList list) {
        this.list = list;
    }

    public static boolean checkValidIndexForListOperation(Parser inputParser, TaskList list) {
        Command[] commandList = {Command.DONE, Command.DELETE, Command.TAG};
        boolean commandRequiresIndexCheck = Arrays.asList(commandList).contains(inputParser.getCommand());
        if (commandRequiresIndexCheck) {
            int index = Integer.parseInt(inputParser.getArgument());
            int listSize = list.getListItems().size();
            if (index <= listSize) {
                return true;
            }
            return false;
        }
        return true;
    }

    public Optional<? extends ListItem> operateListByCommand(Parser inputParser) {
        int index = -1;
        String argument = inputParser.getArgument();
        String optionalArgument = inputParser.getOptionalArgument();
        Command command = inputParser.getCommand();
        boolean isValidIndex = this.checkValidIndexForListOperation(inputParser, this.list);
        if (isValidIndex) {
            switch (command) {
                case DONE:
                    index = Integer.parseInt(argument);
                    list.markItemAsDone(index);
                    return Optional.of(list.getListItems().get(index - 1));
                case EVENT:
                    Event newEvent = new Event(argument, optionalArgument);
                    list.addCommandMutable(newEvent);
                    return Optional.of(newEvent);
                case DEADLINE:
                    Deadline newDeadline = new Deadline(argument, optionalArgument);
                    list.addCommandMutable(newDeadline);
                    return Optional.of(newDeadline);
                case TODO:
                    Todo newTodo = new Todo(argument);
                    list.addCommandMutable(newTodo);
                    return Optional.of(newTodo);
                case DELETE:
                    index = Integer.parseInt(argument);
                    ListItem deletedItem = list.getListItems().get(index - 1);
                    list.deleteCommandMutable(index);
                    return Optional.of(deletedItem);
                case TAG:
                    index = Integer.parseInt(argument);
                    list.updateItemTag(index, optionalArgument);
                    return Optional.of(list.getListItems().get(index - 1));
                default:
                    return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}
