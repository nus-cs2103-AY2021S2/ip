public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();

        String command;
        Tasks taskList = new Tasks();

        ui.greetings();

        while(true){
            command = ui.getCommand();
            if(command.equals("bye")){
                ui.goodbye();
                break;
            }
            if(command.equals("list")){
                taskList.printTasks();
            }else {
                taskList.addTask(command);
            }
        }
    }
}
