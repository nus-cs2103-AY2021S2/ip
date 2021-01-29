package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a class that stores and real files stored in the hard disk.
 */
public class Storage {

    private String fileUrl;

    Storage(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * Loads all tasks stored in the file at the specified fileUrl.
     *
     * @return ArrayList List containing all previously stored tasks.
     * @throws FileNotFoundException If the file cannot be found at the specified file url.
     */
    public ArrayList<String> load() throws FileNotFoundException {
        File storageFile = new File(fileUrl);
        Scanner sc = new Scanner(storageFile);
        ArrayList<String> data = new ArrayList<>();
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        return data;
    }

    /**
     * Stores and writes all tasks in the input list to file at the specified fileUrl.
     *
     * @param taskList ArrayList containing all current tasks.
     * @throws IOException If the tasks strings are not in the correct storage format.
     */
    public void store(ArrayList<String> taskList) throws IOException {
        FileWriter fw = new FileWriter(fileUrl);
        String data = taskList.size() == 0 ? "" : taskList.get(0);
        for (int i = 1; i < taskList.size(); i++) {
            data += System.lineSeparator() + taskList.get(i);
        }
        fw.write(data);
        fw.close();
    }

}
/*
todo
Handling file/folder not found exception
Handling IOException
*/
