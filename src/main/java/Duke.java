import main.java.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage dukeStorage;
    private TaskList dukeTaskList;
    private Ui dukeUi;

    final static String FILENAME = "dukedata.txt";
    final static String FOLDERNAME = "data";
    final static String PATH = FOLDERNAME + "/" + FILENAME;

    public Duke(String filePath,String folderName) {
        dukeUi = new Ui();
        try {
            dukeStorage = new Storage(filePath, folderName);
            dukeTaskList = new TaskList(dukeStorage.load());
            dukeUi.showLoadingSucess();
        } catch (DukeException e) {
            dukeUi.showLoadingError(e.getMessage());
            dukeTaskList = new TaskList();
        }
    }

    public void run() {
        //...
        Scanner scanner = new Scanner(System.in);
        Parser stringParser = new Parser();
        dukeUi.showWelcomeLine();

        String userInput = scanner.nextLine();

        while ( !userInput.equals("bye")){

            if (stringParser.equalsToList(userInput)){
                ArrayList<Task> currentTaskList = dukeTaskList.getCurrentTaskList();
                Ui.showReturnTaskList(currentTaskList);
            }else if(stringParser.equalsToDone(userInput)){
                try {
                    int taskDoneInt = stringParser.parseDoneCommand(userInput);
                    Task doneTask = dukeTaskList.checkTaskAsDone(taskDoneInt);

                    dukeUi.showTaskDone(doneTask);
                }catch(DukeException e){
                    dukeUi.showErrorMsg(e.getMessage());
                }
            }else if(stringParser.equalsToDelete(userInput)){
                try{
                    int taskDeleteInt = stringParser.parseDeleteCommand(userInput);
                    Task deletedTask = dukeTaskList.deleteTask(taskDeleteInt);
                    dukeUi.showTaskDeleted(deletedTask, dukeTaskList.getNumberOfTasks());
                }catch(DukeException e){
                    dukeUi.showErrorMsg(e.getMessage());
                }
            }else if(stringParser.equalsToToDo(userInput)){
                try{
                    String toDoDescription = stringParser.parseToDoCommand(userInput);
                    Task toDoTask = dukeTaskList.addToDoTask(toDoDescription);
                    dukeUi.showAddedTask(toDoTask, dukeTaskList.getNumberOfTasks());
                }catch(DukeException e){
                    dukeUi.showErrorMsg(e.getMessage());
                }
            }else if(stringParser.equalsToEvent(userInput)){
                try {
                    ArrayList<String> eventDescription = stringParser.parseEventCommand(userInput);
                    Task eventTask = dukeTaskList.addEventTask(eventDescription);
                    dukeUi.showAddedTask(eventTask, dukeTaskList.getNumberOfTasks());
                }catch(DukeException e){
                    dukeUi.showErrorMsg(e.getMessage());
                }
            }else if(stringParser.equalsToDeadline(userInput)) {
                try {
                    ArrayList<String> eventDescription = stringParser.parseDeadlineCommand(userInput);
                    Task deadlineTask = dukeTaskList.addDeadlineTask(eventDescription);
                    dukeUi.showAddedTask(deadlineTask, dukeTaskList.getNumberOfTasks());
                } catch (DukeException e) {
                    dukeUi.showErrorMsg(e.getMessage());
                }

            }else{
                dukeUi.showErrorMsg("I'm sorry, but I don't know what that means");
            }
            userInput = scanner.nextLine();

            try {
                dukeStorage.saveToFile(dukeTaskList.getCurrentTaskList());
            }catch(DukeException e){
                dukeUi.showErrorMsg(e.getMessage());
            }
        }
        dukeUi.showGoodbyeLine();
    }

    public static void main(String[] args) {
        new Duke(PATH, FOLDERNAME).run();
    }
}

