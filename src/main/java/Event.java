public class Event extends Task {

    private String start;
    private String end;

    private Event (String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public static Event createEvent (String str) throws ChatException {
        if (!str.startsWith("event")) {
            //i.e. event(followed by one or more empty spaces)
            //i.e. list
            throw new ChatException("wrong instruction for event\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        } else if (str.strip().equals("event")) {
            //i.e. event
            throw new ChatException("event name, start and end date/time missing\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        } else if (!str.startsWith("event ")) {
            //i.e. eventfinal exam
            throw new ChatException("no spacing after event\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        }

        String tempStr = str.replace("event ", "").stripLeading();
        if (tempStr.startsWith("/at")) {
            //i.e. event /at
            //i.e. event /at tmr 5-6pm
            throw new ChatException("event name missing\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        } else if (!tempStr.contains("/at")) {
            //i.e. event final exam
            //i.e. event final exam 5-6pm
            throw new ChatException("/at missing\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        } else if (!tempStr.contains(" /at ")) {
            //i.e. event final exam/at
            //i.e. event final exam/at 5-6pm
            //i.e. event final exam /at5-6pm
            //i.e. event final exam/at5-6pm
            throw new ChatException("missing spaces before or after /at\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        }

        String[] tempArr = tempStr.split(" /at ");
        if (tempArr.length < 2) {
            //i.e. event final exam /at
            throw new ChatException("missing start and end date/time\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        } else if (tempArr.length > 2 || tempArr[1].contains("/at")) {
            //i.e. event final exam /at /at 5-6pm
            throw new ChatException("not allowed to have more than 1 ' /at '\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        } else if (!tempArr[1].contains("-")) {
            //i.e. event final exam /at 5pm
            throw new ChatException("missing '-'\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        }

        String[] timeArr = tempArr[1].split("-");
        if (timeArr.length < 2) {
            //i.e. event final exam /at 5-
            throw new ChatException("missing start or end date/time\n" +
                    "Please input with this format:\n" +
                    "event [name] /at [end date/time]-[start date/time]");
        } else {
            return new Event(tempArr[0].strip(), timeArr[0].stripLeading(), timeArr[1].stripTrailing());
        }
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s-%s)", super.toString(), this.getStart(), this.getEnd());
    }

}
