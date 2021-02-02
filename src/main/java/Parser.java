import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {
    /**
     * Parses the user command and instructs the taskList, ui or sc
     * to carry out different functions according to the command argument.
     *
     * @param command user input
     * @param sc Scanner object.
     * @param ui Ui object.
     * @param taskList TaskList object.
     * @param storage Storage object.
     * @throws NoSuchElementException if additional user input required is not provided.
     * @throws UnknownCommandException if command is not supported by Maya.
     * @throws IOException if null is supplied to Storage or if file is not found.
     */
    static void parse(String command, Scanner sc, Ui ui, TaskList taskList, Storage storage)
            throws NoSuchElementException, UnknownCommandException, IOException {
        switch(command) {
        case "bye":
            ui.showBye();
            break;
        case "list":
            ui.showList(taskList.getList(), false);
            break;
        case "done":
            // To get the index
            int index = sc.nextInt();
            taskList.markTaskAsDone(index);
            storage.writeToFile(taskList.getList());
            break;
        case "todo":
            String name = sc.nextLine();
            if (!name.equals("")) {
                Todo todo = new Todo(name.trim());
                taskList.addTask(todo);
                storage.appendToFile(todo);
            } else {
                throw new NoSuchElementException("☹ OOPS!!! The description of"
                        + " a todo cannot be empty.");
            }
            break;
        case "deadline":
            String desc = sc.nextLine();
            if (!desc.equals("")) {
                String[] split = desc.split("/by", 2);
                Deadline deadline = new Deadline(split[0].trim(), split[1].trim());
                taskList.addTask(deadline);
                storage.appendToFile(deadline);
            } else {
                throw new NoSuchElementException("☹ OOPS!!! The description of"
                        + " a deadline cannot be empty.");
            }
            break;
        case "event":
            String description = sc.nextLine();
            if (!description.equals("")) {
                String[] split = description.split("/at", 2);
                Event event = new Event(split[0].trim(), split[1].trim());
                taskList.addTask(event);
                storage.appendToFile(event);
            } else {
                throw new NoSuchElementException("☹ OOPS!!! The description of"
                        + " an event cannot be empty.");
            }
            break;
        case "find":
            String searchString = sc.nextLine();
            if (!searchString.equals("")) {
                ui.showList(taskList.searchTask(searchString.trim()), true);
            } else {
                throw new NoSuchElementException("    ☹ OOPS!!! The search keyword cannot be empty.");
            }
            break;
        case "delete":
            // To get the index
            int i = sc.nextInt();
            taskList.removeTask(i);
            storage.writeToFile(taskList.getList());
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}
