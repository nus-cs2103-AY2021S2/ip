import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    public String pathString;
    public StringBuffer stringBufferOfData;
    public int numTasks;

    public Storage(String pathString) {
        this.pathString = pathString;
        stringBufferOfData = new StringBuffer();
        numTasks = 0;
    }

    /**
     * Creates data directory and text file.
     *
     * @throws IOException for createDirectories and createFile methods.
     */
    public void createFile() throws IOException {
        Path dirPath = Paths.get(System.getProperty("user.dir") + "/data");
        Files.createDirectories(dirPath);
        Files.createFile(Paths.get(pathString));
    }

    /**
     * Scans file for any existing tasks,
     * parses data to separate task type, boolean isDone and description,
     * adds ToDos/Deadlines/Events objects to arraylist of tasks.
     * Adds data to a StringBuffer.
     * Increments number of tasks accordingly.
     *
     * @param tasks arraylist to add tasks to.
     * @throws FileNotFoundException if file does not exist yet.
     */
    public void loadFileContents(ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File(pathString);
        Scanner scanFile = new Scanner(file);

        while (scanFile.hasNext()) {
            String fileData = scanFile.nextLine();
            if (numTasks <= 0) {
                stringBufferOfData.append(fileData);
            } else {
                stringBufferOfData.append("\n").append(fileData);
            }

            String[] dataArr = fileData.split(" \\| ", 4);
            String taskType = dataArr[0];
            String isDone = dataArr[1];
            String desc = dataArr[2];

            switch (taskType) {
            case "T":
                ToDos newTodo = new ToDos(desc, isDone.equals("1"));
                tasks.add(newTodo);
                break;
            case "D":
                LocalDate by = LocalDate.parse(dataArr[3]);
                Deadlines newDeadline = new Deadlines(desc, by, isDone.equals("1"));
                tasks.add(newDeadline);
                break;
            case "E":
                LocalDate at = LocalDate.parse(dataArr[3]);
                Events newEvent = new Events(desc, at, isDone.equals("1"));
                tasks.add(newEvent);
                break;
            }
            numTasks++;
        }
    }

    /**
     * Writes or appends to file
     * depending on whether there is existing data in the file.
     * Appends text to StringBuffer.
     *
     * @param data text to be added to the file.
     * @throws IOException for FileWriter.
     */
    public void addToFile(String data) throws IOException {
        FileWriter fw;
        if (numTasks <= 0) {
            fw = new FileWriter(pathString);
            fw.write(data);
            stringBufferOfData.append(data);
        } else {
            fw = new FileWriter(pathString, true);
            fw.write(System.lineSeparator() + data);
            stringBufferOfData.append("\n").append(data);
        }

        fw.close();
    }

    /**
     * Replaces text in StringBuffer
     * and writes to the file.
     *
     * @param before text to be replaced.
     * @param after new replacement text.
     * @throws IOException for FileWriter.
     */
    public void modifyFile(String before, String after) throws IOException {
        assert(stringBufferOfData.length() > 0);

        int startIndex = stringBufferOfData.indexOf(before);
        int endIndex = startIndex + before.length();
        stringBufferOfData.replace(startIndex, endIndex, after);

        FileWriter fw = new FileWriter(pathString);
        fw.write(stringBufferOfData.toString());
        fw.close();
    }

    /**
     * Deletes specified text from StringBuffer,
     * and writes to the file.
     *
     * @param data text to be deleted.
     * @throws IOException for FileWriter.
     */
    public void deleteFromFile(String data) throws IOException {
        assert(stringBufferOfData.length() > 0);

        int startIndex = stringBufferOfData.indexOf(data);
        int endIndex = startIndex + data.length() + 1;
        stringBufferOfData.delete(startIndex, endIndex);

        FileWriter fw = new FileWriter(pathString);
        fw.write(stringBufferOfData.toString());
        fw.close();
    }
}
