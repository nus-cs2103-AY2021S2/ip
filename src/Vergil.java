public class Vergil {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructs a Vergil object with a specified save file path.
     * @param filePath the path to the save file.
     */
    public Vergil(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (VergilException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Vergil chatbot system, making use of the Ui, Storage, Parser,
     * and TaskList objects.
     */
    public void run() {
        boolean hasFinished = false;
        Command cmd;

        ui.displayWelcome();


        while (!hasFinished) {
            try {
                cmd = parser.parse(ui.readCommand().trim());

                switch (cmd.getType()) {
                case BYE:
                    ui.displayBye();
                    hasFinished = true;
                    break;

                case LIST:
                    ui.displayTaskList(taskList);
                    break;

                case DONE:
                    taskList.complete(cmd.getListNumber());
                    ui.displaySuccess();
                    storage.save(taskList);
                    break;

                case DELETE:
                    taskList.delete(cmd.getListNumber());
                    ui.displaySuccess();
                    storage.save(taskList);
                    break;

                case TODO:
                    taskList.add(new Todo(cmd.getDesc()));
                    ui.displaySuccess();
                    storage.save(taskList);
                    break;

                case DEADLINE:
                    taskList.add(new Deadline(cmd.getDesc(), cmd.getTime()));
                    ui.displaySuccess();
                    storage.save(taskList);
                    break;

                case EVENT:
                    taskList.add(new Event(cmd.getDesc(), cmd.getTime()));
                    ui.displaySuccess();
                    storage.save(taskList);
                    break;
                }
            } catch (VergilException e) {
                ui.displayError(e.getMessage());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new Vergil("data/vergil_list.sav").run();
    }
}
