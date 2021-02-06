import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Handles the reading and writing of information to an external file.
 */
public class Storage {
    private String filepath;

    private ArrayList<Task> tasks;
    
    private Parser parser;

    Storage(String filepath) {
        this.filepath = filepath;
        tasks = new ArrayList<>();
        parser = new Parser();
    }

    /**
     * Reads the existing txt file and stores the text into Task objects.
     * If the file does not exist, it creates a new file.
     * @return the list of tasks that contains the task objects.
     */
    public ArrayList<Task> load() {
        try {
            File myFile = new File("duke.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists");
                Scanner myReader = new Scanner(myFile);
                while (myReader.hasNextLine()) {
                    String type = myReader.nextLine();
                    if (type.contains("[T]")) {
                        String description = parser.parseTodoDescription(type);
                        Task toAdd = new Todo(description);
                        if (type.contains("\u2713")) {
                            toAdd.markAsDone();
                        }
                        tasks.add(toAdd);
                    } else if (type.contains("[D]")) {
                        String description = parser.parseDeadlineEventDescription(type);
                        String dateString = parser.parseDateTimeDeadline(type);
                        LocalDate date = LocalDate.parse(dateString,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Task toAdd = new Deadline(description, date);
                        if (type.contains("\u2713")) {
                            toAdd.markAsDone();
                        }
                        tasks.add(toAdd);
                    } else if (type.contains("[E]")) {
                        String description = parser.parseDeadlineEventDescription(type);
                        String timeString = parser.parseDateTimeEvent(type);
                        LocalDate time = LocalDate.parse(timeString,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Task toAdd = new Event(description, time);
                        if (type.contains("\u2713")) {
                            toAdd.markAsDone();
                        }
                        tasks.add(toAdd);
                    }
                }
                myReader.close();
            }
        } catch (IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Saves the current information into a txt file.
     * @throws FileNotFoundException
     */
    public void save() throws FileNotFoundException {
        PrintWriter myWriter = new PrintWriter("duke.txt");
        for (Task t : TaskList.getTasklist()) {
            myWriter.println(t.toString());
        }
        myWriter.flush();
        myWriter.close();

    }
}