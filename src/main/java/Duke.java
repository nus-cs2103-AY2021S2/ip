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
                String[] array = s.split(" ");
                if(array[0].equals("done")) {
                    doneCommand(list,array);
                } else {
                    Task t = new Task(s);
                    addCommand(t);
                    list[counter++] = t;
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
            System.out.printf("[%s] %d. %s%n", t.getStatusIcon(), j , t.getDescription());
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

    public static void addCommand(Task t) {
        Duke.printHorizontalLine();
        System.out.printf("added: %s%n", t);
        Duke.printHorizontalLine();
    }
}
