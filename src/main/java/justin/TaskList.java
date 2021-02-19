package justin;

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
     * @param text of the User input
     * @return text to be printed by Justin
     */

    public String addDeadline(String text) {

        String newText = text.substring(9); // remove deadline from the string text
        String description = newText.substring(0, newText.indexOf("/") - 1);
        String date = newText.substring(newText.indexOf("/") + 4);

        try {
            boolean ifExist = false; // checking if there is an instance of a default tasks
            String holder = "";
            checkDuplicates(description, list);

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
        } catch (JustinException e) {
            return e.getMessage();
        }

    }

    /**
     * This method adds a new tod0 class
     *
     * @param description of the given task
     * @return The dialog for Justin
     */

    public String addToDo(String description) {

        try {
            boolean ifExist = false; // checking if there is an instance of a default tasks
            checkDuplicates(description, list);

            String holder = "";

            for (int i = 0; i < list.size(); i++) { // there is an instance of the item in list
                if (list.get(i).description.equals(description)) {
                    Todo td = new Todo(description);
                    // bringing over information from superclass
                    if (list.get(i).isDone) {
                        td.markAsDone();
                    }
                    list.set(i, td); // insert into list
                    holder += "Got it. I've added this task:\n";
                    holder += " " + td.toString() + "\n";
                    holder += "Now you have " + list.size() + " tasks in the list\n";
                    ifExist = true;
                }
            }

            if (!ifExist) {
                Todo td = new Todo(description);
                list.add(td);
                holder += "Got it. I've added this task:\n";
                holder += " " + td.toString() + "\n";
                holder += "Now you have " + list.size() + " tasks in the list\n";
            }

            return holder;

        } catch (JustinException e) {
            return e.getMessage();
        }
    }

    /**
     * This method adds a new event to the ArrayList
     *
     * @param text of the User input
     * @return The string output for Justin
     */


    public String addEvent(String text) {

        String eventText = text.substring(text.indexOf(" ") + 1); // removing the event to get description
        String description = eventText.substring(0, eventText.indexOf("/") - 1);
        String date = eventText.substring(eventText.indexOf("/") + 4);

        try {
            boolean ifExist = false;
            checkDuplicates(description, list);

            String holder = "";

            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).description.equals(description)) {
                    Event e = new Event(description, date);
                    if (list.get(i).isDone) {
                        e.markAsDone();
                    }
                    list.set(i, e);
                    holder += "Got it. I've added this task:\n";
                    holder += " " + e.toString() + "\n";
                    holder += "Now you have " + list.size() + " tasks in the list\n";
                    ifExist = true;
                }
            }

            if (!ifExist) {
                Event e = new Event(description, date);
                list.add(e);
                holder += "Got it. I've added this task:\n";
                holder += " " + e.toString() + "\n";
                holder += "Now you have " + list.size() + " tasks in the list\n";
            }

            return holder;

        } catch (JustinException e) {
            return e.getMessage();
        }

    }


    /**
     * This method deletes a task entry at position num from the list
     *
     * @param num position of the task to be deleted
     * @throws JustinException cannot delete an invalid entry task
     * @return text for Justin to output
     */

    public String delete(String num) throws JustinException {
        try {

            String holder = "";

            int listNum = Integer.parseInt(num); // changes to int
            Task newTask = list.remove(listNum-1); // delete the entry of choice
            holder += "Noted. I've removed this task:\n";
            holder += " " + newTask.toString() + "\n";
            holder += "Now you have " + list.size() + " tasks in the list\n";
            return holder;

        } catch(IndexOutOfBoundsException e) {
            throw new JustinException("OOPS!!! Cannot delete what you don't have!");
        }
    }

    /**
     * This method returns the string of list of found tasks
     * @param key to be checked with the tasks
     * @return the string of list of found tasks
     */
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

    /**
     * This method checks if there are duplicate tasks in tasklist
     * @param description of the task
     * @param tasks to get the description of current task
     * @throws JustinException cannot add duplicate tasks
     */

    static void checkDuplicates(String description, ArrayList<Task> tasks) throws JustinException  {
        for (int i = 0; i < tasks.size(); i++) {
            if (description.equals(tasks.get(i).getDescription())) {
                throw new JustinException("OOPS!!! You cannot add duplicate Tasks!!!!");
            }

        }
    }

    /**
     * This method marks a task as done
     * @param num the task number to be marked
     * @return the int of the marked task
     */

    public int markDone(String num) {
        int listNum = Integer.parseInt(num); // changes to int
        list.get(listNum - 1).markAsDone();
        return listNum;
    }

    /**
     * This method adds a normal task to the task list
     * @param text description of the task
     */

    public void addTask(String text) {

        Task holder = new Task(text);
        list.add(holder); // position corresponds to item number
    }

}