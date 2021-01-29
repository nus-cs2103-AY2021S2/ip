public class Task {
    private final String input;
    private Task_State state;

    public Task(String input) {
        this.input = input;
        this.state = Task_State.NOTDONE;
    }

    public Task doTask() {
        this.state = Task_State.DONE;
        return this;
    }

    public String getInput() {
        return input;
    }

    public Task_State getState() {
        return state;
    }

    public String taskSave() {
        String stateB;
        if (state == Task_State.DONE) {
            stateB = "1";
        } else {
            stateB = "0";
        }
        return " | " + stateB + " | " + input;
    }

    @Override
    public String toString() {
        return "[" + state + "] " + input;
    }
}
