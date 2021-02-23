import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    private File file;
    /**
     * Constructor method
     * @param filePath of the saved file
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (file.createNewFile()) {
            System.out.println("file created!");
        } else {
            System.out.println("file loaded!");
        }
    }

    /*public static void saveTasks(TaskList list, File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task temp : list) {
            if (temp instanceof Deadline) {
                fw.write("D|" + temp.getStatusIcon() + "|" + temp.getDescription().trim() + "|"+ ((Deadline) temp).getDateString() + "\n");
            } else if (temp instanceof Event) {
                fw.write("E|" + temp.getStatusIcon() + "|" + temp.getDescription() + "|" +((Event) temp).getDatetime() + "\n");
            } else {
                fw.write("T|" + temp.getStatusIcon() + "|" + temp.getDescription() + "\n");
            }
        }
        fw.close();
    }*/

    public static void loadTasks(TaskList list, File f) throws IOException {
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            s =     s.trim();

            String[] strArray = s.split("\\|", 4);

            if (strArray[0].trim().equals("D")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(strArray[3],formatter);
                Deadline deadline = new Deadline(strArray[2].trim(), date);
                if (strArray[1].trim().equals("[Done]")) {
                    deadline.isDone = true;
                } else if (strArray[1].trim().equals("[Incompleted]")) {
                    deadline.isDone = false;
                }
                list.addTask(deadline);
            } else if (strArray[0].trim().equals("E")) {
                Event event = new Event(strArray[2].trim(), strArray[3].trim());
                if (strArray[1].trim().equals("[Done]")) {
                    event.isDone = true;
                } else if (strArray[1].trim().equals("[Incompleted]")) {
                    event.isDone = false;
                }
                list.addTask(event);
            } else {
                ToDo todo = new ToDo(strArray[2].trim());
                if (strArray[1].trim().equals("[Done]")) {
                    todo.isDone = true;
                } else if (strArray[1].trim().equals("[Incompleted]")) {
                    todo.isDone = false;
                }
                list.addTask(todo);
            }
        }
    }
}