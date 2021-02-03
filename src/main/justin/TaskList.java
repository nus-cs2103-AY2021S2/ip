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
            System.out.println(listNum);
            Task newTask = list.remove(listNum-1); // delete the entry of choice
            //format
            printLineBreaker();
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + newTask.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list");
            printLineBreaker();

        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error deleting" + e.getMessage());
            throw new JustinException("OOPS!!! Cannot delete what you don't have!");
        }
    }


    public static void printLineBreaker() {

        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}