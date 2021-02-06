package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Task> taskList = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String line : fileLines) {
            String[] userTask = line.split(" \\| ");
            String eventType = userTask[0];
            Task taskInList;
            try {
                switch (eventType) {
                case ("[T]"):
                    taskInList = new ToDo(userTask[2]);
                    taskList.add(taskInList);
                    break;
                case ("[E]"):
                    String eventDuration = parser.parseDate(userTask[3].split("at: ")[1]);
                    String eventDetail = userTask[2];
                    taskInList = new Event(eventDuration, eventDetail);
                    taskList.add(taskInList);
                    break;
                case ("[D]"):
                    String deadline = parser.parseDate(userTask[3].split("by: ")[1]);
                    String deadlineDetail = userTask[2];
                    taskInList = new Deadline(deadline, deadlineDetail);
                    taskList.add(taskInList);
                    break;
                default:

                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return taskList;
    }

    /**
     * Writes user's task list into tasks.txt local file.
     * If the task list isn't been able to be written into filePath, IOException thrown.
     *
     * @param data : the user's task list
     */
    public void writeData(List<Task> data) {
        StringBuilder stringOfData = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                stringOfData.append(data.get(i).toString());
            } else {
                stringOfData.append(data.get(i).toString()).append("\n");
            }
        }
        try {
            Files.writeString(Paths.get(this.filePath), stringOfData.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
