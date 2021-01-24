import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    ArrayList<Task> list;

    public TaskList() throws DukeException{
        this.list = readTasksFromFile();
    }

    private static final String LIST_FILE_PATH = "storage/";
    private static final String LIST_FILE = LIST_FILE_PATH + "list.txt";


    private static ArrayList<Task> readTasksFromFile() throws DukeException {
        File tasks = new File(LIST_FILE);
        Scanner s;
        try {
            s = new Scanner(tasks);
        } catch (FileNotFoundException e) {
            try {
                Path path = Paths.get(LIST_FILE_PATH);
                Files.createDirectories(path);
                tasks.createNewFile();
                s = new Scanner(tasks);
            } catch (IOException e2) {
                throw new DukeException("Error reading task list file. " + e2.getMessage());
            }
        }
        try {
            ArrayList<Task> newList = new ArrayList<>();
            while (s.hasNext()) {
                newList.add(Task.stringToTask(s.nextLine()));
            }
            return newList;
        } catch (TaskException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private void writeTaskToFile(Task task) throws DukeException {
        try {
            File file = new File(LIST_FILE);
            FileWriter fw;
            if (file.exists()) {
                fw = new FileWriter(LIST_FILE, true);
            } else {
                fw = new FileWriter(LIST_FILE);
            }
            fw.write(task.toString());
            fw.append(System.getProperty("line.separator"));
            fw.close();
        } catch (IOException | NullPointerException e) {
            throw new DukeException("Error writing task to storage/list " + e.getMessage());
        }
    }

    public void add(Task task) throws DukeException {
        this.list.add(task);
        writeTaskToFile(task);
        Printer.printWithStyle(new String[] {
                "Got it. I've added this task:",
                "    " + task.toString(),
                "Now you have " + this.list.size() + " tasks in the list."
        });
    }

    public void done(int taskNumber) {
        this.list.get(taskNumber - 1).done();
    }

    public void remove(int taskNumber) throws DukeException {

        Printer.printWithStyle(new String[] {
                "Noted. I've removed this task:",
                this.list.get(taskNumber - 1).toString(),
                "Now you have " + (this.list.size() - 1) + " tasks in the list."
        });
        this.list.remove(taskNumber - 1);
        //Rewrite all tasks
        try {
            FileWriter fw = new FileWriter(LIST_FILE);
            for (Task task : this.list) {
                fw.write(task.toString());
                fw.append(System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write tasks to file. " + e.getMessage());
        }

    }

    public void printList() {
        String[] printedArray = new String[this.list.size() + 1];
        printedArray[0] = "Here are the tasks in your list:";
        for (int i = 0; i < this.list.size(); i++) {
            String listEntry = String.valueOf(i + 1) + "." +
                    this.list.get(i).toString();
            printedArray[i + 1] = listEntry;
        }
        Printer.printWithStyle(printedArray);
    }
}
