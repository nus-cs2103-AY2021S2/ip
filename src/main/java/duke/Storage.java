package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.ToDo;

/**
 * Represents the storage for user's task list.
 * Reads and writes data into user's saved file that stores task list.
 */
public class Storage {
    private final String filePath;
    private final Parser parser = new Parser();

    /**
     * Constructor for Storage object.
     * Makes file tasks.txt if non-existent. Else, validates existence of file
     *
     * @param filePath the file path where user's TaskList is to be stored.
     * @throws IOException if filePath is not "data/tasks.txt"
     * @see Files
     * @see Paths
     */
    public Storage(String filePath) throws IOException {
        String parentPath = filePath.split("tasks.txt")[0];
        if (!Files.exists(Paths.get(parentPath))) {
            Files.createDirectories(Paths.get(parentPath));
        }
        if (!Files.exists(Paths.get(filePath))) {
            Files.createFile(Paths.get(filePath));
        }
        this.filePath = filePath;
    }

    /**
     * Returns user's task list that is in the saved file on user's local disk.
     * Initializes objects using the String data in tasks.txt file.
     * Parses the data saved and adds correct form of tasks in the TaskList object
     *
     * @return ArrayList of user's tasks.
     * @throws IOException if incorrect data in file or incorrectly parsed.
     * @see Parser
     * @see TaskList
     * @see Task
     * @see ToDo
     * @see Event
     * @see Deadline
     * @see Paths
     */
    public ArrayList<Task> getTaskList() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String line : fileLines) {
            assert line.contains("|") : "every line in the file should contain | for splitting of information";

            String[] userTask = line.split(" \\| ");
            String eventType = userTask[0];
            Task taskInList;
            try {
                switch (eventType) {
                case ("[T]"):
                    taskInList = new ToDo(userTask[2]);
                    tasks.add(taskInList);
                    break;
                case ("[E]"):
                    assert line.contains("at") : "every event task should have specified event date after 'at'";

                    String eventDetail = userTask[2];

                    String eventDateStr = userTask[3].split("at: ")[1].replaceAll(" ", "-");
                    LocalDate eventDuration = LocalDate.parse(eventDateStr, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

                    taskInList = new Event(eventDuration, eventDetail);
                    tasks.add(taskInList);
                    break;
                case ("[D]"):
                    assert line.contains("by") : "every deadline task should have deadline date after 'by'";

                    String deadlineDetail = userTask[2];

                    String deadlineStr = userTask[3].split("by: ")[1].replaceAll(" ", "-");
                    LocalDate deadline = LocalDate.parse(deadlineStr, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

                    taskInList = new Deadline(deadline, deadlineDetail);
                    tasks.add(taskInList);
                    break;
                case ("[LC]"):
                case ("[LDT]"):
                    //do nothing
                    break;
                default:
                    assert false : "invalid event type";
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return tasks;
    }

    public String getLastCommand() throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        String lastCommand = "";
        for (String line : fileLines) {
            String type = line.split(" \\| ")[0];
            if (type.equals("[LC]")) {
                lastCommand = line.split(" \\| ")[1];
            }
        }
        return lastCommand;
    }

    public Task getLastDeletedTask() throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        Task lastDeletedTask = null;
        for (String line : fileLines) {
            String type = line.split(" \\| ")[0];
            if (!type.equals("[LDT]")) {
                continue;
            }
            int taskSubStringOffset = 8;
            String taskSubString = line.substring(taskSubStringOffset);
            String[] taskSubStringArr = taskSubString.split(" \\| ");

            String eventType = taskSubStringArr[0];
            switch (eventType) {
            case ("[T]"):
                lastDeletedTask = new ToDo(taskSubStringArr[2]);
                break;
            case ("[E]"):
                String eventDateStr = taskSubStringArr[3].split("at: ")[1].replaceAll(" ", "-");
                LocalDate eventDate = LocalDate.parse(eventDateStr, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

                String eventDetail = taskSubStringArr[2];

                lastDeletedTask = new Event(eventDate, eventDetail);
                break;
            case ("[D]"):
                String deadlineStr = taskSubStringArr[3].split("by: ")[1].replaceAll(" ", "-");
                LocalDate deadline = LocalDate.parse(deadlineStr, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

                String deadlineDetail = taskSubStringArr[2];

                lastDeletedTask = new Deadline(deadline, deadlineDetail);
                break;
            default:
                //do nothing
            }
        }
        return lastDeletedTask;
    }

    /**
     * Writes user's task list into tasks.txt local file.
     * If the task list isn't been able to be written into filePath, IOException thrown.
     *
     * @param data : the user's task list
     */
    public String writeTaskList(List<Task> data) {
        String stringOfData = "";
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                stringOfData += data.get(i).toString();
            } else {
                stringOfData += data.get(i).toString() + "\n";
            }
        }
        return stringOfData;
    }

    public String writeLastCommand(String lastCommand) {
        if (lastCommand == null) {
            lastCommand = "";
        }
        String formattedCommand = "[LC] | " + lastCommand + "\n";
        return formattedCommand;
    }

    public String writeLastDeletedTask (Task deletedTask) {
        String deletedTaskString = "";
        if (deletedTask != null) {
            deletedTaskString = deletedTask.toString();
        }
        String lastDeletedTaskString = "[LDT] | " + deletedTaskString + "\n";
        return lastDeletedTaskString;
    }

    public void writeAllData (TaskList listOfTasks, String lastCommand, Task deletedTask) {
        try {
            String allData = writeLastCommand(lastCommand) + writeLastDeletedTask(deletedTask)
                    + writeTaskList(listOfTasks.getTaskList());
            Files.writeString(Paths.get(this.filePath), allData);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
