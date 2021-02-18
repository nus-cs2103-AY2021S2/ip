package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final String filePath;
    private TaskList taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Loads the file containing the task list from the hard drive when Duke starts up.
     * If the file or folder doesn't exist yet, create a file in the file path or file path first.
     */
    public void retrieveOrCreate() {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        taskList = readFromFile();
    }

    /**
     * Saves the updated task list in the hard drive automatically whenever the task list changes.
     */
    public void writeToFile(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            output.append(taskList.getTask(i).toString()).append("\n");
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(output.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts the line from a string array to a Todo.
     * @param dataArr The line in a string array.
     * @return A Todo.
     */
    public Task convertToTodo(String[] dataArr) {
        StringBuilder description = new StringBuilder();
        for (int i = 2; i < dataArr.length; i++) {
            description.append(dataArr[i]).append(" ");
        }
        return new Todo(description.toString());
    }

    /**
     * Converts the line from a string array to either a Deadline or Event.
     * @param dataArr The line in a string array.
     * @param isDeadline A flag indicating if the line is a Deadline.
     * @return A Deadline or Event depending on isDeadline.
     */
    public Task convertToDeadlineOrEvent(String[] dataArr, boolean isDeadline) {
        int lastIndex = dataArr.length - 1;
        String timeWithBracket = dataArr[lastIndex];
        String[] timeWithBracketArr = timeWithBracket.split("");
        int timeWithBracketLength = timeWithBracketArr.length;
        int timeWithBracketLastIndex = timeWithBracketLength - 2;
        StringBuilder time = new StringBuilder();
        for (int i = 0; i <= timeWithBracketLastIndex; i++) {
            time.append(timeWithBracketArr[i]);
        }
        String yearData = dataArr[lastIndex - 1];
        String[] yearArr = yearData.split("");
        StringBuilder year = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            year.append(yearArr[i]);
        }
        String date = dataArr[lastIndex - 3] + " " + dataArr[lastIndex - 2] + " " + year;
        int descriptionLastIndex = lastIndex - 5;
        StringBuilder description = new StringBuilder();
        for (int i = 2; i <= descriptionLastIndex; i++) {
            description.append(dataArr[i]).append(" ");
        }
        if (isDeadline) {
            return new Deadline(description.toString(), date, time.toString());
        }
        return new Event(description.toString(), date, time.toString());
    }

    /**
     * Reads the whole file line by line.
     * For each line, checks which type of Task it is.
     * Passes the line to the relevant converter to convert into a Task.
     * Adds the task to a list.
     * After reading the entire file, return the list.
     * @return The saved task list.
     */
    public TaskList readFromFile() {
        TaskList taskList = new TaskList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String input = br.readLine();
            while (input != null) {
                String[] inputArr = input.split(" ");
                Task task;
                if (input.startsWith("[T]")) {
                    task = convertToTodo(inputArr);
                } else if (input.startsWith("[D]")) {
                    task = convertToDeadlineOrEvent(inputArr, true);
                } else {
                    task = convertToDeadlineOrEvent(inputArr, false);
                }
                if (inputArr[1].equals("[/]")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                input = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
