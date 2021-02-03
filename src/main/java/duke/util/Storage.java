package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Manages persistent storage of information.
 */
public class Storage {
    private final String path;

    /**
     * Constructor for Storage.
     * @param path Path indicating where information should be stored or retrieved from.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Reads from a saved file. If a file does not yet exist, it creates a new file.
     * @return ArrayList of Tasks created from saved tasks.
     * @throws DukeException Occurs whenever a file could not be found or created.
     */
    public ArrayList<Task> readFile() throws DukeException {
        File file = new File(path);
        ArrayList<Task> taskList = new ArrayList<>();
        int lineIndex = 1;

        if (!file.exists()) {
            try {
                createFile(file);
            } catch (DukeException e) {
                throw new DukeException("Could not create a new file.");
            }
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = processLine(line, lineIndex);
                taskList.add(task);
                lineIndex++;
            }
        } catch (FileNotFoundException | DukeException e) {
            throw new DukeException(e.getMessage());
        }

        return taskList;
    }

    /**
     * Creates a file to save to.
     * @param file File to be saved to.
     * @throws DukeException Occurs when file could not be created.
     */
    private void createFile(File file) throws DukeException {
        try {
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Could not create a new file.");
        }
    }

    /**
     * Processes a line of text in the saved file.
     * @param line Line being processed.
     * @param lineIndex Index of the line in the file.
     * @return Task created from line that is read from.
     * @throws DukeException Occurs when saved file could not be read from or file format is incorrect.
     */
    private Task processLine(String line, int lineIndex) throws DukeException {
        String[] lineArr = line.split(" \\| ");

        String typeOfTask;
        String taskStatus;
        String taskDescription;
        Boolean isTaskDone;
        Task task;
        LocalDate date;

        try {
            typeOfTask = lineArr[0];
            taskStatus = lineArr[1];
            taskDescription = lineArr[2];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Failed to read from saved file!");
        }

        if (taskStatus.equals("0")) {
            isTaskDone = false;
        } else if (taskStatus.equals("1")) {
            isTaskDone = true;
        } else {
            throw new DukeException("Incorrect file format!");
        }

        switch (typeOfTask) {
        case "T":
            task = new Todo(taskDescription, isTaskDone);
            return task;
        case "D":
            try {
                String by = lineArr[3];
                date = DateFormatter.encodeDate(by);
                task = new Deadline(taskDescription, isTaskDone, date);
            } catch (IndexOutOfBoundsException | DukeException e) {
                throw new DukeException("Failed to read from saved file!");
            }
            return task;
        case "E":
            try {
                String at = lineArr[3];
                date = DateFormatter.encodeDate(at);
                task = new Event(taskDescription, isTaskDone, date);
            } catch (IndexOutOfBoundsException | DukeException e) {
                throw new DukeException("Failed to read from saved file!");
            }
            return task;
        default:
            throw new DukeException("Incorrect file format!");
        }
    }

    /**
     * Writes information in a TaskList to a file.
     * @param taskList TaskList that is to be saved.
     * @throws IOException Occurs when an IO exception has occurred while writing.
     * @throws DukeException Occurs when an DukeException has been thrown.
     */
    public void writeFile(ArrayList<Task> taskList) throws IOException, DukeException {
        FileWriter fileWriter = new FileWriter(path);
        StringBuilder stringToWrite = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            stringToWrite.append(formatTask(task));
            stringToWrite.append("\n");
        }
        fileWriter.write(stringToWrite.toString());
        fileWriter.close();
    }

    /**
     * Formats a Task in a standard manner for when writing to disk.
     * @param task Task that is to be formatted to a String.
     * @return Line that is to be saved.
     * @throws DukeException Occurs when a Task is corrupted.
     */
    private String formatTask(Task task) throws DukeException {
        String line;
        LocalDate date;
        String description = task.getDescription();
        int isDone;

        if (task.getIsDone()) {
            isDone = 1;
        } else if (task.getIsDone() == false) {
            isDone = 0;
        } else {
            throw new DukeException("Failed to get if Task isDone!");
        }

        if (task.getTaskType().equals("Todo")) {
            line = String.format("T | %d | %s", isDone, description);
        } else if (task.getTaskType().equals("Deadline")) {
            date = ((Deadline) task).getBy();
            String by = DateFormatter.decodeDateForStorage(date);
            line = String.format("D | %d | %s | %s", isDone, description, by);
        } else if (task.getTaskType().equals("Event")) {
            date = ((Event) task).getAt();
            String at = DateFormatter.decodeDateForStorage(date);
            line = String.format("E | %d | %s | %s", isDone, description, at);
        } else {
            throw new DukeException("Task type unknown, could not write to file!");
        }

        return line;
    }
}
