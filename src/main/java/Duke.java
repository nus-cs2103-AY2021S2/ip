public class Duke {
    //Main method where the classes are initialized
    public static void main(String[] args) {
        UI ui = new UI();

        String command;
        Tasks taskList = new Tasks();

        ui.greetings();

        while(true){
            command = ui.getCommand();
            String[] executable = new String[2];
            executable = command.split(" ");
            if(command.equals("bye")){
                ui.goodbye();
                break;
            }
            if(command.equals("list")){
                taskList.printTasks();
            }else if(executable[0].equals("done")){
                taskList.markAsDone(Integer.parseInt(executable[1]));
            }else{
                taskList.addTask(new Task(command));
            }
        }
    }
}
