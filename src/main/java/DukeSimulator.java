import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DukeSimulator {
    private List<Task> list;

    public DukeSimulator() {
        list = new ArrayList<Task>();
    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "    ____________________________________________________________\n";
        //initialising greeting to be printed and print greeting.
        String greeting = line
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + line;
        System.out.print(greeting);

        Scanner sc = new Scanner(System.in);
        String command;

        List<Task> taskList = new ArrayList<Task>();
        int currIdx = 0;
        //while loop to echo commands of the user
        command = sc.nextLine();
        while(!command.equals("bye")) {
            //if command is list, we print out the to-do list.
            String[] parsedCommand = command.split(" ", 2);
            if(parsedCommand[0].equals("list")) {
                int index = 1;
                System.out.print(line);
                for(Task t : taskList) {
                    System.out.print(String.format("     %d. %s\n",
                            index++, t.toString()));

                }
                System.out.print(line);
            } else if(parsedCommand[0].equals("done")) {
                int taskNum = Integer.valueOf(parsedCommand[1]);
                Task t = taskList.get(taskNum - 1);
                t = t.finishTask();
                taskList.set(taskNum - 1, t);
                String statement = "     Nice! I've marked this task as done:\n"
                        + String.format("\t%s\n", t.toString());
                System.out.print(line + statement + line);

            } else if(parsedCommand[0].equals("delete")) {
                int taskNum = Integer.valueOf(parsedCommand[1]);
                Task t = taskList.get(taskNum - 1);
                taskList.remove(taskNum - 1);
                String taskCount = String.format("     Now you have %d task(s) in the list\n",
                        taskList.size());
                String deleteTask = line + "     Noted. I've removed this task:\n"
                        + "\t" + t.toString() + "\n" + taskCount + line;
                System.out.print(deleteTask);
            } else {
                Task t;
                if(parsedCommand[0].equals("todo")) {
                    t = new ToDo(parsedCommand[1]);
                } else if(parsedCommand[0].equals("deadline")) {
                    String[] ppCmd = parsedCommand[1].split(" /by ", 2);
                    t = new Deadline(ppCmd[0], ppCmd[1]);
                } else {
                    String[] ppCmd = parsedCommand[1].split(" /at ", 2);
                    t = new Event(ppCmd[0], ppCmd[1]);
                }
                taskList.add(t);
                String taskCount =
                        String.format("     Now you have %d task(s) in the list\n",
                                taskList.size());
                String addTask = line + "     Got it. I've added this task:\n"
                        + "\t" + t.toString() + "\n" + taskCount + line;
                System.out.print(addTask);
            }
            command = sc.nextLine();
        }

        //print out the bye message
        String byeMessage = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.print(byeMessage);
    }
}