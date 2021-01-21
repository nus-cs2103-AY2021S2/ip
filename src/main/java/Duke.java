import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    ____________________________________________________________";
        String terminate_input = "bye";
        String show_list = "list";
        String done = "done";
        String indentation = "    ";
        Scanner sc = new Scanner(System.in);
        System.out.println(line);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What I can do for you?");
        System.out.println(line);
        System.out.println();

        Task[] todo = new Task[100];
        int position = 0;

        while (true) {
            String input = sc.nextLine();
            System.out.println(line);
            if (input.equals(terminate_input)) {
                System.out.println(indentation + "Bye. Hope to see you again soon!");
                break;
            } else if (input.equals(show_list)) {
                System.out.println("    Here are the tasks in your list: ");
                for (int i = 1; i < position + 1; i++) {
                    System.out.println(indentation + i + ". " + todo[i - 1]);
                }
            } else if (input.length() > 4 && input.substring(0, 4).equals(done)) {
                int job_done = Integer.valueOf(input.substring(5));
                Task job = todo[job_done - 1];
                job.markAsDone();
                todo[job_done - 1] = job;
                System.out.println(indentation + "Nice! I've marked this task as done:");
                System.out.println(indentation + job);
            } else {
                Task job = new Task(input);
                System.out.println(indentation + "added: " + input);
                todo[position] = job;
                position += 1;
            }
            System.out.println(line);
            System.out.println();
        }
        System.out.println(line);
    }
}
