import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load() throws DukeException{
        try {
            File data = new File(filePath);
            Scanner sc = new Scanner(data);
            ArrayList<Task> savedTasks = new ArrayList<>();
            while(sc.hasNext()) {
                String[] line = sc.nextLine().split("#");
                switch (line[0]) {
                case "T":
                    ToDo savedToDo = new ToDo(line[2]);
                    if (line[1].equals("X")) {
                        savedToDo.setDone();
                    }
                    savedTasks.add(savedToDo);
                    break;
                case "D":
                    Deadline savedDeadline = new Deadline(line[2], line[3]);
                    if (line[1].equals("X")) {
                        savedDeadline.setDone();
                    }
                    savedTasks.add(savedDeadline);
                    break;
                case "E":
                    Event savedEvent = new Event(line[2], line[3]);
                    if (line[1].equals("X")) {
                        savedEvent.setDone();
                    }
                    savedTasks.add(savedEvent);
                }
            }
            return savedTasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("No saved tasks");
        }
    }

    void save(ArrayList<Task> tasks) throws DukeException{
        try {
            FileWriter fw = new FileWriter("duke.txt");
            for (Task t : tasks) {
                String currLine = t.getSymbol() + "#" + t.getStatus() + "#" + t.getName();
                if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    currLine += "#" + d.getTime().toString();
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    currLine += "#" + e.getTime().toString();
                }
                currLine += "\n";
                fw.write(currLine);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot save tasks" + e.getMessage());
        }
    }
}
