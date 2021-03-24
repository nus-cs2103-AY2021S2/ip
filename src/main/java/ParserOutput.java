
public class ParserOutput {
    private boolean bye;
    private Task task;
    private int index;
    private int action;
    private String searchString;

    private ParserOutput(boolean bye, Task task, int action, int index) {
        this.bye = bye;
        this.task = task;
        this.action = action;
        this.index = index;
    }
    private ParserOutput(boolean bye, Task task, int action, int index, String searchString) {
        this.bye = bye;
        this.task = task;
        this.action = action;
        this.index = index;
        this.searchString = searchString;
    }


    public static ParserOutput byeOutput() {
        return new ParserOutput(true, null, 0, 0);
    }

    public static ParserOutput removeOutput(int index) {
        return new ParserOutput(false, null, 1, index);
    }

    public static ParserOutput doneOutput(int index) {
        return new ParserOutput(false, null, 2, index);
    }

    public static ParserOutput addOutput(Task task) {
        return new ParserOutput(false, task, 3, 0);
    }

    public static ParserOutput findOutput(String str) {
        return new ParserOutput(false, null, 4, 0, str);
    }

    public static ParserOutput listOutput() {
        return new ParserOutput(false, null, 5, 0);
    }

    public boolean isBye() {
        return bye;
    }

    public int getIndex() {
        return index;
    }

    public int getAction() {
        return action;
    }

    public Task getTask() {
        return task;
    }

    public String getSearchString(){
        return searchString;
    }
}