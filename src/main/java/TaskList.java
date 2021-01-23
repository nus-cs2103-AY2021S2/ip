import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskList {
    ArrayList<Task> list;

    public TaskList() throws DukeException{
        this.list = readTasksFromFile();
    }

    private static final String LIST_FILE_PATH = "storage/list.txt";

    private static ArrayList<Task> readTasksFromFile() throws DukeException {
        try {
            File tasks = new File(LIST_FILE_PATH);
            Scanner s = new Scanner(tasks);
            ArrayList<Task> newList = new ArrayList<>();
            while (s.hasNext()) {
                newList.add(Task.stringToTask(s.nextLine()));
            }
            return newList;
        } catch (FileNotFoundException | TaskException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private void writeTaskToFile(Task task) throws DukeException {
        try {
            File file = new File(LIST_FILE_PATH);
            FileWriter fw;
            if (file.exists()) {
                fw = new FileWriter(LIST_FILE_PATH, true);
            } else {
                fw = new FileWriter(LIST_FILE_PATH);
            }
            fw.write(task.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing task to storage/list");
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

    public void remove(int taskNumber) {

        Printer.printWithStyle(new String[] {
                "Noted. I've removed this task:",
                this.list.get(taskNumber - 1).toString(),
                "Now you have " + (this.list.size() - 1) + " tasks in the list."
        });
        this.list.remove(taskNumber - 1);
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
