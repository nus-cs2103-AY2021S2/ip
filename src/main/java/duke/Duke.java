package duke;

import java.io.IOException;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 * The types of tasks the user can add are: todo, deadline, event.
 * The user can also delete, check as done, and list tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke class.
     * Initializes Ui, Parser, Storage and TaskList objects.
     *
     * @param filePath file path to file where user wants his task list saved and loaded.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Processes user input and interacts with Ui and TaskList objects.
     */
    public String getResponse(String input) {
        String response = "";
        String[] inputArr = parser.getInputArr(input);
        String cmd = inputArr[0];

        switch (cmd) {
        case "bye":
            response += ui.exit();
            break;
        case "list":
            response += ui.listAllTasks(tasks.list);
            break;
        case "done":
            int numDone = Integer.parseInt(inputArr[1]);
            Task task = tasks.list.get(numDone - 1);
            tasks.checkAsDone(task);
            response += ui.checkAsDoneMessage(task);
            break;
        case "delete":
            int numDelete = Integer.parseInt(inputArr[1]);
            Task deletedTask = tasks.deleteTask(numDelete);
            response += ui.deleteTaskMessage(tasks.list, deletedTask);
            break;
        case "find":
            String keyword = inputArr[1];
            response += ui.findTask(keyword, tasks.list);
            break;
        case "todo":
            try {
                parser.isEmptyDescription(inputArr);
                String details = inputArr[1];
                Task todo = new Todo(details);
                tasks.addTask(todo);
                response += ui.addTaskMessage(tasks.list, todo);
            } catch (DukeException e) {
                response += "OOPS!!! The description of a todo cannot be empty.\n";
            }
            break;
        case "deadline":
            try {
                parser.isEmptyDescription(inputArr);
                String details = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                String date = input.substring(input.indexOf("/") + 4);
                Task deadline = new Deadline(details, date);
                tasks.addTask(deadline);
                response += ui.addTaskMessage(tasks.list, deadline);
            } catch (DukeException e) {
                response += "OOPS!!! The description of a deadline cannot be empty.\n";
            }
            break;
        case "event":
            try {
                parser.isEmptyDescription(inputArr);
                String details = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                String date = input.substring(input.indexOf("/") + 4);
                Task event = new Event(details, date);
                tasks.addTask(event);
                response += ui.addTaskMessage(tasks.list, event);
            } catch (DukeException e) {
                response += "OOPS!!! The description of an event cannot be empty.\n";
            }
            break;
        default:
            try {
                throw new DukeException();
            } catch (DukeException e) {
                response += "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            }
        }
        try {
            storage.saveTasks(tasks.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
