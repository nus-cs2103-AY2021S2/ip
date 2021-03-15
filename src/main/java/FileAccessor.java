import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Represents the accessing a file to write and read.
 */
public class FileAccessor {
    //File f = new File("./");
    //System.out.println(f.getAbsolutePath());
    //to get the path to see which path java is looking
    private static final String FOLDER_PATH = "./data/";
    private static final String FILE_NAME = "All Tasks.txt";

    public static String getFolderPath() {
        return FOLDER_PATH;
    }

    public static String getFileName() {
        return FILE_NAME;
    }

    /**
     * Returns a ArrayList which has the tasks in the hard drive file specified by path appended to it.
     *
     * @param tasks ArrayList to save the tasks from hard drive to.
     * @return ArrayList of tasks saved in path.
     * @throws FileNotFoundException if file at path does not exist.
     * @throws IllegalArgumentException if data in file is not in the correct format.
     */
    public static ArrayList<Task> readFromTasks(ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File(FOLDER_PATH + FILE_NAME);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] split = input.split("\\|");
            //'|' is a special character, has a diff meaning also so need to escape
            //with backslash. but backslash also special character, so need to escape that too so another backslash.
            //or can also escape with [|]
            boolean isDone = false;
            if (split[1].equals("1")) {
                isDone = true;
            }
            //consider indexoutofbounds exception
            if (split[0].equals("T")) {
                tasks.add(new Todo(split[2], isDone));
            } else if (split[0].equals("E")) {
                tasks.add(new Event(split, isDone));
            } else if (split[0].equals("D")) {
                tasks.add(new Deadline(split, isDone));
            } else {
                System.out.println(split[0]);
                throw new IllegalArgumentException();
            }
        }
        return tasks;
    }

    //assume in correct format
    /**
     * Writes the data from tasks to the path.
     *
     * @param tasks ArrayList of tasks to write to path.
     * @throws IOException if unable to write to path.
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FOLDER_PATH + FILE_NAME);
        String taskToWrite = "";
        for (Task task : tasks) {
            String done = "0";
            if (task.isDone) {
                done = "1";
            }
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                taskToWrite = taskToWrite + "T|" + done + "|" + todo.task + System.lineSeparator();
                //'\n' may not work in all OS, use the System.lineSeparator()
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                taskToWrite =
                        taskToWrite + "D|" + done + "|" + deadline.task + "|" + deadline.getDeadline()
                                + System.lineSeparator();
            } else if (task instanceof Event){
                Event event = (Event) task;
                taskToWrite =
                        taskToWrite + "E|" + done + "|" + event.task + "|" + event.getEvent() + System.lineSeparator();
            } else {
                assert false : "Wrong part of writeToFile code";
            }
        }
        fileWriter.write(taskToWrite);
        //write all as one string outside or else will keep writing for each task, but somehow give
        // ioexception when put inside for loop?
        fileWriter.close();
    }
}
