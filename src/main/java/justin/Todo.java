package justin;

import justin.Task;

/**
 * This class creates a TOD0 class that extends
 * from task class
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */


public class Todo extends Task {

    /**
     * This method creates a new TOD0 class
     *
     * @param description of the given class
     */

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}