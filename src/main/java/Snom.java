/**
 * Snom is a Personal Assistant Chatbot that helps
 * a person to keep track of various things.
 *
 * @author: Sharptail
 */

import java.util.ArrayList;

public class Snom {
    static Snomio snomio = new Snomio(System.in, System.out);
    static ArrayList<Task> taskList = new ArrayList<>();
    
    public static void main(String[] args) {
        String command = "";

        snomio.println("--------------------------------");
        snomio.println("Bonjour! I'm Snom! *squish*");
        snomio.println("Try giving me some commands, I might be able to do something!");
        snomio.println("[type 'bye' to exit program]");
        snomio.println("--------------------------------");
        snomio.flush();

        do{
            command = snomio.readWord();
            snomio.println("--------------------------------");
            switch(command){
                case "list":
                    for(int i = 0; i < taskList.size(); i++){
                        snomio.println((i+1) + ". " + taskList.get(i).toString());
                    }
                    break;
                case "done":
                    int taskNo = snomio.readInt() - 1;
                    finishTask(taskNo);
                    break;
                case "bye":
                    snomio.println("Ciao! Hope to see you again soon!");
                    break;
                case "todo":
                    addTask(new Todo(snomio.readLine()));
                    break;
                case "deadline":
                    String[] dlArr = snomio.readLine().split("/by");
                    addTask(new Deadline(dlArr[0], dlArr[1]));
                    break;
                case "event":
                    String[] eArr = snomio.readLine().split("/at");
                    addTask(new Event(eArr[0], eArr[1]));
                    break;
                default:
                    addTask(new Task(command + snomio.readLine()));
            }
            snomio.println("--------------------------------");
            snomio.flush();
        }while(!command.equalsIgnoreCase("bye"));

        snomio.close();
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
     * @param taskNo task number that needs to mark as done
     */
    public static void finishTask(int taskNo){
        Task task = taskList.get(taskNo);
        task.setStatus(true);
        snomio.println("Great Job! I've marked this task as done:");
        snomio.println("\t" + task.toString());
    }
}
