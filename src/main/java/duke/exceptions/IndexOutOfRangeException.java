package duke.exceptions;

public class IndexOutOfRangeException extends ChatBotException {
    public IndexOutOfRangeException() {
        super("OOPS!!! There are not so many chatbot.tasks in the list.");
    }
}
