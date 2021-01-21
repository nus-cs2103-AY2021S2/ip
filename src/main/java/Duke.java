import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // open FastIO to read input
        FastIO reader = new FastIO();

        // greeting for user to see
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // read user input
        String input = reader.nextLine();

        // create list to store tasks
        ArrayList<Task> taskArrayList = new ArrayList<>();

        while(true) {
            // user exit program
            if (input.equals("bye")) {
                reader.println("Bye. Hope to see you again soon!");
                break;

            // user wants to see list of tasks
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int counter = 1;
                for (Task t : taskArrayList) {
                    System.out.println(counter + ". " + t.taskStatus());
                    counter++;
                }
                input = reader.nextLine();

            // user completes a task
            } else if (input.startsWith("done")) {
                String[] split = input.split("\\s+");
                int index = Integer.parseInt(split[1]) - 1;
                Task to_complete = taskArrayList.get(index);
                taskArrayList.set(index, to_complete.completeTask());

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(to_complete.completeTask().taskStatus());

                input = reader.nextLine();

            // user adds new tasks
            } else {
                taskArrayList.add(new Task(input));
                System.out.println("added: " + input);
                input = reader.nextLine();
            }
        }

        // close FastIO to print exit statement
        reader.close();
    }
}

