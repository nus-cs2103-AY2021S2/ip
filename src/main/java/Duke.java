import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String partingLine = "_____________________________"
                + "_______________________________";

        System.out.println("Hello from\n" + logo);
        System.out.println(partingLine);
        System.out.println("Sup. I am Duke.");
        System.out.println("What do you want?");
        System.out.println(partingLine);

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(partingLine);
            if (input.equals("list")) {
                System.out.println("Here are the tasks"
                        + " in your list: ");
                for (int i = 0; i < tasks.size(); i++) {
                    Task tempTask = tasks.get(i);
                    System.out.println(" " + (i + 1) + ".["
                            + tempTask.getStatusIcon() + "] "
                            + tempTask);
                }
            } else if (isDoneCommand(input)){
                int index = Character
                        .getNumericValue(input.charAt(5)) - 1;
                if (index < tasks.size()) {
                    Task completedTask = tasks.get(index);
                    completedTask.complete();
                    System.out.println("Nice! "
                            + "I've marked this task as done: ");
                    System.out.println("  ["
                            + completedTask.getStatusIcon()
                            + "] " + completedTask);
                }
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(" added: " + newTask);
            }
            System.out.println(partingLine);
            input = sc.nextLine();
        }
        System.out.println(partingLine);
        System.out.println(" Seeya.");
        System.out.println(partingLine);
    }

    /**
     * This method judges whether input string is a valid
     * command that contains keyword "done".
     * @param input is the string to be tested
     * @return true if the input is valid, false otherwise
     */
    public static boolean isDoneCommand(String input) {
        return (input.length() > 5
                && input.substring(0, 5).equals("done ")
                && Character.isDigit(input.charAt(5)));
    }
}
