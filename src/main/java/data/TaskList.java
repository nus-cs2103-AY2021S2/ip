package data;

import java.util.ArrayList;

/**
 * TaskList extends List so as to leverage Jackson's (de)serialization.
 * Jackson need the type information but a generic list will lose the type at runtime
 * due to erasure, thus a concrete class would allow Jackson to know what type it is
 * at runtime, thus being able to serialise our tasks.
 */
public class TaskList extends ArrayList<Task> {}
