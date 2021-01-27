import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;
    public boolean isAlive;

    public TaskList() {
        this.list = new ArrayList<>();
        this.isAlive = true;
    }

    public TaskList(ArrayList<Task> taskArr) {
        this.list = taskArr;
        this.isAlive = true;
    }

    private void echoNoOfTask() {
        System.out.println("\t\tNow you have " + getNoOfTasks() + " tasks in the list\n");
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("\t\tOkay I have added this task:\n\t\t\t" + task);
        echoNoOfTask();
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 1 || index > getNoOfTasks()) {
            throw new DukeException("\t\tThere is no such task number!");
        }
        Task task = list.get(index - 1);
        this.list.remove(index - 1);
        System.out.println("\t\tOkay, I've I have removed this task:\n\t\t\t" + task);
        echoNoOfTask();
    }

    public int getNoOfTasks() {
        return list.size();
    }

    public void displayTasks() {
        String text = "";
        if (list.size() == 0) {
            System.out.println("\t\tThere are no tasks");
        } else {
            text += "\t\tHere are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                text += "\t\t" +
                        (i+1) +
                        ". " +
                        list.get(i) +
                        "\n";
            }
            System.out.println(text);
        }
    }

    //TODO consider throwing an exception (eg. DukeException)
    public void complain() throws DukeException {
        throw new DukeException("\t\tSorry I do not understand that!");
    }

    public void exit() {
        System.out.println("\t\t" + "See you again soon!");
        this.isAlive = false;
    }

    public void markAsDone(int num) throws DukeException{
        //TODO consider adding assertion/exception here(to prevent indexing issues)
        if (getNoOfTasks() == 0) {
            System.out.println("\t\tThere are no tasks in the list to mark as done!");
        } else {
            try {
                list.get(num - 1).markAsDone();
                System.out.println("\t\tOkay! I've marked this task as done:\n\t\t" + list.get(num - 1) + "\n");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\t\tOnly Enter natural numbers (i.e. 1,2,3..) not more than " + getNoOfTasks());
            }
        }
    }

    @Override
    public String toString() {
//        return "\t\tHello! I'm Duke" +
//                "\n" +
//                "\t\tWhat can I do for you?\n";
        return "TaskList for Duke";
    }
}
