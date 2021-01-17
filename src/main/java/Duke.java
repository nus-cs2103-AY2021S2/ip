import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t   ____        _        \n"
                + "\t  |  _ \\ _   _| | _____ \n"
                + "\t  | | | | | | | |/ / _ \\\n"
                + "\t  | |_| | |_| |   <  __/\n"
                + "\t  |____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "\t--------------------------------\n";
        String greeting = "\t  Hello! I'm Duke\n" + "\t  What can I do for you?\n";
        String byeMessage = "\t  Bye. Hope to see you again soon!\n";
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontalLine + logo + greeting + horizontalLine);

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            System.out.println(horizontalLine + "\t  " + input + "\n" + horizontalLine);
            input = sc.nextLine();
        }
        System.out.println(horizontalLine + byeMessage + horizontalLine);

    }

}
