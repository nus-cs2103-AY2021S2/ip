import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        Database database = new Database( "data.txt");
        ArrayList<String> listOfTasks;
        ArrayList<Task> myList;
       try {
            listOfTasks = database.readFile();
            myList = readInput(listOfTasks);
        }
        catch (FileNotFoundException e){
            throw new FileNotFoundException("No File Detected");
        }
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
                                    database.writeTaskToFile(myList);
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
                                    String dl = parts[1];
                                    try{
                                        LocalDate deadline = LocalDate.parse(dl);
                                        System.out.println("Got it. I've added this deadline:");
                                        Deadline newDeadline = new Deadline(deadlineDesc, deadline);
                                        System.out.println(" " + newDeadline.toString());
                                        myList.add(newDeadline);
                                        System.out.println("Now you have " + myList.size() + " tasks in the list.");
                                        database.writeTaskToFile(myList);
                                    } catch (DateTimeParseException e){
                                        System.out.println("Please enter date as following\n");
                                        System.out.println("YYYY-MM-DD");
                                    }
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
                                    database.writeTaskToFile(myList);
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
                                    database.writeTaskToFile(myList);
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
                                    database.writeTaskToFile(myList);

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

    private static ArrayList<Task> readInput(ArrayList<String> strings) {
        ArrayList<Task> tasks = new ArrayList<>();
        for(String str: strings) {
            char identifier = str.charAt(1);
            switch(identifier) {
                case 'D':
                    String subString = str.substring(7);
                    String[] inputs = subString.split("by: ");
                    String name = inputs[0].substring(0, inputs[0].length()-2);
                    String deadline = inputs[1].substring(0, inputs[1].length()-1);
                    LocalDate deadline1 = LocalDate.parse(deadline);
                    Deadline deadline2 = new Deadline(name, deadline1);
                    tasks.add(deadline2);
                    break;
                case 'T':
                    String subString1 = str.substring(7);
                    Todo todo1 = new Todo(subString1);
                    tasks.add(todo1);
                    break;
                case 'E':
                    String subString2 = str.substring(7);
                    String[] inputs1 = subString2.split("at: ");
                    String desc = inputs1[0].substring(0, inputs1[0].length()-2);
                    String at = inputs1[1].substring(0, inputs1[1].length()-1);
                    Event event1 = new Event(desc, at);
                    tasks.add(event1);

                    break;
            }
        }
        return tasks;
    }


}
