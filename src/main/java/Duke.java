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
                char caseType = input.charAt(0);
                switch (caseType) {
                    case 'd':
                        int i1 = input.indexOf("/");
                        String by = input.substring(i1 + 3);
                        String d1 = input.substring(8, i1 - 1);
                        Deadline ddl = new Deadline(d1, by);
                        todo[position] = ddl;
                        break;
                    case 'e':
                        int i2 = input.indexOf("/");
                        String at = input.substring(i2 + 3);
                        String d2 = input.substring(5, i2 - 1);
                        Event event = new Event(d2, at);
                        todo[position] = event;
                        break;
                    case 't':
                        String d3 = input.substring(4, input.length());
                        Todo t = new Todo(d3);
                        todo[position] = t;
                        break;
                    default:
                        break;
                }
                int total = position + 1;
                System.out.println(indentation + "Got it. I've added this task:");
                System.out.println(indentation + todo[position]);
                System.out.println(indentation + "Now you have " + total + " tasks in the list.");
                position += 1;
            }
            System.out.println(line);
            System.out.println();
        }
        System.out.println(line);
    }
}
