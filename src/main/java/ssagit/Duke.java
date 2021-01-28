package ssagit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.lang.*;
import java.time.LocalDate;

public class Duke {
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

    /**
     * Duke speaks in chat boxes
     *
     * @param str input string within chat boxes
     */
    static void formatBox(String str) {
        System.out.println("------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------");
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

            // date init
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
            DateValidator validator = new DateValidatorLocalDate(dateFormatter);

            while (true) {
                input = sc.nextLine();
                System.out.println(input);
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
                    String[] inputArrTasks = input.split("/", 2);
                    String[] firstHalf = inputArrTasks[0].split(" ", 2);
                    System.out.println(inputArrTasks[1]);

                    if (inputArrTasks.length != 1) {
                        // create Deadline/Event
                        String[] secondHalf = inputArrTasks[1].split(" ", 2);
                        System.out.println(secondHalf[1]);
                        if (validator.isValid(secondHalf[1])) {
                            Date date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(secondHalf[1]);
                            if (inputArr[0].equals("event")) {
                                taskArr[j] = new EventTask(firstHalf[1], false, date);
                            } else if (inputArr[0].equals("deadline")) {
                                taskArr[j] = new DeadlineTask(firstHalf[1], false, date);
                            }
                        } else {
                            System.out.println("Invalid date format for timed Task");
                            continue;
                        }
                    } else {
                        // create todoTask
                        if (firstHalf.length == 1) {
                            throw new MissingTodoDescriptorException("------------------------------------\n" +
                                    ":( OOPS!!! The description of a todo cannot be empty\n" +
                                    "------------------------------------");
                        } else {
                            taskArr[j] = new Task(firstHalf[1], false);
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
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
