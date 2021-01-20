import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke myDuke = new Duke();
        myDuke.run();
    }

    private void run() {
        System.out.println("---------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("---------------------------------------------");
        Scanner input = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<>();

        while (input.hasNextLine()) {
            String s = input.nextLine();
            if (s.toLowerCase().equals("bye")) {
                System.out.println("---------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("---------------------------------------------");
                break;
            }
            else if (s.toLowerCase().equals("list")) {
                System.out.println("---------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                int len = myList.size();
                for (int i = 1; i < len + 1; i++) {
                    Task curTask = myList.get(i - 1);
                    System.out.println(i + "." + curTask);
                }
                System.out.println("---------------------------------------------");
            }
            else {
                String[] parts = s.split(" ", 2);
                String taskType = parts[0];

                if(parts.length == 1){
                    System.out.println("---------------------------------------------");
                    System.out.println(parts[0]);
                    System.out.println("---------------------------------------------");
                }

//                if (s.substring(0, 4).toLowerCase().equals("done")) {
                if (taskType.toLowerCase().equals("done")) {
//                    int taskNo = Integer.valueOf(s.substring(5, s.length()));
                    try {
                        int taskNo = Integer.valueOf(parts[1]);
                        //if (taskNo <= myList.size()) {
                        Task curTask = myList.get(taskNo - 1);
                        curTask.markAsDone();
                        System.out.println("---------------------------------------------");
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(curTask);
                        System.out.println("---------------------------------------------");
//                        } else {
//                            Task newTask = new Task(s);
//                            myList.add(newTask);
//                            System.out.println("---------------------------------------------");
//                            System.out.println("added: " + s);
//                            System.out.println("---------------------------------------------");
//                        }
                    } catch (Exception e){
                        System.out.println("---------------------------------------------");
                        System.out.println("Oops, unable to find task!");
                        System.out.println("---------------------------------------------");
                    }
                }
                else {
                    if (taskType.toLowerCase().equals("todo")){
                        ToDo newTask = new ToDo(parts[1]);
                        myList.add(newTask);
                        System.out.println("---------------------------------------------");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + myList.size() +" tasks in the list.");
                        System.out.println("---------------------------------------------");
                    }
                    else {
                        if (taskType.toLowerCase().equals("deadline")) {
                            String task = parts[1];
                            String[] details = task.split(" /by ", 2);
                            String description = details[0];
                            String by = details[1];
                            Deadline newTask = new Deadline(description, by);

                            myList.add(newTask);
                            System.out.println("---------------------------------------------");
                            System.out.println("Got it. I've added this task: ");
                            System.out.println("  " + newTask);
                            System.out.println("Now you have " + myList.size() +" tasks in the list.");
                            System.out.println("---------------------------------------------");
                        }
                        else{
                            if (taskType.toLowerCase().equals("event")){
                                String task = parts[1];
                                String[] details = task.split(" /at ", 2);
                                String description = details[0];
                                String time = details[1];
                                Event newTask = new Event(description, time);

                                myList.add(newTask);
                                System.out.println("---------------------------------------------");
                                System.out.println("Got it. I've added this task: ");
                                System.out.println("  " + newTask);
                                System.out.println("Now you have " + myList.size() +" tasks in the list.");
                                System.out.println("---------------------------------------------");
                            }
                        }
                    }

//                    Task newTask = new Task(s);
//                    myList.add(newTask);
//                    System.out.println("---------------------------------------------");
//                    System.out.println("added: " + s);
//                    System.out.println("---------------------------------------------");

                }
            }
        }
    }
}