import java.io.IOException;
import java.util.NoSuchElementException;

public class Parser {

    /**
     * Parses the user command and instructs the taskList, ui or sc
     * to carry out different functions according to the command argument.
     *
     * @param command user input
     * @param ui Ui object
     * @param taskList TaskList object.
     * @param storage Storage object.
     * @throws NoSuchElementException if additional user input required is not provided.
     * @throws UnknownCommandException if command is not supported by Maya.
     * @throws IOException if null is supplied to Storage or if file is not found.
     */
    static String parse(String command, Ui ui, TaskList taskList, Storage storage)
            throws NoSuchElementException, UnknownCommandException, IOException {
        switch(parseCommand(command)) {
        case "bye":
            return ui.showBye();
        case "list":
            return ui.showList(taskList.getList(), false);
        case "done":
            // To get the index
            int index = Integer.parseInt(getDescription(command));
            Task doneTask = taskList.markTaskAsDone(index);
            storage.writeToFile(taskList.getList());
            return ui.showDone(doneTask);
        case "todo":
            String name = getDescription(command);
            if (!name.equals("")) {
                String[] split = name.split("/p", 2);
                Todo todo = new Todo(split[0].trim(), split[1].trim());
                Task newTask = taskList.addTask(todo);
                storage.appendToFile(todo);
                return ui.showAddTask(newTask, taskList.getListSize());
            } else {
                throw new NoSuchElementException("☹ OOPS!!! The description of"
                        + " a todo cannot be empty.");
            }
        case "deadline":
            String desc = getDescription(command);
            if (!desc.equals("")) {
                String[] split = desc.split("/by", 2);
                String[] split2 = split[1].split("/p", 2);
                Deadline deadline = new Deadline(split[0].trim(), split2[0].trim(), split2[1].trim());
                Task newTask = taskList.addTask(deadline);
                storage.appendToFile(deadline);
                return ui.showAddTask(newTask, taskList.getListSize());
            } else {
                throw new NoSuchElementException("☹ OOPS!!! The description of"
                        + " a deadline cannot be empty.");
            }
        case "event":
            String description = getDescription(command);
            if (!description.equals("")) {
                String[] split = description.split("/at", 2);
                String[] split2 = split[1].split("/p", 2);
                Event event = new Event(split[0].trim(), split2[0].trim(), split2[1].trim());
                Task newTask = taskList.addTask(event);
                storage.appendToFile(event);
                return ui.showAddTask(newTask, taskList.getListSize());
            } else {
                throw new NoSuchElementException("☹ OOPS!!! The description of"
                        + " an event cannot be empty.");
            }
        case "find":
            String searchString = getDescription(command);
            if (!searchString.equals("")) {
                return ui.showList(taskList.searchTask(searchString.trim()), true);
            } else {
                throw new NoSuchElementException("    ☹ OOPS!!! The search keyword cannot be empty.");
            }
        case "delete":
            // To get the index
            int i = Integer.parseInt(getDescription(command));
            Task removedTask = taskList.removeTask(i);
            storage.writeToFile(taskList.getList());
            return ui.showRemoveTask(removedTask, taskList.getListSize());
        default:
            throw new UnknownCommandException();
        }
    }

    static String parseCommand(String command) {
        return command.split(" ", 2)[0];
    }

    static String getDescription(String command) {
        return command.split(" ", 2)[1];
    }
}
