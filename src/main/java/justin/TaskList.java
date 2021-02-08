package justin;

import justin.Deadline;
import justin.Event;
import justin.JustinException;
import justin.Task;

import java.util.ArrayList;

/**
 * This class creates a TaskList class that stores an ArrayList of tasks
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */


public class TaskList {

    ArrayList<Task> list;


    public TaskList() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }


    /**
     * This method adds a deadline to a task class
     *
     * @param description of the task
     * @param date of the given task
     */

    public String addDeadline(String description, String date) {

        boolean ifExist = false; // checking if there is an instance of a default tasks

        String holder = "";

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).description.equals(description)) { // meaning this is the task we want to change
                Deadline dl = new Deadline(description, date);
                if (list.get(i).isDone) {
                    dl.markAsDone();
                }

                //printLineBreaker();
                holder += "Got it. I've added this task:\n";
                list.set(i, dl);// insert into the list
                holder += " " + dl.toString() + "\n";
                holder += "Now you have " + list.size() + " tasks in the list\n";
                //printLineBreaker();

                ifExist = true;
            }
        }

        if (!ifExist) {
            Deadline dl = new Deadline(description, date);
            printLineBreaker();
            holder += "Got it. I've added this task:\n";
            list.add(dl);
            holder += " " + dl.toString() + "\n";
            holder += "Now you have " + list.size() + " tasks in the list\n";
        }

        return holder;
    }

    /**
     * This method adds a new tod0 class
     *
     * @param description of the given task
     * @return The dialog for Justin
     */

    public String addToDo(String description) {
        boolean ifExist = false; // checking if there is an instance of a default tasks

        String holder = "";

        for (int i = 0; i < list.size(); i++) { // there is an instance of the item in list
            if (list.get(i).description.equals(description)) {
                Todo td = new Todo(description);
                // bringing over information from superclass
                if (list.get(i).isDone) {
                    td.markAsDone();
                }
                list.set(i, td); // insert into list
                // formatting
                //printLineBreaker();
                holder += "Got it. I've added this task:\n";
                holder += " " + td.toString() + "\n";
                holder += "Now you have " + list.size() + " tasks in the list\n";
                //printLineBreaker();
                ifExist = true;
            }
        }

        if (!ifExist) {
            // no instance of new task in exisiting list, must create new one
            Todo td = new Todo(description);
            list.add(td);
            // formatting
            //printLineBreaker();
            holder += "Got it. I've added this task:\n";
            holder += " " + td.toString() + "\n";
            holder += "Now you have " + list.size() + " tasks in the list\n";
            //printLineBreaker();
        }

        return holder;

    }

    /**
     * This method adds a new event to the ArrayList
     *
     * @param description of the given task
     * @param date of the given task
     * @return The string output for Justin
     */


    public String addEvent(String description, String date) {

        boolean ifExist = false;

        String holder = "";

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).description.equals(description)) {
                Event e = new Event(description, date);
                // bringing over info from superclass
                if (list.get(i).isDone) {
                    e.markAsDone();
                }

                list.set(i, e); // insert into list

                //formatting
                //printLineBreaker();
                holder += "Got it. I've added this task:\n";
                holder += " " + e.toString() + "\n";
                holder += "Now you have " + list.size() + " tasks in the list\n";
                //printLineBreaker();
                ifExist = true;
            }
        }

        if (!ifExist) {
            Event e = new Event(description, date);
            list.add(e);
            //formatting
            //printLineBreaker();
            holder += "Got it. I've added this task:\n";
            holder += " " + e.toString() + "\n";
            holder += "Now you have " + list.size() + " tasks in the list\n";
            //printLineBreaker();
        }

        return holder;
    }


    /**
     * This method deletes a task entry at position num from the list
     *
     * @param num position of the task to be deleted
     * @throws JustinException cannot delete an invalid entry task
     */

    public String delete(String num) throws JustinException {
        try {

            String holder = "";

            int listNum = Integer.parseInt(num); // changes to int
            Task newTask = list.remove(listNum-1); // delete the entry of choice
            //format
            //printLineBreaker();
            holder += "Noted. I've removed this task:\n";
            holder += " " + newTask.toString() + "\n";
            holder += "Now you have " + list.size() + " tasks in the list\n";
            //printLineBreaker();
            return holder;

        } catch(IndexOutOfBoundsException e) {
            throw new JustinException("OOPS!!! Cannot delete what you don't have!");
        }
    }

    public String find(String key) {


        String holder = "";

        for (int i = 0; i < list.size(); i++) {

            String description = list.get(i).getDescription();

            if (description.contains(key)) {
                holder += (i+1) + ". " +list.get(i).toString() + "\n";
            }
        }

        return holder;
    }


    public static void printLineBreaker() {

        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}