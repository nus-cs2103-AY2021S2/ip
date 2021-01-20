import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("---------------------------------------\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );

        System.out.println("---------------------------------------" );

//        Initialize Task container of space 100
        Task[] tasks = new Task[100];
        int taskNumber = 0;

        while(true) {
            Scanner scan = new Scanner(System.in);
            String input;
            input = scan.nextLine();

            if (input.equals("bye")) {
                System.out.println("\n---------------------------------------" );
                System.out.println("Bye. Sayonara and goodbye!");
                System.out.println("---------------------------------------" );
                break;
            } else if (input.equals("list")) {
//                Display list
//                Numbers should change accordingly when deleted (For future Ref.)
                System.out.println("\n---------------------------------------" );
                for (int i = 0; i < taskNumber; i++) {
                    System.out.println(String.valueOf(i+1) + " [" + tasks[i].getStatusIcon() + "] " + tasks[i].name);
                }
                System.out.println("---------------------------------------" );
            } else if (input.split(" ")[0].equals("done")) {
//                Checks if first word is "done"
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markDone();

                System.out.println("\n---------------------------------------" );
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println("[" + tasks[index].getStatusIcon() + "] " + tasks[index].name);
                System.out.println("---------------------------------------" );
            } else {
//            Add to list
                tasks[taskNumber] = new Task(input);
                taskNumber++;

                System.out.println("\n---------------------------------------" );
                System.out.println("added: " + input);
                System.out.println("---------------------------------------" );

            }
        }
    }
}
