package Storage;

import Exceptions.DukeException;
import Task.Task;
import Task.Todo;
import Task.Event;
import Task.Deadline;
import Utils.Command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        File f = new File(filePath);
        f.createNewFile();
        Scanner sc = new Scanner(f);

        while (sc.hasNext()) {
            String input = sc.nextLine().trim();

            if (!input.equals("")) {
                String[] tokens = input.split("\\|");
                Command taskType = Command.valueOf(tokens[0]);
                Task task;

                switch(taskType) {
                    case EVENT:
                        task = new Event(tokens[2], tokens[3]);
                        break;
                    case DEADLINE:
                        task = new Deadline(tokens[2], tokens[3]);
                        break;
                    case TODO:
                        task = new Todo(tokens[2]);
                        break;
                    default:
                        throw new DukeException("Sorry something when wrong loading your safe file :(");
                }

                if (tokens[1].equals("true")) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws DukeException {
        File f = new File(filePath);
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath);
            StringBuilder sb = new StringBuilder();

            for (Task task : tasks)  {
                sb.append(task.serialise()).append('\n');
            }

            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong trying to save your data :(");
        }
    }
}
