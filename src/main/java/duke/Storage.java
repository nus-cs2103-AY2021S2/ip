package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Storage for Duke
 */
public class Storage {
    private String filePath;

    /**
     * Construction with specified file path
     * 
     * @param filePath the file path to load and save from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list from the file path given during initialisation
     * 
     * @return List of saved tasks
     * @throws IOException
     * @throws DateTimeParseException
     */
    public TaskList load() throws IOException, DateTimeParseException {
        int lastDelimiterIndex = this.filePath.lastIndexOf("/");
        if (lastDelimiterIndex < 0) {
            lastDelimiterIndex = this.filePath.length() + 1;
        }
        String dirPath = this.filePath.substring(0, lastDelimiterIndex);
        File dir = new File(dirPath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File f = new File(this.filePath);
        TaskList memList = new TaskList();

        if (!f.exists()) {
            f.createNewFile();
        } else {
            // fill with stored data
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] input = sc.nextLine().split(" \\| ");
                String type = input[0];
                Task task;

                if (type.equals("T")) { // todo
                    task = new ToDo(input[2]);
                } else if (type.equals("D")) { // deadline
                    LocalDate date = LocalDate.parse(input[3]);
                    task = new Deadline(input[2], date);
                } else { // event
                    LocalDate date = LocalDate.parse(input[3]);
                    task = new Event(input[2], date);
                }

                // change status
                if (input[1].equals("1")) {
                    task.setCompletion(true);
                }

                memList.add(task);
            }
            sc.close();
        }

        return memList;
    }

    /**
     * Stores task list into file path specified during initialisation
     * 
     * @param tasks Task list to be saved
     * @throws IOException
     */
    public void store(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);

        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i);
            String tag = currTask.getTag();
            int isComplete = currTask.checkIsComplete() ? 1 : 0;
            String detail = currTask.getDetail();
            String date = currTask.getDate() == null ? "" : currTask.getDate().toString();
            fw.write(tag + " | " + isComplete + " | " + detail + " | " + date + "\n");
        }
        fw.close();

    }

}
