import java.util.Scanner;

public class Duke {
    static String welcome = " __________________________ \n"
            + "|  HI! THIS IS             |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  What can I do for you?  |\n"
            + "|__________________________|\n";

    static String bye = " __________________________ \n"
            + "|  GOOD BYE!               |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  Always with you.        |\n"
            + "|__________________________|\n";

    static String horizontalLine = "____________________________________________________________\n";



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task currTask;
        JobList list = new JobList();

        System.out.println(welcome);
        String command = sc.nextLine();
        while (!command.equalsIgnoreCase("bye")) {
            System.out.print(horizontalLine);
            if (command.equalsIgnoreCase("list")) {
                System.out.print(list.formatList());
            } else {
                currTask = new Task(command);
                list.addJob(currTask);
                System.out.print(StringParser.newLiner("Added: " + currTask.getDescription(), 60));
            }
            System.out.println(horizontalLine);
            command = sc.nextLine();
        }
        System.out.println(bye);
    }
}
