import percy.ui.UserInterface;

import java.util.ArrayList;

import percy.command.Command;
import percy.Task;
// import percy.command.ByeCommand;


public class Percy {
    private UserInterface ui;

    public Percy() {
        this.ui = new UserInterface();
    }

    public void run() {
        ArrayList<Task> list = new ArrayList<Task>();
        ui.showStartUp();
        boolean isExit = false;
        while(!isExit) {
            String command = ui.readCommand();
            Command cmd = new Command(command);
            if (cmd.getVerbCmd().equals("bye")) {
                ui.showBye();
                isExit = true;
            } else if (cmd.getVerbCmd().equals("list")) {
                ui.list(list);
            }
            else if (cmd.getVerbCmd().equals("done")) {
                Integer index = Integer.valueOf(cmd.getItem()) -1;
                list.set(index, list.get(index).doTask());
                ui.checkOff(list.get(index));
            }

            else {
                list.add(new Task(command));
                ui.add(command);
            }
        }
    }

    public static void main(String[] args) {
        new Percy().run();
    }
}
