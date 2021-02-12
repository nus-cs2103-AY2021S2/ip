package duke.storage;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * FileReader deals with reading data from the file.
 */
class FileReader {
    static final String DONE_HEADER = "Done tasks: ";
    static final String PENDING_HEADER = "Pending tasks: ";
    static final String HEADER_IN_EMPTY_LIST = DONE_HEADER + System.lineSeparator()
            + PENDING_HEADER + System.lineSeparator();

    /**
     * Returns a list of tasks after reading the data inside the file.
     * If the file is not created yet, will create a new file and write
     * as if the list is empty.
     *
     * @param path The relative address of the data file.
     * @return A list of tasks.
     * @throws DukeException If an I/O error occurs.
     */
    List<Task> readFile(String path) throws DukeException {
        File f = getFile(path);
        return getTaskList(f);
    }

    private File createNewFile(File file, String path) throws DukeException {
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(path);
            fw.write(HEADER_IN_EMPTY_LIST);
            fw.close();
            
            return new File(path);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private File getFile(String path) throws DukeException {
        File file = new File(path);

        if (!file.exists()) {
            //create a new file and write as if the list is empty
            return createNewFile(file, path);
        }
        return file;
    }

    private List<Task> getTaskList(File f) throws DukeException {
        try {
            Scanner sc = new Scanner(f);
            List<Task> tasks = new ArrayList<>();
            boolean isDone = true;

            while (sc.hasNext()) {
                String currStr = sc.nextLine();

                boolean isHeader = currStr.equals(DONE_HEADER) || currStr.equals(PENDING_HEADER);
                if (isHeader) {
                    if (currStr.equals(PENDING_HEADER)) {
                        isDone = false;
                    }
                    continue;
                }

                Task t = toTask(currStr);
                assert t != null : "task cannot be null";
                
                if (isDone) {
                    t.markAsDone();
                }
                tasks.add(t);
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private Task toTask(String input) throws DukeException {
        Scanner sc = new Scanner(input);
        String command = sc.next();
        String[] args = sc.nextLine().split("[|]");

        String description = args[0].trim();
        String priority = args[1].trim();
        String preposition = null;
        LocalDate date = null;

        if (args.length == 3) {
            String second = args[2].trim();
            String[] prepositionAndDate = second.split("[\\s]");
            preposition = prepositionAndDate[0];
            date = LocalDate.parse(prepositionAndDate[1]);
        }
        return getTask(command, description, preposition, date, priority);
    }
    
    private Task getTask(String command, String description, String preposition
            , LocalDate date, String priority) throws DukeException {
        Task t = null;
        switch (command) {
        case "todo":
            t = new Todo(description);
            break;
        case "event":
            t = new Event(description, preposition, date);
            break;
        case "deadline":
            t = new Deadline(description, preposition, date);
            break;
        default:
            return t;
        }
        t.setPriority(priority);
        return t;
    }
}
