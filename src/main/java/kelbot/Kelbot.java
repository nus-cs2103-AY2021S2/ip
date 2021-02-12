package kelbot;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.application.Platform;

public class Kelbot {
    private Path path = Paths.get(".", "data", "Kelbot.txt");
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    /**
     * Initializes Kelbot.
     */
    public Kelbot() {
        ui = new UI();
        storage = new Storage(path);
        try {
            taskList = storage.load();
        } catch (KelbotException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }
    /**
     * Gets a response from the user input to be delivered to the user.
     * @param input That the user puts in.
     * @return The response to be delivered to the user.
     */
    public String getResponse(String input) {
        String response;
        try {
            Parser parser = new Parser(input);
            if (!parser.getIsValid()) {
                return "Invalid Command!";
            }
            assert parser.getIsValid();
            Command command = parser.getCommand();
            Integer taskNumber = parser.getTaskNumber();
            String keyword = parser.getKeyword();
            String taskName = parser.getTaskName();
            String tagName = parser.getTagName();
            LocalDate date = parser.getDate();
            response = processCommand(command, taskNumber, keyword, taskName, tagName, date);
            if (command == Command.BYE) {
                Platform.exit();
            }
        } catch (DateTimeParseException e) {
            response = "Date format is wrong!";
        } catch (ArrayIndexOutOfBoundsException e) {
            response = "Incomplete command!";
        }
        storage.save(taskList.getTaskList());
        return response;
    }
    /**
     * Gets Task List.
     * @return Task List.
     */
    public TaskList getTaskList() {
        return taskList;
    }
    
    /**
     * Process the command given by the user
     * @param command The command the user inputs
     * @param taskNumber The task number the user inputs, if needed
     * @param keyword The keyword the user inputs, if needed
     * @param taskName The task name the user inputs, if needed
     * @param tagName The tag name the user inputs, if needed
     * @param date The date the user inputs, if needed
     * @return The String to be printed
     */
    public String processCommand(Command command, Integer taskNumber, String keyword, String taskName, String tagName, LocalDate date) {
        if (command == Command.BYE) {
            return ui.sayGoodbye();
        } else if (command == Command.LIST) {
            return ui.printList(taskList);
        } else if (command == Command.TAG) {
            return tag(taskList, tagName, taskNumber);
        } else if (command == Command.DONE || command == Command.DELETE) {
            return doneOrDelete(command, taskList, taskNumber);
        } else if (command == Command.FIND) {
            return find(taskList, keyword);
        } else if (command == Command.SNOOZE) {
            return snooze(taskList, taskNumber, date);
        } else {
            return add(command, taskList, taskName, date);
        }
    }
    /**
     * Tags a task.
     * @param taskList The task list to be acted on.
     * @param tagName The tag name to tag on the task.
     * @param taskNumber The index of the task in the task list to be tagged.
     * @return The string to be printed.
     */
    public String tag(TaskList taskList, String tagName, Integer taskNumber) {
        String response;
        try {
            Task task = taskList.tag(taskNumber, tagName);
            response = ui.printTag(task);
        } catch (IndexOutOfBoundsException e) {
            response = "The list is not that long!";
        }
        return response;
    }
    /**
     * Completes or deletes a task, depending on the command.
     * @param command Either a DONE or DELETE command.
     * @param taskList The task list to be acted on.
     * @param taskNumber The index of the task in the task list to be completed or deleted.
     * @return The string to be printed.
     */
    public String doneOrDelete(Command command, TaskList taskList, Integer taskNumber) {
        String response;
        try {
            assert !taskList.getTaskList().isEmpty();
            if (command == Command.DONE) {
                Task task = taskList.complete(taskNumber);
                response = ui.printDone(task);
            } else {
                Task task = taskList.delete(taskNumber);
                response = ui.printDelete(task);
            }
        } catch (IndexOutOfBoundsException e) {
            response = "The list is not that long!";
        }
        return response;
    }
    /**
     * Finds a keyword from the task list.
     * @param taskList The task list where the keyword will be searched from.
     * @param keyword The keyword to search for in the task list.
     * @return The tasks that have the keywords.
     */
    public String find(TaskList taskList, String keyword) {
        String response;
        try {
            if (keyword.equals("")) {
                throw new KelbotException("Keyword cannot be empty!");
            }
            assert !taskList.getTaskList().isEmpty();
            TaskList taskListToPrint = new TaskList(taskList.search(keyword));
            if (taskListToPrint.toString().equals("")) {
                response = "No tasks match your search!";
            } else {
                response = ui.printRelevantTasks(taskListToPrint);
            }
        } catch (KelbotException e) {
            response = e.getMessage();
        }
        return response;
    }
    /**
     * Adds a task to the task list.
     * @param command The command that indicates which task to add.
     * @param taskList The task list to be added to.
     * @param taskName The name of the task that will be added.
     * @param date The date of the task that will be added, if applicable.
     * @return The response to be printed to the user.
     */
    public String add(Command command, TaskList taskList, String taskName, LocalDate date) {
        String response;
        try {
            if (taskName == "") {
                throw new KelbotException("Task name cannot be empty!");
            } else if (command == Command.TODO) {
                TodoTask newTodoTask = new TodoTask(taskName);
                taskList.add(newTodoTask);
                response = ui.printAdd(newTodoTask, taskList.getSize());
            } else if (command == Command.TRIVIA) {
                TriviaTask newTriviaTask = new TriviaTask(taskName);
                taskList.add(newTriviaTask);
                response = ui.printAdd(newTriviaTask, taskList.getSize());
            } else if (date == null) {
                throw new KelbotException("Date cannot be empty!");
            } else if (command == Command.DEADLINE) {
                DeadlineTask newDeadlineTask = new DeadlineTask(taskName, date);
                taskList.add(newDeadlineTask);
                response = ui.printAdd(newDeadlineTask, taskList.getSize());
            } else {
                EventTask newEventTask = new EventTask(taskName, date);
                taskList.add(newEventTask);
                response = ui.printAdd(newEventTask, taskList.getSize());
            }
        } catch (KelbotException e) {
            response = e.getMessage();
        }
        return response;
    }
    /**
     * Snooze a task in the task list.
     * @param taskList The task list that contains the task to be snoozed.
     * @param taskNumber The index of the task in the task list to be snoozed.
     * @param date The new date of the task.
     * @return The response to be printed to the user.
     */
    public String snooze(TaskList taskList, Integer taskNumber, LocalDate date) {
        String response;
        try {
            if (date == null) {
                throw new KelbotException("Date cannot be empty!");
            }
            Task taskToSnooze = taskList.snooze(taskNumber, date);
            response = ui.printSnooze(taskToSnooze);
        } catch (KelbotException e) {
            response = e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            response = "The list is not that long!";
        }
        return response;
    }
}
