import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DukeSimulator {
    private final String line = "    ____________________________________________________________\n";
    private final String help = line + "     These are the proper format of Duke's commands:\n"
            + "      -help\n"
            + "      -todo (insert task name here)\n"
            + "      -deadline (insert task name here) /by (insert time here)\n"
            + "      -event (insert event name here) /at (insert time here)\n"
            + "      eg: 'deadline typing /by 1pm'\n"
            + line;
    private List<Task> taskList;

    public DukeSimulator() {
        taskList = new ArrayList<Task>();
    }

    public void greeting() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String greeting = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
        System.out.print(line + greeting + line);
    }

    public void bye() {
        String byeMessage = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.print(byeMessage);
    }

    public void run() {
        greeting();
        Scanner sc = new Scanner(System.in);
        String command;
        command = sc.nextLine();
        while(!command.equals("bye")) {
            processCmd(command);
            command = sc.nextLine();
        }
        bye();
    }

    private void processCmd(String command) {
        try {
            String[] parsedCommand = command.split(" ", 2);
            if(parsedCommand[0].equals("list")) {
                printList();
            } else if(parsedCommand[0].equals("done")) {
                doneTask(parsedCommand[1]);
            } else if(parsedCommand[0].equals("delete")) {
                deleteTask(parsedCommand[1]);
            } else {
                addTask(command);
            }
        } catch (DukeException e) {
            System.out.print(line + e.toString() + line);
        }
    }

    private void addTask(String command) throws DukeException {
        String[] parsedCommand = command.split(" ", 2);
        if(parsedCommand.length == 1) {
            throw new DukeMissingDescriptionException(parsedCommand[0]);
        }
        Task t;
        if (parsedCommand[0].equals("todo")) {
            t = toDoMaker(parsedCommand[1]);
        } else if (parsedCommand[0].equals("deadline")) {
            String[] ppCmd = parsedCommand[1].split(" /by ", 2);
            t = new Deadline(ppCmd[0], ppCmd[1]);
        } else if (parsedCommand[0].equals("event")) {
            String[] ppCmd = parsedCommand[1].split(" /at ", 2);
            t = new Event(ppCmd[0], ppCmd[1]);
        } else {
            throw new DukeWrongCommandException(parsedCommand[0]);
        }
        taskList.add(t);
        String taskCount =
                String.format("     Now you have %d task(s) in the list\n",
                        taskList.size());
        String addTask = line + "     Got it. I've added this task:\n"
                + "\t" + t.toString() + "\n" + taskCount + line;
        System.out.print(addTask);
    }

    private Task toDoMaker(String command) throws DukeMissingDescriptionException {
        if(command.equals("")) {
            throw new DukeMissingDescriptionException("todo");
        } else {
            return new ToDo(command);
        }
    }

//    private Task deadlineMaker(String command) {
//
//    }

    private void printList() {
        int index = 1;
        System.out.print(line);
        for(Task t : taskList) {
            System.out.print(String.format("     %d. %s\n",
                    index++, t.toString()));
        }
        System.out.print(line);
    }

    public void doneTask(String s) {
        int taskNum = Integer.valueOf(s);
        Task t = taskList.get(taskNum - 1);
        t = t.finishTask();
        taskList.set(taskNum - 1, t);
        String statement = "     Nice! I've marked this task as done:\n"
                + String.format("\t%s\n", t.toString());
        System.out.print(line + statement + line);
    }

    public void deleteTask(String s) {
        int taskNum = Integer.valueOf(s);
        Task t = taskList.get(taskNum - 1);
        taskList.remove(taskNum - 1);
        String taskCount = String.format("     Now you have %d task(s) in the list\n",
                taskList.size());
        String deleteTask = line + "     Noted. I've removed this task:\n"
                + "\t" + t.toString() + "\n" + taskCount + line;
        System.out.print(deleteTask);
    }
}