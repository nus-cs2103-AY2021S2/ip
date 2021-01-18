import percy.ui.UserInterface;

// import percy.command.Command;
// import percy.command.ByeCommand;


public class Percy {
    private UserInterface ui;

    public Percy() {
        this.ui = new UserInterface();
    }

    public void run() {
        ui.showStartUp();
        boolean isExit = false;
        while(!isExit) {
            String command = ui.readCommand();
            command = command.trim().strip();
            // System.out.println(command);
            ui.showDivider();
            if (command.equals("bye")) {
                ui.showBye();
                isExit = true;
            } else {
                ui.showEcho(command);
            }
            ui.showDivider();
            ui.showBlankLine();
        }
    }

    public static void main(String[] args) {
        new Percy().run();
    }
}
