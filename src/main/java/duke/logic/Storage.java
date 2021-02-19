package duke.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Storage for Duke
 */
public class Storage {
    private String filePath;

    /**
     * Construction with specified file path
     * @param filePath the file path to load and save from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list from the file path given during initialisation
     * @return List of saved tasks
     * @throws IOException
     * @throws DateTimeParseException
     */
    public TaskList load() throws IOException, DateTimeParseException {
        String fileDir = getFileDir();
        boolean fileDirExists = checkDirPath(fileDir);

        if (!fileDirExists) {
            makeFileDir(fileDir);
        }

        File f = new File(this.filePath);

        if (!f.exists()) {
            f.createNewFile();
            assert(f.exists());
            return new TaskList();
        } else {
            TaskList memList = readFromMem(f);
            return memList;
        }
    }

    /**
     * Stores task list into file path specified during initialisation
     * @param tasks Task list to be saved
     * @throws IOException
     */
    public void store(TaskList tasks) throws IOException {
        File f = new File(this.filePath);
        assert(f.exists());

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

    private String getFileDir() {
        int lastDelimiterIndex = this.filePath.lastIndexOf("/");
        if (lastDelimiterIndex < 0) {
            return "";
        }
        String fileDir = this.filePath.substring(0, lastDelimiterIndex);
        return fileDir;
    }

    private boolean checkDirPath(String fileDir) {
        File dir = new File(fileDir);
        return dir.exists();
    }

    private void makeFileDir(String fileDir) {
        File dir = new File(fileDir);
        dir.mkdirs();
    }

    private TaskList readFromMem(File f) throws FileNotFoundException {
        assert(f.exists());

        TaskList memList = new TaskList();

        // fill with stored data
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String taskString = sc.nextLine();
            Task parsedTask = parseTask(taskString);
            memList.add(parsedTask);
        }
        sc.close();
        return memList;
    }

    private Task parseTask(String taskString) {
        String[] inputs = taskString.split(" \\| ");
        String type = inputs[0];
        Task task;

        if (type.equals("T")) { // todo
            assert(inputs.length == 3);
            task = new ToDo(inputs[2]);

        } else if (type.equals("D")) { // deadline
            assert(inputs.length == 4);
            LocalDate date = LocalDate.parse(inputs[3]);
            task = new Deadline(inputs[2], date);

        } else { // event
            assert(inputs.length == 4);
            LocalDate date = LocalDate.parse(inputs[3]);
            task = new Event(inputs[2], date);
        }

        // change status
        if (inputs[1].equals("1")) {
            task.setCompletion(true);
        }

        return task;
    }
}
