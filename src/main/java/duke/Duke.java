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
        TaskList store;
        try {
            ui.loadStart();
            store = Storage.LoadTaskList();
            ui.loadSuccess();
        } catch (IOException e) {
            ui.loadFail();
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
                ui.handleException(e);
            } catch (InvalidCommandException e){
                ui.handleException(e);
            } catch(EmptyArgumentException e){
                ui.handleException(e);
            } catch (BadDateArgumentException e) {
                ui.handleException(e);
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
