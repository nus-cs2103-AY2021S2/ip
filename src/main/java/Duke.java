import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String horzLine = "    _________________________________________________";
        List<Task> list = new ArrayList<>();

        String logo = " ____        _        \n"
                + "               |  _ \\ _   _| | _____ \n"
                + "               | | | | | | | |/ / _ \\\n"
                + "               | |_| | |_| |   <  __/\n"
                + "               |____/ \\__,_|_|\\_\\___|\n";

        System.out.println(horzLine
                + "\n     Hello! I'm" + logo
                + "\n     What can I do for you?\n"
                + horzLine);

        boolean exit = false;
        while (!exit) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) { //bye
                exit = true;
                System.out.println(horzLine
                        + "\n     Bye. Hope to see you again soon!\n"
                        + horzLine);
                
            } else if (userInput.equals("list")) { //list
                System.out.println(horzLine
                    + "\n     Here are the tasks in your list: ");

                for (int i = 0; i < list.size(); i++) {
                    int number = 1 + i;
                    System.out.println("     " + number + ". " + list.get(i));
                }
                System.out.println(horzLine);
                
            } else if (userInput.startsWith("done")) { //Mark task as done
                int taskNumber = Integer.parseInt(userInput.substring(5, 6)) - 1;
                list.get(taskNumber).markAsDone();

                System.out.println(horzLine
                        + "\n     Nice! I've marked this task as done:\n"
                        + "        " + list.get(taskNumber) + "\n"
                        + horzLine);

            } else { //Add new tasks to list
                Task newTask;

                if (userInput.startsWith("todo")) {
                    newTask = new ToDo(userInput.substring(5));
                } else if (userInput.startsWith("deadline")) {
                    int index = userInput.indexOf('/');
                    newTask = new Deadline(userInput.substring(9, index),
                            userInput.substring(index + 4));
                } else {
                    int index = userInput.indexOf('/');
                    newTask = new Event(userInput.substring(6, index),
                            userInput.substring(index + 4));
                }

                list.add(newTask);
                System.out.println(horzLine
                        + "\n      Got it. I've added this task:\n"
                        + "            " + newTask + "\n"
                        + "      Now you have " + list.size() + " tasks in the list.\n"
                        + horzLine);
            }
        }

        sc.close();
    }
}
