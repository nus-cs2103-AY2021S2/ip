package seashell.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import seashell.TaskList;
import seashell.task.Deadline;
import seashell.task.Event;
import seashell.task.Task;
import seashell.task.Todo;


public class SaveHandler {
    public static final String DIR_PATH = "data";
    public static final String FILE_PATH = DIR_PATH + "/saveFile.txt";

    /**
     * If save file exists, read the save file and return the task list read from the save file
     * @return the task arraylist read from save file
     */
    public ArrayList<Task> loadSave() {
        try {
            if (!this.isDirExist()) {
                File dir = new File(DIR_PATH);
                dir.mkdir();
                assert dir.isDirectory();
                File saveFile = new File(FILE_PATH);
                saveFile.createNewFile();
                return new ArrayList<>();
            } else { // dir exists already
                File saveFile = new File(FILE_PATH);
                if (saveFile.createNewFile()) { // file doesn't exist yet -> create new file
                    assert saveFile.exists();
                    System.out.println("Save file was not found, new file created: " + saveFile.getName());
                    return new ArrayList<>();
                } else {
                    assert saveFile.exists();
                    System.out.println("Existing file loaded");
                    Scanner sc = new Scanner(saveFile);
                    ArrayList<Task> loadedTaskList = new ArrayList<>();
                    while (sc.hasNextLine()) {
                        String currentTask = sc.nextLine();
                        if (!currentTask.equals("")) {
                            loadedTaskList.add(textToTask(currentTask));
                        }
                    }
                    sc.close();
                    return loadedTaskList;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to open save file, tasks cannot be saved");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Update save file with the specified task list
     * @param taskList to replace current task list
     */
    public void updateSaveFile(TaskList taskList) {
        try {
            clearSaveFile();
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            for (Task t : taskList.getTaskList()) {
                fileWriter.write(t.getSaveText() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while trying to write to save file");
            e.printStackTrace();
        }
    }

    /**
     * Clear the save file
     */
    public void clearSaveFile() {
        try {
            FileWriter fileEraser = new FileWriter(FILE_PATH);
            fileEraser.write("");
            fileEraser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Task textToTask(String saveText) {
        if (saveText.startsWith("T | ")) {
            String taskName = saveText.substring(8);
            Todo todoTask = new Todo(taskName);
            if (saveText.charAt(4) == '1') {
                todoTask = todoTask.setDone();
            }
            return todoTask;
        } else if (saveText.startsWith("D | ")) {
            int nameEndIndex = saveText.indexOf("/by ") - 1;
            String taskName = saveText.substring(8, nameEndIndex);
            String by = saveText.substring(nameEndIndex + 5);
            Deadline deadlineTask = new Deadline(taskName, by);
            if (saveText.substring(4, 5).equals("1")) {
                deadlineTask = deadlineTask.setDone();
            }
            return deadlineTask;
        } else {
            int nameEndIndex = saveText.indexOf("/at ") - 1;
            String taskName = saveText.substring(8, nameEndIndex);
            String at = saveText.substring(nameEndIndex + 5);
            Event eventTask = new Event(taskName, at);
            if (saveText.substring(4, 5).equals("1")) {
                eventTask = eventTask.setDone();
            }
            return eventTask;
        }
    }

    /**
     * If data directory exists, return false. Else if data directory does not exist, create new data directory and
     * return true
     * @return false if data directory exists and true if directory does not exist
     */
    private boolean isDirExist() {
        File dir = new File(DIR_PATH);
        return dir.isDirectory();
    }
}
