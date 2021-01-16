import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        List<Task> store = new ArrayList<Task>();
        Scanner scan = new Scanner(System.in);
        while(true) {
            String command = scan.nextLine();
            int index = command.indexOf(' ');
            int findSlash = command.indexOf('/');
            if (index > -1) {
                String type = command.substring(0, index);
                switch (type) {
                    case "todo":
                        String descriptionTask = command.substring(index+1);
                        ToDo newToDo = new ToDo(descriptionTask);
                        store.add(newToDo);
                        System.out.println("Got it. I've added this task:\n" + newToDo.toString() + "\nNow you have "
                        + store.size() + " tasks in the list");
                        break;
                    case "deadline":
                        String descriptionDeadline = command.substring(index+1, findSlash-1);
                        String date = command.substring(findSlash+4);
                        Deadline newDeadline = new Deadline(descriptionDeadline, date);
                        store.add(newDeadline);
                        System.out.println("Got it. I've added this task:\n" + newDeadline.toString() +
                                "\nNow you have " + store.size() + " tasks in the list");
                        break;
                    case "event":
                        String descriptionEvent = command.substring(index+1, findSlash-1);
                        String time = command.substring(findSlash+4);
                        Event newEvent = new Event(descriptionEvent, time);
                        store.add(newEvent);
                        System.out.println("Got it. I've added this task:\n" + newEvent.toString() +
                                "\nNow you have " + store.size() + " tasks in the list");
                        break;
                    case "done":
                        int number = Integer.parseInt(command.substring(index+1));
                        Task toMark = store.get(number - 1);
                        String response = toMark.markAsDone();
                        System.out.println(response);
                        break;
                }
            } else {
                switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for(int i =0; i < store.size(); i++) {
                            System.out.println((i + 1) + "." + store.get(i).toString());
                        }
                        break;
                }
            }
            if (command.equals("bye")) {
                break;
            }
        }
    }
}
