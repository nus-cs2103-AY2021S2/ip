import java.util.List;
import java.util.NoSuchElementException;

class Chatbox {
    private final Storage storage;
    private Statement statement;

    Chatbox(){
        storage = new Storage();
    }

    void initialize(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    //accept the input
    void acceptInput(String input){
        statement = new Statement(input);
    }

    //execute the following command
    void executeCommand(){
        try{
            List<String> commAndArgs = statement.parseStatement();
            String command = commAndArgs.get(0);

            switch (command){
                case "list":
                    storage.listOut();
                    break;
                case "todo":
                    storage.add(new Todo(commAndArgs.get(1).trim()));
                    break;
                case "deadline":
                    storage.add(new Deadline(commAndArgs.get(1).trim(),commAndArgs.get(2)));
                    break;
                case "event":
                    storage.add(new Event(commAndArgs.get(1).trim(),commAndArgs.get(2)));
                    break;
                case "done":
                    storage.markTaskAsDone(commAndArgs.get(1).trim());
                    break;
            }
        }catch(DukeException err){
            String errMsg = err.getMessage();
            System.out.println(errMsg);
        }

    }

    void end(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
