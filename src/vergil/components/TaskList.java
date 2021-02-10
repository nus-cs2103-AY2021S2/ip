package vergil.components;

import vergil.types.Task;
import vergil.types.Todo;
import vergil.types.Deadline;
import vergil.types.Event;
import vergil.types.exceptions.VergilException;
import vergil.types.exceptions.VergilFileException;
import vergil.types.exceptions.VergilIndexException;

import java.io.File;
import java.io.FileNotFoundException;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList from the given save file.
     * @param save the save file containing the info needed to create the TaskList.
     * @throws VergilException if the save file is not found.
     */
    public TaskList(File save) throws VergilFileException {
        this();
        try {
            loadFromFile(save);
        } catch (VergilFileException e) {
            throw new VergilFileException(
                    "No save file was found.\n"
                    + "Creating an empty task list...done."
            );
        }
    }

    /**
     * Fills the TaskList with tasks from the given file.
     * @param f the file object containing the tasks to load.
     * @throws VergilFileException if the file is not found.
     */
    public void loadFromFile(File f) throws VergilFileException {
        try {
            Scanner saveEntries = new Scanner(f);

            while (saveEntries.hasNextLine()) {
                String[] taskDetails = saveEntries.nextLine().split("::");

                switch (taskDetails[0]) {
                case "t":
                    tasks.add(new Todo(
                            taskDetails[1],
                            Boolean.parseBoolean(taskDetails[2])));
                    break;

                case "d":
                    tasks.add(new Deadline(
                            taskDetails[1],
                            LocalDateTime.parse(taskDetails[3]),
                            Boolean.parseBoolean(taskDetails[2])));
                    break;

                case "e":
                    tasks.add(new Event(
                            taskDetails[1],
                            LocalDateTime.parse(taskDetails[3]),
                            Boolean.parseBoolean(taskDetails[2])));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new VergilFileException("Unable to find file.");
        }
    }

    /**
     * Adds a task to the list.
     * @param t the task to be added.
     */
    public void add(Task t) {
        assert t != null : "Task should not be null.";
        tasks.add(t);
    }

    public String getAsString(int listNum) throws VergilException {
        try {
            return tasks.get(listNum - 1)
                    .toString();
        } catch (IndexOutOfBoundsException e) {
            throw new VergilIndexException("I cannot find a task with the given list number.");
        }
    }

    /**
     * Marks the task with the given list number as done.
     * @param listNum the number of the task on the list.
     * @throws VergilException if the given number of the task is not found
     * within the task list.
     */
    public void complete(int listNum) throws VergilException {
        try {
            tasks.get(listNum - 1).doTask();
        } catch (IndexOutOfBoundsException e) {
            throw new VergilIndexException();
        }
    }

    /**
     * Deletes the task with the given list number from the list.
     * @param listNum the number of the task on the list.
     * @throws VergilException if the given number of the task is not found
     * within the task list.
     */
    public void delete(int listNum) throws VergilException {
        try {
            tasks.remove(listNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new VergilIndexException("Unable to find task.");
        }
    }

    public int getLength() {
        return tasks.size();
    }

    public TaskList find(String keyword) {
        TaskList subList = new TaskList();

        for (Task t:tasks) {
            if (t.getDescription().contains(keyword)) {
                subList.add(t);
            }
        }

        return subList;
    }

    /**
     * Returns the tasks in the list, but as save file entries.
     * @return a save-file-entries representation of the task list.
     */
    public String toSaveString() {
        StringBuilder str = new StringBuilder();

        for (Task t:tasks) {
            str.append(t.getSaveString()).append("\n");
        }

        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            str.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        return str.toString();
    }
}
