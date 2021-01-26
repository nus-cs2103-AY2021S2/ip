import java.util.List;

class Chatbox {
    private final Storage storage;
    private Statement statement;

    Chatbox(){
        storage = new Storage();
    }

    Chatbox(List<Task> list) {
        storage = new Storage(list);
    }

    static Chatbox initialize(){
        try {
            FileReader fr = new FileReader();
            List<Task> list = fr.readFile();

            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?");

            return new Chatbox(list);

        } catch(DukeException e) {
            System.err.println(e.getMessage());
            return new Chatbox();
        }
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
                case "delete":
                    storage.delete(commAndArgs.get(1).trim());
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
