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
            if (sc.hasNextLine()) {
                input = sc.nextLine();
            } else {
                break;
            }
        }

        sc.close();

        if (input.equals("bye")) {
            printBye();
        }
    }

    public static void readCommand(String fullCmd) {
        String[] fullCmdStrArray = fullCmd.split(" ");
        String cmd = fullCmdStrArray[0];
        switch(cmd) {
            case "list":
                displayList();
                break;
            case "done":
                if (fullCmd.length() > 5 && isNumber(fullCmdStrArray[1])) {
                    int taskNum = Integer.parseInt(fullCmdStrArray[1]) - 1;
                    Task doneTask = taskList.get(taskNum);
                    doneTask.markDone();
                    displayDoneMessage(doneTask);
                }
                break;
            case "todo":
                String taskName = fullCmd.substring(5); // remove "todo "
                TodoTask newTodoTask = new TodoTask(taskName);
                addToList(newTodoTask);
                break;
            case "event":
                String eTaskDetails = fullCmd.substring(6); // remove "event "
                String[] eTaskDetailsArray = eTaskDetails.split(" /at ");
                String eTaskName = eTaskDetailsArray[0];
                String eTaskDate = eTaskDetailsArray[1];
                EventTask newEventTask = new EventTask(eTaskName, eTaskDate);
                addToList(newEventTask);
                break;
            case "deadline":
                String dTaskDetails = fullCmd.substring(9); // remove "deadline "
                String[] dTaskDetailsArray = dTaskDetails.split(" /by ");
                String dTaskName = dTaskDetailsArray[0];
                String dTaskDate = dTaskDetailsArray[1];
                DeadlineTask newDeadlineTask = new DeadlineTask(dTaskName, dTaskDate);
                addToList(newDeadlineTask);
                break;
        }
    }

    private static void displayDoneMessage(Task task) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Well done human on completing " + task.getTaskName() + "!");
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
        System.out.println(PADDING + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(PADDING + i + ". " + taskList.get(i - 1));
        }
        System.out.println(BTM_BORDER);
    }

    private static void addToList(Task task) {
        taskList.add(task);

        System.out.println(TOP_BORDER);
        System.out.println(PADDING + "Got it: I've added this task:");
        System.out.println(PADDING + PADDING + task);
        System.out.println(PADDING + "Now you have " + taskList.size() + (taskList.size() == 1 ? " task " : " tasks ") + "in your list.");
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
                        + " |  Hello! I'm cafebeb, here to help you keep track of measly tasks |\n"
                        + " |  in your mundane human life. How may I help you today?           |\n"
                        + " ╰|╱ ---------------------------------------------------------------╯\n";

        System.out.println(greeting);
    }

    private static void printBye() {
        String farewell = " ╭---------------------------------------╮\n"
                        + " |  Bye! Hope you complete your tasks!   |\n"
                        + " ╰|╱ ------------------------------------╯";
        System.out.println(farewell);
    }
}

