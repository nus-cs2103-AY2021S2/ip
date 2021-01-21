import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> myList = new ArrayList();
        System.out.println("____________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________");

        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()) {
            String s = input.next();
            switch (s){
                case "todo":
                    //maybe want to out this statement above later
                    System.out.println("Got it. I've added this task:");
                    String desc = input.nextLine().substring(1);
                    Todo newTodo = new Todo(desc);
                    System.out.println(" " + newTodo.toString());
                    myList.add(newTodo);
                    System.out.println("Now you have " + myList.size() + " tasks in the list.");

                    break;
                case "deadline":
                    System.out.println("Got it. I've added this task:");
                    String wholeString = input.nextLine().substring(1);
                    String[] parts = wholeString.split(" /by ");
                    String deadlineDesc = parts[0];
                    String deadline = parts[1];
                    Deadline newDeadline = new Deadline(deadlineDesc, deadline);
                    System.out.println(" " + newDeadline.toString());
                    myList.add(newDeadline);
                    System.out.println("Now you have " + myList.size() + " tasks in the list.");

                    break;

                case "event":
                    System.out.println("Got it. I've added this task:");
                    String wholeString1 = input.nextLine().substring(1);
                    String[] parts1 = wholeString1.split(" /at ");
                    String eventDesc = parts1[0];
                    String event = parts1[1];
                    Event newEvent = new Event(eventDesc, event);
                    System.out.println(" " + newEvent.toString());
                    myList.add(newEvent);
                    System.out.println("Now you have " + myList.size() + " tasks in the list.");

                    break;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int i=1; i< myList.size()+1; i++){
                        System.out.println(i +". " + myList.get(i-1).toString());
                    }
                    break;

                case "bye":
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.exit(0);
                    break;

                case "done":
                    System.out.println("Nice! I've marked this task as done");
                    String indexString = input.next();
                    int index =Integer.parseInt(indexString);
                    Task t = myList.get(index - 1);
                    t.markAsDone();
                    System.out.println(t.toString());
                    break;


                default:
                    //create some error handling here
                    break;


            }
            

        }
    }
}
