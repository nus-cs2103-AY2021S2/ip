import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class responsible for loading and saving data into hard drive.
 */
public class Storage {

    private File hardDrive;

    /**
     * Constructor method for storage class.
     * @throws IOException - exception thrown when there is error in file path.
     */
    public Storage() throws DukeWrongInputException {
        try {
            this.hardDrive = new File("duke.txt");
            if (hardDrive.createNewFile()) {
                System.out.println("Hard Disk created.");
            } else {
                System.out.println("Hard Disk loaded.");
            }
        } catch (IOException e) {
            throw new DukeWrongInputException("File not found!");
        }
    }

    /**
     * Loads hard drive from the file
     * @throws FileNotFoundException if file is not found at target location.
     */
    public List<Task> load() throws DukeWrongInputException {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.hardDrive);
            while (sc.hasNextLine()) {
                String[] entry = sc.nextLine().split(" / ");
                switch (entry[0]) {
                case "T":
                    tasks.add(new Todo(entry[2], Boolean.parseBoolean(entry[1])));
                    break;
                case "E":
                    tasks.add(new Event(entry[2], entry[3], Boolean.parseBoolean(entry[1])));
                    break;
                case "D":
                    LocalDate deadline = LocalDate.parse(entry[3], DateTimeFormatter.ofPattern("yyyy-mm-DD"));
                    tasks.add(new Deadline(entry[2], deadline, Boolean.parseBoolean(entry[1])));
                    break;
                default:
                    throw new IOException("File Error: wrong data format in hard drive");
                }
            }
            sc.close();
        } catch (IOException e) {
            throw new DukeWrongInputException("File Error: wrong data format in hard drive");
        }
        return tasks;
    }

    /**
     * Saves the information from the current list of tasks to the hard drive (duke.txt)
     * @param tasks current list of tasks
     * @throws IOException if there is an error with the file.
     */
    public void save(List<Task> tasks) throws DukeWrongInputException {
        try {
            FileWriter fw = new FileWriter(this.hardDrive);
            for (Task t : tasks) {
                if (t instanceof Todo) {
                    fw.write(String.format("T / %s / %s%n", t.getIsDone(), t.getDescription()));
                } else if (t instanceof Event) {
                    fw.write(String.format("E / %s / %s / %s%n", t.getIsDone(), t.getDescription(), (
                            (Event) t).getTimeslot()));
                } else if (t instanceof Deadline) {
                    fw.write(String.format("D / %s / %s / %s%n", t.getIsDone(), t.getDescription(), (
                            (Deadline) t).getDeadline().toString()));
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeWrongInputException("File Error: wrong data format in hard drive");
        }
    }

    /**
     * Returns the number of lines in the file.
     * @return returns the number of lines in the text file
     * @throws IOException if there is an error with the file.
     */
    public int getFileLinesCount() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.hardDrive));
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        reader.close();
        return lines;
    }
}
