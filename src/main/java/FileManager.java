import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a file manager that can print file contents, write to a file or append to a file.
 */
public class FileManager {

    /**
     * Checks if the given filePath exists.
     *
     * @param filePath Path of file.
     * @return True if file exists and false if file does not exist.
     */
    public static boolean doesExist(String filePath) {
        Path path = Path.of(filePath);
        return Files.exists(path);
    }

    /**
     * Prints the contents of a file.
     *
     * @param filePath Path of file.
     * @throws FileNotFoundException If file is not found.
     */
    public static void printFileContents(String filePath) throws FileNotFoundException {

        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Create and writes to a new file.
     *
     * @param filePath Path of file.
     * @param textToAdd Text to write into file.
     * @throws IOException If there are input or output errors.
     */
    public static void writeToNewFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Append text to existing file.
     *
     * @param filePath Path of file.
     * @param textToAppend Text to append to existing file.
     * @throws IOException If there are input or output errors.
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        Path path = Path.of(filePath);
        if (Files.notExists(path)) {
            String folderPath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
            File folder = new File(folderPath);
            folder.mkdirs();
        }
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Delete the file at the given file path.
     *
     * @param filePath Path of file.
     */
    public static void deleteFile(String filePath) {
        File f = new File(filePath);
        f.delete();
    }

    /**
     * Deletes a specific line from a file.
     *
     * @param filePath Path of file.
     * @param lineNumber Number of line to be deleted (1-based indexing).
     * @throws IOException If there are input or output errors.
     */
    public static void deleteLine(String filePath, int lineNumber) throws IOException {
        File inputFile = new File(filePath);
        File outputFile = new File("data/newDuke.txt");
        FileManager.writeToNewFile("data/newDuke.txt", "");

        Scanner sc = new Scanner(inputFile);
        int counter = 0;
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            counter++;
            if (counter != lineNumber) {
                FileManager.appendToFile("data/newDuke.txt", nextLine);
            }
        }

        outputFile.renameTo(inputFile);
    }

    /**
     * Reads text file and returns task list with existing tasks in text file.
     *
     * @param filePath Path of file.
     * @return Task list of existing tasks in file.
     * @throws IOException If there are input or output errors.
     * @throws DukeException If description of task is not in the correct format.
     */
    public static TaskList textFileToArrayList(String filePath) throws IOException, DukeException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        TaskList taskList = new TaskList();

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            String taskTypeLetter = Character.toString(nextLine.charAt(1));
            if (taskTypeLetter.equals("T")) {
                taskList.addTask(TaskType.TODO, nextLine.substring(7), true);
            } else if (taskTypeLetter.equals("D")) {
                String[] nextLineArr = nextLine.substring(7).split(" \\(by: ");
                String description = nextLineArr[0] + " /by " + nextLineArr[1].substring(0, nextLineArr[1].lastIndexOf(")"));
                taskList.addTask(TaskType.DEADLINE, description, true);
            } else if (taskTypeLetter.equals("E")) {
                String[] nextLineArr = nextLine.substring(7).split(" \\(at: ");
                String description = nextLineArr[0] + " /at " + nextLineArr[1].substring(0, nextLineArr[1].lastIndexOf(")"));
                taskList.addTask(TaskType.EVENT, description, true);
            }
        }

        return taskList;
    }

}
