import util.Formatter;
import util.Storage;
import static util.Parser.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
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

        saveToDisk();

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

    public String markTaskDone(HashMap<String, String> argMap) throws NoSuchElementException,
            IndexOutOfBoundsException {
        int position;
        try {
            position = Integer.parseInt(argMap.get("desc")) - 1;
            taskList.get(position).markDone();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: Please incude the index of the task.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error: Please enter a number within the list.");
        }

        saveToDisk();

        return "Nice, another job well done!\n" 
            + taskList.get(position).toString();
    }

    public String deleteTask(HashMap<String, String> argMap) throws NoSuchElementException,
            IndexOutOfBoundsException {
        int position;
        Task taskToRemove;
        try {
            position = Integer.parseInt(argMap.get("desc")) - 1;
            taskToRemove = taskList.get(position);
            taskList.remove(position);
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new NoSuchElementException("Error: Please include the index of the task.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error: Please enter a number within the list.");
        }

        saveToDisk();

        return "I've removed the task:\n" 
            + taskToRemove.toString()
            + taskCountMsg();
    }

    public String listTasks() {
        return "Here is your list of tasks: \n" + Formatter.formatList(taskList
                .stream()
                .map(Task::toString)
                .collect(Collectors.toList())
        );
    }

    public boolean saveToDisk() {
        StringBuilder saveLines = new StringBuilder();
        for (Task t: taskList) {
            saveLines.append(t.toSaveFormat()).append('\n');
        }

        try {
            Storage.writeSave(saveLines.toString());
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean readFromDisk() {
        File file;
        Scanner sc;
        try {
            file = Storage.getFile();
            sc = new Scanner(file);
        } catch (IOException e) {
            return false;
        }

        while(sc.hasNextLine()) {
            String saveLine = sc.nextLine();
            String command = getCommand(saveLine);
            HashMap<String, String> argMap = getArgMap(saveLine);
            Task newTask;

            switch (command) {
                case ToDo.COMMAND_STRING:
                    newTask = ToDo.newInstance(argMap);
                    break;
                case Deadline.COMMAND_STRING:
                    newTask = Deadline.newInstance(argMap);
                    break;
                default:
                    newTask = Event.newInstance(argMap);
                    break;
            }

            taskList.add(newTask);
        }

        return true;
    }
}
