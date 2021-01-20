import java.io.*;
import java.util.ArrayList;
import java.util.List;

// @@author: VRSoorya (??)
// adapted from GitHub repo nus-cs2103-AY2021S2/ip

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String logo = "           ___  _           ,_,\n"
                + "         |  _  | |   ___   / /\n"
                + "         | | | | |  / _ \\ =; ;=,\n"
                + "         | |_| | |_| |_| \\ | |\n"
                + "          \\___/\\___/---;_| |_|\n";

        String border = " --*---*---*---*---*---*---*---*---*---*--";
        System.out.println(logo + "\n" + border + "\n  Hey there, Olaf here!" +
                "\n  What will we be doing today?");

        while(true) {
            String command = bf.readLine();
            if(command.toLowerCase().equals("bye")){
                String out = "Aww hope to see you soon, goodbye!";
                System.out.println(border + "\n  " + out + "\n" + border);
                break;
            } else if(command.toLowerCase().equals("list")){
                System.out.println(border + "\n  Here are your pending tasks:\n");
                // adapted from: https://howtodoinjava.com/java/collections/arraylist/iterate-through-objects/
                int idx = 0;
                while (tasks.size() > idx){
                    System.out.printf("  %s. %s\n",String.valueOf(idx+1),tasks.get(idx++));
                }
                System.out.println(border);
            } else if(command.toLowerCase().startsWith("done")){
                int idx = Integer.parseInt(command.split(" ")[1]);
                tasks.get(idx-1).markAsDone();
                int undone = tasks.stream()
                        .mapToInt(Task::isNotDone)
                        .reduce(0, Integer::sum);
                System.out.println(border + "\n  Great job! You're done with:\n");
                System.out.printf("  %s. %s\n",String.valueOf(idx), tasks.get(idx-1));
                System.out.printf("\n  Now %o tasks are left to be done!\n", undone);
                System.out.println(border);
            } else if(command.toLowerCase().startsWith("todo")){
                try {
                    String expression = command.split(" ", 2)[1];
                    tasks.add(new Todo(expression));
                    int total = tasks.size();
                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(border + "\n  Okie added new task:\n");
                    System.out.printf("  %o. %s\n", total, tasks.get(total-1));
                    System.out.printf("\n  Total %o tasks, only %o left to be done!\n", total, undone);
                    System.out.println(border);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! Please add a task description as follows:\n");
                    System.out.println("  todo <task description>\n" + border);
                }
            } else if(command.toLowerCase().startsWith("deadline")){
                try {
                    String expression = command.split(" ", 2)[1];
                    String[] parts = expression.split("/by", 2);
                    tasks.add(new Deadline(parts[0], parts[1]));
                    int total = tasks.size();
                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(border + "\n  Okie added new task:\n");
                    System.out.printf("  %o. %s\n", total, tasks.get(total-1));
                    System.out.printf("\n  Total %o tasks, only %o left to be done!\n", total, undone);
                    System.out.println(border);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! Please add a deadline using" +
                            "\n  the 24-hour time format as follows:\n");
                    System.out.println("  deadline <task description> /by <DD-MM-YYYY HHMM>\n" + border);
                }
            } else if(command.toLowerCase().startsWith("event")){
                try {
                    String expression = command.split(" ", 2)[1];
                    String[] parts = expression.split("/at", 2);
                    tasks.add(new Event(parts[0], parts[1]));
                    int total = tasks.size();
                    int undone = tasks.stream()
                            .mapToInt(Task::isNotDone)
                            .reduce(0, Integer::sum);
                    System.out.println(border + "\n  Okie added new task:\n");
                    System.out.printf("  %o. %s\n", total, tasks.get(total-1));
                    System.out.printf("\n  Total %o tasks, only %o left to be done!\n", total, undone);
                    System.out.println(border);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(border + "\n  Oops! Please add an event using" +
                            "\n  the 24-hour time format as follows:\n");
                    System.out.println("  event <task description> /at <DD-MM-YYYY HHMM> to <DD-MM-YYYY HHMM>\n" + border);
                }
            } else {
                System.out.println(border + "\n  Hmm I don't understand :(" +
                        "\n  Please use the following to update me!\n");
                System.out.println("    todo <task description>\n      \\_ adds a task to be done");
                System.out.println("    deadline <task description> /by <DD-MM-YYYY>\n      \\_ adds a deadline");
                System.out.println("    event <task description> /at <DD-MM-YYYY HHMM> to <DD-MM-YYYY HHMM>" +
                        "\n      \\_ adds an event");
                System.out.println("    list\n      \\_ lists all tasks, deadlines and events");
                System.out.println("    done <task number>\n      \\_ marks a task as done");
                System.out.println("    bye\n      \\_ exists this application\n" + border);
            }
        }
    }
}
