import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class FileManager {
    File file;

    public FileManager(String pathname) {
        file = new File(pathname);
    }

    ListManager getList() throws IOException, UnknownCommandException {
        if (file.exists()) {
            return new ListManager(this.convertFileToList());
        } else {
            this.createFile();
            return new ListManager();
        }
    }

    List<Task> convertFileToList() throws FileNotFoundException, UnknownCommandException {
        Scanner sc = new Scanner(file);
        List<Task> list = new ArrayList<>();

        // Splice the lines in the file
        // and generate the appropriate Tasks
        while (sc.hasNext()) {
            String item = sc.nextLine();
            String[] split = item.split(" : ", 0);
            System.out.println(Arrays.toString(split));
            switch (split[0]) {
            case "T":
                list.add(new Todo(split[2], split[1].equals("1")));
                break;
            case "D":
                list.add(new Deadline(split[2], split[1].equals("1"), split[3]));
                break;
            case "E":
                list.add(new Event(split[2], split[1].equals("1"), split[3]));
                break;
            default:
                throw new UnknownCommandException();
            }
        }

        return list;
    }

    void createFile() throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    void appendToFile(Task task) throws IOException {
        // create a FileWriter in append mode
        FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
        fw.write(this.translateTask(task));
        fw.close();
    }

    String translateTask(Task task) {
        int isDone = task.isDone ? 1 : 0;
        if (task instanceof Todo) {
            return String.format("T : %d : %s\n", isDone, task.name);
        } else if (task instanceof Deadline) {
            return String.format("D : %d : %s : %s\n", isDone, task.name, ((Deadline) task).time);
        } else {
            return String.format("E : %d : %s : %s\n", isDone, task.name, ((Event) task).time);
        }
    }
}
