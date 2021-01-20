import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws Exception {
        initialisation();
        Duke myDuke = new Duke();
        myDuke.run();
    }
    private static void initialisation(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("---------------------------------------------");
    }
    private void run() throws DukeException {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<>();
        // initialize task list
        ArrayList<String> taskList = new ArrayList<>();
        taskList.add("todo");
        taskList.add("deadline");
        taskList.add("event");
        taskList.add("done");
        taskList.add("delete");

        while (input.hasNextLine()) {
            String s = input.nextLine();
            if (s.toLowerCase().equals("bye")) {
                exit();
                break;
            } else if (s.toLowerCase().equals("list")) {
                displayList(myList);
            } else {
                try {
                    executeTask(s, taskList, myList);
                } catch (DukeException e) {
                    System.out.println("---------------------------------------------");
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("---------------------------------------------");
                }
            }
        }
    }

    private void executeTask(String s, ArrayList<String> taskList, ArrayList<Task> myList) throws DukeException{
        String[] parts = s.split(" ", 2);
        String taskType = parts[0];

        if (!taskList.contains(taskType)) {
            throw new DukeException("invalid input");
        } else{
            if (taskType.toLowerCase().equals("done")) {
                completeTask(parts, myList);
            }else {
                if (taskType.toLowerCase().equals("delete")) {
                    deleteTask(parts, myList);
                } else {
                    if (taskType.toLowerCase().equals("todo")) {
                        try{
                            addToDo(parts, myList);
                        } catch (DukeException e) {
                            System.out.println("---------------------------------------------");
                            System.out.println("OOPS!!! The description of a todo cannot be empty.");
                            System.out.println("---------------------------------------------");
                        }
                    } else {
                        if (taskType.toLowerCase().equals("deadline")) {
                            addDeadline(parts, myList);
                        } else {
                            if (taskType.toLowerCase().equals("event")) {
                                addEvent(parts, myList);
                            }
                        }
                    }
                }
            }
        }
    }

    private void exit(){
        System.out.println("---------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------");
    }

    private void displayList(ArrayList<Task> myList){
        System.out.println("---------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        int len = myList.size();
        for (int i = 1; i < len + 1; i++) {
            Task curTask = myList.get(i - 1);
            System.out.println(i + "." + curTask);
        }
        System.out.println("---------------------------------------------");
    }

    private void completeTask(String[] parts, ArrayList<Task> myList){
        int taskNo = Integer.valueOf(parts[1]);
        Task curTask = myList.get(taskNo - 1);
        curTask.markAsDone();
        System.out.println("---------------------------------------------");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(curTask);
        System.out.println("---------------------------------------------");
    }

    private void deleteTask(String[] parts, ArrayList<Task> myList){
        int taskNo = Integer.valueOf(parts[1]);
        Task curTask = myList.get(taskNo - 1);
        myList.remove(taskNo-1);
        System.out.println("---------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println(curTask);
        System.out.println("Now you have " + myList.size() + " tasks in the list.");
        System.out.println("---------------------------------------------");
    }

    private void addToDo(String[] parts, ArrayList<Task> myList) throws DukeException{
        if(parts.length == 1) {
            throw new DukeException("empty task");
        } else{
            ToDo newTask = new ToDo(parts[1]);
            myList.add(newTask);
            System.out.println("---------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + newTask);
            System.out.println("Now you have " + myList.size() + " tasks in the list.");
            System.out.println("---------------------------------------------");
        }
    }

    private void addDeadline(String[] parts, ArrayList<Task> myList){
        String task = parts[1];
        String[] details = task.split(" /by ", 2);
        String description = details[0];
        String by = details[1];
        Deadline newTask = new Deadline(description, by);
        myList.add(newTask);
        System.out.println("---------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + myList.size() + " tasks in the list.");
        System.out.println("---------------------------------------------");
    }

    private void addEvent(String[] parts, ArrayList<Task> myList){
        String task = parts[1];
        String[] details = task.split(" /at ", 2);
        String description = details[0];
        String time = details[1];
        Event newTask = new Event(description, time);

        myList.add(newTask);
        System.out.println("---------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + myList.size() + " tasks in the list.");
        System.out.println("---------------------------------------------");
    }
}