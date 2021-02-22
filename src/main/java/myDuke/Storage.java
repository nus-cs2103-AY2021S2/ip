package myDuke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A storage that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from an input data file and populate them onto the taskList.
     * @throws FileNotFoundException if the input data file provided by the filePath is invalid.
     * @throws InvalidArgException if the params provided in the input file are insufficient (<3),
     * or excessive (>4).
     * @throws NumberFormatException if the String argument to be parsed into an integer is invalid.
     * @throws InvalidTypeException if the String argument for the task type is not "T", "E" or "D".
     * @throws DateTimeParseException if the String argument to be parsed into DateTime is invalid.
     * @returns a list of Tasks collated from the input data file.
     */
    List<Task> loadTasks() throws FileNotFoundException, InvalidArgException,
            NumberFormatException, InvalidTypeException, DateTimeParseException {

        File f = new File(this.filePath); // create a File for the given file path
        Scanner sc = new Scanner(f); // create a Scanner using the File as the source
        List<Task> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            // System.out.println(input);
            String[] inputArr = input.split(" @ ");
            if (inputArr.length < 3 && inputArr.length > 4) {
                throw new InvalidArgException("Paikia Bot: eh walao something is wrong with this input: "
                        + input + ", pls double check and rectify");
            }
            // System.out.println(inputArr.length);
            boolean isDone = Integer.parseInt(inputArr[1]) == 1;
            if (inputArr[0].equals("T")) {
                list.add(new ToDo(inputArr[2], isDone));
            } else if (inputArr[0].equals("E")) {
                list.add(new Event(LocalDate.parse(inputArr[3]), inputArr[2], isDone));
            } else if (inputArr[0].equals("D")) {
                list.add(new Deadline(LocalDate.parse(inputArr[3]), inputArr[2], isDone));
            }
        }
        sc.close();
        return list;

    }

    /**
     * Save tasks from the taskList and update the input data file.
     * @param list Tasklist from the MyDuke class.
     * @throws FileNotFoundException if there are I/O errors whilst creating the FileWriter object.
     */
    void saveToFile(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task t : list) {
            int doneStatus = t.isDone ? 1 : 0;
            if (t instanceof ToDo) {
                fw.write("T @ " + doneStatus + " @ " + t.info + System.lineSeparator());
            } else if (t instanceof Event) {
                Event e = (Event) t;
                fw.write("E @ " + doneStatus + " @ " + t.info + " @ " + e.date + System.lineSeparator());
            } else {
                Deadline d = (Deadline) t;
                fw.write("D @ " + doneStatus + " @ " + t.info + " @ " + d.deadline + System.lineSeparator());
            }
        }
        fw.close();
    }

    /**
     * Save tasks from the taskList and update the input data file in a new directory.
     * @throws FileNotFoundException if there is I/O errors whilst creating the FileWriter object.
     * @param list Tasklist from the MyDuke class.
     * @param newDir new String directory for the input data file to be saved.
     */
    void saveToFile(List<Task> list, String newDir) throws IOException {
        FileWriter fw = new FileWriter(newDir);
        for (Task t : list) {
            int doneStatus = t.isDone ? 1 : 0;
            if (t instanceof ToDo) {
                fw.write("T @ " + doneStatus + " @ " + t.info + System.lineSeparator());
            } else if (t instanceof Event) {
                Event e = (Event) t;
                fw.write("E @ " + doneStatus + " @ " + t.info + " @ " + e.date + System.lineSeparator());
            } else {
                Deadline d = (Deadline) t;
                fw.write("D @ " + doneStatus + " @ " + t.info + " @ " + d.deadline + System.lineSeparator());
            }
        }
        fw.close();
    }

}