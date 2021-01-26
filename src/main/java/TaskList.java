import util.Formatter;
import util.Saver;

import java.io.File;
import java.io.FileNotFoundException;
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
            Saver.writeSave(saveLines.toString());
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean readFromDisk() {
        File file;
        Scanner sc;
        try {
            file = Saver.getFile();
            sc = new Scanner(file);
        } catch (IOException e) {
            return false;
        }

        while(sc.hasNextLine()) {
            String saveLine = sc.nextLine();
            String[] argArr = saveLine.split("\\|");
            Task newTask;

            switch (argArr[0].charAt(0)) {
                case ToDo.TYPE_SYMBOL:
                    newTask = new ToDo(argArr[2]);
                    break;
                case Deadline.TYPE_SYMBOL:
                    newTask = new Deadline(argArr[2], argArr[3]);
                    break;
                default:
                    newTask = new Event(argArr[2], argArr[3]);
                    break;
            }

            if (argArr[1].equals("1")) {
                newTask.markDone();
            }

            taskList.add(newTask);
        }

        return true;
    }
}
