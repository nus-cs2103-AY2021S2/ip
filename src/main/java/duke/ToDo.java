package duke;

class ToDo extends Task {

    /**
     * ToDo constructor where the boolean isDone is set to false
     * @param s name of ToDo
     */
    ToDo(String s) {
        super(s);
    }

    /**
     * ToDo constructor where the boolean isDone is given
     * @param s name of ToDo
     * @param c value that boolean isDone will be set to
     */
    ToDo(String s, boolean c) {
        super(s, c);
    }

    /**
     * Provides the format for which the ToDo will be saved in the txt file
     * @return a string in the format to be saved in the txt file
     */
    @Override
    String saveName() {
        return String.format("todo1!1%s1!1%b", super.getName(), super.getIsDone());
    }

    /**
     * Checks if the ToDo is to be done on the day
     * @param s the day that is given in yyy-mm-dd format (e.g. 2021-01-31)
     * @return true if !isDone
     */
    @Override
    boolean onDay(String s) {
        return !super.getIsDone();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
