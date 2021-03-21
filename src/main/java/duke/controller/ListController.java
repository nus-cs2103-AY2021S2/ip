package duke.controller;

import java.util.Arrays;
import java.util.Optional;

import duke.model.Command;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.ListItem;
import duke.model.task.TaskList;
import duke.model.task.Todo;

public class ListController {
    private TaskList tasks;

    public ListController(TaskList list) {
        this.tasks = list;
    }

    public static boolean checkValidIndexForListOperation(Command command, String argument, TaskList list) {
        Command[] commandList = {Command.DONE, Command.DELETE, Command.TAG};
        boolean commandRequiresIndexCheck = Arrays.asList(commandList).contains(command);
        if (commandRequiresIndexCheck) {
            int index = Integer.parseInt(argument);
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
}
