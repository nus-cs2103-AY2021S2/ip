import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the accessing a file to write and read.
 */
public class FileAccessor {

    /**
     * Returns a ArrayList which has the tasks in the hard drive file specified by path appended to it.
     *
     * @param path Specify the path where the file with data exists.
     * @param tasks ArrayList to save the tasks from hard drive to.
     * @return ArrayList<Task> list of tasks saved in path.
     * @throws FileNotFoundException if file at path does not exist.
     * @throws IllegalArgumentException if data in file is not in the correct format.
     */
    public static ArrayList<Task> ReadFromTasks(String path, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(path);
        Scanner sc = new Scanner(f);
        while(sc.hasNext()) {
            String s = sc.nextLine();
            String[] split = s.split("\\|");// '|' is a special character, has a diff meaning also so need to escape
            // with backslash. but backslash also special character, so need to escape that too so another backslash.
            //or can also escape with [|]
            boolean isDone = false;
            if(split[1].equals("1")) {
                isDone = true;
            }
            //consider indexoutofbounds exception
            if(split[0].equals("T")) {
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
     * @param path Specify the path where the file with data exists.
     * @param tasks ArrayList of tasks to write to path.
     * @throws IOException if unable to write to path.
     */
    public static void WriteToTasks(String path, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(path);
        String s = "";
        for(Task task : tasks) {
            String done = "0";
            if(task.isDone) {
                done = "1";
            }
            //'\n' may not work in all OS, use the System.lineSeparator()
            if(task instanceof Todo) {
                Todo todo = (Todo)task;
                s = s + "T|" + done + "|" + todo.task + System.lineSeparator();
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline)task;
                s = s + "D|" + done + "|" + deadline.task + "|" + deadline.deadline + System.lineSeparator();
            } else {
                Event event = (Event)task;
                s = s + "E|" + done + "|" + event.task + "|" + event.event + System.lineSeparator();
            }
        }
        fw.write(s);//write all as one string outside or else will keep writing for each task, but somehow give
        // ioexception when put inside for loop?
        fw.close();//rmb to close
    }
}
