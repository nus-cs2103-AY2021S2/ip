import java.time.LocalDate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String currdir = System.getProperty("user.dir");
    private static final Path path = Paths.get(currdir, "data");
    private static final Path file = Paths.get(currdir, "data", "duke.txt");

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        ArrayList<Task> tasks = duke.loadtasks();
        System.out.println("Hello! I'm Duke. What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        duke.chat(scanner, tasks);
    }

    abstract class Task {
        abstract Task done();
    }

    class ToDo extends Task {
        private String name;
        private boolean done;

        ToDo(String name) {
            this.name = name;
            this.done = false;
        }

        ToDo(String name, boolean done) {
            this.name = name;
            this.done = done;
        }

        @Override
        Task done() {
            return new ToDo(this.name, true);
        }

        @Override
        public String toString() {
            if (this.done) {
                return "[T][X] " + this.name;
            }
            return "[T][ ] " + this.name;
        }
    }

    class Deadline extends Task {
        private String name;
        private LocalDate deadline;
        private boolean done;

        Deadline(String name, String deadline) {
            this.name = name;
            this.deadline = LocalDate.parse(deadline);
            this.done = false;
        }

        Deadline(String name, String deadline, boolean done) {
            this.name = name;
            this.deadline = LocalDate.parse(deadline);
            this.done = done;
        }

        Deadline(String name, LocalDate deadline, boolean done) {
            this.name = name;
            this.deadline = deadline;
            this.done = done;
        }

        @Override
        Task done() {
            return new Deadline(this.name, this.deadline.toString(), true);
        }

        @Override
        public String toString() {
            if (this.done) {
                return String.format("[D][X] %s (by: %s)", this.name, this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            }
            return String.format("[D][ ] %s (by: %s)", this.name, this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }
    }

    class Event extends Task {
        private String name;
        private LocalDate duration;
        private boolean done;

        Event(String name, String duration) {
            this.name = name;
            this.duration = LocalDate.parse(duration);
            this.done = false;
        }

        Event(String name, String duration, boolean done) {
            this.name = name;
            this.duration = LocalDate.parse(duration);
            this.done = done;
        }

        Event(String name, LocalDate duration, boolean done) {
            this.name = name;
            this.duration = duration;
            this.done = done;
        }

        @Override
        Task done() {
            return new Event(this.name, this.duration.toString(), true);
        }

        @Override
        public String toString() {
            if (this.done) {
                return String.format("[E][X] %s (at: %s)", this.name, this.duration.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            }
            return String.format("[E][ ] %s (at: %s)", this.name, this.duration.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }
    }

    class NoSuchCommandException extends Exception {
        NoSuchCommandException() {
            super("I'm sorry, but I don't know what that means :(");
        }
    }

    class IncompleteCommandException extends Exception {
        IncompleteCommandException() {
            super("The description cannot be empty.");
        }
    }

    public void savetasks(ArrayList<Task> tasks) throws IOException {
        try {
            String string = "";
            for (int i = 0; i < tasks.size(); i++) {
                string += tasks.get(i) + "\n";
            }
            Files.writeString(file, string);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Task> loadtasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                if (!task.isEmpty()) {
                    if (task.startsWith("[T]")) {
                        boolean done = task.charAt(4) == 'X';
                        tasks.add(new ToDo(task.substring(7), done));
                    } else if (task.startsWith("[E]")) {
                        boolean done = task.charAt(4) == 'X';
                        int index = task.indexOf('(');
                        int endindex = task.indexOf(')');
                        LocalDate date = LocalDate.parse(task.substring(index + 5, endindex), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        tasks.add(new Event(task.substring(7, index - 1), date, done));
                    } else {
                        boolean done = task.charAt(4) == 'X';
                        int index = task.indexOf('(');
                        int endindex = task.indexOf(')');
                        LocalDate date = LocalDate.parse(task.substring(index + 5, endindex), DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        tasks.add(new Event(task.substring(7, index - 1), date, done));
                    }
                }
            }
            sc.close();
            return tasks;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void chat(Scanner scanner, ArrayList<Task> tasks) throws IOException {
        //ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
            } else if (input.startsWith("done")) {
                System.out.println("Nice! I've marked this task as done:");
                int tasknum = Integer.parseInt(input.substring(5));
                Task newtask = tasks.get(tasknum - 1).done();
                System.out.println(newtask.toString());
                tasks.set(tasknum - 1, newtask);
            } else if (input.startsWith("delete")) {
                System.out.println("Noted. I've removed this task:");
                int tasknum = Integer.parseInt(input.substring(7));
                System.out.println(tasks.get(tasknum - 1).toString());
                tasks.remove(tasknum - 1);
                System.out.println(String.format("Now you have %s tasks in the list.", tasks.size()));
            } else if (input.startsWith("todo")) {
                try {
                    Task newtask = new ToDo(input.substring(5));
                    tasks.add(newtask);
                    System.out.println(String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.", newtask.toString(), tasks.size()));
                } catch (Exception e) {
                    IncompleteCommandException ice = new IncompleteCommandException();
                    System.out.println(ice);
                }
            } else if (input.startsWith("event")) {
                try {
                    int index = input.indexOf("/at");
                    Task newtask = new Event(input.substring(6, index - 1), input.substring(index + 4));
                    tasks.add(newtask);
                    System.out.println(String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.", newtask.toString(), tasks.size()));
                } catch (Exception e) {
                    IncompleteCommandException ice = new IncompleteCommandException();
                    System.out.println(ice);
                }
            } else if (input.startsWith("deadline")) {
                try {
                    int index = input.indexOf("/by");
                    Task newtask = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
                    tasks.add(newtask);
                    System.out.println(String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.", newtask.toString(), tasks.size()));
                } catch (Exception e) {
                    IncompleteCommandException ice = new IncompleteCommandException();
                    System.out.println(ice);
                }
            } else {
                NoSuchCommandException nsce = new NoSuchCommandException();
                System.out.println(nsce);
            }
        }
        savetasks(tasks);
    }
}
