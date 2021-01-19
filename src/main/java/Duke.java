import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final ArrayList<Task> listToDo;

    private Duke() {
        this.listToDo = new ArrayList<>();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________");
        System.out.println(logo);
        System.out.println("Hello! I'm Danh's Duke\nWhat can I do for you, Mr Danh?");
        System.out.println("    ____________________________________________________________\n");
        Duke myDuke = new Duke();
        Scanner input = new Scanner(System.in);
        boolean signalToExit = false;
        while (!signalToExit && input.hasNextLine()) {
            String command = input.nextLine();
            if (command.startsWith("list")) {
                if (command.length() != 4) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    printList(myDuke);
                }
            } else if (command.startsWith("bye")) {
                if (command.length() != 3) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    signalToExit = true;
                }
            } else if (command.startsWith("done ")) {
                if (command.length() == 5 || !isNumeric(command.substring(5))) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else if (Integer.parseInt(command.substring(5)) > myDuke.listToDo.size()) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    markTaskDone(myDuke, Integer.parseInt(command.substring(5)));
                }
            } else if (command.startsWith("todo ")) {
                if (command.length() == 5) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    addToList(myDuke, command.substring(5));
                }
            } else if (command.startsWith("deadline ")) {
                if (command.length() == 9 || !command.contains("/by ")) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else if (command.indexOf("/by ") + 4 == command.length()) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    addToList(myDuke, command.substring(9));
                }
            } else if (command.startsWith("event ")) {
                if (command.length() == 6 || !command.contains("/at ")) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else if (command.indexOf("/at ") + 4 == command.length()) {
                    try {
                        executeFalseCommand(command);
                    } catch (DukeException err) {
                        printErrMsg(err);
                    }
                } else {
                    addToList(myDuke, command.substring(6));
                }
            } else {
                try {
                    executeFalseCommand(command);
                } catch (DukeException err) {
                    printErrMsg(err);
                }
            }
            if (command.equals("bye")) {
                echoBye();
            }
        }
    }

    public static void echoBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }

    public static void printList(Duke duke) {
        int index = 1;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (Task task : duke.listToDo) {
            System.out.format("     %d. " + task.printTask() + "\n", index);
            index++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void addToList(Duke duke, String taskdescription) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        Task task;
        if (taskdescription.contains("/at")) {
            String taskName = taskdescription.substring(0, taskdescription.indexOf("/at"));
            String dlTime = taskdescription.substring(taskdescription.indexOf("/at") + 4);
            task = new Event(taskName, dlTime);
        } else if (taskdescription.contains("/by")) {
            String taskName = taskdescription.substring(0, taskdescription.indexOf("/by"));
            String dlTime = taskdescription.substring(taskdescription.indexOf("/by") + 4);
            task = new Deadline(taskName, dlTime);
        } else {
            task = new ToDo(taskdescription);
        }
        System.out.println("       " + task.printTask());
        duke.listToDo.add(task);
        System.out.format("     Now you have %d tasks in the list.\n", duke.listToDo.size());
        System.out.println("    ____________________________________________________________\n");
    }

    public static void markTaskDone(Duke duke, int index) {
        System.out.println("    ____________________________________________________________");
        Task task = duke.listToDo.get(index - 1);
        task.markAsDone();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + task.printTask());
        System.out.println("    ____________________________________________________________\n");
    }

    public static void executeFalseCommand(String command) throws DukeException {
        if (command.startsWith("list")) {
            throw new DukeException("     list command should not have body, Sir!");
        } else if (command.startsWith("bye")) {
            throw new DukeException("     bye command should not have body, Sir!");
        } else if (command.startsWith("done ")) {
            throw new DukeException("     No body or wrong body format or unvalid number for done command, Sir!");
        } else if (command.startsWith("todo ")) {
            throw new DukeException("     No body detected for todo command, Sir!");
        } else if (command.startsWith("deadline ")) {
            throw new DukeException("     no body detected or no dlTime detected for deadline command, Sir!");
        } else if (command.startsWith("event ")) {
            throw new DukeException("     no body detected or no eTime detected for Event command, Sir!");
        } else {
            throw new DukeException("     Invalid command format");
        }
    }

    public static void printErrMsg(DukeException err) {
        System.out.println("    ____________________________________________________________\n" + err.getMessage() + "\n" + "    ____________________________________________________________\n");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}


