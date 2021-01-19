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
            Task task;
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
                if(components[0].equals("deadline")) {
                    StringBuilder desc = new StringBuilder(), time = new StringBuilder();
                    int i = 1;
                    while(!components[i].equals("/by")) {
                        desc.append(components[i] + " ");
                        i++;
                    }
                    i++;
                    while(i < components.length) {
                        time.append(components[i] + " ");
                        i++;
                    }
                    time.deleteCharAt(time.length() - 1);
                    task = new Deadline(desc.toString(), time.toString());
                }
                else if (components[0].equals("todo")) {
                    StringBuilder desc = new StringBuilder();
                    int i = 1;
                    while(i < components.length) {
                        desc.append(components[i] + " ");
                        i++;
                    }
                    task = new ToDo(desc.toString());
                }
                else if (components[0].equals("event")) {
                    StringBuilder desc = new StringBuilder(), time = new StringBuilder();
                    int i = 1;
                    while(!components[i].equals("/at")) {
                        desc.append(components[i] + " ");
                        i++;
                    }
                    i++;
                    while(i < components.length) {
                        time.append(components[i] + " ");
                        i++;
                    }
                    time.deleteCharAt(time.length() - 1);
                    task = new Event(desc.toString(), time.toString());
                }
                else task = new Task(str);
                taskLst.add(task);
                System.out.println("Got it. Now I have added this " +
                        "task:\n" + "  " + task.toString() + "\n" +
                        "Now you have " + taskLst.size() + " tasks in the list.");
            }
            System.out.println(sep);
        }
    }
}
