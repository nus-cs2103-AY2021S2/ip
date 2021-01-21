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
                if (Integer.valueOf(commandArr[1]) > currList.size()) {
                    System.out.println("Did you miscount your tasks? "
                            + "This task number isn't valid. Add it into my list!");
                    continue;
                }
                //If valid, change status of task and output done task
                Task item = currList.get(Integer.valueOf(commandArr[1]) - 1);
                item.markAsDone();
                System.out.println("Nice job! I've marked this task as done: \n"
                        + item.toString());
            } else {
                currList.add(new Task(command));
                System.out.println("Got it. I've added this task: " + command);
            }
        }
    }
}

