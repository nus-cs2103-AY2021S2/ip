package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

public class Storage {
    private final String fileDir;
    private final String fileName;

    /**
     * Create new Storage interface with a particular folder path and file name.
     *
     * @param directory Relative directory path to store data in
     * @param fileName Name of file to store data in
     */
    public Storage(String directory, String fileName) {
        this.fileDir = directory;
        this.fileName = fileName;
    }

    /**
     * Attempts to parse a line from a file and load a corresponding Task.
     *
     * @param line The line to be parsed
     * @return A task if successful, or null otherwise
     */
    private Task parseLineFromFile(String line) {
        Task t;
        String pattern = "([TED]),([01]),(\\d*),(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if (!m.find()) {
            return null;
        }
        String type = m.group(1);
        boolean isDone = m.group(2).equals("1");
        int taskLength = Integer.parseInt(m.group(3));
        String task = m.group(4).substring(0, taskLength);
        String leftover = m.group(4).substring(taskLength);
        try {
            if (type.equals("E") || type.equals("D")) {
                line = leftover.substring(1);
                pattern = "(\\d*),(.*)";
                r = Pattern.compile(pattern);
                m = r.matcher(line);
                if (!m.find()) {
                    return null;
                }
                int timeLength = Integer.parseInt(m.group(1));
                String timeData = m.group(2).substring(0, timeLength);

                switch (type) {
                case "E":
                    t = new Event(task, timeData);
                    break;
                case "D":
                    t = new Deadline(task, timeData);
                    break;
                default:
                    return null;
                }
            } else {
                t = new ToDos(task);
            }
        } catch (EmptyArgumentException | BadDateArgumentException e) {
            return null;
        }
        if (isDone) {
            t.setDone();
        }
        return t;
    }

    /**
     * Loads data from a fixed constant, location relative to the program location
     *
     * @return TaskList that corresponds to the loaded data
     * @throws IOException Uncontrollable IO Error
     */
    public TaskList loadTaskList() throws IOException {
        List<Task> taskList = new ArrayList<>();
        File file = getOrCreateFile();
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            Task t = parseLineFromFile(s.nextLine());
            if (t != null) {
                taskList.add(t);
            }
        }
        s.close();
        return new TaskList(taskList);
    }

    /**
     * Saves TaskList to disk.
     *
     * @param data TaskList to save to disk
     * @throws IOException Unable to create subfolder
     */
    public void saveTaskList(TaskList data) throws IOException {
        String saveText = data.toFileString();
        File f = getOrCreateFile();
        FileWriter writer = new FileWriter(f);
        writer.write(saveText);
        writer.close();
    }

    private File getOrCreateFile() throws IOException {
        Files.createDirectories(Paths.get(fileDir));
        File file = new File(fileDir, fileName);
        file.createNewFile();
        return file;
    }
}
