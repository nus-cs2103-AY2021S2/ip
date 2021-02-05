import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Scanner;

/**
 * Represents a file manager.
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
     * @param filePath  Path of file.
     * @param textToAdd Text to write into file.
     * @throws IOException If there are input or output errors.
     */
    public static void writeToNewFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
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
     * @param filePath   Path of file.
     * @param lineNumber Number of line to be deleted (1-based indexing).
     * @throws IOException If there are input or output errors.
     */
    public static void deleteLine(String filePath, int lineNumber, Storage storage) throws IOException {
        File inputFile = new File(filePath);
        File outputFile = new File("data/newDuke.txt");
        FileManager.writeToNewFile("data/newDuke.txt", "");

        Scanner sc = new Scanner(inputFile);
        int counter = 0;
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            counter++;
            if (counter != lineNumber) {
                storage.appendToFile("data/newDuke.txt", nextLine);
            }
        }

        outputFile.renameTo(inputFile);
    }

}
