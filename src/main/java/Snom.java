/**
 * Snom is a Personal Assistant Chatbot that helps
 * a person to keep track of various things.
 *
 * @author: Sharptail
 */
import java.util.ArrayList;

public class Snom {
    private static Snomio snomio = new Snomio(System.in, System.out);
    private static SnomFile snomFile = new SnomFile("./src/main/data", "snom.txt");
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        init();
        execute();
        snomio.close();
    }

    /**
     * Initialises the chatbot application.
     * Creates a new save file or read from save file if exists.
     */
    public static void init(){
        snomFile.init();
        taskList = snomFile.readFile();

        snomio.println("--------------------------------");
        snomio.println("Bonjour! I'm Snom! *squish*");
        snomio.println("Try giving me some commands, I might be able to do something!");
        snomio.println("[type 'bye' to exit program]");
        snomio.println("--------------------------------");
        snomio.flush();
    }

    /**
     * Executes the main code that will run all the respective command.
     * Commands will simply be validated by switch cases. Dont need exception for this.
     * Contents will be validated with exception before execution.
     *
     * @throws SnomException if error occurs specific to Snom
     */
    public static void execute(){
        String input;
        CommandEnum command;
        do{
            input = snomio.readWord();
            command = CommandEnum.getCommand(input);
            snomio.println("--------------------------------");
            try{
                switch(command){
                    case LIST:
                        printTaskList();
                        break;
                    case FINISH:
                        int[] finishList = snomio.readContentWithNumbers(command.name());
                        finishTask(finishList);
                        break;
                    case DELETE:
                        int[] deleteList = snomio.readContentWithNumbers(command.name());
                        deleteTask(deleteList);
                        break;
                    case BYE:
                        snomio.println("Ciao! Hope to see you again soon!");
                        break;
                    case TODO:
                        String content;
                        content = snomio.readContent(command.name());
                        addTask(new Todo(content));
                        break;
                    case DEADLINE:
                        String[] dlArr = snomio.readContentWithDate(command.name(), "/by");
                        addTask(new Deadline(dlArr[0], dlArr[1]));
                        break;
                    case EVENT:
                        String[] eArr = snomio.readContentWithDate(command.name(), "/at");
                        addTask(new Event(eArr[0], eArr[1]));
                        break;
                    default:
                        snomio.println("OOPS!!! I'm sorry, but I don't know what '" + input + "' means :-(");
                }
            }catch (SnomException e) {
                snomio.println(e.getMessage());
            }
            snomio.println("--------------------------------");
            snomio.flush();
        }while(command != CommandEnum.BYE);
    }

    /**
     * Prints out the entire task list.
     *
     * @throws SnomException if there is content after the command or there isn't any task in the task list
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
     * Adds the given task to task list, can be a todo, deadline or event task.
     * Then prints out respective messages.
     *
     * @param task either Todo, Deadline, Event
     */
    public static void addTask(Task task){
        taskList.add(task);
        snomio.println("Got it. I've added this task:");
        snomio.println("\t" + task.toString());
        snomio.println("Now you have " + taskList.size() + " tasks in the list.");
        snomFile.saveFile(taskList);
    }

    /**
     * Set the task status by the given task numbers as finished.
     * Then prints out the complete messages.
     *
     * @param taskNums       task number list that needs to mark as finish
     * @throws SnomException If the task number is not available in the task list.
     */
    public static void finishTask(int[] taskNums) throws SnomException{
        for(int i = 0; i < taskNums.length; i++){
            int taskNo = taskNums[i] - 1;
            try{
                Task task = taskList.get(taskNo);
                task.setStatus(true);

                // Only print this for the first task marked as finish
                if(i == 0) snomio.println("Great Job! I've marked this task(s) as finish:");
                snomio.println("\t" + task.toString());
            }catch(IndexOutOfBoundsException e){
                throw new SnomException("Oops! You have entered a task number: " + taskNums[i] + " which is invalid! Please try again!");
            }
            snomFile.saveFile(taskList);
        }
    }

    /**
     * Removes the given task numbers from the task list.
     * Then prints out the deleted messages.
     *
     * @param  taskNums      task number list that needs to be removed
     * @throws SnomException If the task number is not available in the task list.
     */
    public static void deleteTask(int[] taskNums) throws SnomException{
        for(int i = 0; i < taskNums.length; i++){
            int taskNo = taskNums[i] - 1 - i;
            try{
                Task task = taskList.get(taskNo);
                taskList.remove(task);

                // Only print this for the first task removed
                if(i == 0) snomio.println("Noted, I've removed this task");
                snomio.println("\t" + task.toString());
            }catch(IndexOutOfBoundsException e){
                throw new SnomException("Oops! You have entered a task number: " + taskNums[i] + " which is invalid! Please try again!");
            }
            snomFile.saveFile(taskList);
        }
    }
}
