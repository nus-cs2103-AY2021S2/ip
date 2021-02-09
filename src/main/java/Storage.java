import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with the loading and saving of tasks to the hard disk.
 */
public class Storage {
    protected String filePath;
    protected File saveFile;
    protected FileWriter filewriter;

    /**
     * Constructor for this Storage object.
     * @param   filePath  File path to save task to hard disk.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.saveFile = new File(filePath);
    }

    /**
     * Reads and adds saved tasks from the hard disk.
     * Creates a new save file if it does not exist.
     * @param  tasks The TaskList object containing all tasks.
     */
    public void initialise(TaskList tasks) throws IOException {
        if (!saveFile.exists()) {
            boolean isCreated = saveFile.createNewFile();
            if (isCreated) {
                System.out.println("New save file created!");
            }
        } else {
            Scanner fileScanner = new Scanner(saveFile);
            while (fileScanner.hasNext()) {
                String taskStr = fileScanner.nextLine();
                char taskType = taskStr.charAt(1);
                int len = taskStr.length();
                if (taskType == 'T') {
                    tasks.addTodo(taskStr.substring(7));

                } else if (taskType == 'D'){
                    int ind = taskStr.indexOf(" (by: ");
                    tasks.addDeadline(taskStr.substring(7, ind + 1), taskStr.substring(ind + 6, len - 1));

                } else if (taskType == 'E'){
                    int ind = taskStr.indexOf(" (at: ");
                    tasks.addEvent(taskStr.substring(7, ind + 1), taskStr.substring(ind + 6, len - 1));

                }
                if (taskStr.charAt(4) == 'X') {
                    tasks.getAtInd(tasks.getNumItems() - 1).markAsDone();
                }
            }
        }
    }

    /**
     * Saves all tasks in the TaskList to the harddrive.
     * @param  tasks The TaskList object containing all tasks.
     */
    public void finalise(TaskList tasks) throws IOException {
        assert !filePath.isBlank() : "No file path specified";
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.getNumItems(); i++) {
            fw.write(tasks.getAtInd(i).save() + "\n");
        }
        fw.close();
    }

}
