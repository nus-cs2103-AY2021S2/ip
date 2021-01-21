import java.util.*;
import java.lang.*;

public class Duke {

    /**
     * Task class with names and a boolean variable checking if they are done or not
     */
    static class Task {
        String taskName;
        boolean isDone;

        public Task(String taskName, boolean isDone) {
            this.taskName = taskName;
            this.isDone = isDone;
        }

        public void markDone() {
            this.isDone = true;
        }

        public String toString() {
            if (isDone) return "[X] " + taskName;
            else return "[ ] " + taskName;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introduction = "I'm Duke!\nWhat can I do for ya?\n";
        formatBox(introduction);

        Scanner sc = new Scanner(System.in);
        Task[] taskArr = new Task[100];
        String input;
        int j = 0;

        while (true) {
            input = sc.nextLine();
            String inputArr[] = input.split(" ");
            if (input.equals("bye"))
                break;
            else if (input.equals("list")) {
                System.out.println("------------------------------------");
                System.out.println("Here are the tasks in your list: ");
                for (Task t : taskArr) {
                    if (t == null) break;
                    System.out.println(t.toString());
                }
                System.out.println("------------------------------------");
            } else if (inputArr[0].equals("done")) {
                int taskNum = Integer.parseInt(inputArr[1]) - 1;
                taskArr[taskNum].markDone();
                formatBox("Nice! I've marked this task as done:\n" + taskArr[taskNum].toString());
            }
            else {
                // add to list
                taskArr[j] = new Task(input, false);
                j++;
                // print output
                String formattedInput = "added Task: ";
                formattedInput = formattedInput.concat(input);
                formatBox(formattedInput);
            }
        }
        String bye = "Bye. Hope to see you again soon!";
        formatBox(bye);
    }

    /**
     * Duke speaks in chat boxes
     * @param str input string within chat boxes
     */
    public static void formatBox(String str) {
        System.out.println("------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------");
    }
}
