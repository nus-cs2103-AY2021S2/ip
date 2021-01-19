import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String sep = "****************************\n";
        System.out.println(sep);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskLst = new ArrayList<>();
        while(true) {
            String str = sc.nextLine();
            Task task = new Task(str);
            String[] components = str.split(" ");
            if(str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(str.equals("list")) {
                int counter = 1;
                for(Task t: taskLst) {
                    System.out.println(counter + "." + t.toString());
                    counter++;
                }
            }
            else if (components[0].equals("done")) {
                System.out.println("Nice! I've marked this task as done: ");
                int index = Integer.parseInt(components[1]) - 1;
                taskLst.get(index).markDone();
                System.out.println("  " + taskLst.get(index).toString());
            }
            else {
                taskLst.add(task);
                System.out.println("added: " + task.getTask());
            }
            System.out.println(sep);
        }
    }
}
