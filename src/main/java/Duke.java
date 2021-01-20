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
        String[] tasks = new String[100];
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
                    System.out.println(tasks[i]);
                }
                System.out.println("---------------------------------------" );
            } else {
//            Add to list
                tasks[taskNumber] = String.valueOf(taskNumber+1) + ". " + input;
                taskNumber++;
                System.out.println("\n---------------------------------------" );
                System.out.println("added: " + input);
                System.out.println("---------------------------------------" );

            }
        }
    }
}
