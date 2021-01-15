import java.util.*;

public class Duke {

    public static List<Task> tasks = new ArrayList<>();

    public static void greeting() {
        partition();
        String logo = "    __  _____ _  ___   ___   _ ___ \n" +
                "    \\ \\/ /_ _| \\| \\ \\ / / | | | __|\n" +
                "     >  < | || .` |\\ V /| |_| | _| \n" +
                "    /_/\\_\\___|_|\\_| |_|  \\___/|___|\n";
        System.out.println("    Hi there! Welcome to\n" + logo);
        System.out.println("    What can I do for you today?");
        partition();
    }

    public static void farewell() {
        partition();
        System.out.println("    Goodbye. Have a nice day!!");
        partition();
    }

    public static void partition() {
        System.out.println("    ---------------------------");
    }

    public static void addTask(String userInput) {
        tasks.add(new Task(userInput));
        partition();
        System.out.println("    added: " + userInput);
        partition();
    }

    public static void listTasks() {
        partition();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); ++i) {
            System.out.println("    " + i + "." + tasks.get(i - 1).toString());
        }
        partition();
    }

    public static void markTaskAsDone(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        partition();
        if (task.isDone()) {
            System.out.println("    You have already completed this task:");
        } else {
            task.markAsDone();
            System.out.println("    Congratulations! You have completed this task:");
        }
        System.out.println("        " + task.toString());
        partition();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greeting();

        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            String[] userInputArr = userInput.split(" ");

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                listTasks();
            } else if (userInputArr[0].equals("done")) {
                int taskIndex = Integer.parseInt(userInputArr[1]);
                markTaskAsDone(taskIndex);
            } else {
                addTask(userInput);
            }
        }

        farewell();
        sc.close();
    }
}