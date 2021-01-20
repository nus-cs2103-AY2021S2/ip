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
            //Read the whole line, dissect each command word, including the condition after "/"
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");
            String text = commandArr[0];

            if (text.equals("bye")) {
                goodbye();
                break;
            } else if (text.equals("list")) {
                showList();
            } else if (text.equals("done")) {
                int taskNumber = Integer.parseInt(commandArr[1]) - 1;
                completeTask(taskNumber);
            } else {
                String taskName = "";
                String type = text;
                String condition = "";


                String[] commandArrFirst2Parts = command.split(" ", 2);
                String[] commandArrSecond2Parts = commandArrFirst2Parts[1].split(" /", 2);

//                //Check for correct splitting
//                System.out.println("Second2Parts: " + Arrays.toString(commandArrSecond2Parts));


                taskName = commandArrSecond2Parts[0];
                //If the array is in 2 parts, there is a condition, add that
                if (commandArrSecond2Parts.length > 1) {
                    condition = commandArrSecond2Parts[1];
                }

//                Unwanted code:
//                while(sc.hasNext()) { //To manage for tasks that are named longer than one word
//                    String currStr = sc.next();
//                    if(currStr.substring(0, 0).equals("/")) { //Account for task duration condition
//                        condition = currStr.substring(1);
//                    }
//                    taskName = taskName + currStr + " ";
//                }

                addItem(taskName, type, condition);
            }
        }
        sc.close();
    }

    public static void addItem(String echoedText, String type, String condition) {
        Task currentTask = new Task(echoedText, type, condition);
        tasks.add(currentTask);
        System.out.println(line + "Got it! Kobe added this task:\n" + ind + ind +
                 currentTask);
        System.out.println(ind + "Kobe sees that you have " + tasks.size() + " task(s) in the list.\n" + line);
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
        System.out.println(ind + tasks.get(taskNumber));
        System.out.println(line);
    }
}
