import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filepath;

    public Storage() {
        this.filepath = "Duke.txt";
    }

    /**
     * Store the list of task into the file stated by the filepath.
     *
     * @param tasks list of tasks.
     * @throws DukeException if filepath is incorrect.
     */
    public void store(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filepath);
            String string;
            String task;
            String time;

            for (Task value : tasks) {
                string = value.toString();
                if (string.contains("(")) {
                    String[] str = string.split("\\(", 2);
                    task = str[0];
                    str = str[1].split("\\)", 2);
                    time = str[0];
                    fw.write(task + "/" + time + "\n");
                } else {
                    fw.write(string + "\n");
                }
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! There is an error in storing the file.");
        }
    }

    /**
     * Load the list of tasks from the file stated by the filepath.
     *
     * @return list of tasks.
     * @throws DukeException if file not found or corrupted.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filepath);
            Scanner scan = new Scanner(f);

            ArrayList<Task> list = new ArrayList<>();

            String string;
            String info;
            String temp;
            String time;

            char action;
            char done;

            Task task;

            while (scan.hasNext()) {
                string = scan.nextLine();
                action = string.charAt(1);
                done = string.charAt(4);
                String[] str = string.split("] ", 2);
                temp = str[1];

                if (temp.contains(" /")) {
                    str = temp.split(" /", 2);
                    info = str[0];
                    str = str[1].split(" ", 2);
                    time = str[1];
                } else {
                    info = temp;
                    time = "";
                }

                switch (action) {
                case 'T':
                    task = new Todo(info);
                    break;
                case 'D':
                    task = new Deadline(info, time);
                    break;
                case 'E':
                    task = new Event(info, time);
                    break;
                default:
                    throw new DukeException("OOPS!!! There is an error in loading the file.");
                }

                if (done == 'X') {
                    task.markAsDone();
                }
                list.add(task);
            }

            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS!!! There is no Duke.txt file.");
        }
    }

}
