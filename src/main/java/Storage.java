import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTaskList(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task currTask = taskList.getIndex(i);
                fileWriter.write(currTask.getSavingString());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save data.");
        }
    }

    public void initializeTaskList(TaskList taskList) throws DukeException {
        try {
            File savedTaskList = new File(filePath);
            savedTaskList.getParentFile().mkdirs();
            savedTaskList.createNewFile();

            if (savedTaskList.length() != 0) { // file is not empty
                Scanner sc = new Scanner(savedTaskList);
                while (sc.hasNextLine()) {
                    Task currTask = loadTaskFromFile(sc.nextLine());
                    taskList.add(currTask);
                }
            }
        } catch (IOException e) {
            throw new DukeException("Saved data file corrupted.");
        }
    }

    public Task loadTaskFromFile(String taskString) throws DukeException {
        String[] taskStringArr = taskString.split("\\|");
        CommandName commandName = CommandName.valueOf(taskStringArr[0]);
        int taskStatus = -1;
        String taskName;
        String taskDate;
        Task taskToReturn = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        switch (commandName) {
        case TODO:
            taskStatus = Integer.parseInt(taskStringArr[1]);
            taskName = taskStringArr[2];
            taskToReturn = new TodoTask(taskName);
            break;
        case EVENT:
            taskStatus = Integer.parseInt(taskStringArr[1]);
            taskName = taskStringArr[2];
            taskDate = taskStringArr[3];
            try {
                LocalDateTime ldtEvent = LocalDateTime.parse(taskDate, dtf);
                taskToReturn = new EventTask(taskName, ldtEvent);
            } catch (DateTimeParseException e) {
                throw new DukeException("Save data file corrupted");
            }
            break;
        case DEADLINE:
            taskStatus = Integer.parseInt(taskStringArr[1]);
            taskName = taskStringArr[2];
            taskDate = taskStringArr[3];
            try {
                LocalDateTime ldtDeadline = LocalDateTime.parse(taskDate, dtf);
                taskToReturn = new DeadlineTask(taskName, ldtDeadline);
            } catch (DateTimeParseException e) {
                throw new DukeException("Save data file corrupted");
            }
            break;
        }
        if (taskStatus == -1 || taskToReturn == null) {
            throw new DukeException("Saved data file corrupted.");
        }
        if (taskStatus == 1) {
            taskToReturn.markDone();
        }
        return taskToReturn;
    }
}
