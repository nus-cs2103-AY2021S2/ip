import java.util.ArrayList;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    //this is where all the collected output will be stored
    private ArrayList<String> outList;
    private String currentCommand;
    private boolean hasError;

    public Duke(String filePath) {
        TaskList tasks1;

        //new UI is created
        ui = new Ui();
        this.hasError = false;
        //Storage created via filePath
        storage = new Storage(filePath);
        //try to load tasks if not catch the error in UI
        try {
            tasks1 = new TaskList(storage.load());
            this.hasError = true;
        } catch (BaseException e) {
            ui.showError(e.toString());
            tasks1 = new TaskList();
        }
        //create outList;
        tasks = tasks1;
        this.outList = ui.outList;
        this.currentCommand = "";
        this.ui.showWelcome();
    }

    public boolean getHasError(){
        return this.hasError;
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
            this.hasError = false;
            return ret;
        }
        catch (BaseException e){
            this.hasError = true;
            return e.getMessage();
        }
    }
}
