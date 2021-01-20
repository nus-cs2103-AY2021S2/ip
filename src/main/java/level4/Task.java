package level4;
//imports
import java.io.IOException;
import java.util.ArrayList;

public abstract class Task {
    //var declaration
    protected static ArrayList<Task> arrayListOfTasks; //an array list of the task
    protected boolean haveWeCompletedItOrNot; // have we done the task of rnor
    protected String detailsOfTheMessage; //details from the message

    public Task(){ //constructor
        this.detailsOfTheMessage = ""; //default message
        //this.haveWeCompletedItOrNot = true; WRONG!
        this.haveWeCompletedItOrNot = false;  //in the begining we obviouslt have not done it
    }

    public Task(String detailsOfTheMessage){ //constructor with 1 input
        this(); //default
        this.detailsOfTheMessage = detailsOfTheMessage; //update the message component
    }

    public static boolean listMakingMethod(){
        arrayListOfTasks = new ArrayList<Task>(); //create a new array list of task
        return !arrayListOfTasks.equals(null); //return if not null
    }

    //public static void putInANewTask(Task newTask){
    public static boolean putInANewTask(Task newTask){
        // boolean to see if it was wasTaskAdded
        boolean wasTaskAdded = arrayListOfTasks.add(newTask);
        Duke.writer.notifyMeThatTaskWasAdded(newTask); //notify me that the task was added
        return wasTaskAdded; //return the boolean wether it was added or not
    }

    //getter methods using end of string
    //previous method in 1,2,3 was ineffective.  this method was found on stack exchange
    static Task obtainTheTaskUsingIndex(int endIndex) throws IndexOutOfBoundsException { //credit to stackexchange
        return arrayListOfTasks.get(endIndex); //credit to stack exchange
    }

    public static Task getTaskAtPosition(int position) throws IndexOutOfBoundsException { //credit stack exchange
        return obtainTheTaskUsingIndex(position - 1); //credit stack exchange
    }

    public static void xsOfTasks(){
        if (!arrayListOfTasks.isEmpty()){ //if the array is not empty
            Duke.writer.tellMeSomething("Your list contains these tasks: "); //message to tell theres something
            //int initialListNum = 0;
            int initialListNum = 1; //should start with 1
            for (Task task : arrayListOfTasks){ //for every task that belongs in the array list
                Duke.writer.addMessage(String.format("%d.%s%n", initialListNum++, task)); //we add the message
            }
            Duke.writer.talk(); //and tells us
        }
    }


    public char getTheTickOrCrossIcon(){
        //have we completed it or not
        return haveWeCompletedItOrNot ? '✔'
                                      : '✘'; //credit to stack so i dont have to use u12something
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