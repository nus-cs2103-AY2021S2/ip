import java.lang.reflect.Array;
import java.util.*;
// potential exceptions to catch:
// 1) deleting a non-existent task
// 2) marking a non-existent task as done
// 3) marking an already done task done again



public class Duke {
    public enum Command {
        LIST,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            ArrayList<Task> col = new ArrayList<>(100);
            System.out.println("----------------------------------------------");
            System.out.println("Hello I'm Duke\n" + "What can I do for you?");
            System.out.println("----------------------------------------------");

            String x = sc.nextLine();
            String[] command = x.split(" ");
            while (!x.equals("bye")) {
                String task = command[0].toUpperCase();
                Command c = Command.valueOf(task);
                switch(c) {
                case LIST:
                    listTasks(col);
                    break;
                case DONE:
                    int index = Integer.parseInt(command[1]);
                    markTaskDone(col, index);
                    break;
                case DELETE:
                    int i = Integer.parseInt(command[1]);
                    deleteTask(col, i);
                    break;
                case TODO:
                    createTodo(col, command);
                    break;
                case DEADLINE:
                    createDeadline(col, command);
                    break;
                case EVENT:
                     createEvent(col, command);
                     break;
                default:
                     throw(new DukeException(x));
                }
                x = sc.nextLine();
                command = x.split(" ");
            }
            System.out.println("----------------------------------------------");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("----------------------------------------------");
        } catch(DukeException e) {
            System.out.println(e);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("You can't have an empty todo :(");
        }
    }


    public static void listTasks(ArrayList<Task> col) {
        System.out.println("----------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < col.size(); i++) {
            System.out.println((i + 1) + ". " + col.get(i));
        }
        System.out.println("----------------------------------------------");
    }
    public static void markTaskDone(ArrayList<Task> col, int index) {
        System.out.println("----------------------------------------------");
        Task t = col.get(index - 1);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        System.out.println("----------------------------------------------");
    }
    public static void deleteTask(ArrayList<Task> col, int index) {
        System.out.println("----------------------------------------------");
        Task t = col.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now there are " + col.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
    public static void createTodo(ArrayList<Task> col, String[] com) {
        System.out.println("----------------------------------------------");
        String todo = "";
        for (int i = 1; i < com.length; i++) {
            todo += " " + com[i];
        }
        ToDoTask newTodo = new ToDoTask(todo);
        col.add(newTodo);
        System.out.println("Added this:");
        System.out.println(newTodo);
        System.out.println("Now there are " + col.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
    public static void createDeadline(ArrayList<Task> col, String[] com) {
        System.out.println("----------------------------------------------");
        String description = "";
        String by = "";
        for (int i = 1; i < com.length; i++) {
            if (com[i].equals("/by")) {
                for (int j = i + 1; j < com.length; j++) {
                    by += " " + com[j];
                }
                break;
            }
            description += " " + com[i];
        }
        DeadlineTask newDdlTask = new DeadlineTask(description, by);
        col.add(newDdlTask);
        System.out.println("Added this:");
        System.out.println(newDdlTask);
        System.out.println("Now there are " + col.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
    public static void createEvent(ArrayList<Task> col, String[] com) {
        System.out.println("----------------------------------------------");
        String description = "";
        String at = "";
        for (int i = 1; i < com.length; i++) {
            if (com[i].equals("/at")) {
                for (int j = i + 1; j < com.length; j++) {
                    at += " " + com[j];
                }
                break;
            }
            description += " " + com[i];
        }
        EventTask newETask = new EventTask(description, at);
        col.add(newETask);
        System.out.println("Added this:");
        System.out.println(newETask);
        System.out.println("Now there are " + col.size() + " tasks on the list");
        System.out.println("----------------------------------------------");
    }
}



//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";