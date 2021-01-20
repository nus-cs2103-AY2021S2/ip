public class Task {
    private String input;
    private Task_State state;

    public Task(String input) {
        this.input = input;
        this.state = Task_State.NOTDONE;
    }

    public void doTask() {
        this.state = Task_State.DONE;
    }

    public String getInput() {
        return input;
    }

    public Task_State getState() {
        return state;
    }
}
