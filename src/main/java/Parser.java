package main.java;

import java.util.List;
import java.util.ArrayList;

/*
 * Deal with making sense of users' commands.
 */
public class Parser {
    private String command;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private boolean isFinished;

    public Parser(TaskList tasklist, Ui ui, Storage storage) {
        this.command = "";
        this.taskList = tasklist;
        this.ui = ui;
        this.storage = storage;
        this.isFinished = false;
    }

    /*
     * Add a new command to be dealt with.
     *
     * @param command New command.
     */
    public void insertCommand(String command) {
        this.command = command;
    }

    /*
     * Check if Parser is done dealing with all commands.
     *
     * @return Done status.
     */
    public boolean isFinished() {
        return this.isFinished;
    }

    /*
     * Deal with the current command.
     * Pick the appropriate response.
     * Pass the response to the Ui to handle.
     * In the case of a bad command, a relevant exception is thrown.
     * The exception is caught and the relevant message is printed.
     */
    public void process() {
        try {
            if (command.equals("list")) {
                ui.replace("Here are the tasks in your list:");
                for (int i = 0; i < taskList.numOfTasks(); i++) {
                    Integer taskNum = i + 1;
                    ui.replace(taskNum.toString() + "." + taskList.getTask(i).toString());
                }
            } else if (command.contains("todo")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new IncompleteException("The description for Todo cannot be empty.");
                }
                ui.replace("Got it. I've added this task:");
                String description = "";
                for (int i = 1; i < length; i++) {
                    if (i + 1 == length) {
                        description += line[i];
                    } else {
                        description += line[i] + " ";
                    }
                }
                Todo todo = new Todo(description);
                taskList.addTask(todo);
                ui.replace(todo.toString());
                Integer numOfTasks = taskList.numOfTasks();
                ui.replace("Now you have " + numOfTasks.toString() + " tasks in the list.");
                storage.writeToFile(taskList);
            } else if (command.contains("deadline")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new IncompleteException("The description for Deadline cannot be empty.");
                }
                String description = "";
                String date = "";
                String time = "";
                boolean isDesc = true;
                boolean isDate = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/by")) {
                        isDesc = false;
                    } else if (isDesc) {
                        if (i + 1 == length) {
                            description += line[i];
                        } else {
                            description += line[i] + " ";
                        }
                    } else if (isDate) {
                        date += line[i];
                        isDate = false;
                    } else {
                        time += line[i];
                    }
                }
                if (date.equals("")) {
                    throw new IncompleteException("The date for Deadline cannot be empty.");
                } else if (time.equals("")) {
                    throw new IncompleteException("The time for Deadline cannot be empty.");
                } else {
                    ui.replace("Got it. I've added this task:");
                    Deadline deadline = new Deadline(description, date, time);
                    deadline.formatDate();
                    deadline.formatTime();
                    taskList.addTask(deadline);
                    ui.replace(deadline.toString());
                    Integer numOfTasks = taskList.numOfTasks();
                    ui.replace("Now you have " + numOfTasks.toString() + " tasks in the list.");
                    storage.writeToFile(taskList);
                }
            } else if (command.contains("event")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new IncompleteException("The description for Event cannot be empty.");
                }
                String description = "";
                String date = "";
                String time = "";
                boolean isDesc = true;
                boolean isDate = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/at")) {
                        isDesc = false;
                    } else if (isDesc) {
                        if (i + 1 == length) {
                            description += line[i];
                        } else {
                            description += line[i] + " ";
                        }
                    } else if (isDate) {
                        date += line[i];
                        isDate = false;
                    } else {
                        time += line[i];
                    }
                }
                if (date.equals("")) {
                    throw new IncompleteException("The date for Event cannot be empty.");
                } else if (time.equals("")) {
                    throw new IncompleteException("The time for Event cannot be empty.");
                } else {
                    ui.replace("Got it. I've added this task:");
                    Event event = new Event(description, date, time);
                    event.formatDate();
                    event.formatTime();
                    taskList.addTask(event);
                    ui.replace(event.toString());
                    Integer numOfTasks = taskList.numOfTasks();
                    ui.replace("Now you have " + numOfTasks.toString() + " tasks in the list.");
                    storage.writeToFile(taskList);
                }
            } else if (command.contains("done")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new IncompleteException("The line number for Done cannot be empty.");
                }
                int number = Integer.parseInt(line[1]);
                if (number > taskList.numOfTasks()) {
                    throw new DeleteException("Invalid line number.");
                }
                int index = number - 1;
                Task task = taskList.getTask(index);
                task.markAsDone();
                taskList.replaceTask(index, task);
                ui.replace("Nice! I've marked this task as done:");
                ui.replace(task.toString());
                storage.writeToFile(taskList);
            } else if (command.contains("delete")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new IncompleteException("The line number for Delete cannot be empty.");
                }
                int number = Integer.parseInt(line[1]);
                if (number > taskList.numOfTasks()) {
                    throw new DeleteException("Invalid line number.");
                }
                ui.replace("Noted. I've removed this task:");
                int index =  number - 1;
                Task deletedTask = taskList.deleteTask(index);
                ui.replace(deletedTask.toString());
                Integer numOfTasks = taskList.numOfTasks();
                ui.replace("Now you have " + numOfTasks.toString() + " tasks in the list.");
                storage.writeToFile(taskList);
            } else if (command.contains("find")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new IncompleteException("The description for Find cannot be empty.");
                }
                String word = line[1];
                List<String> matches = new ArrayList<>();
                Integer num = 1;
                boolean isFound = false;
                for (int i = 0; i < taskList.numOfTasks(); i++) {
                    Task task = taskList.getTask(i);
                    String description = task.toString();
                    if (description.contains(word)) {
                        matches.add(num.toString() + ". " + description);
                        num++;
                        isFound = true;
                    }
                }
                if (isFound) {
                    ui.replace("Here are the matching tasks in your list:");
                    for (int i = 0; i < matches.size(); i++) {
                        ui.replace(matches.get(i));
                    }
                } else {
                    throw new FindException("Your list does not contain this word.");
                }
            } else if (command.equals("bye")) {
                ui.replace("Goodbye! Hope to see you again soon!");
                this.isFinished = true;
            } else {
                throw new UnknownException("I'm sorry, but I don't know what that means.");
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
