package duke.handler;

import java.time.LocalDateTime;

import duke.tasks.Event;

public class EventHandler extends TaskHandler {

    public EventHandler(String eventDes, LocalDateTime dateTimeAt) {
        super(new Event(eventDes, dateTimeAt));
    }

    public Event getEventTask() {
        assert toAdd instanceof Event;
        Event eventTask = (Event) toAdd;
        return eventTask;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof EventHandler) {
            EventHandler eventHandler = (EventHandler) obj;
            Event eventTask = eventHandler.getEventTask();
            return toAdd.equals(eventTask);
        } else {
            return false;
        }
    }
}
