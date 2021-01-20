public class EventTask extends Task {
    EventTask(String task) {
        super(task);
    }

    String[] divideCommand = task.split(" ");

    @Override
    public String toString() {
        String time = "";
        for (int i = 4; i < divideCommand.length; i++) {
            if (i == divideCommand.length - 1) {
                time += divideCommand[i];
            } else {
                time += divideCommand[i] + " ";
            }
        }
        String taskRepresent = divideCommand[1] + " " + divideCommand[2]
                + " (" + divideCommand[3].substring(1) + ": " + time + ")";
        if (this.getStatus()) {
            return "[E][X] " + taskRepresent;
        } else {
            return "[E][ ] " + taskRepresent;
        }
    }
}
