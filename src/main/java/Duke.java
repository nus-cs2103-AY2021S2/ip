import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> list;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! How can I help you?");

        Scanner scanner = new Scanner(System.in);
        list  = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye! See you soon :)");
                scanner.close();
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).toString());
                }
            } else if (input.startsWith("done") && (input.length() == 6)) {
                Task task = list.get(Character.getNumericValue(input.charAt(5)) - 1);
                task.markAsDone();
                System.out.println("Good job! You got " + task.description + " done!");
            } else {
//                String[] inputSplit = input.split(" ");
                Task newTask;
                if (input.startsWith("todo")) {
                    newTask = new TodoTask(input.substring(5));
                } else if (input.startsWith("deadline")) {
                    String[] inputSplit = input.split("/");
                    newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1), inputSplit[1].substring(3));
                } else {
                    String[] inputSplit = input.split("/");
                    newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1), inputSplit[1].substring(3));
                }
//                list.add(new Task(input));
                list.add(newTask);
                System.out.println("Added: " + newTask.toString());
            }
        }
    }
}
