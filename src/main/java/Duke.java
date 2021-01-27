import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static List<Task> tasks = new ArrayList<>();
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, DukeException {
        greet();
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            file.createNewFile();
            System.out.println("     You have no saved task ");
            System.out.println("     What can I do for you?");
            System.out.println("    ____________________________________________________________");
        } else {
            scanFile();
            if (tasks.size() == 0) {
                System.out.println("     You have no saved task!");
                System.out.println("     What can I do for you?");
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("     You have " + tasks.size() + " saved tasks!");
                System.out.println("     What can I do for you?");
                System.out.println("    ____________________________________________________________");
            }
        }
        scanIn();
        save();
    }

    static public void scanFile() throws FileNotFoundException {
        File file = new File("data/duke.txt");

        Scanner fileScanner = new Scanner(file);
        int i = 0;
        while (fileScanner.hasNextLine()) {
            String type = fileScanner.next();
            fileScanner.next();
            String done = fileScanner.next();
            fileScanner.next();
            if (type.equals("T")) {
                String desc = fileScanner.next() + fileScanner.nextLine();
                tasks.add(new ToDo(desc));
                if (done.equals("1")) {
                    tasks.get(i).markAsDone();
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
                tasks.add(new Deadline(desc, by, time));
                if (done.equals("1")) {
                    tasks.get(i).markAsDone();
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
                tasks.add(new Event(desc, at, time));
                if (done.equals("1")) {
                    tasks.get(i).markAsDone();
                }
            }
            i++;
        }
    }

    static public void scanIn() throws DukeException {
        while (sc.hasNextLine()) {
            String key = sc.next();
            if (key.equals("bye")) {
                bye();
                break;
            } else {
                execute(key);
            }
        }
    }

    static public void save() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
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
            if (i != tasks.size() - 1) {
                fw.write("\n");
            }
        }
        fw.close();
    }

    static public void execute(String key) throws DukeException {
        if (key.equals("list")) {
            list();
        } else if (key.equals("done")) {
            int n = sc.nextInt();
            done(n);
        } else if (key.equals("todo")) {
            String desc = sc.nextLine();
            if (desc.length() > 0) {
                addTask(new ToDo(desc.substring(1)));
            } else {
                throw new DukeException("\n    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                        "    ____________________________________________________________");
            }
        } else if (key.equals("event") || key.equals("deadline")) {
            String desc = sc.next();
            while (true) {
                String curr = sc.next();
                if (!(curr.equals("/by") || curr.equals("/at"))) {
                    desc += " " + curr;
                } else {
                    break;
                }
            }
            String date = sc.next();
            String time = sc.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            if (key.equals("event")) {
                addTask(new Event(desc, date, time));
            } else {
                addTask(new Deadline(desc, date, time));
            }
        } else if (key.equals("delete")) {
            int n = sc.nextInt();
            delete(n);
        } else {
            throw (new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________"));
        }
    }

    static public void delete(int num) {
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n     " +
                tasks.get(num - 1) + "\n" +
                "     Now you have " + (tasks.size() - 1) + " tasks in the list.\n" +
                "    ____________________________________________________________");
        tasks.remove(num - 1);
    }

    static public void list() {
        String s = "";
        if (tasks.size() == 0) {
            System.out.println("    ____________________________________________________________\n     " +
                    "Here are the tasks in your list:\n    " +
                    "____________________________________________________________\n");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            s += (i + 1) + "." + tasks.get(i) + "\n";
            if (i != tasks.size() - 1) {
                s += "     ";
            }
        }
        System.out.println("    ____________________________________________________________\n     " +
                "Here are the tasks in your list:\n     " + s +
                "    ____________________________________________________________\n");
    }

    static public void addTask(Task task) {
        tasks.add(task);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    static public void done(int num) {
        Task task = tasks.get(num - 1);
        task.markAsDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       " + task + "\n" +
                "    ____________________________________________________________\n");
    }

    static public void bye() {
        System.out.println("    ____________________________________________________________\n     " +
                "Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }

    static public void greet() {
        System.out.println("    ____________________________________________________________\n     " +
                " ____        _        \n" +
                "     |  _ \\ _   _| | _____ \n" +
                "     | | | | | | | |/ / _ \\\n" +
                "     | |_| | |_| |   <  __/\n" +
                "     |____/ \\__,_|_|\\_\\___|\n\n     " +
                "Hello! I'm Duke :P");
    }
}
