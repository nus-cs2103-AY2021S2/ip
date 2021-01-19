import java.util.*;

public class Duke {
    public static void main(String[] args) {

        //***VANILLA ip-master CODE***
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);

        // Starting line for UI
        System.out.println(logo);
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");

        // Condition for Duke to stop
        boolean terminate = false;

        // create LinkedList to store information of user inputs
        ArrayList<Task> tasks = new ArrayList<>();

        //Duke will keep repeating until command given "Bye"
        while (!terminate) {
            String text = sc.nextLine();

            // ***** level 5 *****
            try {
                validate(text);
                // ******* LEVEL 1 *****
                if (text.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    terminate = true; // terminates Duke
                } else if (text.equals("list")) {
                    // print all the tasks stored currently in the list
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i+1 + ". " + tasks.get(i).toString());
                    }
                } else if (text.contains("done")) {
                    // ***** LEVEL 3 ******
                    String num = text.substring(5); // take out the int value of the task to be completed
                    int listNum = Integer.parseInt(num); // changes to int
                    Task hold = tasks.get(listNum-1);
                    hold.markAsDone();


                    // format
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(listNum-1).toString());
                    // ***** LEVEL 1 *****

                } else if (text.contains("deadline")) {
                    // ****** LEVEL 4 *****
                    // insert code for deadline
                    String newText = text.substring(9); // remove deadline from the string text
                    //System.out.println(newText); // for debugging

                    // set delimiter to take out the description of the deadline
                    String description = newText.substring(0, newText.indexOf("/")-1);
                    //System.out.println(description); // for debugging

                    // set delimiter to take out date of the deadline
                    String date = newText.substring(newText.indexOf("/")+4);
                    //System.out.println(date); // for debugging

                    System.out.println("Got it. I've added this task:");

                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i).description.equals(description)) { // meaning this is the task we want to change
                            Deadline dl = new Deadline(description, date);
                            if (tasks.get(i).isDone) {
                                dl.markAsDone();
                            }

                            tasks.set(i, dl);// insert into the list

                            System.out.println(" " + dl.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        }
                    }
                } else if (text.contains("todo")) {
                    String description = text.substring(text.indexOf(" ")+1); // take out the item from the text
                    //System.out.println(description); // for debugging

                    for (int i = 0; i < tasks.size(); i++) { // there is an instance of the item in list
                        if (tasks.get(i).description.equals(description)) {
                            Todo td = new Todo(description);
                            // bringing over information from superclass
                            if (tasks.get(i).isDone) {
                                td.markAsDone();
                            }
                            tasks.set(i, td); // insert into list
                            // formatting
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + td.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        }
                    }
                } else if (text.contains("event")) {

                    String newText = text.substring(text.indexOf(" ")+1); // removing the event to get description
                    System.out.println(newText); // for debugging

                    // set delimiter to obtain the description and the at
                    String description = newText.substring(0, newText.indexOf("/")-1);
                    String date = newText.substring(newText.indexOf("/")+4);

                    //System.out.println(description + " " + date); // for debugging

                    for (int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).description.equals(description)) {
                            Event e = new Event(description, date);
                            // bringing over info from superclass
                            if (tasks.get(i).isDone) {
                                e.markAsDone();
                            }

                            tasks.set(i, e); // insert into list

                            //formatting
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + e.toString());
                            //tasksNum++; // increment
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        }
                    }
                }
                else if (text.contains("delete")) {
                    // ***** level 6 *****

                }
                // adding of tasks
                else {
                    System.out.println("added: " + text);
                    // create new instance of task and add to the list
                    Task holder = new Task(text);
                    tasks.add(holder); // position corresponds to item number
                }
            } catch (Exception m) {
            System.out.println(m);
            }
        }

        sc.close();

    }

    //  ***** level 5 *****
    static void validate(String text) throws DukeException {
        if (text.length() < 5 && text.contains("todo") ) { // case 1
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else if (text.contains("blah")) { // case 2
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (text.length() < 10 && text.contains("deadline")) { // case 3
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (text.length() < 6 && text.contains("event")) { // case 4
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }


}
