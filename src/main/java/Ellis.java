import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ellis {
    List<Task> lst = new ArrayList<>();

    /**
     * Iterates and returns enumerated list of tasks.
     */
    public void iterateList() {
        System.out.println("Here are the items in your list:\n");
        int i = 1;
        for (Task item : lst) {
            System.out.println(i + ". " + item.toString());
            i++;
        }
    }

    /**
     * Formats text.
     */
    public void formatText(String str) {
        System.out.println("**************************************************");
        System.out.println(str);
        System.out.println("**************************************************");
    }

    /**
     * Checks for task in the given index and marks it as completed if valid.
     *
     * @param index task number given one-based indexing.
     */
    public void handleTaskStatus(int index) {
        if (index >= lst.size()) {
            formatText("Index out of bounds! You don't have so many items, hun.");
        } else {
            Task task = lst.get(index);
            task.markAsDone();
            formatText("Sweet! I have marked the following task as done:\n"
                    + task.toString());
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                // close program
                formatText("Bye, see you soon!");
                break;
            } else if (input.equals("list")) {
                // show everything in the list
                System.out.println("**************************************************");
                iterateList();
                System.out.println("**************************************************");
            } else if (input.split(" ",2)[0].equals("done")){
                // mark task with the given index as completed
                int index = Integer.parseInt(input.split(" ",2)[1]) - 1;
                handleTaskStatus(index);
            } else {
                // add new task to list
                lst.add(new Task(input));
                formatText("Added: " + input);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        String logo = ".------..------..------..------..------.\n"
                + "|E.--. ||L.--. ||L.--. ||I.--. ||S.--. |\n"
                + "| (\\/) || :/\\: || :/\\: || (\\/) || :/\\: |\n"
                + "| :\\/: || (__) || (__) || :\\/: || :\\/: |\n"
                + "| '--'E|| '--'L|| '--'L|| '--'I|| '--'S|\n"
                + "`------'`------'`------'`------'`------'\n";

        System.out.println("Hello, this is \n" + logo);
        Ellis e = new Ellis();
        e.run();
    }
}
