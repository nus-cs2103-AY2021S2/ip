package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException{
        List<Task> taskList = new ArrayList<>();

        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String currLine = s.nextLine();
                String[] lineArr = currLine.split(" \\| ");
//                System.out.println(lineArr[0]);
//                System.out.println(lineArr[1]);
//                System.out.println(lineArr[2]);
//                System.out.println(lineArr[3]);

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

    public void save(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        // checking if data folder exists
        File dataFolder = new File("/data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        // delete the original file if exist
        File file = new File(filePath);

        try {
            FileWriter fe = new FileWriter(filePath);
            fe.close();

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
