import percy.exception.PercyException;
import percy.task.Deadline;
import percy.task.Event;
import percy.ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

import percy.command.Command;
import percy.command.Parser;
import percy.task.Task;
import percy.task.Deadline;
import percy.task.Todo;
import percy.task.Event;
import percy.task.TaskList;

// import percy.command.ByeCommand;


public class Percy {
    TaskList list;
    UserInterface ui;
    public Percy() { this.list = new TaskList();
    this.ui = new UserInterface();
    }

    public void run() throws IOException {
        UserInterface.printStartUpMsg();
        while (true) {
            String command = UserInterface.readCommand();
            Parser parser = new Parser(command);
            try {
                Command cmd = parser.getCommand();
                String response = "";
                response = cmd.execute(this.list);
                System.out.println(response);
                if (cmd.isExit() == true) {
                    break;
                }
            } catch (IOException e) {
                break;
            } catch (PercyException e) {
                System.out.println(e.toString());
            }
        }
    }
        /*
            // Parse the command, and create accordingly
            // execute the command
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
            else { // Add a task
                Task t;
                if (cmd.getVerbCmd().equals("deadline")) {
                    t = new Deadline(cmd.getItem(), cmd.getBy());
                    list.add(t);
                }
                else if (cmd.getVerbCmd().equals("todo")) {
                    t = new Todo(cmd.getItem());
                    list.add(t);
                } else {
                    t = new Event(cmd.getItem(), cmd.getBy());
                    list.add(t);
                }
                ui.add(t, list);
            }
        }
        */

    public static void main(String[] args) {
        try {
            new Percy().run();
        } catch(IOException ex) {
            System.err.println(ex);
        }
    }
}
