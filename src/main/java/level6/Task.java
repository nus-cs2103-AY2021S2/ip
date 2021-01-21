package level6;
//imports
import java.io.IOException;
import java.util.ArrayList;

public abstract class Task {
    // A list of tasks.
    protected static ArrayList<Task> arrayListOfTasks; //an array list of the task
    protected boolean haveWeCompletedItOrNot; // have we done the task of rnor
    protected String detailsOfTheMessage; //details from the message


    Task(){ //constructor
        this.detailsOfTheMessage = ""; //default message
        //this.haveWeCompletedItOrNot = true; WRONG!
        this.haveWeCompletedItOrNot = false; //in the begining we obviouslt have not done it
    }

    public Task(String detailsOfTheMessage) { //constructor with 1 input
        this(); //default
        this.detailsOfTheMessage = detailsOfTheMessage; //update the message component
    }


    public static boolean listMakingMethod(){
        arrayListOfTasks = new ArrayList<Task>(); //create a new array list of task
        return !arrayListOfTasks.equals(null); //return if not null
    }

    public static boolean putInANewTask(Task task){
        // boolean to see if it was wasTaskAdded
        boolean added = arrayListOfTasks.add(task);
        Duke.writer.notifyMeThatTaskWasAdded(task); // boolean to see if it was wasTaskAdded
        return added; //return the boolean wether it was added or not
    }

    ///credit stack exchange
    static Task obtainTheTaskUsingIndex(int index) throws IndexOutOfBoundsException{
        return arrayListOfTasks.get(index);
    }

    ///credit stack exchange
    public static Task getTaskAtPosition(int position) throws IndexOutOfBoundsException {
        return obtainTheTaskUsingIndex(position - 1);
    }

    public static void atThePositionDeleteTask(int position){ //delete at position
        Duke.writer.notifyMeThatTaskWasDeleted(getTaskAtPosition(position));
        arrayListOfTasks.remove(position - 1); //-1 is out of bound
    }

    public static void xsOfTasks() {
        if (!arrayListOfTasks.isEmpty()){ //if the array is not empty
            Duke.writer.tellMeSomething("Your list contains these tasks: "); //message to tell theres something
            int initialListNum = 1; //should start with 1
            for (Task task : arrayListOfTasks){ //for every task that belongs in the array list
                Duke.writer.addMessage(String.format("%d.%s%n", initialListNum++, task)); //we add the message
            }
            Duke.writer.talk(); //and tells us
        }
    }

    public char getTheTickOrCrossIcon() {
        //have we completed it or not
        return haveWeCompletedItOrNot ? '✔' : '✘';
    }

    public boolean markTaskAsCompleted(){ //marking
        this.haveWeCompletedItOrNot = true; //at this point we've completed it
        Duke.writer.notifyMeThatTaskWasMarked(this); //notify me on the update
        return haveWeCompletedItOrNot; //return the updated boolean
    }

    public abstract char forIconGetWhatIsTaskType();
    
    @Override
    public String toString(){ //string to string update
        return String.format("[%c]\t %s", this.getTheTickOrCrossIcon(), this.detailsOfTheMessage);
    }
}