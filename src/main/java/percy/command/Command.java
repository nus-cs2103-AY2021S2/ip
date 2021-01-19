package percy.command;

public class Command {
    public String fullCmd;

    public Command(String fullCmd) {
        this.fullCmd =  fullCmd.trim().strip();
    }

    private String[] splitCmd() {
        String[] splitCmd = this.fullCmd.trim().split(" ", 2); // split into 2 chunks
        if (splitCmd.length != 2) { // 1 length only
            splitCmd = new String[]{splitCmd[0], ""};
        }
        return splitCmd;
    }

    public String getVerbCmd() {
        return this.splitCmd()[0];
    }

    public String getItem() {
        return this.splitCmd()[1];
    }
}
