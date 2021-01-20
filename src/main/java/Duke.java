import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> list;

    public static class Task {
        enum Type { TODOS, DEADLINES, EVENTS }

        protected String description;
        protected boolean isDone;
        protected Type type;
//        protected String date;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public Task(Type type, String description) {
            this.description = description;
            this.isDone = false;
            this.type = type;
        }

//        public Task(Type type, String description, String date) {
//            this.description = description;
//            this.isDone = false;
//            this.type = type;
//            this.date = date;
//        }

        public String getStatusIcon() {
//            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
            return (isDone ? "X" : " "); //return tick or X symbols
        }

//        public String getTypeIcon () {
//            return (type == Type.TODOS ? "T" : type == Type.DEADLINES ? "D" : "E");
//        }

        public void markAsDone() {
            isDone = true;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
        }
    }

    public static class TodoTask extends Task {
        public TodoTask(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class DeadlineTask extends Task {
        protected String by;

        public DeadlineTask(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class EventTask extends Task {
        protected String at;

        public EventTask(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! How can I help you?");

        Scanner scanner = new Scanner(System.in);
        list  = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye! See you soon :)");
                scanner.close();
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).toString());
                }
            } else if (input.startsWith("done") && (input.length() == 6)) {
                Task task = list.get(Character.getNumericValue(input.charAt(5)) - 1);
                task.markAsDone();
                System.out.println("Good job! You got " + task.description + " done!");
            } else {
//                String[] inputSplit = input.split(" ");
                Task newTask;
                if (input.startsWith("todo")) {
                    newTask = new TodoTask(input.substring(5));
                } else if (input.startsWith("deadline")) {
                    String[] inputSplit = input.split("/");
                    newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1), inputSplit[1].substring(3));
                } else {
                    String[] inputSplit = input.split("/");
                    newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1), inputSplit[1].substring(3));
                }
//                list.add(new Task(input));
                list.add(newTask);
                System.out.println("Added: " + newTask.toString());
            }
        }
    }
}
