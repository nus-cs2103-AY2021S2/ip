import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "    ____________________________________________________________\n";
        //initialising greeting to be printed and print greeting.
        String greeting = line
                + "    Hello! I'm Duke\n"
                + "    What can I do for you?\n"
                + line;
        System.out.print(greeting);

        Scanner sc = new Scanner(System.in);
        String command;

        List<Task> toDoList = new ArrayList<Task>();
        int currIdx = 0;
        //while loop to echo commands of the user
        command = sc.nextLine();
        while(!command.equals("bye")) {
            //if command is list, we print out the to-do list.
            String[] parsedCommand = command.split(" ", 2);
            if(parsedCommand[0].equals("list")) {
                int index = 1;
                System.out.print(line);
                for(Task t : toDoList) {
                    System.out.print(String.format("    %d. [%s] %s\n",
                            index++, t.getStatusIcon(), t.toString()));

                }
                System.out.print(line);
            } else if(parsedCommand[0].equals("done")) {
                int taskNum = Integer.valueOf(parsedCommand[1]);
                Task t = toDoList.get(taskNum - 1);
                t = t.finishTask();
                toDoList.set(taskNum - 1, t);
                String statement = "     Nice! I've marked this task as done:\n" + "" +
                        String.format("\t [%s] %s\n", t.getStatusIcon(), t.toString());
                System.out.print(line + statement + line);

            } else {
                toDoList.add(new Task(command));
                System.out.print(line + "    added: " + command + "\n" + line);
            }
            command = sc.nextLine();
        }

        //print out the bye message
        String byeMessage = line
                + "    Bye. Hope to see you again soon!\n"
                + line;
        System.out.print(byeMessage);
    }
}
