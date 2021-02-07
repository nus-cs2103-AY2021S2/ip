import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filepath;

    public Storage() {
        filepath = "Duke.txt";
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
     * @param stat statistic.
     * @return list of tasks.
     * @throws DukeException if file not found or corrupted.
     */
    public ArrayList<Task> load(Statistics stat) throws DukeException {
        File file;
        Scanner scan;

        try {
            file = new File(filepath);
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("There is no Duke.txt file currently.");
        }

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
                stat.changeStat(1, "todo");
                break;
            case 'D':
                task = new Deadline(info, time);
                stat.changeStat(1, "deadline");
                break;
            case 'E':
                task = new Event(info, time);
                stat.changeStat(1, "event");
                break;
            default:
                throw new DukeException("OOPS!!! There is an error in loading the file.");
            }

            if (done == 'X') {
                task.markAsDone();
                stat.changeStat(1, "done");
            }
            list.add(task);
        }

        return list;
    }

}
