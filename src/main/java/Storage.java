import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage() {
        this.file = new File("/Users/sidneychong/Desktop/CS2103/iP/PreviousTaskList.txt");
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
        System.out.println(isSavedHistory());
        if (isSavedHistory()) {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String current = s.nextLine().toLowerCase();
                System.out.println(current);
                assert current != null : "current at initialise in Storage";
                if (current.contains("todo")) {
                    Task task = Todo.readTaskFromStorage(current);
                    tasklist.add(task);
                } else if (current.contains("deadline")) {
                    tasklist.add(Deadline.readTaskFromStorage(current));
                } else if (current.contains("event")) {
                    tasklist.add(Event.readTaskFromStorage(current));
                } else {
                    if (s.hasNext()) { System.out.println("reached");
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
        tasklist.forEach(x -> {
            try {
                fw.write(x.saveInStorageAs() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fw.close();
    }
}
