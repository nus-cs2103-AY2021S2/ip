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

        LinkedList<String> lst = new LinkedList<>();

        while (!request.equals("bye")) {
            request = sc.nextLine();
            if (request.equals("bye")) {
                dprint("Bye. Hope to see you again soon!");
                break;
            } else if(request.equals("list")) {
                printlist(lst);
            } else {
                lst.add(request);
                dprint("added: " + request);
            }
        }
    }

    public static void dprint(String msg) {
        String appendMsg = "____________________________________________________________\n"
            + msg + "\n____________________________________________________________";
        System.out.println(appendMsg);
    }

    public static void printlist(List lst) {
        String msg = "";
        for (int i = 1; i <= lst.size(); i++) {
            msg += i + ". " + lst.get(i - 1);
            if (i < lst.size()) {
                msg += "\n";
            }
        }
        dprint(msg);
    }
}
