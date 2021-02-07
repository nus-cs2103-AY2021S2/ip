import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        try {
            // task list
            FileReaderWriter frw = new FileReaderWriter();
            ArrayList<Task> taskList = frw.getMemList();

            // greet
            printMessage("Hello! I'm Duke\nWhat can I do for you?");

            // setup scanner for inputs
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().strip();

            // input loop
            while (!input.equals("bye")) {
                try {
                    if (input.equals("list")) {
                        // list
                        printList(taskList);
                    } else {
                        // split input into action and detail
                        String[] splitInput = input.split(" ", 2);

                        if (splitInput[0].equals("done")) {
                            // done
                            int taskNum = Integer.parseInt(splitInput[1]);
                            Task finishedTask = taskList.get(taskNum - 1);
                            finishedTask.setCompletion(true);
                            printMessage("Nice! I've marked this task as done:\n   " + finishedTask);

                        } else if (splitInput[0].equals("delete")) {
                            int taskNum = Integer.parseInt(splitInput[1]);
                            Task deletedTask = taskList.remove(taskNum - 1);
                            printMessage("Noted. I've removed this task:\n" + "  " + deletedTask + "Now you have "
                                    + taskList.size() + " tasks in the list.");

                        } else if (splitInput[0].equals("check")) {
                            // list events and deadlines on a specific date
                            if (splitInput.length < 2) {
                                throw new EmptyCheckException();
                            }
                            LocalDate dateToCheck = LocalDate.parse(splitInput[1]);
                            ArrayList<Task> filteredList = new ArrayList<>();

                            for (int i = 0; i < taskList.size(); i++) {
                                Task currTask = taskList.get(i);
                                if (dateToCheck.equals(currTask.getDate())) {
                                    filteredList.add(currTask);
                                }
                            }
                            printList(filteredList);
                        } else if (splitInput[0].equals("todo")) {
                            // check for empty task
                            if (splitInput.length < 2) {
                                throw new EmptyTaskException("todo");
                            }

                            // add todo
                            addTask(taskList, new ToDo(splitInput[1]));
                        } else if (splitInput[0].equals("deadline")) {
                            // check for empty task
                            if (splitInput.length < 2) {
                                throw new EmptyTaskException("deadline");
                            }

                            // add deadline
                            String[] details = splitInput[1].split(" /by ");
                            addTask(taskList, new Deadline(details[0], details[1]));
                        } else if (splitInput[0].equals("event")) {
                            // check for empty task
                            if (splitInput.length < 2) {
                                throw new EmptyTaskException("event");
                            }
                            // add event
                            String[] details = splitInput[1].split(" /at ");
                            addTask(taskList, new Deadline(details[0], details[1]));
                        } else {
                            throw new NotDukeCommandException();
                        }
                    }

                } catch (DukeException e) {
                    printMessage(e.getMessage());
                } catch (DateTimeParseException e) {
                    printMessage("Date given is not in the correct format!\nPlease try again in the format YYYY-MM-DD");
                }

                input = sc.nextLine();
            }

            // exit
            printMessage("Bye. Hope to see you again soon!");
            sc.close();
            frw.storeMemList(taskList);
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    // add task function
    private static void addTask(ArrayList<Task> taskList, Task addedTask) {
        taskList.add(addedTask);
        String numOfTasks = taskList.size() + (taskList.size() > 1 ? " tasks" : " task");
        printMessage(
                "Got it. I've added this task:\n  " + addedTask + "\nNow you have " + numOfTasks + " in the list.");
    }

    // prints list item number and string
    private static void printList(ArrayList<Task> taskList) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i).toString());
        }
        System.out.println("    ____________________________________________________________\n");
    }

    // format for greeting, echo and exit
    private static void printMessage(String message) {
        String newMessage = message.replaceAll("\n", "\n     ");
        System.out.println("    ____________________________________________________________\n" + "     " + newMessage
                + "\n" + "    ____________________________________________________________\n");
    }
}
