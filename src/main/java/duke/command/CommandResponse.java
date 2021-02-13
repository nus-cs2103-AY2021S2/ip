package duke.command;

public class CommandResponse {

    private String msg;
    private Boolean toExit;

    public CommandResponse(String msg, Boolean toExit) {
        this.msg = msg;
        this.toExit = toExit;
    }

    public String getMsg() {
        return this.msg;
    }

    public Boolean canExit() {
        return this.toExit;
    }
}
