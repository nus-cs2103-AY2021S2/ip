package duke.controller;

import duke.model.Command;
import duke.model.task.*;


import java.util.Optional;

public class ListController {
    private TaskList list;

    public ListController(TaskList list) {
        this.list = list;
    }

    public Optional<? extends ListItem> operateListByCommand(Parser inputParser){
        int index = -1;
        String argument = inputParser.getArgument();
        String optionalArgument = inputParser.getOptionalArgument();
        Command command = inputParser.getCommand();
        switch (command) {
            case DONE:
                list.markItemAsDone(Integer.parseInt(argument));
                return Optional.of(list.getListItems().get(index));
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
                return Optional.of(list.getListItems().get(index));
            default:
                return Optional.empty();
        }
    }
}
