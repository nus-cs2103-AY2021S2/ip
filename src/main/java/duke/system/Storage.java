package duke.system;

import duke.system.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ListItem;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an object that interacts with I/O according the file path provided,
 * imports list from local file with <code>loadData</code> and export using <code>writeData</code> accordingly
 */
public class Storage {
    private final String filePath;
    public Storage(String path){
        this.filePath = path;
    }

    /**
     * Reads the list and loop till the end of the file and add corresponding tasks.
     * @return a list of tasks that can be manipulated later
     * @throws DukeException.IOErrorException
     */
    public List<ListItem> loadData() throws DukeException.IOErrorException {
        List<ListItem> importedList;
        importedList = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] result = s.nextLine().split("\\|");
                switch (result[0]) {
                    case "T":
                        importedList.add(new Todo(result[2], result[1] == "1"));
                        break;
                    case "D":
                        importedList.add(new Deadline(result[2], result[3], result[1] == "1"));
                        break;
                    case "E":
                        importedList.add(new Event(result[2], result[3], result[1] == "1"));
                        break;
                }
            }
            s.close();
            return importedList;
        }catch(FileNotFoundException ex){
            throw new DukeException.IOErrorException();
        }
    }

    /**
     * write data to local file
     * @param input current task list in string form
     * @throws DukeException.IOErrorException
     */
    public void writeData(String input) throws DukeException.IOErrorException{
        String statusCode = "";
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(input);
            fw.close();
        }catch(IOException ex){
            throw new DukeException.IOErrorException();
        }
    }
}
