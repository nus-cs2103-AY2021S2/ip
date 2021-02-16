package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private Ui ui = new Ui();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This function loads the tasks from the file which is specified by the file path
     * @return an arraylist of Tasks
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File tasks = new File(filePath);
        try {
            if (tasks.exists()) {
                Scanner sc1 = new Scanner(tasks);
                String data;
                while (sc1.hasNextLine()) {
                    data = sc1.nextLine();
                    list.add(Parser.parseFile(data));
                }
                sc1.close();
            } else {
                File directory = new File("./data");
                if (!directory.exists()) {
                    directory.mkdir();
                }
                tasks.createNewFile();
            }
        } catch (FileNotFoundException e){
            throw new DukeException("File not found.");
        } catch (IOException e) {
            throw new DukeException("Error occurred when creating file.");
        } catch (DukeException e) {
            throw e;
        }
        return list;
    }

    /**
     * This function writes and save the taskList to a file
     * @param tasks TaskList object containing a list of tasks
     * @param filePath The path to save the file
     * @throws DukeException
     */
    public void writeToFile(TaskList tasks, String filePath) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            int size = tasks.getSize();
            Task task;

            for (int i = 0; i < size; i++) {
                task = tasks.getTask(i);
                writer.write(task.eventType + " | " + task.isDone + " | " + task.eventName);
                if (task instanceof Deadlines) {
                    writer.write(" | " + ((Deadlines) task).deadLine);
                } else if (task instanceof Events) {
                    writer.write(" | " + ((Events) task).time);
                }
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }
}
