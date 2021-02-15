import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * provides a platform for users to key in tasks to add to the list.
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("____________________________");

        Storage st = new Storage();
        st.initialise(st.getDefaultFilePath());
        ArrayList<Task> lst = new ArrayList<>();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            TaskList tl = new TaskList();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else {
                tl.createTask(input, sc, lst);
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    ArrayList<Task> list = new ArrayList<>();
    public String getResponse(String input) {
        String[] splited = input.split("\\s+");
        return TaskList.guiTask(splited, list);
    }

}