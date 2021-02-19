package helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
/**
 * Handles storage
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the saved tasks (wip)
     * @return the list of tasks
     * @throws DukeException
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            File myFile = new File("duke.txt");
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                parseLine(line, taskList);
            }

        } catch (Exception e) {
            throw new DukeException("No such file");
        }
        return taskList;
    }

    /**
     * Parse each line in saved file and update taskList
     * @param line String in the file
     * @param taskList the task list
     */
    private void parseLine(String line, List<Task> taskList) {
        Task task = null;
        int index = line.indexOf("] ", -1);
        String boxes = line.substring(0, index + 1);
        if (boxes.contains("[T]")) {
            task = new Todo(line.substring(index + 2));
        } else if (boxes.contains("[D]")) {
            int dateIndex = line.indexOf(" (by: ");
            String stringBeforeDate = line.substring(index + 2, dateIndex);
            LocalDate date = parseTime(line.substring(dateIndex));
            task = new Deadline(stringBeforeDate, date);
        } else if (boxes.contains("[E]")) {
            int dateIndex = line.indexOf(" (at: ");
            String stringBeforeDate = line.substring(index + 2, dateIndex);
            LocalDate date = parseTime(line.substring(dateIndex));
            task = new Event(stringBeforeDate, date);
        } else {
            return;
        }
        handleDone(boxes, task);
        taskList.add(task);
    }

    /**
     * Handles the doneness of tasks
     * @param boxes
     * @param task
     */
    private void handleDone(String boxes, Task task) {
        if (boxes.contains("[X]")) {
            task.setDone(true);
        }
    }

    /**
     * Helps parse the time
     * @param s String with time info
     * @return date
     */
    private LocalDate parseTime(String s) {
        List<Date> dates = new PrettyTimeParser().parse(s);
        Date date = dates.get(0);
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }



    /**
     * Save the tasks
     * @param taskList Tasklist
     * @throws DukeException
     */
    public void saveFile(TaskList taskList) throws DukeException {
        try {
            File myFile = new File("duke.txt");
            if (myFile.exists()) {
                myFile.delete();
            } else {
                myFile.createNewFile();
            }
            FileWriter myWriter = new FileWriter("duke.txt");
            for (Task t: taskList.getTaskList()) {
                myWriter.write(t.toString() + System.lineSeparator());
            }
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("Cannot save file. Do you have it open?");
        }


    }

}
