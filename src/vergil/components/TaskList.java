package vergil.components;

import vergil.types.*;
import vergil.types.exceptions.VergilAnomalyException;
import vergil.types.exceptions.VergilException;
import vergil.types.exceptions.VergilFileException;
import vergil.types.exceptions.VergilIndexException;

import java.io.File;
import java.io.FileNotFoundException;

import java.sql.Time;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks;
    private HashMap<LocalDateTime, Task> dateTimeTaskPairs;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        dateTimeTaskPairs = new HashMap<>();
    }

    /**
     * Constructs a new TaskList from the given save file.
     * @param save the save file containing the info needed to create the TaskList.
     * @throws VergilException if the save file is not found.
     */
    public TaskList(File save) throws VergilException {
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
    public void loadFromFile(File f) throws VergilException {
        try {
            Scanner saveEntries = new Scanner(f);

            while (saveEntries.hasNextLine()) {
                String[] taskDetails = saveEntries.nextLine().split("::");

                switch (taskDetails[0]) {
                case "t":
                    add(new Todo(
                            taskDetails[1],
                            Boolean.parseBoolean(taskDetails[2])));
                    break;

                case "d":
                    add(new Deadline(
                            taskDetails[1],
                            LocalDateTime.parse(taskDetails[3]),
                            Boolean.parseBoolean(taskDetails[2])));
                    break;

                case "e":
                    add(new Event(
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
    public void add(Task t) throws VergilAnomalyException {
        if (t instanceof TimedTask) {
            checkAnomaly((TimedTask) t);
            dateTimeTaskPairs.put(((TimedTask) t).getTime(), t);
        }

        tasks.add(t);
    }

    public void checkAnomaly(TimedTask tt) throws VergilAnomalyException {
        if (dateTimeTaskPairs.containsKey(tt.getTime())) {
            throw new VergilAnomalyException(
                    "This task clashes with another task:\n"
                            + dateTimeTaskPairs.get(tt.getTime()).toString()
            );
        }
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
            Task deletedTask = tasks.remove(listNum - 1);
            if (deletedTask instanceof TimedTask) {
                dateTimeTaskPairs.remove(((TimedTask) deletedTask).getTime());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new VergilIndexException("Unable to find task.");
        }
    }

    public int getLength() {
        return tasks.size();
    }

    public TaskList find(String keyword) throws VergilAnomalyException {
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
