package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;
    private String dirPath;

    /**
     * @param filePath The file path of the file which is used to store task list.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        parseFilePath();
    }

    /**
     * Extracts directory path from the file path.
     */
    public void parseFilePath() {
        String pattern = "(.*)/(.*)$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(filePath);
        if (m.find()) {
            this.dirPath = m.group(1);
        }
    }

    /**
     * Loads task list from the file and returns the task list.
     * @return The task list which is loaded from the file.
     * @throws DukeException Exception if there is error when loading from the file.
     */
    public ArrayList<Task> load() throws DukeException {
        // create the folder called data if not found
        ArrayList<Task> tasks = new ArrayList<>();

        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }

        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                tasks.add(parseTask(sc.nextLine()));
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot load from file!");
        }
    }

    /**
     * Parses the task string to a duke.task.Task object.
     * @param str The string which represents a single task.
     * @return A duke.task.Task object which represents a task as the string.
     * @throws DukeException Exception if there is error when parsing the task string.
     */
    public Task parseTask(String str) throws DukeException {
        try {
            char typeLabel = str.charAt(1);
            boolean isDone = str.charAt(4) == 'X';
            String body = str.substring(7);
            String pattern;
            Pattern r;
            Matcher m;
            switch (typeLabel) {
                case 'T':
                    return new ToDo(body, isDone);
                case 'D':
                    pattern = "(.*) \\(by: (.*)\\)";
                    r = Pattern.compile(pattern);
                    m = r.matcher(body);
                    if (m.find()) {
                        LocalDate deadline = LocalDate.parse(m.group(2), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        return new Deadline(m.group(1), isDone, deadline);
                    }
                case 'E':
                    pattern = "(.*) \\(at: (.*)\\)";
                    r = Pattern.compile(pattern);
                    m = r.matcher(body);
                    if (m.find()) {
                        LocalDate time = LocalDate.parse(m.group(2), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        return new Event(m.group(1), isDone, time);
                    }
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            throw new DukeException("error occurs and loading from file is suspended!");
        }
    }

    /**
     * Saves the task list to the file.
     * @param tasks The TaskList object whose string representation will be saved to the file.
     * @throws DukeException Exception when there is an error when saving task list to the file.
     */
    public void save(TaskList tasks) throws DukeException {
        // make the data directory if not found
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(tasks.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Save to file error!");
        }
    }
}
