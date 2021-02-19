package oracle;

import entry.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    /**
     * @param filePath: filePath of the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return List of String lines corresponding to the various tasks from the data file. Each line corresponds
     * to a task. Throws file not found error, but handling of the error is done by the Oracle object
     * @throws FileNotFoundException to be handled by Oracle
     */
    public List<String> load() throws FileNotFoundException {
        ArrayList<String> res = new ArrayList<>();
        //load file if available
        File store = new File(filePath);
        Scanner myReader = new Scanner(store);
        while (myReader.hasNextLine()) {
            res.add(myReader.nextLine());
        }
        myReader.close();
        return res;
    }

    /**
     * Store the tasks in the txt file. Format of the text file is handled by the Task classes
     * @param tasks: this is the arraylist used during runtime, to be converted to text file.
     */
    public void store(ArrayList<Task> tasks) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (Task task : tasks) {
                myWriter.write(task.toStorage() + '\n');
            }
            myWriter.close();
        } catch (IOException ignored) {
        }
    }
}
