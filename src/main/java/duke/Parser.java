package duke;

//make sense of user command
public class Parser {

    public Parser() {
    }

    public void isEmptyDesc(String[] inputArr) throws DukeException {
        if (inputArr.length == 1) {
            throw new DukeException();
        }
    }

    public String[] getInputArr(String input) {
        return input.split(" ", 2);
    }
}
