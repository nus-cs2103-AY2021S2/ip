public class Duke {
    //Main method where the classes are initialized
    public static void main(String[] args) throws DukeException {
        UI ui = new UI();

        String command;
        Tasks taskList = new Tasks();

        ui.greetings();

        while (true) {
            command = ui.getCommand();
            String[] executable = new String[100];
            executable = command.split(" ");

            if (command.equals("bye")) {
                ui.goodbye();
                break;
            } else if (command.equals("list")) {
                taskList.printTasks();
            } else if (executable.length > 1) {
                if (executable[0].equals("done")) {
                    taskList.markAsDone(Integer.parseInt(executable[1]));
                } else if (executable[0].equals("todo")) {
                    taskList.addTask(new TodoTask(command));
                } else if (executable[0].equals("deadline")) {
                    taskList.addTask(new DeadlineTask(command));
                } else if (executable[0].equals("event")) {
                    taskList.addTask(new EventTask(command));
                }
            } else {
                if (executable[0].equals("todo") || executable[0].equals("deadline")
                        || executable[0].equals("event")) {
                    throw new DukeException("Oops!!! Incomplete command :(");
                } else {
                    throw new DukeException("Oops!!! Invalid Input :(");
                }
            }

        }
    }
}
