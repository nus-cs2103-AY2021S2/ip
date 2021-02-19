package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;


/**
 * Storage class to load and save list of tasks into a text file
 */
public class Storage {
    private static DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private String path;
    private File file;

    /**
     * Storage constructor
     *
     * @param path Filepath of .txt file to save tasks in
     * @throws IOException If Duke is unable to read the file
     */
    public Storage(String path) {
        this.path = path;
        try {
            file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads saved tasks
     *
     * @return ArrayList of saved tasks
     * @throws FileNotFoundException If Duke is unable to read the file
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                String[] spiltString = nextLine.split(" \\|\\| ");

                String taskType = spiltString[0];
                assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E");
                assert spiltString[1].equals("1") || spiltString[1].equals("0");
                boolean isCompleted = spiltString[1].equals("1");
                String taskName = spiltString[2];

                if (spiltString.length == 4) {
                    LocalDate time = LocalDate.parse(spiltString[3], OUTPUT_FORMATTER);
                    if (taskType.equals("D")) {
                        list.add(new Deadline(taskName, isCompleted, time.toString()));
                    } else if (taskType.equals("E")) {
                        list.add(new Event(taskName, isCompleted, time.toString()));
                    } else {
                        System.out.println("Error");
                    }
                } else {
                    if (taskType.equals("T")) {
                        list.add(new Todo(taskName, isCompleted));
                    } else {
                        System.out.println("Error");
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error!!");
        }
        return list;
    }

    /**
     * Writes tasks into .txt file
     *
     * @param taskList List of tasks
     * @throws IOException If Duke is unable to write the file
     */
    public void writeFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(path);
            ArrayList<Task> list = taskList.getList();

            for (Task curr : list) {
                String storageString = curr.toStorageString();
                fw.write(storageString + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
