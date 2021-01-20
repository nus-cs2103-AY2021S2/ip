public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();

        String command;
        String echo;

        ui.greetings();

        while(true){
            command = ui.getCommand();
            echo = command;
            if(command.equals("bye")){
                ui.goodbye();
                break;
            }
            System.out.println(echo);
        }
    }
}
