package duke;

public class Duke {
    private Storage storage;
    private TaskList taskList;


    /**
     * This constructor create a duke class after loading the tasks from a given file path
     * @param filePath path of the file which stores the list of tasks from last time
     * @return a Duke object with all of its variables initialised
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.showError(e);
            taskList = new TaskList();
        }
    }



    public String getResponse(String string) {
        switch (string) {
        case "bye":
            try {
                storage.writeToFile(taskList, "./data/duke.txt");
            } catch (DukeException e) {
                showError(e);
            }
            return "Bye. See you again!";
        case "list":
            String output = "";
            if (taskList.getSize() == 0) {
                return "You have no tasks in the list!";
            }
            for (int i = 0; i < taskList.getSize(); i++) {
                output += taskList.getTask(i).toString() + "\n";
            }
            assert output.length() != 0 : "No tasks in the list";
            return output;
        default:
            Pair result = Parser.parseInput(string, taskList);
            taskList = result.getTaskList();
            return result.getMessage();
        }
    }

    public String showError(Exception e) {
        return e.getMessage();
    }
}