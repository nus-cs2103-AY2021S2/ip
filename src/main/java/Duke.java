import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("   Hello there! I'm Duke, always here for you!");
        System.out.println("   How can I help you today?");

        Task[] arr = new Task[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            //System.out.println("input is: " + input + "\n");
            int first = input.indexOf(" ");
            String command;
            String date;
            if (first == -1) {
                command = input;
            } else {
                command = input.substring(0, first);
            }
            switch (command) {
                case "list":
                    System.out.println("___");
                    for (int i = 0; i < counter; i++) {
                        System.out.println("   " + (i + 1) + ". " + arr[i]);
                    }
                    System.out.println("___");
                    input = sc.nextLine();
                    break;

                case "done":
                    int task_No = Integer.parseInt(input.substring(first + 1));
                    arr[task_No - 1].doTask();
                    System.out.println("___\n   Nice! I've marked this task as done:");
                    System.out.println("      " + arr[task_No - 1] + "\n___");
                    input = sc.nextLine();
                    break;

                case "todo":
                    ToDo todo = new ToDo(input.substring(first).strip());
                    arr[counter] = todo;
                    counter++;
                    System.out.println("___\n   Got it! I've added this task:\n      " + todo);
                    System.out.println("   Now you have " + counter + " tasks in the list\n___");
                    input = sc.nextLine();
                    break;

                case "deadline":
                    int byDate = input.lastIndexOf("/by ");
                    date = input.substring(byDate + 4);
                    Deadline deadline = new Deadline(input.substring(first, byDate).strip(), date);
                    arr[counter] = deadline;
                    counter++;
                    System.out.println("___\n   Got it! I've added this task:\n      " + deadline);
                    System.out.println("   Now you have " + counter + " tasks in the list\n___");
                    input = sc.nextLine();
                    break;

                case "event":
                    int atDate = input.lastIndexOf("/at ");
                    date = input.substring(atDate + 4);
                    Event event = new Event(input.substring(first, atDate).strip(), date);
                    arr[counter] = event;
                    counter++;
                    System.out.println("___\n   Got it! I've added this task:\n      " + event);
                    System.out.println("   Now you have " + counter + " tasks in the list\n___");
                    input = sc.nextLine();
                    break;

//                default:
//                    System.out.println("___\n   added: " + input.substring(first) + "\n___");
//                    Task newTask = new Task(input);
//                    arr[counter] = newTask;
//                    counter++;
//                    input = sc.nextLine();
//                    break;
            }
        }

        System.out.println("___\n   Bye! Hope to see you again! :)\n___\n");
    }
}
