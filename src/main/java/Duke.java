import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String DIVIDER = "____________________________________________________________";

        ArrayList<Task> tasks = new ArrayList<Task>();

        // initialize scanner
        Scanner sc = new Scanner(System.in);

        // welcome sequence
        System.out.println(DIVIDER);
        System.out.println("Welcome to Duke!");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        // get user input
        String userInput = sc.nextLine();

        // loop until the user exits
        while (!userInput.toLowerCase().equals("bye")) {
            System.out.println(DIVIDER);
            // display list
            if (userInput.toLowerCase().equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    String item = String.valueOf(i + 1) + ". " + tasks.get(i).toString();
                    System.out.println(item);
                }
            // finish a task
            } else if (userInput.toLowerCase().matches("^(do(ne)?|finish(ed)?|completed?) \\d+$")) {
                String[] bits = userInput.split(" ");
                int idx = Integer.parseInt(bits[1]) - 1; // zero-indexed task index
                if (idx >= 0 && idx < tasks.size()) {
                    tasks.get(idx).finish();
                } else {
                    System.out.println("That doesn't appear to be a valid task ID!");
                }
            // add task to list
            } else if (userInput.toLowerCase().matches("^(todo|deadline|event) .+$")) {
                String[] bits = userInput.split(" ");

                // format entry
                StringBuilder descBuilder = new StringBuilder();
                for (int i = 1; i < bits.length; i++) {
                    descBuilder.append(bits[i]);
                    descBuilder.append(" ");
                }
                String desc = descBuilder.toString().trim();

                Task newTask = new Task(desc.toString()); // placeholder
                if (bits[0].toLowerCase().equals("todo")) {
                    newTask = new Todo(desc.toString());
                } else if (bits[0].toLowerCase().equals("event")) {
                    String[] taskParts = desc.toString().split(" /on ");
                    newTask = new Event(taskParts[0], taskParts[1]);
                } else if (bits[0].toLowerCase().equals("deadline")) {
                    String[] taskParts = desc.toString().split(" /by ");
                    newTask = new Deadline(taskParts[0], taskParts[1]);
                }
                tasks.add(newTask);
                System.out.println("I've added this task: " + newTask.toString());
                System.out.printf("You now have %d items on your todo list.\n", tasks.size());
            } else {
                System.out.println("Unknown command!");
            }
            System.out.println(DIVIDER);

            // get next input
            userInput = sc.nextLine();
        }

        // exit sequence
        System.out.println(DIVIDER);
        System.out.println("Bye! Hope to see you again :)");
        System.out.println(DIVIDER);
    }
}
