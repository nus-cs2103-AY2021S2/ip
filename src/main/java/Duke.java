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

    /**
     * Exception class for missing todo descriptor
     */
    static class MissingTodoDescriptorException extends Exception {
        public MissingTodoDescriptorException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Exception class for unknown input parameters
     */
    static class UnknownInputParamException extends Exception {
        public UnknownInputParamException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static void main(String[] args) {
        try {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            String introduction = "I'm Duke!\nWhat can I do for ya?\n";
            formatBox(introduction);

            // inits
            Scanner sc = new Scanner(System.in);
            Task[] taskArr = new Task[100];
            String input;
            int j = 0; // task counter

            while (true) {
                input = sc.nextLine();
                String inputArr[] = input.split(" ");
                if (inputArr[0].equals("bye"))
                    break;
                else if (inputArr[0].equals("list")) {
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
                } else if (inputArr[0].equals("todo") || inputArr[0].equals("event") ||
                        inputArr[0].equals("deadline")) {
                    // add to list
                    String[] inputArrTasks = input.split("/");
                    String[] firstHalf = inputArrTasks[0].split(" ", 2);

                    if (inputArrTasks.length != 1) {
                        String[] secondHalf = inputArrTasks[1].split(" ", 2);
                        taskArr[j] = new Task(firstHalf[1], false,
                                Type.valueOf(firstHalf[0].toUpperCase()), secondHalf[1]);
                    } else {
                        if (firstHalf.length == 1) {
                            throw new MissingTodoDescriptorException("------------------------------------\n" +
                                    ":( OOPS!!! The description of a todo cannot be empty\n" +
                                    "------------------------------------");
                        } else {
                            taskArr[j] = new Task(firstHalf[1], false,
                                    Type.valueOf(firstHalf[0].toUpperCase()), "");
                        }
                    }
                    j++; // increase task count in list
                    // print output
                    String formattedInput = "Got it. I've added this task:\n  ";
                    formattedInput = formattedInput.concat(taskArr[j - 1].toString()).concat("\n");
                    formattedInput = formattedInput.concat("Now you have " + Integer.toString(j) + " tasks in the list.");
                    formatBox(formattedInput);
                } else if (inputArr[0].equals("delete")) {
                    int removeIndex = Integer.parseInt(inputArr[1]);
                    j--; // reduce task count in list
                    String formattedInput = "Got it. I've removed this task:\n  ";
                    formattedInput = formattedInput.concat(taskArr[removeIndex - 1].toString()).concat("\n");
                    formattedInput = formattedInput.concat("Now you have " + Integer.toString(j) + " tasks in the list.");
                    formatBox(formattedInput);

                    // actually delete the task and move all other tasks forward
                    for (int i = removeIndex - 1; i < taskArr.length - 1; i++) {
                        taskArr[i] = taskArr[i + 1];
                    }
                } else {
                    throw new UnknownInputParamException("------------------------------------\n" +
                            ":( OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "------------------------------------");
                }
            }
            String bye = "Bye. Hope to see you again soon!";
            formatBox(bye);
        } catch (MissingTodoDescriptorException e) {
            System.out.println(e.getMessage());
        } catch (UnknownInputParamException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Duke speaks in chat boxes
     *
     * @param str input string within chat boxes
     */
    public static void formatBox(String str) {
        System.out.println("------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------");
    }
}
