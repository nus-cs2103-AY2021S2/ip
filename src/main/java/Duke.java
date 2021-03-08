
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import  java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Duke {

    public static String line= "__________________________________________________";
    private Storage storage;
    private UI ui;
    private TaskList taskList;
    private int count;

    public Duke(String path)
    {
        this.storage = new Storage(path);
        this.taskList = new TaskList(this.storage);
        this.count= 0;
        this.ui = new UI();
    }
    public static void main(String[] args) {




        File logFile = new File("./logs");
        try{
            if (logFile.isFile()){
                Scanner logger = new Scanner(logFile);
                while (logger.hasNextLine()){
                    System.out.println(logger.nextLine());
                }
                logger.close();
            }else{
                logFile.createNewFile();
            }


        }catch (IOException e){
            System.out.println("Problem with creating new file:"+ e);
        }


        new Duke("./logs").run();


    }
    private void readParse(ParserOutput parserOutput) throws DukeException {
        switch (parserOutput.getAction()) {
            case 1: //remove
                this.taskList.remove(parserOutput.getIndex());
                ui.printRemoved(parserOutput.getIndex());
                break;
            case 2: //done
                Task doneTask = this.taskList.get(parserOutput.getIndex());
                doneTask.setDone(true);
                this.taskList.set(parserOutput.getIndex(), doneTask);
                ui.printDone(doneTask);
                break;
            case 3: //add
                this.taskList.add(parserOutput.getTask());
                ui.printAdded(parserOutput.getTask(), this.taskList.getSize());
                break;
            case 4: //set
                this.taskList.set(parserOutput.getIndex(), parserOutput.getTask());
                break;
            case 5: //list
                ui.printList(this.taskList);
                break;
            default:
                throw new DukeException("action not found");
        }
    }
    public int run() {
        UI.printHello();
        while(true){
            String userInput = ui.getInputFromUser();
            ParserOutput parserOutput = null;
            try {
                parserOutput = Parser.parse(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue;
            }
            UI.printHRule();
            if (ParserOutput.byeOutput().isBye()) {
                break;
            }
            UI.printHRule();
        }
        UI.printGoodbye();
        return 0;
    }
}
