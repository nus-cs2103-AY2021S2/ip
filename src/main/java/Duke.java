import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 0;
        Duke.printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Duke.printHorizontalLine();

        while(true) {
            String s = sc.nextLine();
            if(s.equals("bye")) {
                Duke.byeCommand();
                break;
            } else if(s.equals("list")) {
                Duke.printHorizontalLine();
                Duke.listCommand(list, counter);
                Duke.printHorizontalLine();
            } else {
                if(s.contains("done")) {
                    String[] array = s.split(" ");
                    doneCommand(list,array);
                } else if(s.contains("todo")) {
                    String st = s.substring(5);
                    toDoCommand(counter, st, list);
                    counter++;
                    continue;
                } else if(s.contains("deadline")) {
                    String[] strArr = s.split("/by ");
                    String description = strArr[0].substring(9).trim();
                    String by = strArr[1];
                    deadlineCommand(counter, description, by, list);
                    counter++;
                    continue;

                } else if(s.contains("event")) {
                    String[] strArr = s.split("/at ");
                    String description = strArr[0].substring(6).trim();
                    String date = strArr[1];
                    eventCommand(counter, description, date, list);
                    counter++;
                    continue;
                } else {
                    //do nothing
                    continue;
                }
            }
        }
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void byeCommand() {
        Duke.printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.printHorizontalLine();
    }

    public static void listCommand(Task[] list, int counter) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < counter; i++) {
            int j = i + 1;
            Task t = list[i];
            System.out.println((j) + "." + t);
        }
    }

    public static void doneCommand(Task[] list, String[] array) {
        int m = Integer.valueOf(array[1]) - 1;
        Task t  = list[m];
        list[m] = t.markAsDone();
        t = list[m];
        Duke.printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[%s] %s%n", t.getStatusIcon(), t.getDescription());
        Duke.printHorizontalLine();
    }

    /*public static void addCommand(Task t) {
        Duke.printHorizontalLine();
        System.out.printf("added: %s%n", t);
        Duke.printHorizontalLine();
    }*/

    public static void toDoCommand(int counter, String s, Task[] list) {
        Duke.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        ToDo td = new ToDo(s);
        list[counter] = td;
        System.out.println(" " + td.toString());
        if(counter == 0) {
            System.out.printf("Now you have %d task in the list.%n", counter + 1);
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", counter + 1);
        }
        Duke.printHorizontalLine();
    }

    public static void deadlineCommand(int counter, String description, String by, Task[] list) {
        Duke.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        Deadline d = new Deadline(description, by);
        list[counter] = d;
        System.out.println(" " + d.toString());
        if(counter == 0) {
            System.out.printf("Now you have %d task in the list.%n", counter + 1);
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", counter + 1);
        }
        Duke.printHorizontalLine();
    }

    public static void eventCommand(int counter, String description, String date, Task[] list) {
        System.out.println("Got it. I've added this task:");
        Event e = new Event(description, date);
        list[counter] = e;
        System.out.println(" " + e.toString());
        if(counter == 0) {
            System.out.printf("Now you have %d task in the list.%n", counter + 1);
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", counter + 1);
        }
        Duke.printHorizontalLine();
    }
}
