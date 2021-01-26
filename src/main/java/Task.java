public class Task {
    private String task;
    private String date;
    private boolean done = false;
    private int type; // 0 is todo, 1 is deadline, 2 is event

    public Task(String s, int i) {
        if (i == 0) {
            this.task = s;
            this.date = null;
        } else {
            String[] info = s.split("/");
            if (info.length == 1 || info[0].equals(" ")) {
                throw new IllegalArgumentException();
            } else {
                this.task = info[0];
                this.date = info[1].substring(3);
            }
        }
        this.type = i;
    }

    public void markDone() {
        this.done = true;
    }

    public String checkDone() {
        if (this.done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String checkType() {
        if (this.type == 0) {
            return "[T]";
        } else if (this.type == 1) {
            return "[D]";
        } else {
            return "[E]";
        }
    }
    
    public String toString() {
        if (this.type == 0) {
            return checkType() + checkDone() + this.task;
        } else if  (this.type == 1) {
            return checkType() + checkDone() + this.task + "(by: " + this.date + ")";
        } else {
            return checkType() + checkDone() + this.task + "(at: " + this.date + ")";
        }
    }

    public String export() {
        String done = this.done ? " 1" : " 0";
        String deadline = this.type == 0 ? "" : this.type == 1 ? "/by " + this.date : "/at " + this.date;
        return this.type + done + this.task + deadline;
    }
}
