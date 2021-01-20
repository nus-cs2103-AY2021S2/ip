import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> list;

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
//            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
            return (isDone ? "\u2713" : " "); //return tick or X symbols
        }

        public void markAsDone() {
            isDone = true;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
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
                list.add(new Task(input));
                System.out.println("Added: " + input);
            }
        }
    }
}
