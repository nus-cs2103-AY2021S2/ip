import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        List<String> taskList = new ArrayList<>();
        List<Boolean> doneList = new ArrayList<>();

        String indent = "         ";
        String horizSep = indent + "________________________________________________";

        String greeting = indent + " Hello! I'm Duke\n" + indent + " What can I do for you?\n";
        String farewell = indent + " Bye. Hope to see you again soon!\n";
        Scanner sc = new Scanner(System.in);



        System.out.println(horizSep + "\n" + greeting + horizSep + "\n");

        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            String[] params = next.split(" ");

            if (next.equals("bye")) {

                System.out.println(horizSep + "\n" + farewell + horizSep + "\n");
                sc.close();
                return;
            } else if (next.equals("list")) {
                ListIterator<String> taskIter = taskList.listIterator();
                ListIterator<Boolean> doneIter = doneList.listIterator();

                System.out.println(horizSep);
                System.out.println(indent + " Here are the tasks in your list:");
                while (taskIter.hasNext()) {
                    String doneIndicator;
                    if (doneIter.next()) {
                        doneIndicator = "X";
                    } else {
                        doneIndicator = " ";
                    }

                    System.out.println(indent + " " + String.valueOf(taskIter.nextIndex() + 1) + "." + "[" + doneIndicator + "]" + " " + taskIter.next());
                }
                System.out.println(horizSep + "\n");

            } else if (params[0].equals("done")) {
                Integer index = Integer.parseInt(params[1]) - 1;
                doneList.set(index, true);
                System.out.println(horizSep + "\n" + indent + " Nice! I've marked this task as done:");
                System.out.println(indent + "   [X] " + taskList.get(index));
                System.out.println(horizSep);

            } else {
                taskList.add(next);
                doneList.add(false);
                System.out.println(horizSep + "\n" +  indent + " added: " + next + "\n" + horizSep + "\n");
            }
        }

    }
}
