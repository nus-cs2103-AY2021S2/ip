import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        ArrayList<Task> myList = new ArrayList();
        System.out.println("____________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________");

        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()) {
            String s = input.next();
            switch (s){
                case "todo":
                    System.out.println("____________________________________");
                    try {
                        String s1 = input.nextLine();
                        if (s1.equals("")) {
                            throw new DukeException("Enter a valid todo");
                        } else {
                            char[] chars = s1.toCharArray();
                            if(chars[1] == ' '){
                                throw new DukeException("Enter valid todo");
                            } else {
                                try{
                                    System.out.println("Got it. I've added this todo:");
                                    String desc = s1.substring(1);
                                    Todo newTodo = new Todo(desc);
                                    System.out.println(" " + newTodo.toString());
                                    myList.add(newTodo);
                                    System.out.println("Now you have " + myList.size() + " tasks in the list.");
                                } catch (Exception e){
                                    System.out.println("Enter valid todo");
                                }

                            }
                        }
                    } catch (DukeException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("____________________________________");
                    break;

                case "deadline":

                    System.out.println("____________________________________");
                    try{
                        String s1 = input.nextLine();
                        if(s1.equals("")){
                            throw new DukeException("Enter valid deadline task.");
                        } else {
                            try {
                                String wholeString = s1.substring(1);
                                String[] parts = wholeString.split(" /by ");
                                String deadlineDesc = parts[0];
                                if(parts.length == 1){
                                    throw new DukeException("Please adhere to convention:\n(task /by deadline timing)");
                                } else {
                                    String deadline = parts[1];
                                    System.out.println("Got it. I've added this deadline:");
                                    Deadline newDeadline = new Deadline(deadlineDesc, deadline);
                                    System.out.println(" " + newDeadline.toString());
                                    myList.add(newDeadline);
                                    System.out.println("Now you have " + myList.size() + " tasks in the list.");
                                }
                            } catch(DukeException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                    } catch (DukeException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("____________________________________");
                    break;

                case "event":

                    System.out.println("____________________________________");
                    try{
                        String s1 = input.nextLine();
                        if(s1.equals("")){
                            throw new DukeException("Enter valid Event.");
                        } else {
                            try {
                                String wholeString = s1.substring(1);
                                String[] parts = wholeString.split(" /at ");
                                String eventDesc = parts[0];
                                if(parts.length == 1){
                                    throw new DukeException("Please adhere to convention:\n(Event /at event details)");
                                } else {
                                    String eventDetails = parts[1];
                                    System.out.println("Got it. I've added this Event:");
                                    Event newEvent = new Event(eventDesc, eventDetails);
                                    System.out.println(" " + newEvent.toString());
                                    myList.add(newEvent);
                                    System.out.println("Now you have " + myList.size() + " tasks in the list.");
                                }
                            } catch(DukeException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                    } catch (DukeException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("____________________________________");
                    break;

                case "list":
                    System.out.println("____________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for(int i=1; i< myList.size()+1; i++){
                        System.out.println(i +". " + myList.get(i-1).toString());
                    }
                    System.out.println("____________________________________");

                    break;

                case "bye":
                    System.out.println("____________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________");
                    System.exit(0);

                    break;

                case "done":

                    System.out.println("____________________________________");
                    try{
                        String s1 = input.nextLine();
                        if(s1.equals("")){
                            throw new DukeException("Please specify what task is done");
                        } else {
                            String parts[] = s1.split(" ");
                            if(parts.length> 2){
                                throw new DukeException("Please insert valid index to mark as done");
                            } else {
                                try {
                                    String indexString = parts[1];
                                    int index =Integer.parseInt(indexString);
                                    Task t = myList.get(index - 1);
                                    t.markAsDone();
                                    System.out.println("Nice! I've marked this task as done");
                                    System.out.println(t.toString());
                                } catch (Exception e){
                                    System.out.println("Please enter a valid index");
                                }
                            }
                        }

                    } catch(DukeException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("____________________________________");

                    break;

                case "delete":
//
                    System.out.println("____________________________________");
                    try{
                        String s1 = input.nextLine();
                        if(s1.equals("")){
                            throw new DukeException("Please specify which task to delete");
                        } else {
                            String parts[] = s1.split(" ");
                            if(parts.length> 2){
                                throw new DukeException("Please insert valid index to delete");
                            } else {
                                try {
                                    String indexString = parts[1];
                                    int index =Integer.parseInt(indexString);
                                    Task t = myList.get(index-1);
                                    System.out.println("Noted i have removed this task");
                                    System.out.println(myList.get(index-1));
                                    myList.remove(index-1);
                                    System.out.println("Now you have " + myList.size() + " tasks in the list");

                                } catch (Exception e){
                                    System.out.println("Please enter a valid index");
                                }
                            }
                        }

                    } catch(DukeException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("____________________________________");


                    break;

                default:
                    //clear the buffer
                    input.nextLine();
                    System.out.println("____________________________________");
                    System.out.println("Please insert a valid command");
                    System.out.println("____________________________________");

                    break;


            }

        }
    }
}
