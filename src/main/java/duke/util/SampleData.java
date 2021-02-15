package duke.util;

import java.util.List;

/**
 * Provides sample data for duke.
 */
public class SampleData {

    /**
     * Returns a list of sample tasks.
     *
     * @return List of sample tasks.
     * @throws DukeInputException If invalid creation of tasks.
     */
    public static List<Task> loadSampleData() throws DukeInputException {
        return List.of(
                Todo.createTodo("apply for internship"),
                Todo.createTodo("water plants").setHighPriority(),
                Todo.createTodo("check email").markDone(),
                Todo.createTodo("return bob $10"),
                Todo.createTodo("buy eggs").setHighPriority(),
                Todo.createTodo("clean fan").markDone(),
                Deadline.createDeadline("return library book /by 2021-02-22"),
                Deadline.createDeadline("assignment 1 /by 2021-02-22").setHighPriority(),
                Deadline.createDeadline("ip level 10 /by 2021-02-05").markDone(),
                Event.createEvent("sec school reunion! /at 2021-02-19"),
                Event.createEvent("2103 finals /at 2021-04-24"),
                Event.createEvent("lunch with mom /at 2021-02-20").setHighPriority(),
                Event.createEvent("group meeting /at 2021-02-14").markDone()
        );
    }
}
