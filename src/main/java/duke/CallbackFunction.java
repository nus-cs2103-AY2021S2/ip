package duke;

public class CallbackFunction implements Runnable {

    Runnable callback;

    /**
     * Initializes a CallbackFunction with a Runnable.
     * @param callback the Runnable to be run when CallbackFunction.run() is executed.
     */
    public CallbackFunction(Runnable callback) {
        this.callback = callback;
    }

    /**
     * Returns a CallbackFunction which does nothing when run.
     * @return the empty CallbackFunction
     */
    public static CallbackFunction empty() {
        return new CallbackFunction(null);
    }

    /**
     * Runs the callback function.
     */
    @Override
    public void run() {
        if (!(this.callback == null)) {
            this.callback.run();
        }
    }
}
