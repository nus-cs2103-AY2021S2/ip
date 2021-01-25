import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Storage {

    public String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> readTasksFromFile() throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] elements = line.replace(" ","" ).split("\\|", -2);
            if (elements[0].equals("D")) {
                boolean isDone = Boolean.parseBoolean(elements[1]);
                String description = elements[2];
                String by = elements[3];
                Task task = new Deadline(description, by);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            } else if (elements[0].equals("E")) {
                boolean isDone = Boolean.parseBoolean(elements[1]);
                String description = elements[2];
                String at = elements[3];
                Task task = new Event(description, at);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            } else if (elements[0].equals("T")) {
                boolean isDone = Boolean.parseBoolean(elements[1]);
                String description = elements[2];
                Task task = new ToDo(description);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }


        }
        return tasks;
    }

    public void writeTasksToFile(ArrayList<Task> tasks) throws IOException {

        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileWriter fw = new FileWriter(this.filePath);

        String fileString = "";

        for(Task task: tasks){
            fileString += task.fileString()+"\n";
        }
        fw.write(fileString);
        fw.close();
    }

}