public class UpdateCommand extends Command {
    private int option;
    private String field;
    private String value;

    /**
     * Creates a update command.
     *
     * @param option , a number indicating which task to update.
     * @param field , field of task to update.
     * @param value , value to update to.
     */
    public UpdateCommand(int option, String field, String value) {
        this.option = option;
        this.field = field;
        this.value = value;
    }

    public String execute(TaskList taskList, Ui ui) {
        return taskList.update(option, field, value);
    }
}
