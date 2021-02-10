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
        assert filePath.contains("tasks.txt") : "file path provided should contain tasks.txt";

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
        List<String> fileLines = Files.readAllLines(Paths.get(this.filePath));
        for (String line : fileLines) {
            assert line.contains("|") : "every line in the file should contain | for splitting of information";

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
                    assert line.contains("at") : "every event task should have specified event date after 'at'";

                    String eventDuration = parser.parseDate(userTask[3].split("at: ")[1]);
                    String eventDetail = userTask[2];
                    taskInList = new Event(eventDuration, eventDetail);
                    taskList.add(taskInList);
                    break;
                case ("[D]"):
                    assert line.contains("by") : "every deadline task should have deadline date after 'by'";

                    String deadline = parser.parseDate(userTask[3].split("by: ")[1]);
                    String deadlineDetail = userTask[2];
                    taskInList = new Deadline(deadline, deadlineDetail);
                    taskList.add(taskInList);
                    break;
                default:
                    // do nothing
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
        assert data != null : "List of tasks should not be empty.";

        String stringOfData = "";
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                stringOfData += data.get(i).toString();
            } else {
                stringOfData += data.get(i).toString() + "\n";
            }
        }
        try {
            Files.writeString(Paths.get(this.filePath), stringOfData);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
