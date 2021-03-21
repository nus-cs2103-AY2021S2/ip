package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {
    private String command;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private boolean isEnd;

    /**
     * Constructor method
     *
     * @param tasklist which contains the Task objects
     * @param ui       which handles the response
     */
    public Parser(TaskList tasklist, Ui ui, Storage storage) {
        this.command = "";
        this.taskList = tasklist;
        this.ui = ui;
        this.storage = storage;
        this.isEnd = false;
    }

    /**
     * Method to insert command
     */
    public void insertCommand(String command) {
        this.command = command;
    }

    /**
     * Method to check if Duke should stop running
     * @return a boolean value
     */
    public boolean getIsEnd() {
        return this.isEnd;
    }

    /**
     * Processes the commands entered by user
     * @return
     */
    public String process() {
        String displayed = "";
        if (command.equals("list")) {
            String list = "";
            if (taskList.numOfTasks() == 0) {
                return "There are no tasks";
            }
            return "Here are the tasks in your list:\n" + taskList.toString();
        } else if (command.contains("todo")) {
            String display = "";
            String[] save = command.split(" ");
            if (save.length == 1) {
                return "Please add a description for your ToDo task";
            }
            String description = "";
            ToDo t = new ToDo("", "");
            if (save.length > 2) {
                t.setDescription(save[1]);
                t.setTag(save[2]);
            } else {
                t.setDescription(save[1]);
                t.setTag("");
            }
            taskList.addTask(t);
            taskList.checkForDuplicate();
            storage.saveTasks(taskList);
            Integer numOfTask = taskList.numOfTasks();
            return ("Got it. I've added this task: + \n" + t.toString() + "\n" + "Now you have "
                    + numOfTask.toString() + " tasks in the list.");
        } else if (command.contains("deadline")) {
            String[] input = command.split("/by ");
            int length = input.length;
            if (length == 1) {
                return "Please add a description or date for your Deadline task";
            }
            input[0] = input[0].replaceAll("deadline", "");
            LocalDate date = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Deadline d = new Deadline(input[0], date);
            taskList.addTask(d);
            taskList.checkForDuplicate();
            storage.saveTasks(taskList);
            Integer numOfTask = taskList.numOfTasks();
            return ("Got it. I've added this task: " + "\n" + d.toString() + "\n"
                    + "Now you have " + numOfTask.toString() + " tasks in the list.");
        } else if (command.contains("event")) {
            String[] info = command.split("/at");
            int length = info.length;
            if (length == 1) {
                return "Please add a description or date for your Deadline task";
            }
            info[0] = info[0].replaceAll("event", "");
            Event e = new Event(info[0], info[1]);
            taskList.addTask(e);
            taskList.checkForDuplicate();
            storage.saveTasks(taskList);
            Integer numOfTask = taskList.numOfTasks();
            return ("Got it. I've added this task: " + "\n" + e.toString() + "\n"
                    + "Now you have " + numOfTask.toString() + " tasks in the list.");
        } else if (command.contains("done")) {
            String[] strArray = command.split(" ");
            int value = Integer.parseInt(strArray[1]);
            if (value > taskList.numOfTasks()) {
                return "Invalid Index";
            }
            Task complete = taskList.getTask(value - 1);
            complete.markAsDone();
            storage.saveTasks(taskList);
            return (" Nice! I've marked this task as done: " + "\n" + complete.toString());
        } else if (command.contains("delete")) {
            String[] arr = command.split(" ");
            int value = Integer.parseInt(arr[1]);
            if (value > taskList.numOfTasks()) {
                return "Invalid Index";
            }
            Task remove = taskList.getTask(value - 1);
            taskList.deleteTask(value - 1);
            storage.saveTasks(taskList);
            Integer numOfTask = taskList.numOfTasks();
            return ("Noted. I've removed this task: " + "\n" + remove.toString() + "Now you have "
                    + numOfTask.toString() + " tasks in the list.");
        } else if (command.contains("find")) {
            String outcome = "";
            String[] find = command.split(" ");
            String keyword = find[1];
            ArrayList<Task> output = new ArrayList<>();
            for (int i = 0; i < taskList.numOfTasks(); i++) {
                Task check = taskList.getTask(i);
                if (check.getDescription().contains(keyword)) {
                    output.add(check);
                }
            } if (output.isEmpty()) {
                return ("Nothing in the list matches your keyword");
            } else {
                for (int i = 1; i < output.size() + 1; i++) {
                    if (i == output.size()) {
                        outcome += i + ". " + output.get(i - 1);
                    } else {
                        outcome += i + ". " + output.get(i - 1) + "\n";
                    }
                }
            }
            return ("Here are the matching tasks in your list:" + "\n"
                    + outcome);
        } else if (command.equals("bye")) {
            isEnd = true;
            return "Thanks for using Duke.Hope to see you again!";
        } else {
            return "OOPS, not sure what you have entered.";
        }
    }
}
