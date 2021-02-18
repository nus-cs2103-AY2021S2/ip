package duke.task;

public class Notes extends Task {
    String note;

    public Notes(String task) {
        super(task);
        note = "";
        for(int i = 1; i < super.divideCommand.length; i++){
            note += i == super.divideCommand.length - 1
                    ? super.divideCommand[i] : super.divideCommand[i] + " ";
        }
    }

    public String getNote(){
        return note;
    }

    @Override
    public String toString() {
        return note;
    }
}
