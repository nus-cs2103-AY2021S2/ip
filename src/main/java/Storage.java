import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException, DukeException {
        File file = new File(filePath);
        if (!file.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            file.createNewFile();
            System.out.println("     Welcome! New user :)");
            System.out.println("     What can I do for you?");
            System.out.println("    ____________________________________________________________");
        } else {
            scanFile();
            if (TaskList.tasks.size() == 0) {
                System.out.println("     You have no saved task!");
                System.out.println("     What can I do for you?");
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("     You have " + TaskList.tasks.size() + " saved tasks!");
                System.out.println("     What can I do for you?");
                System.out.println("    ____________________________________________________________");
            }
        }
        return TaskList.tasks;
    }

    public void scanFile() throws FileNotFoundException, DukeException {
        Ui ui = new Ui();
        File file = new File(filePath);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        Scanner fileScanner = new Scanner(file);
        int i = 0;
        while (fileScanner.hasNextLine()) {
            String type = fileScanner.next();
            fileScanner.next();
            String done = fileScanner.next();
            fileScanner.next();
            if (type.equals("T")) {
                String desc = fileScanner.next() + fileScanner.nextLine();
                TaskList.tasks.add(new ToDo(desc));
                if (done.equals("1")) {
                    TaskList.tasks.get(i).markAsDone();
                }
            } else if (type.equals("D")) {
                String desc = fileScanner.next();
                while (true) {
                    String curr = fileScanner.next();
                    if (curr.equals("|")) {
                        break;
                    }
                    desc += " " + curr;
                }
                String by = fileScanner.next();
                String time = fileScanner.next();
                TaskList.tasks.add(new Deadline(desc, LocalDate.parse(by), LocalTime.parse(time)));
                if (done.equals("1")) {
                    TaskList.tasks.get(i).markAsDone();
                }
            } else {
                String desc = fileScanner.next();
                while (true) {
                    String curr = fileScanner.next();
                    if (curr.equals("|")) {
                        break;
                    }
                    desc += " " + curr;
                }
                String at = fileScanner.next();
                String time = fileScanner.next();
                TaskList.tasks.add(new Event(desc, LocalDate.parse(at), LocalTime.parse(time)));
                if (done.equals("1")) {
                    TaskList.tasks.get(i).markAsDone();
                }
            }
            i++;
        }
    }

    public static void save() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            Task task = TaskList.tasks.get(i);
            if (task instanceof ToDo) {
                int isDone = 0;
                if (task.isDone) {
                    isDone = 1;
                }
                fw.write("T | " + isDone + " | " + task.description);
            } else if (task instanceof Event) {
                int isDone = 0;
                if (task.isDone) {
                    isDone = 1;
                }
                fw.write("E | " + isDone + " | " + task.description + " | " + ((Event) task).at + " "
                        + ((Event) task).time);
            } else {
                int isDone = 0;
                if (task.isDone) {
                    isDone = 1;
                }
                fw.write("D | " + isDone + " | " + task.description + " | " + ((Deadline) task).by + " "
                        + ((Deadline) task).time);
            }
            if (i != TaskList.tasks.size() - 1) {
                fw.write("\n");
            }
        }
        fw.close();
    }

    public static String format(String date) {
        if (date.charAt(1) == '/') {
            date = "0" + date;
        }
        if (date.charAt(4) == '/') {
            date = date.substring(0, 3) + "0" + date.substring(3);
        }
        return date;
    }
}
