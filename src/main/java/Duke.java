import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        start();
    }
    
    public static void start() {
        Scanner sc = new Scanner(System.in);
        String request = "";
        printFormatted("Hello! I'm Duke\nWhat can I do for you?");

        LinkedList<Task> lst = new LinkedList<>();

        while (!request.equals("bye")) {
            request = sc.nextLine();
            String[] tk = request.split(" ");
            request = tk[0];
            String taskname = "";
            // System.out.println(request);
            for (int i = 1; i < tk.length; i++) {
                taskname += tk[i];
                if (i < tk.length - 1) {
                    taskname += " ";
                }
            }

            if (request.equals("bye")) {
                printFormatted("Bye. Hope to see you again soon!");
                break;
            } else if (request.equals("list")) {
                printList(lst);
            } else if (request.equals("done")) {
                try {
                    int taskNo = Integer.parseInt(taskname);
                    markDone(lst, taskNo);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    printFormatted("Please enter integer values..");
                }
            } else if (request.equals("delete")) {
                try {
                    int taskNo = Integer.parseInt(taskname);
                    delete(lst, taskNo);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    printFormatted("Please enter integer values..");
                }
            } else if (request.equals("todo")) {
                try {
                    addTodo(lst, taskname);
                    printAdded(lst);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                }
            } else if (request.equals("deadline")) {
                try {
                    String[] deadStr = formatCommand(taskname);
                    lst.add(new Task("D", deadStr[0], deadStr[1], deadStr[2]));
                    printAdded(lst);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                }
            } else if (request.equals("event")) {
                try {
                    String[] eventStr = formatCommand(taskname);
                    lst.add(new Task("E", eventStr[0], eventStr[1], eventStr[2]));
                    printAdded(lst);
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                }
            } else {
                try {
                    throwDK();
                } catch (DukeException ex) {
                    printFormatted(ex.getMessage());
                }
            }
        }
    }
    
    public static void delete(List<Task> lst, int taskNo) throws DukeException {
        if (taskNo >= lst.size() || taskNo < 0) {
            throw new DukeException(
                    String.format("Tried to delete nothing ????. (Size: %d | Task No: %d)", lst.size(), taskNo));
        }
        Task task = lst.remove(taskNo - 1);
        String msg = "Noted. I've removed this task:\n" +
                "  " + task +
                "\n Now you have " + lst.size() + " tasks in the list.";
        printFormatted(msg);
    }

    public static void addTodo(List<Task> lst, String taskname) throws DukeException {
        if (taskname.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        lst.add(new Task("T", taskname));
    }

    public static void throwDK() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static String[] formatCommand(String n) throws DukeException {
        try {
            String[] arr = new String[3];
            arr[0] = n.split("/")[0].split(" ")[0];
            arr[1] = n.split("/")[1].substring(n.split("/")[1].split(" ")[0].length() + 1, n.split("/")[1].length());
            arr[2] = n.split("/")[1].split(" ")[0];
            return arr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The format you have entered is wrong.");
        }
    }
    
    public static void printAdded(List<Task> lst) {
        String msg = "Got it. I've added this task:\n"
                + "  " + lst.get(lst.size() - 1)
                + "\nNow you have " + lst.size() + " tasks in the list.";
        printFormatted(msg);
    }

    public static void printFormatted(String msg) {
        String appendMsg = "____________________________________________________________\n"
                + msg
                + "\n____________________________________________________________";
        System.out.println(appendMsg);
    }

    public static void printList(List<Task> lst) {
        String msg = "Here are the tasks in your list:\n";
        for (int i = 1; i <= lst.size(); i++) {
            msg += i + ". " + lst.get(i - 1);
            if (i < lst.size()) {
                msg += "\n";
            }
        }
        printFormatted(msg);
    }

    public static void markDone(List<Task> lst, int taskNo) throws DukeException{
        if (taskNo >= lst.size() || taskNo < 0) {
            throw new DukeException(
                    String.format("Tried to mark nothing ????. (Size: %d | Task No: %d)", lst.size(), taskNo));
        }
        lst.get(taskNo - 1).setDone();
        String msg = "Nice! I've marked this task as done:\n";
        msg += "  " + lst.get(taskNo - 1);
        printFormatted(msg);
    }
}
