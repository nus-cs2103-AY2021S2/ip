import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        DukeBot bot = new DukeBot("Duke");
        Scanner sc = new Scanner(System.in);
        System.out.println(bot);
        String command;

        while (sc.hasNext()) {
            command = sc.nextLine();
            String arr[] = command.split(" ", 2);
            String firstWord = arr[0];
            switch (firstWord) {
                case "bye":
                    System.out.println("\t\t" + "See you again soon!");
                    sc.close();
                    break;
                case "list":
                    bot.displayTasks();
                    break;
                case "done":
                    String num = arr[1];
                    bot.markAsDone(Integer.valueOf(num));
                    break;
                default:
                    bot.addTask(new Task(command));
            }
            if (command.equals("bye")) {
                break;
            }

        }
    }
}
