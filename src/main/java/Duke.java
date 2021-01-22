import java.util.*;

public class Duke {
    public static void main(String[] args) {

<<<<<<< HEAD

        /*
        * Justin is a chatbot that help users plan and organise tasks
        * Justin stands for JUSt a TImetable (New) : JUSTIN
        *
        * Justin is able to create and manage new tasks
        *   1) Mark tasks off as done
        *   2) Set tasks as To Do's - <keyword> <name>
        *   3) Set tasks as Deadline's - keyword  : <keyword> <name> /by <day>
        *   4) Set tasks as Event's - keyword : <keyword> <name> /at <day time>
        *   5) Supports deletion of completed tasks with command delete <int>
        *
        * Justin also supports viewing the entire tasks list with the command list - keyword : <keyword>
        * To end off the session user can input bye to terminate program - keyword : <keyword>
        *
        * @author Goh Wei Kiat aka github : mrweikiat
        * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
        */


        Scanner sc = new Scanner(System.in);

        // Starting line for UI
        printLineBreaker();
        System.out.println("Hello I'm Justin");
=======
        /*
        ***VANILLA ip-master CODE***
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        Scanner sc = new Scanner(System.in);

        // Starting line for UI
        System.out.println("Hello I'm Duke");
>>>>>>> parent of 98bc1c3... level6
        System.out.println("What can I do for you?");
        printLineBreaker();

        // Condition for Duke to stop
        boolean terminate = false;

        // create LinkedList to store information of user inputs
        Task[] tasks = new Task[100];

        // to keep track of num of task
        int itemNum = 0; // starts from 1
        int tasksNum = 0;

        //Duke will keep repeating until command given "Bye"
        while (!terminate) {
            String text = sc.nextLine();
            try {
<<<<<<< HEAD



                validate(text); // throw current text to test for exceptions



=======
                validate(text);// ****************************************** LEVEL 1 *************************************************
>>>>>>> parent of 98bc1c3... level6
                if (text.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    terminate = true; // terminates Duke
                } else if (text.equals("list")) {
                    // print all the tasks stored currently in the list
<<<<<<< HEAD

                    System.out.println("Here are the tasks in your list");

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i+1 + ". " + tasks.get(i).toString());
=======
                    for (int i = 0; i < itemNum; i++) {
                        System.out.println(i+1 + ". " + tasks[i].toString());
>>>>>>> parent of 98bc1c3... level6
                    }

                } else if (text.contains("done")) {
<<<<<<< HEAD
=======
                    // ************************************** LEVEL 3 **********************************
>>>>>>> parent of 98bc1c3... level6
                    String num = text.substring(5); // take out the int value of the task to be completed
                    int listNum = Integer.parseInt(num); // changes to int
                    Task hold = tasks[listNum - 1];
                    hold.markAsDone();
<<<<<<< HEAD

                    // format
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(listNum-1).toString());

                } else if (text.contains("deadline")) {
=======
                    // *********************** LEVEL 3 *********************************8

                    // format
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[listNum-1].toString());
                    // ****************************************** LEVEL 1 *************************************************

                } else if (text.contains("deadline")) {
                    // *********************************** LEVEL 4 ***************************************************
>>>>>>> parent of 98bc1c3... level6
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

                    for (int i = 0; i < itemNum; i++) {
                        if (tasks[i].description.equals(description)) { // meaning this is the task we want to change
                            Deadline dl = new Deadline(description, date);
                            if (tasks[i].isDone) {
                                dl.markAsDone();
                            }

                            tasks[i] = dl; // insert into the list
                            System.out.println(" " + dl.toString());
                            tasksNum++;
                            System.out.println("Now you have " + tasksNum + " tasks in the list");
                        }
                    }
                    // ****************************************** LEVEL 4 *************************************************8
                } else if (text.contains("todo")) {
                    // ************************************ LEVEL 4 *******************************************
                    String description = text.substring(text.indexOf(" ")+1); // take out the item from the text
                    //String description = text.substring(4);
                    //System.out.println(description); // for debugging

                    for (int i = 0; i < itemNum; i++) { // there is an instance of the item in list
                        if (tasks[i].description.equals(description)) {
                            Todo td = new Todo(description);
                            // bringing over information from superclass
                            if (tasks[i].isDone) {
                                td.markAsDone();
                            }
                            tasks[i] = td; // insert into list
                            // formatting
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + td.toString());
                            tasksNum++; // increment
                            System.out.println("Now you have " + tasksNum + " tasks in the list");
                        }
                    }
                    // ************************************** LEVEL 4 ************************************************
                } else if (text.contains("event")) {
                    // ************************************* LEVEL 4 **************************************************
                    String newText = text.substring(text.indexOf(" ")+1); // removing the event to get description
                    System.out.println(newText); // for debugging

                    // set delimiter to obtain the description and the at
                    String description = newText.substring(0, newText.indexOf("/")-1);
                    String date = newText.substring(newText.indexOf("/")+4);

                    //System.out.println(description + " " + date); // for debugging

                    for (int i = 0; i < itemNum; i++) {
                        if(tasks[i].description.equals(description)) {
                            Event e = new Event(description, date);
                            // bringing over info from superclass
                            if (tasks[i].isDone) {
                                e.markAsDone();
                            }

                            tasks[i] = e; // insert into list
                            //formatting
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + e.toString());
                            tasksNum++; // increment
                            System.out.println("Now you have " + tasksNum + " tasks in the list");
                        }
                    }
<<<<<<< HEAD
                }
                else if (text.contains("delete")) {

=======
                    // ************************************* LEVEL 4 **************************************************
>>>>>>> parent of 98bc1c3... level6
                }
                // adding of tasks
                else {
                    System.out.println("added: " + text);
                    // create new instance of task and add to the list
                    Task holder = new Task(text);
                    tasks[itemNum] = holder; // position corresponds to item number
                    itemNum++; // increase the item list count
                    tasksNum++; // increase task list count
                }
            } catch (Exception m) {
            System.out.println(m);
            }
            // ***** level 5 *****

        }

        sc.close();

    }

    static void validate(String text) throws DukeException {
        if (text.length() < 5 && text.contains("todo") ) { // case 1
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else if (text.contains("blah")) { // case 2
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (text.length() < 10 && text.contains("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (text.length() < 6 && text.contains("event")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    static void printLineBreaker() {
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }

        System.out.println();
    }


}
