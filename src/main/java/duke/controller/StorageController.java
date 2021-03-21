package duke.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.Duke;
import duke.model.exception.DukeException;
import duke.model.task.Deadline;
import duke.model.task.Event;
import duke.model.task.ListItem;
import duke.model.task.Todo;

/**
 * Represents an object that interacts with I/O according the file path provided,
 * imports list from local file with <code>loadData</code> and export using <code>writeData</code> accordingly
 */
public class StorageController {
    private Duke duke;
    private final String filePath;

    /**
     * Default constructor takes in Duke to interact and filepath to get the local file
     * @param duke
     * @param path local file path
     */
    public StorageController(Duke duke, String path) {
        this.duke = duke;
        this.filePath = path;
    }

    /**
     * Reads the list and loop till the end of the file and add corresponding tasks.
     *
     * @return a list of tasks that can be manipulated later
     * @throws DukeException.InputOutputErrorException
     */
    public List<ListItem> loadData() throws DukeException.InputOutputErrorException {
        List<ListItem> importedList;
        importedList = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            List<String> tagList = new ArrayList<>();
            while (s.hasNext()) {
                String[] result = s.nextLine().split("\\|");
                switch (result[0]) {
                case "T":
                    if (result.length == 4) {
                        tagList = Arrays.stream(result[3].split("#")).sorted().collect(Collectors.toList());
                    }
                    importedList.add(new Todo(result[2], result[1] == "1", tagList));
                    break;
                case "D":
                    if (result.length == 5) {
                        tagList = Arrays.stream(result[4].split("#")).sorted().collect(Collectors.toList());
                    }
                    importedList.add(new Deadline(result[2], result[3], result[1] == "1", tagList));
                    break;
                case "E":
                    if (result.length == 5) {
                        tagList = Arrays.stream(result[4].split("#")).sorted().collect(Collectors.toList());
                    }
                    importedList.add(new Event(result[2], result[3], result[1] == "1", tagList));
                    break;
                default:
                    break;
                }
            }
            s.close();
            return importedList;
        } catch (FileNotFoundException ex) {
            throw new DukeException.InputOutputErrorException();
        }
    }

    /**
     * write data to local file after calling method to convert current list to string
     * @throws DukeException.InputOutputErrorException when IOException is caught
     */
    public void writeNewListToLocal() throws DukeException.InputOutputErrorException {
        String statusCode = "";
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            String input = duke.getListController().printListAsStringForIO();
            fw.write(input);
            fw.close();
        } catch (IOException ex) {
            throw new DukeException.InputOutputErrorException();
        }
    }
}
