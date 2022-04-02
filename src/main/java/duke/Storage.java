package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class acts as an agent of communication between the data saved locally under the directory
 * and the program
 */
public class Storage {

    protected String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of tasks contained in a List<Task> by reading inputs from text file
     * If the text file is empty or there is loading errors, an empty List<Task> is returned
     *
     * @return List<Task>
     * @throws DukeException If there is reading error of the text file
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();

        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String currLine = s.nextLine();
                String[] lineArr = currLine.split(" \\| ");

                // extract tasks from the current line
                if (lineArr[0].equals("T")) {
                    String todoDesc = lineArr[2];
                    Todo newTodo = new Todo(todoDesc);

                    // check if the task is done
                    String isDone = lineArr[1];
                    if (isDone.equals("1")) {
                        newTodo.markAsDone();
                    }

                    taskList.add(newTodo);
                } else if (lineArr[0].equals("D")) {
                    String dlDesc = lineArr[2];
                    String dlBy = lineArr[3];
                    Deadline newDL = new Deadline(dlDesc, dlBy);

                    // check if the task is done
                    String isDone = lineArr[1];
                    if (isDone.equals("1")) {
                        newDL.markAsDone();
                    }

                    taskList.add(newDL);
                } else if (lineArr[0].equals("E")) {
                    String evDesc = lineArr[2];
                    String evAt = lineArr[3];
                    Deadline newEV = new Deadline(evDesc, evAt);

                    // check if the task is done
                    String isDone = lineArr[1];
                    if (isDone.equals("1")) {
                        newEV.markAsDone();
                    }

                    taskList.add(newEV);
                }
            }
        } catch (IOException e) {
            return taskList;
        }

        return taskList;
    }

    /**
     * Writes tasklist into text file
     *
     * @param taskList a list of all the tasks
     */
    public void save(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();

        // checking if data folder exists
        File dataFolder = new File("/data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        try {
            // clear the original file
            FileWriter fe = new FileWriter(filePath);
            fe.close();

            // write in the file
            FileWriter fw = new FileWriter(filePath, true);
            for (Task t : tasks) {
                fw.write(t.saveToFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write in: " + e.getMessage());
        }
    }
}
