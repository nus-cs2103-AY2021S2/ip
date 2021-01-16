/**
 * Snom is a Personal Assistant Chatbot that helps
 * a person to keep track of various things.
 *
 * @author: Sharptail
 */

import java.util.ArrayList;

public class Snom {
    public static void main(String[] args) {
        Snomio snomio = new Snomio(System.in, System.out);
        String command = "";
        ArrayList<Task> todoList = new ArrayList<>();

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
                    for(int i = 0; i < todoList.size(); i++){
                        Task task = todoList.get(i);
                        String status = task.getStatusSymbol();
                        snomio.println((i+1) + ".[" + status + "]" + task.getDescription());
                    }
                    break;
                case "done":
                    int taskNo = snomio.readInt() - 1;
                    Task task = todoList.get(taskNo);
                    task.setStatus(true);
                    String status = task.getStatusSymbol();
                    snomio.println("Great Job! I've marked this task as done:");
                    snomio.println("[" + status + "]" + task.getDescription());
                    break;
                case "bye":
                    snomio.println("Ciao! Hope to see you again soon!");
                    break;
                default:
                    String content = command;
                    while(snomio.hasMoreWord()){
                        content += " " + snomio.readWord();
                    }
                    todoList.add(new Task(content));
                    snomio.println(content + " has been added to your to do list!");
            }
            snomio.println("--------------------------------");
            snomio.flush();
        }while(!command.equalsIgnoreCase("bye"));

        snomio.close();
    }
}
