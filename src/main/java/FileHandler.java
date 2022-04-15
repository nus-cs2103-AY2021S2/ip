import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * A class that is responsible for the interactions with a text file (i.e
 * writing to the file and reading from the file)
 */

public class FileHandler {

    public FileHandler() { }

    /**
     * Reads a command containing a task and stores it in a task list
     * @param command a String in the proper format specifying the task to be stored
     * @param tasks the task list to store the task into
     * @return a task list with the new task added
     * @throws DukeException if the command is not "T" (for ToDo),
     * "D" (for Deadline) or "E" (for Event)
     */
    public TaskList handleFileCommand(String command, TaskList tasks) throws DukeException {
        String[] splitInputs = command.split(" # ");
        String typeOfCommand = splitInputs[0];
        if (typeOfCommand.equals("T")) {
            return addToDoFromFile(splitInputs, tasks);
        } else if (typeOfCommand.equals("D")) {
            return addDeadlineFromFile(splitInputs, tasks);
        } else if (typeOfCommand.equals("E")) {
            return addEventFromFile(splitInputs, tasks);
        } else {
            throw new DukeException("Pardon! No such command is allowed.");
        }
    }

    /**
     * Adds a <code>ToDo</code> task specified from the file in a proper format to the task list
     * @param splitInputs the split inputs from the full command in the file
     * @param tasks the task list where the <code>ToDo</code> is to be added into
     * @return a task list with the new <code>ToDo</code> added
     */
    public TaskList addToDoFromFile(String[] splitInputs, TaskList tasks) {
        ToDo newTask = new ToDo(splitInputs[2]);
        if (Integer.parseInt(splitInputs[1]) == 1) {
            newTask.markAsDone();
        }
        tasks = tasks.addByTask(newTask);
        return tasks;
    }

    /**
     * Adds a <code>Deadline</code> task specified from the file in a proper format to the task list
     * @param splitInputs the split inputs from the full command in the file
     * @param tasks the task list where the <code>Deadline</code> is to be added into
     * @return a task list with the new <code>Deadline</code> added
     */
    public TaskList addDeadlineFromFile(String[] splitInputs, TaskList tasks) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String dateAndTime = splitInputs[3];
        String date = dateAndTime.split(" ")[0];
        String time = dateAndTime.split(" ")[1];
        Deadline newDeadline = new Deadline(splitInputs[2], LocalDate.parse(date),
                LocalTime.parse(time, formatter));
        if (Integer.parseInt(splitInputs[1]) == 1) {
            newDeadline.markAsDone();
        }
        tasks = tasks.addByTask(newDeadline);
        return tasks;
    }

    /**
     * Adds an <code>Event</code> task specified from the file in a proper format to the task list
     * @param splitInputs the split inputs from the full command in the file
     * @param tasks the task list where the <code>Event</code> is to be added into
     * @return a task list with the new <code>Event</code> added
     */
    public TaskList addEventFromFile(String[] splitInputs, TaskList tasks) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String dateAndTime = splitInputs[3];
        String date = dateAndTime.split(" ")[0];
        String startTime = dateAndTime
                .split(" ")[1]
                .split("-")[0];
        String endTime = dateAndTime
                .split(" ")[1]
                .split("-")[1];
        Event newEvent = new Event(splitInputs[2], LocalDate.parse(date),
                LocalTime.parse(startTime, formatter),
                LocalTime.parse(endTime, formatter));

        if (Integer.parseInt(splitInputs[1]) == 1) {
            newEvent.markAsDone();
        }
        tasks = tasks.addByTask(newEvent);
        return tasks;
    }

    /**
     * Reads from a text file and stores all the tasks specified within
     * into a task list
     * @param tasks a task list to store the tasks into
     * @return a task list containing all the new tasks read from the file
     * @throws IOException
     * @throws DukeException if the file contains input of an invalid format
     */
    public TaskList readFromFile(TaskList tasks) throws IOException, DukeException {
        File directory = new File("data");
        //creating directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdir();
        }
        File f = new File("data/oui.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner scf = new Scanner(f);
        while (scf.hasNext()) {
            try {
                String nextLine = scf.nextLine();
                tasks = handleFileCommand(nextLine, tasks);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
        return tasks;
    }

    /**
     * Writes all the tasks contained within the task list into a text file.
     * @param list the task list that contains the tasks to be written
     *             into the text file
     * @throws IOException
     */
    public void writeToFile(TaskList list) throws IOException {
        File textFile = new File("data/oui.txt");
        assert textFile.exists() : "Data file does not exist";
        FileWriter fw = new FileWriter("data/oui.txt");
        PrintWriter pw = new PrintWriter("data/oui.txt");
        //before writing to it, clear file to make sure it is empty and no content is leftover from last run
        pw.print("");
        pw.close();
        String textToAdd = "";
        for (Task t : list.getList()) {
            textToAdd = textToAdd + t.generateText() + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }


}
