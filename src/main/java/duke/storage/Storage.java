package duke.storage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.dukeexception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {
    /** The path of the file storing all tasks */
    private String filePath;

    /**
     * Class constructor.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from a given file if it exists, otherwise, creates a new file.
     * Transforms data to tasks.
     *
     * @return an arraylist of tasks.
     * @throws DukeException and IOException  If an input or output
     *                      exception occurred
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File myObj = new File("data/duke.txt");
        if (myObj.exists()) {
            System.out.println("exists");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Character type = data.charAt(0);
                boolean isDone = false;
                if (data.charAt(4) == '1') {
                    isDone = true;
                }
                data = data.substring(8);
                int mid = data.indexOf("|");
                String name;
                String date = null;
                int year = -999;
                int mon = -999;
                int day = -999;
                if (mid > 0) {
                    name = data.substring(0, mid - 1);
                    date = data.substring(mid + 2);
                    year = Integer.valueOf(date.substring(0, 4));
                    mon = Integer.valueOf(date.substring(5, 7));
                    day = Integer.valueOf(date.substring(8));
                } else {
                    name = data;
                }
                if (type == 'T') {
                    tasks.add(new ToDo(name, isDone));
                } else if (type == 'D') {
                    LocalDate d = LocalDate.of(year, mon, day);
                    tasks.add(new Deadline(name, d, isDone));
                } else if (type == 'E') {
                    LocalDate d = LocalDate.of(year, mon, day);
                    tasks.add(new Event(name, d, isDone));
                }
            }
            myReader.close();
        } else {
            myObj.createNewFile();
        }
        return tasks;
    }
}
