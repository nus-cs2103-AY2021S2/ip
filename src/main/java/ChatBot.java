import java.util.*;

public class ChatBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Task taskList = new Task();

        //Greeting the user
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                //inputs is bye, terminate the chat bot
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                //input is to read the list
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).get(0).equals("Done")) {
                        System.out.println ((i+1) + ".[X] " + (taskList.get(i).get(1)));
                    } else {
                        System.out.println ((i+1) + ".[ ] " + (taskList.get(i).get(1)));
                    }
                }
            } else if (isDone(input)) {
                //input is to finish a task
                String[] inputDone = input.split(" ");
                int index = Integer.parseInt(inputDone[1]) - 1;
                taskList.taskDone(index);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[X] " + taskList.get(index).get(1));

            } else {
                //inputs are tasks, add into arrayList
                taskList.addTask((input));
                System.out.println("added: " + input);
            }


        }
    }
    public static boolean isDone(String input) {
        if (input.charAt(0) == ('d') &&
                input.charAt(1) == ('o') &&
                input.charAt(2) == 'n' &&
                input.charAt(3) == 'e' &&
                input.charAt(4) == ' ' &&
                (input.charAt(5) == '0' ||
                        input.charAt(5) == '1' ||
                        input.charAt(5) == '2' ||
                        input.charAt(5) == '3' ||
                        input.charAt(5) == '4' ||
                        input.charAt(5) == '5' ||
                        input.charAt(5) == '6' ||
                        input.charAt(5) == '7' ||
                        input.charAt(5) == '8' ||
                        input.charAt(5) == '9' )
                        ) {
                return true;
        } else {
            return false;
        }
    }
}
