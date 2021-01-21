import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void greet() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it. I've added this task:" + "\n" + newTask.toString() +
                "\n" + "Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void listTask() {
        System.out.println("Here are the task in your list:");
        for(int i = 0; i < taskList.size(); ++i) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }
    }

    public static void markDone(int i) {
        Task task = taskList.get(i-1);
        task.done();
        System.out.println("Nice! I've marked this task as done: " + "\n" + task.toString());
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            if(input.startsWith("done")) {
                String[] spiltInput = input.split("\\s+");
                int taskNumber = Integer.parseInt(spiltInput[1]);
                markDone(taskNumber);
            } else if (input.startsWith("event")) {
                String[] spiltInput = input.split("\\s+");
                String time = "";
                String desc = "";
                int start = 0;
                for(int i = 1; i < spiltInput.length; ++i) {
                    if (spiltInput[i].equals("/at")){
                        start = i;
                        break;
                    }
                    if(desc.equals("")) {
                        desc += spiltInput[i];
                    } else {
                        desc = desc + " " + spiltInput[i];
                    }
                }
                for(int i = start+1; i < spiltInput.length; ++i) {
                    if(time.equals("")) {
                        time += spiltInput[i];
                    } else {
                        time = time + " " + spiltInput[i];
                    }
                }
                EventTask task = new EventTask(desc, time);
                addTask(task);
            } else if (input.startsWith("deadline")) {
                String[] spiltInput = input.split("\\s+");
                String time = "";
                String desc = "";
                int start = 0;
                for(int i = 1; i < spiltInput.length; ++i) {
                    if (spiltInput[i].equals("/by")){
                        start = i;
                        break;
                    }
                    if(desc.equals("")) {
                        desc += spiltInput[i];
                    } else {
                        desc = desc + " " + spiltInput[i];
                    }
                }
                for(int i = start+1; i < spiltInput.length; ++i) {
                    if(time.equals("")) {
                        time += spiltInput[i];
                    } else {
                        time = time + " " + spiltInput[i];
                    }
                }
                DeadlineTask task = new DeadlineTask(desc, time);
                addTask(task);
            } else if(input.startsWith("todo")) {
                String[] spiltInput = input.split("\\s+");
                String desc = "";
                for(int i = 1; i < spiltInput.length; ++i) {
                    if(desc.equals("")) {
                        desc += spiltInput[i];
                    } else {
                        desc = desc + " " + spiltInput[i];
                    }
                }
                ToDoTask task = new ToDoTask(desc);
                addTask(task);

            } else if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                listTask();
            } else {
                //addTask(input);
                System.out.println("End");
            }
        }
    }
}
