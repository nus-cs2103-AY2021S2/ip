package Task;

import Exceptions.InvalidDateException;
import Exceptions.InvalidInputException;
import Utils.Command;
import Utils.DateTime;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static Utils.Print.printWithIndentation;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void printTasks() {
        if (taskList.size() == 0) {
            printWithIndentation("You have not added any tasks.");
        } else {
            String[] tasksArr = new String[taskList.size()];

            for (int i = 0; i < taskList.size(); i++) {
                tasksArr[i] = (i + 1) + "." + taskList.get(i).toString();
            }

            printWithIndentation(tasksArr);
        }
    }

    public void addTask(Command command, String input) throws ArrayIndexOutOfBoundsException, InvalidDateException {
        String[] tokens;
        Task task;

        switch (command) {
            case TODO:
                task = new Todo(input);
                break;
            case DEADLINE:
                tokens = input.split(" /by ", 2);
                input = tokens[0];
                try {
                    task = new Deadline(input, DateTime.parseDate(tokens[1]));
                } catch (DateTimeParseException e) {
                    throw new InvalidDateException(tokens[1]);
                }
                break;
            case EVENT:
                tokens = input.split(" /at ", 2);
                input = tokens[0];
                try {
                    task = new Event(input, DateTime.parseDate(tokens[1]));
                } catch (DateTimeParseException e) {
                    throw new InvalidDateException(tokens[1]);
                }
                break;
            default:
                printWithIndentation("I do not understand.");
                return;
        }

        taskList.add(task);
        int numTasks =  taskList.size();
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";
        printWithIndentation("Got it! I've added this task:",
                "  " + task.toString(),
                "Now you have " + formattedTasksCount + " in the list.");
    }

    public void markAsDone(int idx) throws InvalidInputException {
        Task task;
        try {
            task = taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(taskList.size());
        }

        task.markAsDone();
        printWithIndentation("Good Job! I've marked this task as done!", task.toString());
    }

    public void delete(int idx) throws InvalidInputException {
        Task task;
        try {
            task = taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.size() == 0) {
                throw new InvalidInputException();
            } else {
                throw new InvalidInputException(taskList.size());
            }
        }

        taskList.remove(idx);

        int numTasks =  taskList.size();
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";
        printWithIndentation("Got it! I've removed this task:",
                "  " + task.toString(),
                "Now you have " + formattedTasksCount + " in the list.");
    }
}
