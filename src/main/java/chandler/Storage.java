package chandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import chandler.command.CommandName;
import chandler.task.DeadlineTask;
import chandler.task.EventTask;
import chandler.task.Task;
import chandler.task.TodoTask;
import chandler.ui.Ui;

/**
 * The Storage class encapsulates information and methods to handle loading and saving tasks
 * to and from a saved data file.
 */
public class Storage {

    private String filePath;
    private Ui ui;

    /**
     * Create an initialize a storage object to handle loading and saving of tasks to a saved data file.
     *
     * @param filePath The path of the file to save the list of tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
    }

    /**
     * Saves the changes to the saved data file after every change.
     *
     * @param taskList The list of tasks.
     * @throws ChandlerException if the file is unable to be found or is unable to be written to.
     */
    public void saveTaskList(TaskList taskList) throws ChandlerException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task currTask = taskList.getIndex(i);
                fileWriter.write(currTask.getSavingString());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new ChandlerException(ui.saveDataError());
        }
    }

    /**
     * Populates the task list with the tasks saved in the task data file upon start of program.
     *
     * @param taskList The task list to be populated.
     * @throws ChandlerException if the file is unable to be found or the file's contents are in the wrong format.
     */
    public void initializeTaskList(TaskList taskList) throws ChandlerException {
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
        } catch (IOException | ChandlerException e) { // cannot read file or contents of file do not follow format
            taskList.clear();
            throw new ChandlerException(ui.corruptFileDataError());
        }
    }

    /**
     * Converts the contents of the saved data file of tasks in String form to Task form
     *
     * @param taskString Task in String form.
     * @return Corresponding Task.
     * @throws ChandlerException if the Task String is in the wrong format.
     */
    public Task loadTaskFromFile(String taskString) throws ChandlerException {
        String[] taskStringArr = taskString.split("\\|");
        CommandName commandName = null;
        try {
            commandName = CommandName.valueOf(taskStringArr[0]);
        } catch (IllegalArgumentException e) {
            throw new ChandlerException(ui.corruptFileDataError());
        }
        switch (commandName) {
        case TODO:
            return loadTodoFromFile(taskStringArr);
        case EVENT:
            return loadEventFromFile(taskStringArr);
        case DEADLINE:
            return loadDeadlineFromFile(taskStringArr);
        default:
            throw new ChandlerException(ui.corruptFileDataError());
        }
    }

    private Task loadDeadlineFromFile(String[] taskStringArr) throws ChandlerException {
        int taskStatus = -1;
        LocalDateTime doneDate = null;
        String taskName = taskStringArr[2];
        String taskDate = taskStringArr[3];;
        Task taskToReturn;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            taskStatus = Integer.parseInt(taskStringArr[1]);
        } catch (NumberFormatException e) {
            throw new ChandlerException(ui.corruptFileDataError());
        }

        try {
            LocalDateTime ldtDeadline = LocalDateTime.parse(taskDate, dtf);
            taskToReturn = new DeadlineTask(taskName, ldtDeadline);
            if (taskStringArr.length == 5) { // has a doneDate
                doneDate = LocalDateTime.parse(taskStringArr[4], dtf);
            }
        } catch (DateTimeParseException e) {
            throw new ChandlerException(ui.corruptFileDataError());
        }

        boolean isCommandFormatError = taskStatus == -1 || taskStatus > 1
                || taskToReturn == null || taskStringArr.length > 5;
        if (isCommandFormatError) {
            throw new ChandlerException(ui.corruptFileDataError());
        }
        if (taskStatus == 1) {
            assert doneDate != null : "Done Date cannot be null";
            taskToReturn.markDonePast(doneDate);
        }
        return taskToReturn;
    }

    private Task loadEventFromFile(String[] taskStringArr) throws ChandlerException {
        int taskStatus = -1;
        LocalDateTime doneDate = null;
        String taskName = taskStringArr[2];;
        String taskDate = taskStringArr[3];;
        Task taskToReturn;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            taskStatus = Integer.parseInt(taskStringArr[1]);
        } catch (NumberFormatException e) {
            throw new ChandlerException(ui.corruptFileDataError());
        }

        try {
            LocalDateTime ldtEvent = LocalDateTime.parse(taskDate, dtf);
            taskToReturn = new EventTask(taskName, ldtEvent);
            if (taskStringArr.length == 5) { // has a doneDate
                doneDate = LocalDateTime.parse(taskStringArr[4], dtf);
            }
        } catch (DateTimeParseException e) {
            throw new ChandlerException(ui.corruptFileDataError());
        }

        boolean isCommandFormatError = taskStatus == -1 || taskStatus > 1
                || taskToReturn == null || taskStringArr.length > 5;
        if (isCommandFormatError) {
            throw new ChandlerException(ui.corruptFileDataError());
        }
        if (taskStatus == 1) {
            assert doneDate != null : "Done Date cannot be null";
            taskToReturn.markDonePast(doneDate);
        }
        return taskToReturn;
    }

    private Task loadTodoFromFile(String[] taskStringArr) throws ChandlerException {
        int taskStatus = -1;
        LocalDateTime doneDate = null;
        Task taskToReturn;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            taskStatus = Integer.parseInt(taskStringArr[1]);
        } catch (NumberFormatException e) {
            throw new ChandlerException(ui.corruptFileDataError());
        }
        String taskName = taskStringArr[2];
        if (taskStringArr.length == 4) { // has a doneDate
            try {
                doneDate = LocalDateTime.parse(taskStringArr[3], dtf);
            } catch (DateTimeParseException e) {
                throw new ChandlerException(ui.corruptFileDataError());
            }
        }
        taskToReturn = new TodoTask(taskName);

        boolean isCommandFormatError = taskStatus == -1 || taskStatus > 1
                || taskStringArr.length > 4;
        if (isCommandFormatError) {
            throw new ChandlerException(ui.corruptFileDataError());
        }
        if (taskStatus == 1) {
            assert doneDate != null : "Done Date cannot be null";
            taskToReturn.markDonePast(doneDate);
        }
        return taskToReturn;
    }
}
