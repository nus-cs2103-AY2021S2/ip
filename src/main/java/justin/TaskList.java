package justin;

import justin.Deadline;
import justin.Event;
import justin.JustinException;
import justin.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void addDeadline(String description, String date) {

        System.out.println("Got it. I've added this task:");

        boolean ifExist = false; // checking if there is an instance of a default tasks

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).description.equals(description)) { // meaning this is the task we want to change
                Deadline dl = new Deadline(description, date);
                if (list.get(i).isDone) {
                    dl.markAsDone();
                }

                printLineBreaker();
                System.out.println("Got it. I've added this task:");
                list.set(i, dl);// insert into the list
                System.out.println(" " + dl.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
                printLineBreaker();

                ifExist = true;
            }
        }

        if (!ifExist) {
            Deadline dl = new Deadline(description, date);
            printLineBreaker();
            System.out.println("Got it. I've added this task:");
            list.add(dl);
            System.out.println(" " + dl.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list");
        }
    }

    public void addToDo(String description) {
        boolean ifExist = false; // checking if there is an instance of a default tasks

        for (int i = 0; i < list.size(); i++) { // there is an instance of the item in list
            if (list.get(i).description.equals(description)) {
                Todo td = new Todo(description);
                // bringing over information from superclass
                if (list.get(i).isDone) {
                    td.markAsDone();
                }
                list.set(i, td); // insert into list
                // formatting
                printLineBreaker();
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + td.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
                printLineBreaker();
                ifExist = true;
            }
        }

        if (!ifExist) {
            // no instance of new task in exisiting list, must create new one
            Todo td = new Todo(description);
            list.add(td);
            // formatting
            printLineBreaker();
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + td.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list");
            printLineBreaker();
        }

    }

    public void addEvent(String description, String date) {

        boolean ifExist = false;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).description.equals(description)) {
                Event e = new Event(description, date);
                // bringing over info from superclass
                if (list.get(i).isDone) {
                    e.markAsDone();
                }

                list.set(i, e); // insert into list

                //formatting
                printLineBreaker();
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + e.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
                printLineBreaker();
                ifExist = true;
            }
        }

        if (!ifExist) {
            Event e = new Event(description, date);
            list.add(e);
            //formatting
            printLineBreaker();
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + e.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list");
            printLineBreaker();
        }
    }

    public void delete(String num) throws JustinException {
        try {

            int listNum = Integer.parseInt(num); // changes to int
            Task newTask = list.remove(listNum-1); // delete the entry of choice
            //format
            printLineBreaker();
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + newTask.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list");
            printLineBreaker();

        } catch(IndexOutOfBoundsException e) {
            throw new JustinException("OOPS!!! Cannot delete what you don't have!");
        }
    }

    public ArrayList<String> find(String key) {

        boolean isFound = false; // if any tasks matches description we can return a justin.TaskList
        ArrayList<String> holder = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            String description = list.get(i).getDescription();

            if (description.contains(key)) {
                holder.add(list.get(i).toString());
            }

            isFound = true; // there is at least ONE instance of description with key word
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