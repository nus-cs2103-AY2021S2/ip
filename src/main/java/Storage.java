import java.io.*;
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
     * @param filePath file path for the hard drive file.
     * @throws IOException - exception thrown when there is error in file path.
     */
    public Storage(String filePath) throws IOException {
        this.hardDrive = new File(filePath);
        if (hardDrive.createNewFile()) {
            System.out.println("Hard Disk created.");
        } else {
            System.out.println("Hard Disk loaded.");
        }
    }

    /**
     * Loads hard drive from the file
     * @throws FileNotFoundException if file is not found at target location.
     */
    public List<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(this.hardDrive);
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] entry = sc.nextLine().split(" / ");
            switch (entry[0]){
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
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Saves the information from the current list of tasks to the hard drive (duke.txt)
     * @param tasks current list of tasks
     * @throws IOException if there is an error with the file.
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.hardDrive);
        for (Task t: tasks) {
            if (t instanceof Todo) {
                fw.write(String.format("T / %s / %s%n", t.getIsDone(), t.getDescription()));
            } else if (t instanceof Event) {
                fw.write(String.format("E / %s / %s / %s%n", t.getIsDone(), t.getDescription(),
                        ((Event) t).getTimeslot()));
            } else if (t instanceof Deadline) {
                fw.write(String.format("D / %s / %s / %s%n", t.getIsDone(), t.getDescription(),
                        ((Deadline) t).getDeadline().toString()));
            }
        }
        fw.close();
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