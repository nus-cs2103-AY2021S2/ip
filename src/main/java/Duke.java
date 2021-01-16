import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String TOP_BORDER = "╭--------------------------------------------╮";
    private static final String BTM_BORDER = "╰|╱ -----------------------------------------╯";
    private static final String PADDING = "  ";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        printGreeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            readCommand(input);
            input = sc.nextLine();
        }
        sc.close();

        printBye();
    }

    public static void readCommand(String cmd) {
        if (cmd.equals("list")) {
            // list
            displayList();
        } else if (cmd.split(" ")[0].equals("done") && cmd.length() > 5 && isNumber(cmd.split(" ")[1])) {
            // done
            int taskNum = Integer.parseInt(cmd.split(" ")[1]) - 1;
            Task doneTask = taskList.get(taskNum);
            doneTask.markDone();
            displayDoneMessage(doneTask);
        } else {
            // add to list
            addToList(cmd);
        }
    }

    private static void displayDoneMessage(Task task) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Well done human on completing " + task.toString().substring(4) + "!");
        System.out.println(PADDING + "I have marked it as done.");
        System.out.println(PADDING + PADDING + task);
        System.out.println(BTM_BORDER);
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static void displayList() {
        System.out.println(TOP_BORDER);
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(PADDING + i + ". " + taskList.get(i - 1));
        }
        System.out.println(BTM_BORDER);
    }

    private static void printGreeting() {
        String logo = "              .--.    .-.         .-.   \n" +
                "             : .-'    : :         : :   \n" +
                " .--.  .--.  : `;.--. : `-.  .--. : `-. \n" +
                "'  ..'' .; ; : :' '_.'' .; :' '_.'' .; :\n" +
                "`.__.'`.__,_;:_;`.__.'`.__.'`.__.'`.__.'";
        System.out.println(logo);
        String greeting = " ╭------------------------------------------------------------------╮\n"
                        + " |  Hello! I'm cafebeb, here to help you keep track of measly tasks | \n"
                        + " |  in your mundane human life. How may I help you today?           |\n"
                        + " ╰|╱ ---------------------------------------------------------------╯  \n";

        System.out.println(greeting);
    }

    private static void printBye() {
        String farewell = " ╭---------------------------------------╮\n"
                        + " |  Bye! Hope you complete your tasks!   |\n"
                        + " ╰|╱ ------------------------------------╯\n";
        System.out.println(farewell);
    }

    private static void addToList(String input) {
        taskList.add(new Task(input));
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "added: " + input);
        System.out.println(BTM_BORDER);
    }
}

