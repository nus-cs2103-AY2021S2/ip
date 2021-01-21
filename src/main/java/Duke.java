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
        System.out.println("Hello Master. Nice to meet you, my name is " + name);
        System.out.println("How may I be of service, Master?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            input = sc.nextLine();

            while (!input.equals("bye")) {
                try {
                    String[] inputWords = input.split(" ");
                    String action = inputWords[0];

                    if (input.equals("list")) {
                        System.out.println("Here is a list of your tasks:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(i + 1 + "." + taskList.get(i).toString());
                        }
                        input = sc.nextLine();
                    } else if (action.equals("done")) {
                        if (inputWords.length < 2) {
                            throw new MissingArgumentException("Wrong number of arguments");
                        }
                        int idx = Integer.parseInt(inputWords[1]);
                        System.out.println(taskList.get(idx - 1).markCompleted());
                        input = sc.nextLine();
                    } else {
                        Task newTask = new Task(input);
                        taskList.add(newTask);
                        System.out.println("added: " + newTask.getInfo());
                        input = sc.nextLine();
                    }
                } catch (MissingArgumentException error) {
                    System.out.println("Missing arguments");
                    input = sc.nextLine();
                }
            }
            System.out.println("Have a good day Master.");
            break;
        }
        System.exit(1);
    }
}
