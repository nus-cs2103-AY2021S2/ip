import util.Formatter;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    private String taskCountMsg() {
        return "\nNow you have " + taskList.size() + " task(s) in your list";
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public String addTask(Task task) {
        taskList.add(task);
        return "Gotcha. I've added the task: \n    " 
                + task 
                + taskCountMsg();
    }

    public String markTaskDone(int position) throws NoSuchElementException, IndexOutOfBoundsException {
        try {
            taskList.get(position).markDone();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Please incude the index of the task.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error: Please enter a number within the list.");
        }
        return "Nice, another job well done!\n" 
            + taskList.get(position).toString();
    }

    public String markTaskDone(Optional<String> args) throws NoSuchElementException, 
            IndexOutOfBoundsException {
        int position;
        try {
            position = Integer.parseInt(args.get()) - 1;
            taskList.get(position).markDone();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Please incude the index of the task.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error: Please enter a number within the list.");
        }
        return "Nice, another job well done!\n" 
            + taskList.get(position).toString();
    }

    public String deleteTask(Optional<String> args) throws NoSuchElementException, 
            IndexOutOfBoundsException {
        int position;
        Task taskToRemove;
        try {
            position = Integer.parseInt(args.get()) - 1;
            taskToRemove = taskList.get(position);
            taskList.remove(position);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Please incude the index of the task.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error: Please enter a number within the list.");
        }
        return "I've removed the task:\n" 
            + taskToRemove.toString()
            + taskCountMsg();
    }

    public String listTasks() {
        return "Here is your list of tasks: \n" + Formatter.formatList(taskList
                .stream()
                .map(t -> t.toString())
                .collect(Collectors.toList())
        );
    }
}
