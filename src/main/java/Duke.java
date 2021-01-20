import java.util.*;

public class Duke {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println(line + "Hello! I'm Kobe\n" + ind + "What can I do for you?\n" + line);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String text = sc.next();
            if (text.equals("bye")) {
                goodbye();
                break;
            } else if (text.equals("list")) {
                showList();
            } else if (text.equals("done")) {
                int taskNumber = Integer.parseInt(sc.next()) - 1;
                completeTask(taskNumber);
            } else {
                addItem(text);
            }
        }
        sc.close();
    }

    public static void addItem(String echoedText) {
        tasks.add(new Task(echoedText));
        System.out.println(line + "added: " + echoedText + "\n" + line);
    }

    public static void goodbye() {
        System.out.println(line + "Bye. Kobe hopes to see you again soon!\n" + line);
    }

    public static void showList() {
        System.out.print(line + "Here are the tasks in your list:\n");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.print(ind + (i+1) + ". " + tasks.get(i) + "\n");
        }
        System.out.println(line);
    }

    public static void completeTask(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        System.out.print(line + "Nice work! Kobe will mark your task as done!\n" + ind);
        System.out.print(tasks.get(taskNumber) + "\n");
        System.out.println(line);
    }
}
