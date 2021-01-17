import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("**************************\nHey, I am Duke.\nHow can I help you?\n" +
                "**************************");

        Scanner sc= new Scanner(System.in);
        String command = sc.nextLine();

        while(!command.equals("bye")){
            System.out.println("   >>> " + command);
            command = sc.nextLine();
        }

        System.out.println("**************************\n" +
                       "Goodbye and see you soon!\n" +
                       "**************************");

    }
}
