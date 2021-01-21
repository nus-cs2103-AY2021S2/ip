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
                int counter = 1;
                for (Task t: taskArrayList) {
                    System.out.println(counter + ". " + t.getTask_details());
                    counter++;
                }
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

