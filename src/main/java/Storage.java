import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage() {
        this.file = new File("PreviousTaskList.txt");
    }

    public boolean isSavedHistory() {
        return this.file.exists();
    }

    /**
     * Takes in an empty TaskList and adds the stored tasks into it.
     *
     * @param tasklist
     * @throws FileNotFoundException
     */
    public void initialise(TaskList tasklist) throws FileNotFoundException {
        if (isSavedHistory()) {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String current = s.nextLine().toLowerCase();
                if (current.contains("todo")) {
                    Task task = Todo.readTask(current);
                    tasklist.add(task);
                } else if (current.contains("deadline")) {
                    tasklist.add(Deadline.readTask(current));
                } else if (current.contains("event")) {
                    tasklist.add(Event.readTask(current));
                } else {
                    if (s.hasNext()) {
                        current = s.nextLine();
                    } else {
                        throw new FileNotFoundException("History saved corrupted");
                    }
                }
            }
            s.close();
        }
    }

    /**
     * Takes a TaskList that has been added with various Task to store those tasks
     *
     * @param tasklist
     * @throws IOException
     */
    public void saveHistory(TaskList tasklist) throws IOException {
        FileWriter fw = new FileWriter("PreviousTaskList.txt");
        try {
            for (int i = 0; i < tasklist.size(); i++) {
                if (i == 0) {
                    fw.write(tasklist.write(i));
                } else {
                    fw.write(System.lineSeparator() + tasklist.write(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fw.close();
    }
}
