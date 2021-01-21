import java.util.*;
import java.lang.*;

public class Duke {

    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Task class
     */
    static class Task {

        String taskName;
        boolean isDone;
        Type type;
        String time;

        /**
         * Task class constructor
         */
        public Task(String taskName, boolean isDone, Type type, String time) {
            this.taskName = taskName;
            this.isDone = isDone;
            this.type = type;
            this.time = time;
        }

        public void markDone() {
            this.isDone = true;
        }

        public String toString() {
            if (type == Type.TODO) {
                if (isDone) return "[T][X] " + taskName;
                return "[T][ ] " + taskName;
            } else if (type == Type.DEADLINE) {
                if (isDone) return "[D][X] " + taskName + " (by: " + time + ")";
                return "[D][ ] " + taskName + " (by: " + time + ")";
            } else if (type == Type.EVENT) {
                if (isDone) return "[E][X] " + taskName + " (at: " + time + ")";
                return "[E][ ] " + taskName + " (at: " + time + ")";
            } else return "O_o How did you input other types?";
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
                String[] inputArrTasks = input.split("/");
                String[] firstHalf = inputArrTasks[0].split(" ", 2);

                if (inputArrTasks.length != 1) {
                    String[] secondHalf = inputArrTasks[1].split(" ", 2);
                    taskArr[j] = new Task(firstHalf[1], false, Type.valueOf(firstHalf[0].toUpperCase()), secondHalf[1]);
                } else
                    taskArr[j] = new Task(firstHalf[1], false, Type.valueOf(firstHalf[0].toUpperCase()), "");

                j++;
                // print output
                String formattedInput = "Got it. I've added this task:\n  ";
                formattedInput = formattedInput.concat(taskArr[j - 1].toString()).concat("\n");
                formattedInput = formattedInput.concat("Now you have " + Integer.toString(j) + " tasks in the list.");
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
