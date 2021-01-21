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
        dprint("Hello! I'm Duke\nWhat can I do for you?");

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
                dprint("Bye. Hope to see you again soon!");
                break;
            } else if(request.equals("list")) {
                printlist(lst);
            } else if(request.equals("done")) {
                int taskNo = Integer.parseInt(taskname);
                markdone(lst, taskNo);
            } else if(request.equals("delete")) {
                int taskNo = Integer.parseInt(taskname);
                delete(lst, taskNo);
            } else if(request.equals("todo")) {
                try {
                    trytodo(lst, taskname);
                    printadded(lst);
                } catch (DukeException ex) {
                    dprint(ex.getMessage());
                }
            } else if(request.equals("deadline")) {
                String[] deadStr = format(taskname);
                lst.add(new Task("D", deadStr[0], deadStr[1]));
                printadded(lst);
            } else if(request.equals("event")) {
                String[] eventStr = format(taskname);
                lst.add(new Task("E", eventStr[0], eventStr[1]));
                printadded(lst);
            } else {
                try {
                    throwDK();
                } catch (DukeException ex) {
                    dprint(ex.getMessage());
                }
            }
        }
    }
    
    public static void delete(List<Task> lst, int taskNo) {
        Task task = lst.remove(taskNo - 1);
        String msg = "Noted. I've removed this task:\n" +
            "  " + task +
            "\n Now you have " + lst.size() + " tasks in the list.";
        dprint(msg);
    }

    public static void trytodo(List<Task> lst, String taskname) throws DukeException {
        if (taskname.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        lst.add(new Task("T", taskname));
    }

    public static void throwDK() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static String[] format(String n) {
        String[] arr = new String[3];
        arr[0] = n.split("/")[0];
        arr[1] = "(" + n.split("/")[1].split(" ")[0] + ":" +
            n.split("/")[1].substring(n.split("/")[1].split(" ")[0].length(), n.split("/")[1].length()) + ")";
        return arr;
    }
    
    public static void printadded(List<Task> lst) {
        String msg = "Got it. I've added this task:\n";
        msg += "  " + lst.get(lst.size() - 1);
        msg += "\nNow you have " + lst.size() + " tasks in the list.";
        dprint(msg);
    }

    public static void dprint(String msg) {
        String appendMsg = "____________________________________________________________\n"
            + msg + "\n____________________________________________________________";
        System.out.println(appendMsg);
    }

    public static void printlist(List<Task> lst) {
        String msg = "Here are the tasks in your list:\n";
        for (int i = 1; i <= lst.size(); i++) {
            msg += i + ". " + lst.get(i - 1);
            if (i < lst.size()) {
                msg += "\n";
            }
        }
        dprint(msg);
    }

    public static void markdone(List<Task> lst, int taskNo) {
        lst.get(taskNo - 1).setDone();
        String msg = "Nice! I've marked this task as done:\n";
        msg += "  " + lst.get(taskNo - 1);
        dprint(msg);
    }
}
