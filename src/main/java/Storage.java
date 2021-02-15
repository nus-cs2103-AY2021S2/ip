import java.io.File;
import java.io.FileNotFoundException;
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
     * Constructs a storage object.
     *
     * @throws IOException - exception thrown when there is error in file path.
     */
    public Storage() throws DukeIoException {
        try {
            this.hardDrive = new File("duke.txt");
            if (hardDrive.createNewFile()) {
                System.out.println("Hard Disk created.");
            } else {
                System.out.println("Hard Disk loaded.");
            }
        } catch (IOException e) {
            throw new DukeIoException("File not found.");
        }
    }

    /**
     * Loads hard drive from the file.
     *
     * @throws DukeIoException if file is not found at target location or data is corrupted.
     */
    public List<Task> load() throws DukeIoException {
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
                    throw new DukeIoException("File Error: data corrupted in hard drive.");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeIoException("File error: File not found.");
        } catch (DukeIoException e) {
            throw new DukeIoException("File Error: data corrupted in hard drive");
        }
        return tasks;
    }

    /**
     * Saves the information from the current list of tasks to the hard drive (duke.txt)
     *
     * @param tasks current list of tasks.
     * @throws DukeIoException if there is an error when writing to file.
     */
    public void save(List<Task> tasks) throws IOException {

        FileWriter fw = new FileWriter(this.hardDrive);
        for (Task t : tasks) {
            if (t instanceof Todo) {
                fw.write(String.format("T / %s / %s%n", t.getIsDone(), t.getDescription()));
            } else if (t instanceof Event) {
                fw.write(String.format("E / %s / %s / %s%n", t.getIsDone(), t.getDescription(), (
                        (Event) t).getEventTime()));
            } else if (t instanceof Deadline) {
                fw.write(String.format("D / %s / %s / %s%n", t.getIsDone(), t.getDescription(), (
                        (Deadline) t).getDeadline().toString()));
            }
        }
        fw.close();
    }
}
