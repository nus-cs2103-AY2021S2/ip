import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String DEFAULT_STORAGE_FILE = "data/duke.txt";
    public String filePath;
    public File f;

    public Storage(String filePath) throws DukeException {
        if (!isValidFileExtension(filePath)) {
            throw new DukeException("\t\tFile should end with .txt extension");
        }
        this.filePath = filePath;
        this.f = new File(filePath);

    }

    public Storage() {
        this.filePath = "data";
        this.f = new File(filePath, "duke.txt");
    }

    public boolean isValidFileExtension(String filePath) {
        return filePath.endsWith(".txt");
    }

    public ArrayList<Task> loadFile() throws DukeException {

        ArrayList<Task> taskArr = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.f);
            while (sc.hasNext()) {
                taskArr.add(stringToTask(sc.nextLine()));
            }
        }
        catch (FileNotFoundException e) {
            throw new DukeException("\t\tFile not found!");
        }
        return taskArr;

    }

    public void saveFile(ArrayList<Task> TaskArr) throws DukeException {
        try {
            FileWriter fw = new FileWriter(DEFAULT_STORAGE_FILE);
            for (Task t : TaskArr) {
                fw.write(taskToString(t) + "\n");
            }
            fw.close();
        }
        catch (IOException e) {
            throw new DukeException("\t\tError saving the Tasks into a file");
        }
    }

    public Task stringToTask(String task) throws DukeException{
        String arr[] = task.split(", ");
        Task result;
        if (arr.length == 0) {
            throw new DukeException("\t\tRead invalid task from the file");
        }
        String taskType = arr[0];

        if (taskType.equals("T")) {
            if (arr.length != 3) {
                throw new DukeException("\t\tRead invalid Todo task from the file");
            }
            result = new ToDo(arr[2]);
            if (Integer.valueOf(arr[1]) == 1) {
                result.isDone = true;
            }
            return result;
        } else if (taskType.equals("D")) {
            if (arr.length != 4) {
                throw new DukeException("\t\tRead invalid Deadline task from the file");
            }
            result = new Deadline(arr[2], arr[3]);
            if (Integer.valueOf(arr[1]) == 1) {
                result.isDone = true;
            }
        } else if (taskType.equals("E")) {
            if (arr.length != 4) {
                throw new DukeException("\t\tRead invalid Event task from the file");
            }
            result = new Deadline(arr[2], arr[3]);
            if (Integer.valueOf(arr[1]) == 1) {
                result.isDone = true;
            }
        } else {
            throw new DukeException("\t\tRead invalid Task type");
        }

        return result;
    }

    public String taskToString(Task task) {
        int num = 0;
        String result = "";
        if (task.isDone) {
            num = 1;
        }

        if (task instanceof Event) {
            result = "E, " + num
                    + ", " + task.taskDescription
                    + ", " + ((Event) task).at;
        } else if (task instanceof Deadline){
            result = "D, " + num
                    + ", " + task.taskDescription
                    + ", " + ((Deadline) task).by;
        } else if (task instanceof ToDo) {
            result = "T, " + num
                    + ", " + task.taskDescription;
        }
        return result;

    }
}
