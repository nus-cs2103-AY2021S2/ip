/**
 * This TaskList class handles the logic of adding and deleting tasks of Duke
 * @param file Task file to remember the tasks
 * @author WangYihe
 * @author E0424695
 */

package duke.fileSaver;
import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class FileSaver {
    private File file;

    public FileSaver(String folderName, String fileName) {
        file = new File(folderName, fileName);
    }

    /**
     * Create folder and file, if success, print message
     */
    public void createFile() throws DukeException {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                System.out.println("CREATED_FOLDER");
            }
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("CREATED_SAVE_FILE");
            }
        } catch (IOException e) {
            //TODO: handle exception
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * add task to the list
     */
    public void addTask(TaskList task, Task t, String isDone) {
        if (isDone.trim().equals("1")) {
            t.markAsDone();
        }
        task.add(t);
    }

    /**
     * check whether the date added is in the correct format
     */
    public static boolean isDateFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException e) {
            //TODO: handle exception
            return false;
        }
        return true;
    }

    /**
     * parse the task input to create correct object
     */
    public void parseTask(TaskList task, String info) throws IndexOutOfBoundsException {
        String[] taskInfo = info.split(" \\| ");
        String type = taskInfo[0].trim();
        switch (type) {
        case "T":
            Todo t = new Todo(taskInfo[2]);
            addTask(task, t, taskInfo[1]);
            break;
        case "E":
            Event e = new Event(taskInfo[2], taskInfo[3]);
            addTask(task, e, taskInfo[1]);
            break;
        case "D":
            String time = taskInfo[3];
            if (isDateFormat(time)) {
                LocalDate date = LocalDate.parse(time);
                time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            }
            Deadline deadline = new Deadline(taskInfo[2], time);
            addTask(task, deadline, taskInfo[1]);
            break;
        default:
            break;
        } 
    }

    /**
     * load the task from text file
     */
    public void load(TaskList task) throws DukeException {
        createFile();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                parseTask(task, sc.nextLine());
            }
        } catch (IndexOutOfBoundsException e) {
            //TODO: handle exception
            throw new DukeException("File is not being read properly.");
        } catch (FileNotFoundException f) {
            throw new DukeException("File is not found");
        }
    }

    /**
     * save the task to the text file
     */
    public void save(TaskList task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < task.getSize(); i++) {
                Task t = task.get(i);
                String saveFormat = t.savedFormat();
                assert saveFormat.length() >= 0 : "Save format error, length less than 0";
                fw.write(t.savedFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            //TODO: handle exception
            throw new DukeException("There is something wrong writing to the file");
        }
    }
}
