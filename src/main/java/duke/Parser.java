package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Deals with making sense of users' commands.
 */
public class Parser {
    private String inputCommand;
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private boolean hasTerminated;

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        inputCommand = "";
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
        hasTerminated = false;
    }

    /**
     * Inputs a new command to be processed.
     * @param command The input command.
     */
    public void addCommand(String command) {
        this.inputCommand = command;
    }

    /**
     * Checks if Parser is done dealing with all commands and has terminated.
     * @return the Parser's status.
     */
    public boolean hasTerminated() {
        return hasTerminated;
    }

    /**
     * Checks if the input command is valid, if not, respond accordingly.
     * Creates a Todo and adds it to the task list.
     * Saves any changes to the hard drive.
     * @param command The todo command followed its description, date and time.
     * @return The newly added todo task with the number of tasks in the task list.
     */
    public String processTodo(String command) {
        String description = command.replace("todo", "").strip();
        if (description.length() == 0) {
            return ui.missingDescriptionReply();
        }
        Todo todo = new Todo(description);
        if (taskList.hasTask(todo)) {
            return ui.detectDuplicateReply(todo.toString());
        }
        taskList.addTask(todo);
        storage.writeToFile(taskList);
        int numOfTasks = taskList.numOfTasks();
        return ui.addTaskReply(todo.toString(), String.valueOf(numOfTasks));
    }

    /**
     * Checks if the input command is valid, if not, respond accordingly.
     * Creates a Deadline and adds it to the task list.
     * Saves any changes to the hard drive.
     * @param command The deadline command followed its description, date and time.
     * @return The newly added deadline task with the number of tasks in the task list.
     */
    public String processDeadline(String command) {
        String input = command.replace("deadline", "").strip();
        if (input.length() == 0) {
            return ui.missingDescriptionReply();
        }
        String[] inputArr = input.split(" ");
        StringBuilder description = new StringBuilder();
        StringBuilder date = new StringBuilder();
        StringBuilder time = new StringBuilder();
        boolean isDescription = true;
        boolean isTime = false;
        for (String string : inputArr) {
            if (string.equals("/by")) {
                isDescription = false;
            } else if (isDescription) {
                description.append(string).append(" ");
            } else if (isTime) {
                time.append(string);
            } else {
                date.append(string);
                isTime = true;
            }
        }
        if (date.length() == 0) {
            return ui.missingDateReply();
        } else if (time.length() == 0) {
            return ui.missingTimeReply();
        }
        Deadline deadline = new Deadline(description.toString(), date.toString(), time.toString());
        deadline.formatDate();
        String formattedTime = deadline.formatTime(time.toString());
        deadline.setTime(formattedTime);
        if (taskList.hasTask(deadline)) {
            return ui.detectDuplicateReply(deadline.toString());
        }
        taskList.addTask(deadline);
        storage.writeToFile(taskList);
        int numOfTasks = taskList.numOfTasks();
        return ui.addTaskReply(deadline.toString(), String.valueOf(numOfTasks));
    }

    /**
     * Checks if the input command is valid, if not, respond accordingly.
     * Creates an Event and adds it to the task list.
     * Saves any changes to the hard drive.
     * @param command The event command followed its description, date and time.
     * @return The newly added event task with the number of tasks in the task list.
     */
    public String processEvent(String command) {
        String input = command.replace("event", "").strip();
        if (input.length() == 0) {
            return ui.missingDescriptionReply();
        }
        String[] inputArr = input.split(" ");
        StringBuilder description = new StringBuilder();
        StringBuilder date = new StringBuilder();
        StringBuilder time = new StringBuilder();
        boolean isDescription = true;
        boolean isTime = false;
        for (String string : inputArr) {
            if (string.equals("/at")) {
                isDescription = false;
            } else if (isDescription) {
                description.append(string).append(" ");
            } else if (isTime) {
                time.append(string);
            } else {
                date.append(string);
                isTime = true;
            }
        }
        if (date.length() == 0) {
            return ui.missingDateReply();
        } else if (time.length() == 0) {
            return ui.missingTimeReply();
        }
        Event event = new Event(description.toString(), date.toString(), time.toString());
        event.formatDate();
        event.formatStartEndTime();
        if (taskList.hasTask(event)) {
            return ui.detectDuplicateReply(event.toString());
        }
        taskList.addTask(event);
        storage.writeToFile(taskList);
        int numOfTasks = taskList.numOfTasks();
        return ui.addTaskReply(event.toString(), String.valueOf(numOfTasks));
    }

    /**
     * Checks if the input command is valid, if not, respond accordingly.
     * Marks the task at the given line number as done.
     * Saves any changes to the hard drive.
     * @param command The done command followed by the line number.
     * @return The completed task.
     */
    public String processDone(String command) {
        String input = command.replace("done", "").strip();
        if (input.length() == 0) {
            return ui.missingLineNumberReply();
        }
        int lineNumber = Integer.parseInt(input);
        if (lineNumber > taskList.numOfTasks()) {
            return ui.invalidLineReply();
        }
        int index = lineNumber - 1;
        Task completedTask = taskList.markTaskAsDone(index);
        storage.writeToFile(taskList);
        return ui.markAsDoneReply(completedTask.toString());
    }

    /**
     * Checks if the input command is valid, if not, respond accordingly.
     * Deletes the task at the given line number.
     * Saves any changes to the hard drive.
     * @param command The delete command followed by the line number.
     * @return The deleted task with the number of tasks left in the task list.
     */
    public String processDelete(String command) {
        String input = command.replace("delete", "").strip();
        if (input.length() == 0) {
            return ui.missingLineNumberReply();
        }
        int lineNumber = Integer.parseInt(input);
        if (lineNumber > taskList.numOfTasks()) {
            return ui.invalidLineReply();
        }
        int index =  lineNumber - 1;
        Task deletedTask = taskList.deleteTask(index);
        int numOfTasks = taskList.numOfTasks();
        storage.writeToFile(taskList);
        return ui.deleteTaskReply(deletedTask.toString(), String.valueOf(numOfTasks));
    }

    /**
     * Checks if the input command is valid, if not, respond accordingly.
     * Searches through the task list.
     * List out the tasks with descriptions containing the search word, if any.
     * Gives the appropriate response based on the input command.
     * @param command The find command followed by the search word.
     * @return A list of tasks containing the search word in their descriptions, if any.
     */
    public String processFind(String command) {
        String word = command.replace("find", "").strip();
        if (word.length() == 0) {
            return ui.missingSearchWordReply();
        }
        List<String> matches = new ArrayList<>();
        boolean isFound = false;
        int matchNumber = 1;
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            Task task = taskList.getTask(i);
            String taskInString = task.toString();
            if (taskInString.contains(word)) {
                matches.add(matchNumber + "." + taskInString);
                isFound = true;
                matchNumber++;
            }
        }
        if (!isFound) {
            return ui.wordNotFoundReply();
        }
        StringBuilder matchesInString = new StringBuilder();
        for (String match : matches) {
            matchesInString.append(match).append("\n");
        }
        return ui.findWordReply(matchesInString.toString());
    }

    /**
     * Processes the input command and passes it on to the relevant processor, responds accordingly.
     * @return The appropriate response based on the input command.
     */
    public String process() {
        String response;
        if (inputCommand.startsWith("list")) {
            String list = taskList.listAllTasks();
            response = ui.provideList(list);
        } else if (inputCommand.startsWith("todo")) {
            response = processTodo(inputCommand);
        } else if (inputCommand.startsWith("deadline")) {
            response = processDeadline(inputCommand);
        } else if (inputCommand.startsWith("event")) {
            response = processEvent(inputCommand);
        } else if (inputCommand.startsWith("done")) {
            response = processDone(inputCommand);
        } else if (inputCommand.startsWith("delete")) {
            response = processDelete(inputCommand);
        } else if (inputCommand.startsWith("find")) {
            response = processFind(inputCommand);
        } else if (inputCommand.startsWith("bye")) {
            hasTerminated = true;
            response = ui.bidFarewell();
        } else {
            response = ui.invalidCommandReply();
        }
        assert response.length() > 0 : "the response cannot be empty";
        return response;
    }
}
