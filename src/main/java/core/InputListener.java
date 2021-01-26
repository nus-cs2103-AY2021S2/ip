package core;

/**
 * Functional Interface which only has a function to deal with new method.
 */
public interface InputListener {
    /**
     * Method to deal with any input.
     * @param type - type of input
     * @param data - the data for the task
     * @return
     */
    String onNewInput(InputType type, String data);
}
