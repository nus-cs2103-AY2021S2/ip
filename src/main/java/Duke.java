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
        System.out.println("Hello Master. Nice to meet you, my name is " + name + ".");
        System.out.println("How may I be of service, Master?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            input = sc.nextLine();

            while (!input.equals("bye")) {
                try {
                    String[] inputWords = input.split(" ");
                    String action = inputWords[0];

                    if (input.equals("list")) {
                        System.out.println("Here is a list of your tasks, Master:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(i + 1 + ". " + taskList.get(i).toString());
                        }
                        input = sc.nextLine();
                    } else if (action.equals("done")) {
                        if (inputWords.length < 2) {
                            throw new MissingArgumentException("Wrong number of arguments");
                        }
                        int idx = Integer.parseInt(inputWords[1]);
                        System.out.println(taskList.get(idx - 1).markCompleted());
                        input = sc.nextLine();
                    } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")){
                        if (inputWords.length < 2) {
                            throw new MissingArgumentException("Wrong number of arguments");
                        }
                        System.out.println("Master, I've added this task as requested:");
                        StringBuilder sb = new StringBuilder();
                        String taskItem;

                        if (action.equals("todo")) {
                            for (int i = 1; i < inputWords.length; i++) {
                                sb.append(" ");
                                sb.append(inputWords[i]);
                            }
                            taskItem = sb.toString();
                            ToDo toDoItem = new ToDo(taskItem);
                            taskList.add(toDoItem);
                            System.out.println(toDoItem.toString());
                        } else {
                            int slashIdx = 0;

                            for (int i = 0; i < inputWords.length; i++) {
                                if (inputWords[i].contains(Character.toString('/'))) {
                                    slashIdx = i;
                                }
                            }
                            for (int j = 0; j < slashIdx; j++) {
                                sb.append(" ");
                                sb.append(inputWords[j]);
                            }
                            taskItem = sb.toString();
                            StringBuilder sbSlash = new StringBuilder();

                            for (int k = slashIdx + 1; k < inputWords.length; k++) {
                                sbSlash.append(" ");
                                sbSlash.append(inputWords[k]);
                            }

                            if (action.equals("deadline")) {
                                Deadline deadlineItem = new Deadline(taskItem, sbSlash.toString());
                                taskList.add(deadlineItem);
                                System.out.println(deadlineItem.toString());
                            } else {
                                Event eventItem = new Event(taskItem, sbSlash.toString());
                                taskList.add(eventItem);
                                System.out.println(eventItem.toString());
                            }
                        }
                        if (taskList.size() == 1) {
                            System.out.println("\nYou have " + taskList.size() + " task in the list now, Master.");
                        } else {
                            System.out.println("\nYou have " + taskList.size() + " tasks in the list now, Master.");
                        }

                        input = sc.nextLine();
                    }
                } catch (MissingArgumentException error) {
                    System.out.println("Missing arguments");
                    input = sc.nextLine();
                }
            }
            System.out.println("Have a good day, Master.");
            break;
        }
        System.exit(1);
    }
}
