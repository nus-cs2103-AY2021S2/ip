package kelbot;

public class TriviaTask extends Task {
    /**
     * Initializes TriviaTask.
     * @param name The name of the TriviaTask.
     */
    public TriviaTask(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "Trivia: " + super.getName() + super.getStringTag();
    }
}
