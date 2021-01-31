package commands;

public class CommandResponse {
    private String responseMsg;
    private boolean isExit;

    public CommandResponse(String responseMsg, boolean isExit){
        this.responseMsg = responseMsg;
        this.isExit = isExit;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }
}
