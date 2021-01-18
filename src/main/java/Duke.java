import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();

    }

    private List<Task> checkList = new ArrayList<>();

    private void start() {
        Scanner sc = new Scanner(System.in);

        echo(List.of("Hello! I'm Duke","What can I do for you?"));

        for (;;) {
            String command = sc.next();
            String input = sc.nextLine().strip();

            switch(command) { 
                case "bye": 
                    echo("Bye. Hope to see you again soon!");  
                    return; 
                case "list": 
                    displayList(checkList);
                    break; 
                case "done":
                    completeTask(input);
                    break;
                case "todo":
                    checkList.add(Todo.createTodo(input));
                    taskAdded();
                    break;
                case "deadline":
                    break;
                case "event":
                    break;
                default: 
                    echo("invalid input");
            } 
        }
    }

    private void echo(String s) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t  " + s);
        System.out.println("\t____________________________________________________________");
    }

    private void echo(List<String> lst) {
        System.out.println("\t____________________________________________________________");
        for (String s : lst) {
            System.out.println("\t  " + s);
        }
        System.out.println("\t____________________________________________________________");
    }

    private void displayList(List<Task> lst) {
        System.out.println("\t____________________________________________________________");
        System.out.println("Here are the tasks in your list:");

        int num = 1;
        for (Task t : lst) {
            System.out.printf("\t  %d. %s\n", num++, t);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    private void completeTask(String s) {
        int taskNum = Integer.parseInt(s);
        Task t = checkList.get(taskNum-1);
        t.completed();
        
        echo(List.of("Nice! I've marked this task as done:", t.toString()));
    }

    private void taskAdded() {
        echo(List.of("Got it. I've added this task:",
                checkList.get(checkList.size()-1).toString(),
                "Now you have "+checkList.size()+" tasks in the list."));
    }

}
