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
        String content = "";
        ArrayList<String> todoList = new ArrayList<>();

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
                        snomio.println((i+1) + ". " + todoList.get(i));
                    }
                    break;
                case "bye":
                    snomio.println("Ciao! Hope to see you again soon!");
                    break;
                default:
                    content = command;
                    while(snomio.hasMoreWord()){
                        content += " " + snomio.readWord();
                    }
                    todoList.add(content);
                    snomio.println(content + " has been added to your to do list!");
            }
            snomio.println("--------------------------------");
            snomio.flush();
        }while(!command.equalsIgnoreCase("bye"));

        snomio.close();
    }
}
