import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke. What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();
        duke.chat(scanner);
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
        private String deadline;
        private boolean done;
        Deadline(String name, String deadline) {
            this.name = name;
            this.deadline = deadline;
            this.done = false;
        }
        Deadline(String name, String deadline, boolean done) {
            this.name = name;
            this.deadline = deadline;
            this.done = done;
        }
        @Override
        Task done() {
            return new Deadline(this.name, this.deadline, true);
        }
        @Override
        public String toString() {
            if (this.done) {
                return String.format("[D][X] %s (by:%s)", this.name, this.deadline);
            }
            return String.format("[D][ ] %s (by:%s)", this.name, this.deadline);
        }
    }

    class Event extends Task {
        private String name;
        private String duration;
        private boolean done;
        Event(String name, String duration) {
            this.name = name;
            this.duration = duration;
            this.done = false;
        }
        Event(String name, String duration, boolean done) {
            this.name = name;
            this.duration = duration;
            this.done = done;
        }
        @Override
        Task done() {
            return new Event(this.name, this.duration, true);
        }
        @Override
        public String toString() {
            if (this.done) {
                return String.format("[E][X] %s (at:%s)", this.name, this.duration);
            }
            return String.format("[E][ ] %s (at:%s)", this.name, this.duration);
        }
    }

    public void chat(Scanner scanner) {
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
            } else if (input.substring(0, 4).equals("done")) {
                System.out.println("Nice! I've marked this task as done:");
                int tasknum = Integer.parseInt(input.substring(5));
                Task newtask = tasks.get(tasknum - 1).done();
                System.out.println(newtask.toString());
                tasks.set(tasknum - 1, newtask);
            } else {
                Task newtask;
                if (input.substring(0, 4).equals("todo")) {
                    newtask = new ToDo(input.substring(5));
                    tasks.add(newtask);
                } else if (input.substring(0, 5).equals("event")) {
                    int index = input.indexOf("/at");
                    newtask = new Event(input.substring(6, index - 1), input.substring(index + 3));
                    tasks.add(newtask);
                } else {
                    int index = input.indexOf("/by");
                    newtask = new Deadline(input.substring(9, index - 1), input.substring(index + 3));
                    tasks.add(newtask);
                }
                System.out.println(String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.", newtask.toString(), tasks.size()));
            }
        }
    }
}
