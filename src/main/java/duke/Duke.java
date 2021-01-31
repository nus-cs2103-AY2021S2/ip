package duke;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidCommandException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.startUpMessage();
        TaskList store = null;
        try {
            System.out.println("Loading From File...");
            store = Storage.LoadTaskList();
            System.out.println("Loaded");
        } catch (IOException e) {
            System.out.println("Failed to Load file. Aborting.");
            return;
        }
        Scanner in = new Scanner(System.in);
        String line;
        do{
            ui.prompt();
            line = in.nextLine();
            try {
                Command c = Parser.parse(line);
                if (c==null){ //Bye command
                    break;
                }
                String data = store.run(c);
                ui.commandMessage(c,data);
            } catch (ParseException e) {
                System.out.println("Command has invalid parsing.");
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e){
                System.out.println(e.getMessage());
            } catch(EmptyArgumentException e){
                System.out.println("Cannot have empty argument");
            } catch (BadDateArgumentException e) {
                System.out.println("Date must be of format 'dd MM yyyy'; Eg: 27 08 2044");
            } finally {
                if(store.isEdited()){
                    try {
                        Storage.saveTaskList(store);
                        store.markSaved();
                    } catch (IOException e) {
                        ui.dumpState(store);
                    }
                }
            }
        }while(true);
        ui.goodByeMessage();
        in.close();
    }
}
