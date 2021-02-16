package duke.storage;

import duke.datevalidator.DateValidator;
import duke.taskclass.DeadlineTask;
import duke.taskclass.EventTask;
import duke.taskclass.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Storage {
    private Path relativePath;
    private Path absolutePath;
    private File taskText;

    /**
     * Constructor, creates a file of the searched file if it doesn't exist.
     * Afterwards, create a {@code FileWriter} which is used to write to the file.
     * @param relativePath relative path of file to be searched for.
     */
    public Storage (String relativePath) {
        this.relativePath = Paths.get(relativePath);
        this.absolutePath = this.relativePath.toAbsolutePath();

        try {
            Files.createDirectories(Paths.get("./data/"));
        } catch (IOException e) {
            System.out.println("IOException has occurred");
            e.printStackTrace();
        }
        taskText = new File(String.valueOf(this.absolutePath));
        try {
            if (!taskText.exists()) {
                System.out.println("new file created");
                taskText.createNewFile(); // creates the file if it doesn't exist
            }
        } catch (IOException e) {
            System.out.println("IOException has occurred");
            e.printStackTrace();
        }
    }

    /**
     * Load a _TODO_ task from the disc.
     * @param taskName Name of the task.
     * @param isDone Whether or not task is done.
     * @return The loaded task.
     */
    public Task createTodoTask(String taskName, boolean isDone) {
        return new Task(taskName, isDone);
    }

    /**
     * Load an EVENT task from the disk.
     * @param validator Validator to check if date input is appropriate.
     * @param strArr User input, only strArr[3] is used here to get user date input.
     * @param taskName Name of the task.
     * @param isDone Whether or not task is done.
     * @return The loaded task.
     */
    public Task createEventTask(DateValidator validator, String[] strArr, String taskName, boolean isDone) {
        if (!validator.isValid(strArr[3].trim())) {
            System.out.println("Invalid date format for timed Task");
            return null;
        }
        try {
            Date eventDate = new SimpleDateFormat("d/MM/yyyy HHmm").parse(strArr[3].trim());
            return new EventTask(taskName, isDone, strArr[3].trim(), eventDate);
        } catch (ParseException e) {
            System.out.println("ParseException has occurred");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Load a DEADLINE task from the disk.
     * @param validator Validator to check if date input is appropriate.
     * @param strArr User input, only strArr[3] is used here to get user date input.
     * @param taskName Name of the task.
     * @param isDone Whether or not task is done.
     * @return The loaded task.
     */
    public Task createDeadlineTask(DateValidator validator, String[] strArr, String taskName, boolean isDone) {
        if (!validator.isValid(strArr[3].trim())) {
            System.out.println("Invalid date format for timed Task");
            return null;
        }
        try {
            Date eventDate = new SimpleDateFormat("d/MM/yyyy HHmm").parse(strArr[3].trim());
            return new DeadlineTask(taskName, isDone, strArr[3].trim(), eventDate);
        } catch (ParseException e) {
            System.out.println("ParseException has occurred");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads a file into the program and parses each line into a Task and puts it into taskArr.
     * @param taskArr array of Task objects.
     * @return number of tasks currently available in list (1 index).
     */
    public int readTaskListToArray(Task[] taskArr, DateValidator validator) {
        int taskIterator = 0;
        try {
            List<String> list = Files.readAllLines(Paths.get(absolutePath.toString()), Charset.defaultCharset());
            String[] taskListStr = list.toArray(new String[list.size()]);
            for (String str : taskListStr) {
                String[] strArr = str.split(" \\| ");
                String taskType = strArr[0];
                String isDoneStr = strArr[1];
                String taskName = strArr[2];

                if (taskType.equals("todo")) {
                    taskArr[taskIterator] = createTodoTask(taskName, isDoneStr.equals("done"));
                } else if (taskType.equals("event")) {
                    taskArr[taskIterator] = createEventTask(validator, strArr, taskName, isDoneStr.equals("done"));
                } else if (taskType.equals("deadline")) {
                    taskArr[taskIterator] = createDeadlineTask(validator, strArr, taskName, isDoneStr.equals("done"));
                }
                taskIterator++;
            }
            return taskIterator;
        } catch (IOException e) {
            System.out.println("IOException has occurred");
            e.printStackTrace();
        }
        return taskIterator;
    }

    /**
     * Writes all tasks from an array of {@code Task} objects to file output.
     * @param taskArr Array of {@code Task} objects.
     */
    public void writeTasks(Task[] taskArr) {
        try {
            FileWriter fw = new FileWriter(absolutePath.toString());
            for (Task t : taskArr) {
                if (t == null) {
                    continue;
                } else {
                    fw.write(t.toOutputFileString() + "\n");
                    fw.flush();
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException has occurred");
            e.printStackTrace();
        }
    }
}
