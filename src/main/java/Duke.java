import java.util.*;

public class Duke {

    static Task[] tasks = new Task[100];
    static int numTask = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                bye();
                break;
            }
            if (s.length() >= 6) {
                if (s.substring(0, 4).equals("done")) {
                    int num = Integer.parseInt(s.substring(5));
                    done(num);
                    continue;
                }
            }
            store(s);
        }
    }

    static public void done(int num) {
        Task task = tasks[num - 1];
        task.markAsDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       " + task + "\n" +
                "    ____________________________________________________________\n");
    }

    static public void store(String str) {
        if (str.equals("list")) {
            String printList = "";
            int idx = 1;
            for (int i = 0; i < numTask; i++) {
                Task task = tasks[idx - 1];
                printList += idx + "." + task + "\n    ";
                if (i != numTask - 1) {
                    printList += " ";
                }
                idx++;
            }
            System.out.println("    ____________________________________________________________\n     " +
                    printList +
                    "____________________________________________________________\n");
        } else {
            tasks[numTask] = new Task(str);
            numTask++;
            System.out.println("    ____________________________________________________________\n     " +
                    "added: " + str + "\n" +
                    "    ____________________________________________________________\n");
        }
    }

    static public void bye() {
        System.out.println("    ____________________________________________________________\n     " +
                "Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }

    static public void greet() {
        System.out.println("    ____________________________________________________________\n     " +
                " ____        _        \n" +
                "     |  _ \\ _   _| | _____ \n" +
                "     | | | | | | | |/ / _ \\\n" +
                "     | |_| | |_| |   <  __/\n" +
                "     |____/ \\__,_|_|\\_\\___|\n\n     " +
                "Hello! I'm Duke\n     " +
                "What can I do for you?\n" +
                "    ____________________________________________________________\n");
    }
}
