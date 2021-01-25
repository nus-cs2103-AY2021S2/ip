package duke;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private final String path;

    Storage(String path) {
        this.path = path;
    }


    public TaskList open() throws DukeException {

        File file = new File(path);
        TaskList taskList = new TaskList();
        int taskID = taskList.getSize() + 1;

        if (!file.exists()) {
            createFile(file);
        }

        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String newLine = sc.nextLine();
                Task task = processFileContents(newLine, taskID);
                taskList.addTask(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found or is in a wrong format");
        }

        return taskList;
    }

    public void write(TaskList taskList) throws IOException, DukeException {
        FileWriter fw = new FileWriter(path);
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            sb.append(formatFileContents(task));
            sb.append("\n");
        }
        fw.write(sb.toString());
        fw.close();
    }

    private void createFile(File file) throws DukeException {
        try {
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to create new file.");
        }
    }

    private String formatFileContents(Task task) throws DukeException {
        String format;
        String description = task.getDescription();
        int status = task.isDone() ? 1 : 0;

        if (task instanceof ToDoTask) {
            format = String.format("T | %d | %s", status, description);
        } else if (task instanceof DeadlineTask) {
            String deadline = ((DeadlineTask) task).serializeDeadline();
            format = String.format("D | %d | %s | %s", status, description, deadline);
        } else if (task instanceof EventTask) {
            String eventDateTime = ((EventTask) task).serializeEvent();
            format = String.format("E | %d | %s | %s", status, description, eventDateTime);
        } else {
            throw new DukeException("Error in file writing: Unknown duke.task type");
        }
        return format;
    }

    private Task processFileContents(String newLine, int taskID) throws DukeException {
        String[] lineContents = newLine.split(" \\| ");
        String taskType = lineContents[0];
        int status = Integer.parseInt(lineContents[1]);
        String description = lineContents[2];

        switch (taskType) {
            case "T":
                return new ToDoTask(description, taskID, status);
            case "D":
                String time = lineContents[4];
                LocalDate deadline = LocalDate.parse(lineContents[3]);
                return new DeadlineTask(description, taskID, status, deadline, time);
            case "E":
                LocalDate eventDate = LocalDate.parse(lineContents[3]);
                String startTime = lineContents[4];
                String endTime = lineContents[5];
                return new EventTask(description, taskID, status, eventDate, startTime, endTime);
            default:
                throw new DukeException("Error in file reading: Unknown duke.task type");
        }
    }

}
