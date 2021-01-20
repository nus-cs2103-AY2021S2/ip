import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
    
    // Constants
    private static final String line = "____________________________________________________________";

    // Attributes
    private Scanner scanner;
    private boolean isActive;
    private ArrayList<Todo> todoList;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.isActive = true;
        this.todoList = new ArrayList<>();
    }

    public void start() {
        String greeting = "Hello! I'm Duke" + "\n"
        + "What can I do for you?";
        print(greeting);

        while(isActive) {
            listen();
        }
    }

    private void listen() {
        String input = scanner.nextLine();
        switch(input) {
            case "list":
                printList();
                break;
            case "bye":
                isActive = false;
                String output = "Bye. Hope to see you again soon!";
                print(output);
                break;
            default:
                addToList(new Todo(input));
                print("Added: " + input);
                break;
        }
    }

    private void printList() {
        String output = "";
        for(int i = 0; i < todoList.size(); i++) {
            output += (i + 1) + ". " + todoList.get(i).toString() + "\n";
        }
        print(output);
    }

    private void addToList(Todo todo) {
        this.todoList.add(todo);
    }

    private void print(String input) {
        System.out.println(line + "\n" + input + "\n" + line + "\n");
    }
}

