import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Chatbot that provides todo-list function for users.
 */
public class Chatbot {
    private final List<Task> todo;

    Chatbot() {
        todo = new ArrayList<>();
    }

    /**
     * Print out all the things in the list.
     *
     * @param todo The list of things to do.
     */
    public static void printTodo(List<Task> todo) {
        System.out.print(Duke.horizontalLine);
        for (int i = 0; i < todo.size(); i++) {
            System.out.println("\t  " + (i + 1) + "." + todo.get(i).toString());
        }
        System.out.println(Duke.horizontalLine);
    }

    public void markDone(int order) {
        System.out.print(Duke.horizontalLine);
        System.out.println("\t  Nice! I've marked this task as done: ");
        System.out.println("\t\t[x] " + todo.get(order).getName());
        System.out.println(Duke.horizontalLine);
        todo.get(order).markDone();
    }

    public void addTask(Task newTask) {
        todo.add(newTask);
        System.out.print(Duke.horizontalLine);
        System.out.println("\t  Got it. I've added this task: ");
        System.out.println("\t\t" + newTask.toString());
        System.out.println("\t  Now you have " + todo.size() + " tasks in the list.");
        System.out.println(Duke.horizontalLine);
    }

    /**
     * Execute the chat bot.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] taskTimeSplit;
        Task newTask;
        while (!input.toLowerCase().equals("bye")) {  // exit only when user input "bye"
            String[] taskTypeSplit = input.split(" ");
            if (input.toLowerCase().equals("list")) {
                printTodo(todo);
            } else if (taskTypeSplit[0].toLowerCase().contains("done")) {
                int tempOrder = Integer.parseInt(taskTypeSplit[1]);
                if (taskTypeSplit.length == 2 && tempOrder > 0 && tempOrder <= todo.size()) {
                    markDone(tempOrder - 1);
                }
            } else if (taskTypeSplit[0].toLowerCase().equals("todo")) {
                newTask = new ToDo(input.substring(5), TaskType.TODO);
                addTask(newTask);
            } else if (taskTypeSplit[0].toLowerCase().equals("deadline")) {
                taskTimeSplit = input.split(" /by ");
                newTask = new Deadline(taskTimeSplit[0].substring(9), TaskType.DEADLINE, taskTimeSplit[1]);
                addTask(newTask);

            } else if (taskTypeSplit[0].toLowerCase().equals("event")) {
                taskTimeSplit = input.split(" /at ");
                newTask = new Event(taskTimeSplit[0].substring(6), TaskType.EVENT, taskTimeSplit[1]);
                addTask(newTask);
            }
            input = sc.nextLine();
        }
    }

}
