import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    //this is where all the collected output will be stored
    private ArrayList<String> outList;
    private String currentCommand;

    public Duke(String filePath) {

        //new UI is created
        ui = new Ui();
        //Storage created via filePath
        storage = new Storage(filePath);
        //try to load tasks if not catch the error in UI
        try {
            tasks = new TaskList(storage.load());
        } catch (BaseException e) {
            ui.showError(e.toString());
            //tasks = new TaskList();
        }
        //create outList;
        this.outList = ui.outList;
        this.currentCommand = "";
        this.ui.showWelcome();
    }

    public void setCurrentCommand(String currentCommand) {
        this.currentCommand = currentCommand;
    }

    public String getCurrentCommand(){
        return this.currentCommand;
    }

    public String lastOutListElement(){
        if(this.outList.size() > 0) {
            return this.outList.get(this.outList.size() - 1);
        }
        else{
            return "error";
        }
    }

    public String returnOutput(){
        try {
            Command c = Parser.parse(this.currentCommand);
            c.execute(tasks, ui, storage);
            this.outList = ui.outList;
            String ret = this.outList.get(this.outList.size() - 1);
            return ret;
        }
        catch (BaseException e){
            return e.getMessage();
        }
    }
    /*
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommmand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommmand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BaseException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("src/main/java/data.txt").run();
    }

     */
}
