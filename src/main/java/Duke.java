import java.util.*;
public class Duke {
    List<String> list;

    public Duke(){
        this.list = new ArrayList<>();
    }
    public void greet(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void echo(String input){
        System.out.println(input);

    }

    public void addToList(String message){
        this.list.add(message);
        System.out.println("added: " + message);
    }

    public void printList(){
        int counter = 1;
        for(String element:this.list){
            System.out.println(counter + ". " + element);
            counter++;
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
