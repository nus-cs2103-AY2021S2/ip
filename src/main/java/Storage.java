import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * The Storage class is for loading and saving the list of tasks
 * to the local hard disk.
 */
public class Storage {
    private File txtFile;

    public Storage(String filePath) throws IOException {
        this.txtFile = new File(filePath);
        if (txtFile.createNewFile()) {
            System.out.println("File created!");
        } else {
            System.out.println("File loaded!");
        }
    }

    /**
     * Loads list of tasks from the hard disk into the list of tasks
     * @return array list of tasks
     * @throws FileNotFoundException when the file cannot be found on the hard disk
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(this.txtFile);
        ArrayList<Task> tasksArrayList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String tempStr = sc.nextLine();
            String checkForTick = tempStr.substring(0, 5);
            String cmd = tempStr.substring(7);
            String typeOfTask = tempStr.substring(0, 3);
            switch(typeOfTask) {
                case "[T]":
                    ToDo toDoTask = new ToDo(cmd);
                    if (!checkForTick.contains(" ")) {
                        toDoTask.markAsDone();
                    }
                    tasksArrayList.add(toDoTask);
                    break;
                case "[D]":
                    String[] strArray = cmd.split("by:", 2);
                    String inst = strArray[0].substring(0, strArray[0].length() - 2);
                    String date = strArray[1].substring(0, strArray[1].length() - 1).trim();
                    try {
                        Date deadlineDate = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy").parse(date);
                        Deadline deadlineTask = new Deadline(inst + " ", deadlineDate);
                        if (!checkForTick.contains(" ")) {
                            deadlineTask.markAsDone();
                        }
                        tasksArrayList.add(deadlineTask);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    break;
                case "[E]":
                    String[] stringArray = cmd.split("at:", 2);
                    String instruction = stringArray[0].substring(0, stringArray[0].length() - 2);
                    String time = stringArray[1].substring(0, stringArray[1].length() - 1);
                    Event eventTask = new Event(instruction + " ", time);
                    if (!checkForTick.contains(" ")) {
                        eventTask.markAsDone();
                    }
                    tasksArrayList.add(eventTask);
                    break;
                case "[N]":
                    Note noteTask = new Note(cmd);
                    if (!checkForTick.contains(" ")) {
                        noteTask.markAsDone();
                    }
                    tasksArrayList.add(noteTask);
                    break;
            }
        }
        sc.close();
        return tasksArrayList;
    }

    /**
     * Saves the list of tasks on to the hard disk after Mister Duke
     * has been terminated
     * @param taskArrayList list of tasks
     * @throws IOException when there is a wrong/incomplete user input
     */
    public void save(ArrayList<Task> taskArrayList) throws IOException {
        FileWriter fwriter = new FileWriter(this.txtFile);
        for (Task task : taskArrayList) {
            if (task instanceof ToDo) {
                fwriter.write(task.toString() + "\n");
            } else if (task instanceof Deadline) {
                fwriter.write(task.toString() + "\n");
            } else if (task instanceof Event) {
                fwriter.write(task.toString() + "\n");
            } else {
                fwriter.write(task.toString() + "\n");
            }
        }
        fwriter.close();
    }
}