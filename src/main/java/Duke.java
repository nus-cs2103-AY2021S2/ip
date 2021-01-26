import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> currList = new ArrayList<>();

        //Duke starts up with an introduction
        System.out.println("Hey, I'm Duke!\n" + "How can I help you?");

        while (input.hasNextLine()) {
            String command = input.nextLine();
            String[] commandArr = command.split(" ", 2);

            if (command.equals("bye")) {
                //Duke exits when user types 'bye' command
                System.out.println("Aw. It was nice chatting with you! Don't forget me! :)");
                break;
            } else if (command.equals("list")) {
                //Every item is listed out when user types 'list' command
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < currList.size(); i++) {
                    Task item = currList.get(i);
                    System.out.println((i + 1) + "." + item.toString());
                }
            } else if ((commandArr[0]).equals("done")) {
                //If user enters an invalid task number, prompt a message
                if (Integer.parseInt(commandArr[1]) > currList.size()) {
                    System.out.println("Did you miscount your tasks? "
                            + "This task number isn't valid. Add it into my list!");
                    continue;
                }
                //If valid, change status of task and output done task
                Task item = currList.get(Integer.parseInt(commandArr[1]) - 1);
                item.markAsDone();
                System.out.println("Nice job! I've marked this task as done:\n"
                        + item.toString());
            } else if ((commandArr[0]).equals("delete")) {
                Task item = currList.remove(Integer.parseInt(commandArr[1]) - 1);
                System.out.println("Gotcha. I've removed this task:\n"
                        + item.toString()
                        + "\nNow you have " + currList.size() + " task(s) in the list.");
            } else {
                String taskType = commandArr[0];
                String wholeTask;
                Task item;
                switch (taskType) {
                case "todo":
                    if (commandArr.length == 1) {
                        System.out.println("Oops! The description of todo cannot be empty!");
                        continue;
                    }
                    wholeTask = commandArr[1];
                    item = new Todo(wholeTask);
                    break;
                case "deadline": {
                    if (commandArr.length == 1) {
                        System.out.println("Oops! The description of deadline cannot be empty!");
                        continue;
                    }
                    wholeTask = commandArr[1];
                    String[] taskArr = wholeTask.split("/");
                    item = new Deadline(taskArr[0], taskArr[1]);
                    break;
                }
                case "event": {
                    if (commandArr.length == 1) {
                        System.out.println("Oops! The description of event cannot be empty!");
                        continue;
                    }
                    wholeTask = commandArr[1];
                    String[] taskArr = wholeTask.split("/");
                    item = new Event(taskArr[0], taskArr[1]);
                    break;
                }
                default:
                    System.out.println("Oops! I don't know what this means! :(");
                    continue;
                }
                currList.add(item);
                System.out.println("Got it. I've added this task:\n"
                        + item.toString()
                        + "\nNow you have " + currList.size() + " task(s) in the list.");
            }
        }
    }
}

