/**
 * Snom is a Personal Assistant Chatbot that helps
 * a person to keep track of various things.
 *
 * @author: Sharptail
 */

import java.util.ArrayList;

public class Snom {
    private static Snomio snomio = new Snomio(System.in, System.out);
    private static ArrayList<Task> taskList = new ArrayList<>();
    
    public static void main(String[] args) {
        String command = "";

        init();
        execute(command);

        snomio.close();
    }

    /**
     * Initialising the chatbot application.
     * This is just the startup messages for now.
     * Might be useful for expansion in the future.
     */
    public static void init(){
        snomio.println("--------------------------------");
        snomio.println("Bonjour! I'm Snom! *squish*");
        snomio.println("Try giving me some commands, I might be able to do something!");
        snomio.println("[type 'bye' to exit program]");
        snomio.println("--------------------------------");
        snomio.flush();
    }

    /**
     * This is the main execution code that will run all the respective command.
     * Commands will simply be validated by switch cases. Dont need exception for this.
     * Contents will be validated with exception before execution.
     *
     * @param command command to be executed
     */
    public static void execute(String command){
        do{
            command = snomio.readWord();
            snomio.println("--------------------------------");
            switch(command){
                case "list":
                    try {
                        printTaskList();
                    } catch (SnomException e) {
                        snomio.println(e.getMessage());
                    }
                    break;
                case "done":
                    try {
                        int[] taskNums = snomio.readContentWithNumbers(command);
                        finishTask(taskNums);
                    } catch (SnomException e) {
                        snomio.println(e.getMessage());
                    }
                    break;
                case "bye":
                    snomio.println("Ciao! Hope to see you again soon!");
                    break;
                case "todo":
                    String content;
                    try {
                        content = snomio.readContent(command);
                        addTask(new Todo(content));
                    } catch (SnomException e) {
                        snomio.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        String[] dlArr = snomio.readContentWithDate(command, "/by");
                        addTask(new Deadline(dlArr[0], dlArr[1]));
                    } catch (SnomException e) {
                        snomio.println(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        String[] eArr = snomio.readContentWithDate(command, "/at");
                        addTask(new Event(eArr[0], eArr[1]));
                    } catch (SnomException e) {
                        snomio.println(e.getMessage());
                    }
                    break;
                default:
                    snomio.println("OOPS!!! I'm sorry, but I don't know what '" + command + "' means :-(");
            }
            snomio.println("--------------------------------");
            snomio.flush();
        }while(!command.equalsIgnoreCase("bye"));
    }

    /**
     * This method is to print out the entire task list
     *
     * @throws SnomException throw exception if there is content after the command or there isn't any task in the task list
     */
    public static void printTaskList() throws SnomException{
        if(taskList.size() > 0){
            snomio.println("Here are the tasks in your list:");
            for(int i = 0; i < taskList.size(); i++){
                snomio.println((i+1) + ". " + taskList.get(i).toString());
            }
        }else{
            throw new SnomException("You have no task in your list right now, try adding some and try again :D");
        }
    }

    /**
     * This method add the given task, can be a todo, deadline or event task.
     * Then prints out respective messages.
     *
     * @param task either Todo, Deadline, Event
     */
    public static void addTask(Task task){
        taskList.add(task);
        snomio.println("Got it. I've added this task:");
        snomio.println("\t" + task.toString());
        snomio.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * This method mark the task by the given task number as done.
     * Then prints out the complete messages.
     *
     * @param taskNums       task number list that needs to mark as done
     * @throws SnomException throws exception when the task number is not available in the task list.
     */
    public static void finishTask(int[] taskNums) throws SnomException{
        for(int i = 0; i < taskNums.length; i++){
            if(i <= taskList.size()){
                Task task = taskList.get(taskNums[i] - 1);
                task.setStatus(true);

                // Only print this for the first task marked as done
                if(i == 0) snomio.println("Great Job! I've marked this task(s) as done:");
                snomio.println("\t" + task.toString());
            }else{
                throw new SnomException("Oops! You have entered a task number that is invalid! Please try again!");
            }
        }
    }
}
