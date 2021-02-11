import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to deal with loading and writing files.
 * @author Zhang Peng
 * @version 28 Jan 2021
 */
public class Storage {
    public Storage() {
    }
    /**
     * This is the method to load file from disk
     * @param arrayList takes in the arrayList
     * @param s takes in the a scanner to scan
     */
    public void loadingFile(ArrayList<Task> arrayList, Scanner s) {
        while (s.hasNextLine()) {
            String line = s.nextLine();
            assert line != null;
            if (line.contains("[D]")) {
                containsD(arrayList, line);
            } else if (line.contains("[E]")) {
                containsE(arrayList, line);
            } else if (line.contains("[T]")) {
                containsT(arrayList, line);
            }
        }
    }

    private void containsT(ArrayList<Task> arrayList, String line) {
        Task task = new Todo(line.substring(7));
        if (line.charAt(4) == 'X') {
            task.isDone = true;
        }
        task.index = arrayList.size() + 1;
        arrayList.add(task);
    }

    private void containsE(ArrayList<Task> arrayList, String line) {
        String[] parts = line.split("at");
        String part1 = parts[0];
        String part2 = parts[1];
        Task task = new Event(part1.substring(7, part1.length() - 1),
                part2.substring(2, part2.length() - 1));
        if (part1.charAt(4) == 'X') {
            task.isDone = true;
        }
        task.index = arrayList.size() + 1;
        arrayList.add(task);
    }

    private void containsD(ArrayList<Task> arrayList, String line) {
        String[] parts = line.split("by");
        String part1 = parts[0];
        String part2 = parts[1];
        Task task = new Deadline(part1.substring(7, part1.length() - 1),
                part2.substring(2, part2.length() - 1));
        if (part1.charAt(4) == 'X') {
            task.isDone = true;
        }
        task.index = arrayList.size() + 1;
        arrayList.add(task);
    }

    /**
     * This is the the method for saving changes to the disk
     * @param arrayList takes in the arrayList
     * @param path specifies to file path to write to
     */
    public void savingFile(ArrayList<Task> arrayList, String path) {
        assert path != null;
        try {
            savingToTheFile(arrayList, path);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void savingToTheFile(ArrayList<Task> arrayList, String path) throws IOException {
        int i = 0;
        if (arrayList.size() == 0) {
            System.out.print("");
        } else if (arrayList.size() == 1) {
            writeToFile(path, arrayList.get(i) + System.lineSeparator());
        } else {
            addingToFile(arrayList, path, i);
        }
    }

    private void addingToFile(ArrayList<Task> arrayList, String path, int i) throws IOException {
        writeToFile(path, arrayList.get(i) + System.lineSeparator());
        i++;
        while (i <= arrayList.size() - 1) {
            appendToFile(path, arrayList.get(i) + System.lineSeparator());
            i++;
            // append to file with in the writetofile.
        }
    }

    /**
     * This is the the method for writing to file
     * @param path specifies the path to the file
     * @param s specifies text to add
     * @return Nothing.
     */

    private static void writeToFile(String path, String s) throws IOException {
        assert path != null;
        assert s != null;
        FileWriter fw = new FileWriter(path);
        fw.write(s);
        fw.close();
    }
    /**
     * This is the the method for appending text to file
     * @param path specifies the path to the file
     * @param textToAppend specifies text to append
     * @return Nothing.
     */
    private static void appendToFile(String path, String textToAppend) throws IOException {
        assert path != null;
        assert textToAppend != null;
        FileWriter fw = new FileWriter(path, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
