package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class handles any operations by Duke that involves writing or reading from a file.
 */
public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends a Duke readable String representation of a Task to the end of the file designated by filePath.
     * @param task the Task to be added.
     */
    public void addToFile(Task task) {
        File save = new File(this.filePath);
        try {
            FileWriter fw = new FileWriter(save, true);
            fw.write(task.fileString() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a Task specified by its index (line number) from the file designated by filePath.
     * @param index the index of the Task to be deleted.
     */
    public void deleteFromFile(int index) {
        File save = new File(this.filePath);
        File temp = new File("./data/temp.txt");
        int ctr = 0;
        try {
            //create temp file
            temp.createNewFile();
            Scanner sc = new Scanner(save);
            FileWriter fw = new FileWriter(temp);

            //copy all lines except line to be deleted to temp
            while (sc.hasNextLine()) {
                //skip the line to be deleted
                if (ctr != index) {
                    fw.write(sc.nextLine() + System.lineSeparator());
                } else {
                    sc.nextLine();
                }
                ctr++;
            }
            fw.close();
            sc.close();

            //delete original save and rename temp to save
            save.delete();
            temp.renameTo(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks a Task specified by its index (line number) as done from the file designated by filePath.
     * @param index the index of the Task to be marked as done.
     */
    public void markDoneInFile(int index) {
        File save = new File(this.filePath);
        File temp = new File("./data/temp.txt");
        int ctr = 0;
        try {
            //create temp file
            temp.createNewFile();
            Scanner sc = new Scanner(save);
            FileWriter fw = new FileWriter(temp);

            //copy all lines except line to be edited to temp
            while (sc.hasNextLine()) {
                //change line to be edited
                if (ctr != index) {
                    fw.write(sc.nextLine() + System.lineSeparator());
                } else {
                    fw.write(sc.nextLine().replaceFirst("false", "true") + System.lineSeparator());
                }
                ctr++;
            }
            fw.close();
            sc.close();

            //delete original save and rename temp to save
            save.delete();
            temp.renameTo(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the current Tasks from the file specified by filePath to Duke.
     * If the file does not exist it is created.
     * @param taskList The TaskList for the Tasks to be stored in.
     * @throws IOException
     */
    public void loadData(TaskList taskList) throws IOException {
        File save = new File(this.filePath);
        if (save.getParentFile() != null) {
            save.getParentFile().mkdirs();
        }
        save.createNewFile();
        Scanner sc = new Scanner(save);
        while (sc.hasNext()) {
            String taskString = sc.nextLine();
            String[] taskArgsArray = taskString.split(" [|] ");
            Task task;
            if (taskArgsArray[0].equals("T")) {
                task = new Todo(Boolean.parseBoolean(taskArgsArray[1]), taskArgsArray[2]);
            } else if (taskArgsArray[0].equals("D")) {
                task = new Deadline(Boolean.parseBoolean(taskArgsArray[1]), taskArgsArray[2], taskArgsArray[3]);
            } else {
                task = new Event(Boolean.parseBoolean(taskArgsArray[1]), taskArgsArray[2], taskArgsArray[3]);
            }
            taskList.add(task);
        }
        sc.close();
    }
}
