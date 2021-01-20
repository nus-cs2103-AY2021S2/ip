import java.util.Scanner;
import java.util.List;

public class ToDoBeast {
    public static void main(String[] args) {
        String line = "\t________________________________________________________________\n";
        String logo = "                                                     \n" +
                "\t88                                                   \n" +
                "\t88                                            ,d     \n" +
                "\t88                                            88     \n" +
                "\t88,dPPYba,   ,adPPYba, ,adPPYYba, ,adPPYba, MM88MMM  \n" +
                "\t88P'    \"8a a8P_____88 \"\"     `Y8 I8[    \"\"   88     \n" +
                "\t88       d8 8PP\"\"\"\"\"\"\" ,adPPPPP88  `\"Y8ba,    88     \n" +
                "\t88b,   ,a8\" \"8b,   ,aa 88,    ,88 aa    ]8I   88,    \n" +
                "\t8Y\"Ybbd8\"'   `\"Ybbd8\"' `\"8bbdP\"Y8 `\"YbbdP\"'   \"Y888  \n" +
                "\t                                                     \n" +
                "\t                                                     \n";
        String greeting = line + logo + "\tWelcome to ToDoBeast, your best productivity task tracker tool!\n"
                + "\tLet's get this bread! How would you like to be productive today?\n" + line;
        System.out.println(greeting);

        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printTaskList(taskManager.getTaskList());
            } else {

                System.out.println(line + "\t" + userInput + "\n" + line);
                userInput = sc.nextLine();
            }

        }

        String exitMsg = line + "\tThis app may have stopped but the grind never stops.\n\tSee you again soon!\n" + line;
        System.out.println(exitMsg);
        System.exit(0);
    }

    public static void printTaskList(List<Task> taskList) {
        int count = 1;
        for (Task task : taskList) {
            System.out.println(count++ + ". " + task);
        }
    }
}
