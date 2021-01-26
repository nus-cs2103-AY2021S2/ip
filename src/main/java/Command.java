public abstract class Command {
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException;
    
    public boolean isExit() { 
        return false;
    };
    
    public Task checkCommandIndex(TaskList tasks, String command, String str) throws ChatException {
        //for commands with format: [command] [index]
        //returns valid index if index is correct
        if (str.strip().equals(command)) {
            throw new ChatException(String.format("Missing index\n" +
                    "Please input with this format:\n" +
                    "%s [index]", command));
        } else if (!str.contains(" ")) {
            throw new ChatException(String.format("Missing space before index\n" +
                    "Please input with this format:\n" +
                    "%s [index]", command));
        }
        try {
            int i = Integer.parseInt(str.split(" ")[1]) - 1;
            return tasks.getTaskList().get(i);
        } catch (IndexOutOfBoundsException e1) {
            //list is empty, hence i results in index out of bounds
            //or when i >= taskArr.size()
            throw new ChatException("List is empty or index is out of bounds");
        } catch (NumberFormatException e3){
            //i.e. [command] string
            throw new ChatException(String.format("Index should be an integer\n" +
                    "Please input with this format:\n" +
                    "%s [index]", command));
        }
    }
    
}
