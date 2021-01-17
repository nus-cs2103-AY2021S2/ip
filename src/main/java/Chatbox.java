class Chatbox {
    private final Storage storage;

    Chatbox(){
        storage = new Storage();
    }

    void initialize(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    //called when the command is list
    void acceptCommand(String command){
        if(command.equals("list")) storage.listOut();
    }

    //called when the command is add and mark as done, these two require additional
    //information about the task to manipulate
    void acceptCommand(String command, String item){
        if(command.equals("add")){
            Task newTask = new Task(item);
            storage.add(newTask);
        }else if(command.equals("done")){
            storage.markTaskAsDone(item);
        }
    }

    void end(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
