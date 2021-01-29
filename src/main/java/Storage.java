import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads and writes task list file.
 * If the given file path leads to an empty file, Storage will write a new file in
 * the given path.
 */
public class Storage {

    /** task list file. */
    private File file;

    /** directory path of the task list file. */
    private String filePath;

    /**
     * Initializes Storage with a file path to read a file.
     *
     * @param filePath path of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    /**
     * Reads the file from filePath.
     *
     * @return An ArrayList of type Task containing tasks.
     * @throws DukeException if no file found at given filePath.
     */
    public ArrayList<Task> readFile() throws DukeException {

        ArrayList<Task> output = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);

            Task toAdd;

            while (scanner.hasNext()) {
                String taskLine = scanner.nextLine();
                String[] split = taskLine.split(" ");
                String taskType = split[0];
                String taskDone = split[1];
                boolean isDone = taskDone.equals("1");
                String task = "";

                int counter;

                outerloop:
                for (counter = 2; counter < split.length; counter++) {
                    if (split[counter].startsWith("(")) {
                        break outerloop;
                    } else {
                        if (counter == 1) {
                            task = task + split[counter];
                        } else {
                            task = task + " " + split[counter];
                        }
                    }
                }

                if (!taskType.equals("T")) {
                    String fullDate, day, month, year;
                    counter++;
                    day = split[counter];
                    counter++;
                    month = split[counter];
                    counter++;
                    year = split[counter];
                    fullDate = day + " " + month + " " + year;
                    LocalDate date = LocalDate.parse(fullDate, DateTimeFormatter.ofPattern("d MMM yyyy"));

                    counter++;
                    LocalTime time = LocalTime.parse(split[counter], DateTimeFormatter.ofPattern("HHmm"));

                    if (taskType.equals("E")) {
                        toAdd = new Event(task, date, time);
                    } else {
                        toAdd = new Deadline(task, date, time);
                    }

                } else {
                    toAdd = new ToDo(task);
                }

                if (isDone) {
                    toAdd.completeTask();
                }

                output.add(toAdd);
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("loading error");
        }

        return output;
    }

    /**
     * Writes into the file from a given list of tasks.
     *
     * @param taskList List of tasks to write into the file.
     */
    public void writeFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            String toWrite = "";
            ArrayList<Task> arr = taskList.getTaskArrayList();

            for (int i = 0; i < arr.size(); i++) {
                if (i == arr.size() - 1) {
                    toWrite = toWrite + arr.get(i).taskStatus();
                } else {
                    toWrite = toWrite + arr.get(i).taskStatus() + "\n";
                }
            }

            fileWriter.write(toWrite);
            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
