import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String fileName;

    /**
     * return tasks loaded from fileName given.
     * @param fileName (file to load task from)
     * @return tasks, an arrayList of task
     * @throws DukeException e, an exception thrown for unable to create file
     * @throws DukeDeadlineException e, an exception regarding deadline of the task
     */

    public ArrayList<Task> load(String fileName) throws DukeException, DukeDeadlineException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(fileName);
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String strTask = s.nextLine();
                Task task = Parser.parseForText(strTask);
                assert task != null;
                tasks.add(task);
            }
            this.fileName = fileName;
            s.close();
        } catch (IOException e) {
            throw new DukeException("Unable to create file");
        } catch (DukeDeadlineException e) {
            throw e;
        }
        return tasks;
    }

    /**
     * Saving tasks into file given during loaded.
     * @param taskList , ArrayList of tasks to be saved
     */
    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(this.fileName);
            for (Task t : taskList) {
                fw.write(t.save());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File cannot be opened");
        }
    }
}
