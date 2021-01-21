import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String name = "Duke";
        String input;
        ArrayList<Task> taskList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hi! Nice to meet you, I'm " + name);
        System.out.println("How may I help you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            input = sc.nextLine();

            while (!input.equals("bye")) {
                String[] inputWords = input.split(" ");
                String action = inputWords[0];

                if (input.equals("list")) {
                    System.out.println("Here is a list of your tasks:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i+1 + "." + taskList.get(i).toString());
                    }
                    input = sc.nextLine();
                } else if (input.equals("blah")) {
                    System.out.println("blah");
                    input = sc.nextLine();
                } else {
                    Task newTask = new Task(input);
                    taskList.add(newTask);
                    System.out.println("added: " + newTask.getInfo());
                    input = sc.nextLine();
                }
            }
            System.out.println("Goodbye! Have a nice day!");
        }
        System.exit(1);
    }
}
