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

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.saveFile = new File(filePath);
    }

    public void finalise(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.getNumItems(); i++) {
            fw.write(list.getAtInd(i).save() + "\n");
        }
        fw.close();
    }

    public void initialise(TaskList list) throws IOException {
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
                    list.addTodo(taskStr.substring(7));

                } else if (taskType == 'D'){
                    int ind = taskStr.indexOf(" (by: ");
                    list.addDeadline(taskStr.substring(7, ind + 1), taskStr.substring(ind + 6, len - 1));

                } else if (taskType == 'E'){
                    int ind = taskStr.indexOf(" (at: ");
                    list.addEvent(taskStr.substring(7, ind + 1), taskStr.substring(ind + 6, len - 1));

                }
                if (taskStr.charAt(4) == 'X') {
                    list.getAtInd(list.getNumItems() - 1).markAsDone();
                }
            }
        }
    }

}
