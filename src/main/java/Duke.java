import java.util.*;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        List<Task> taskList = new ArrayList<>();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();

            String[] tokens = input.split(" ");

            if (tokens[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");

            } else if (tokens[0].equals("list")) {
                for (int i = 0; i < taskList.size() ; i++ ) {
                    System.out.println(i + 1 + "." + taskList.get(i).getStatusIcon() + " " + taskList.get(i));
                }

            } else if(tokens[0].equals("done")) {
                Task task = taskList.get(Integer.parseInt(tokens[1]) - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + task.getStatusIcon() + " " + task.toString());

            } else {
                Task task = new Task(input);
                taskList.add(task);
                System.out.println("added: " + task.toString());
            }
        }
    }
}
