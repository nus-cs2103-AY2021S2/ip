package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains static methods that deal with loading tasks from the file and
 * saving tasks in the file.
 */
class Storage {
    private static final String FILE_NAME = "./data/duke.txt";

    /**
     * If there is an existing text file on the local computer, this method
     * will load the text file. Otherwise it will create a new text file so
     * that the tasks can be saved on the local computer.
     *
     * @return The text file to save the tasks tn.
     * @throws IOException When there is an error in loading or creating the
     * text file.
     */
    public static File initialiseFile() throws IOException {
        File storageTextFile = new File(FILE_NAME);
        // Creates the folder if it is not on the local hard disk.
        new File("./data").mkdirs();
        storageTextFile.createNewFile();
        return storageTextFile;
    }

    /**
     * Updates the text file to match the input task list.
     *
     * @param taskList The corresponding task list which the text file is based on.
     * @throws IOException When there is an error in writing to the file.
     */
    public static void updateTextFile(ArrayList<Task> taskList) throws IOException {
        File storageTextFile = new File(FILE_NAME);
        FileWriter fileWriter = new FileWriter(storageTextFile);
        for (Task task : taskList) {
            if (task instanceof ToDo) {
                fileWriter.write(toDoToText((ToDo) task));
            } else if (task instanceof Event) {
                fileWriter.write(eventToText((Event) task));
            } else if (task instanceof Deadline) {
                fileWriter.write(deadlineToText((Deadline) task));
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    /**
     * Method to read an existing text file and convert it into a corresponding task list within
     * the Dukebot.
     *
     * @param storageTextFile The text file in which the task list is created from.
     * @param taskList The corresponding task list to copy the text file over.
     * @throws IOException When there is an error in reading the file.
     */
    public static void convert(File storageTextFile, ArrayList<Task> taskList)
            throws IOException {
        Scanner scanner = new Scanner(storageTextFile);
        while (scanner.hasNext()) {
            String[] parsedString = parseString(scanner.nextLine());
            taskList.add(parsedStringToTask(parsedString));
        }
        scanner.close();
    }

    private static String taskToText (Task task) {
        String taskText = "";
        if (task.isComplete()) {
            taskText += "1 | ";
        } else {
            taskText += "0 | ";
        }
        taskText += task.taskName;
        return taskText;
    }

    private static String deadlineToText (Deadline deadline) {
        String deadlineText = "D | ";
        deadlineText += taskToText(deadline);
        deadlineText += " | " + deadline.getDate();
        return deadlineText;
    }

    private static String eventToText (Event event) {
        String eventText = "E | ";
        eventText += taskToText(event);
        eventText += " | " + event.getDate();
        return eventText;
    }

    private static String toDoToText (ToDo toDo) {
        String toDoText = "T | ";
        toDoText += taskToText(toDo);
        return toDoText;
    }

    private static String[] parseString(String inputString) {
        String[] parsedString = new String[4];
        int counter = 0;
        parsedString[0] = "";
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == '|') {
                counter++;
                parsedString[counter] = "";
            } else if (inputString.charAt(i) != ' ' || counter >= 2) {
                parsedString[counter] += inputString.charAt(i);
            }
        }
        return parsedString;
    }

    private static Task parsedStringToTask (String[] parsedString) throws IOException {
        String typeOfTask = parsedString[0];
        boolean isCompleted = parsedString[1].equals("1");
        String taskName = parsedString[2].substring(1);
        if (typeOfTask.equals("T")) {
            return new ToDo(taskName, isCompleted);
        }
        LocalDate date = convertTextToDate(parsedString[3].substring(1));
        if (typeOfTask.equals("D")) {
            return new Deadline(taskName, isCompleted, date);
        } else if (typeOfTask.equals("E")) {
            return new Event(taskName, isCompleted, date);
        } else {
            throw new IOException("File is not in a supported format");
        }
    }

    private static LocalDate convertTextToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
