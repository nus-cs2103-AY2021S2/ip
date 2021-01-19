import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String indentation = "    ";
    private static String horizon = "------------------------------------------------------";
    //private static Task[] taskList = new Task[100];
    private static ArrayList<Task>taskList= new ArrayList<Task>();
    private static void printReply(String reply){
        System.out.println(indentation+horizon);
        switch (reply) {
            case "hello":
                System.out.println(indentation + "Hello! I'm Duke Y(^_^)Y");
                System.out.println(indentation+"What can I do for you?\n");
                break;
            case "bye":
                System.out.println(indentation + "Bye. (>_<) Hope to see you again soon! ");
                break;

            case "list":
                System.out.println(indentation + "Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(indentation + (i+1) + "." + taskList.get(i).getTaskInfo());
                }
                break;
            case"error_no_meaning":
                System.out.println(indentation + "OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            case"error_done_empty":
            case"error_delete_empty":
                System.out.println(indentation + "OOPS!!! The number cannot be empty :-(");
                break;
            case"error_done_no_meaning":
            case "error_delete_no_meaning":
                System.out.println(indentation + "OOPS!!! Please input a number of Task :-(");
                break;
            case"error_done_non_existed_task":
            case "error_delete_non_existed_task":
                System.out.println(indentation + "OOPS!!! the Task you choosing isn't existed :-(");
                break;
            case"error_todo_empty":
                System.out.println(indentation + "OOPS!!! The description of a todo task cannot be empty. :-(");
                break;
            case"error_deadline_empty":
                System.out.println(indentation + "OOPS!!! The description of a deadline task cannot be empty. :-(");
                break;
            case "error_deadline_by":
                System.out.println(indentation + "OOPS!!! The deadline of a deadline task cannot be empty. :-(");
                System.out.println(indentation + "Please enter according to the format eg.description /by deadline");
                break;
            case"error_event_empty":
                System.out.println(indentation + "OOPS!!! The description of a event task cannot be empty. :-(");
                break;
            case "error_event_at":
                System.out.println(indentation + "OOPS!!! The period of a event task cannot be empty. :-(");
                System.out.println(indentation + "Please enter according to the format eg.description /at period");
                break;
            default:
                System.out.println(indentation+"Got it. I've added this task:");
                System.out.println(indentation + taskList.get(taskList.size()-1).getTaskInfo());
                System.out.println(indentation + "Now you have "+taskList.size()+" tasks in the list.");
        }
        System.out.println(indentation+horizon);
    }

    private  static void printDoneReply(int done){
        System.out.println(indentation+horizon);
        System.out.println(indentation+"Nice! I've marked this task as done:");
        System.out.println(indentation + (done+1) + "." + taskList.get(done).getTaskInfo());
        System.out.println(indentation+horizon);
    }
    private  static void printDeleteReply(int done){
        System.out.println(indentation+horizon);
        System.out.println(indentation+"Noted. I've removed this task: ");
        System.out.println(indentation + (done+1) + "." + taskList.get(done).getTaskInfo());
        System.out.println(indentation+"Now you have "+(taskList.size()-1)+" tasks in the list.");
        System.out.println(indentation+horizon);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String command = "";

        System.out.println("Hello from\n" + logo);
        printReply("hello");
        Scanner scanner = new Scanner(System.in);

        do {
            command = scanner.nextLine().trim();
            switch (command){
                case "bye":
                case "list":
                case "hello":
                    printReply(command);break;
                default:
                    String[] commandSplit = command.split(" ");
                    switch (commandSplit[0]){
                        case "done":
                            try {
                                taskList.get(Integer.parseInt(commandSplit[1]) - 1).markAsDone();
                                printDoneReply(Integer.parseInt(commandSplit[1]) - 1);
                            }catch (ArrayIndexOutOfBoundsException e){
                                printReply("error_done_empty");
                            }catch (NumberFormatException e){
                                printReply("error_done_no_meaning");
                            }catch (NullPointerException e){
                                printReply("error_done_non_existed_task");
                            }
                            break;
                        case "delete":
                            try {
                                taskList.get(Integer.parseInt(commandSplit[1]) - 1);
                                printDeleteReply(Integer.parseInt(commandSplit[1]) - 1);
                                taskList.remove(Integer.parseInt(commandSplit[1]) - 1);
                            }catch (ArrayIndexOutOfBoundsException e){
                                printReply("error_delete_empty");
                            }catch (NumberFormatException e){
                                printReply("error_delete_no_meaning");
                            }catch (NullPointerException e){
                                printReply("error_delete_non_existed_task");
                            }catch (IndexOutOfBoundsException e){
                                printReply("error_delete_non_existed_task");
                            }
                            break;
                        case "todo":
                            try{
                                String test = commandSplit[1];
                            }catch (ArrayIndexOutOfBoundsException e){
                                printReply("error_todo_empty");
                                break;
                            }
                            command = command.replaceAll("todo"," ").trim();
                            taskList.add(new Todo(command)) ;
                            printReply(command);
                            break;

                        case "deadline":
                            try{
                                String test = commandSplit[1];
                            }catch (ArrayIndexOutOfBoundsException e){
                                printReply("error_deadline_empty");
                                break;
                            }
                            command = command.replaceAll("deadline"," ").trim();
                            commandSplit = command.split("/by");
                            try{
                                taskList.add(new Deadline(commandSplit[0].trim(),commandSplit[1].trim()));
                                printReply(command);
                            }catch (ArrayIndexOutOfBoundsException e){
                                printReply("error_deadline_by");
                            }
                            break;
                        case "event":
                            try{
                                String test = commandSplit[1];
                            }catch (ArrayIndexOutOfBoundsException e){
                                printReply("error_event_empty");
                                break;
                            }
                            command = command.replaceAll("event"," ").trim();
                            commandSplit = command.split("/at");
                            try {
                                taskList.add(new Event(commandSplit[0].trim(),commandSplit[1].trim()));
                                printReply(command);
                            }catch (ArrayIndexOutOfBoundsException e){
                                printReply("error_event_at");
                            }

                            break;
                        default:printReply("error_no_meaning");
                    }
            }

        }while(!command.equals("bye"));
    }
}
