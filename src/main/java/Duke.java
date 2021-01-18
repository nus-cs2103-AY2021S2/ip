import java.util.*;

public class Duke {

    static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String key = getKeyword(s);
            if (key.equals("bye")) {
                bye();
                break;
            } else if (key.equals("list")) {
                list();
            } else if (key.equals("done")) {
                done(Integer.parseInt(s.substring(5)));
            } else if (key.equals("todo") || key.equals("event") || key.equals("deadline")) {
                addTask(getTask(s, key));
            }
        }
    }

    static public Task getTask(String s, String key) {
        String desc = "";
        if (key.equals("todo")) {
            return new ToDo(s.substring(5));
        }
        int keyLen = key.length();
        for (int i = keyLen + 1; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && s.charAt(i + 1) == '/') {
                break;
            }
            desc += s.charAt(i);
        }
        String time = s.substring(keyLen + desc.length() + 6);
        if (key.equals("event")) {
            return new Event(desc, time);
        } else {
            return new Deadline(desc, time);
        }
    }

    static public void list() {
        String s = "";
        if (tasks.size() == 0) {
            System.out.println("    ____________________________________________________________\n     " +
                    "Here are the tasks in your list:\n    " +
                    "____________________________________________________________\n");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            s += (i + 1) + "." + tasks.get(i) + "\n";
            if (i != tasks.size() - 1) {
                s += "          ";
            }
        }
        System.out.println("    ____________________________________________________________\n     " +
                "     Here are the tasks in your list:\n          " + s +
                "    ____________________________________________________________\n");
    }

    static public void addTask(Task task) {
        tasks.add(task);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    static public String getKeyword(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                str += s.charAt(i);
            } else {
                break;
            }
        }
        return str;
    }

    static public void done(int num) {
        Task task = tasks.get(num - 1);
        task.markAsDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       " + task + "\n" +
                "    ____________________________________________________________\n");
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