package stub;

import task.Task;


public class TodoStub extends Task {

    public TodoStub(String input) {
        this.type = "T";
        this.description = input;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

}
