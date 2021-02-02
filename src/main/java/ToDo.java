public class ToDo extends Task {
    private String name;
    private boolean done;

    ToDo(String name) {
        this.name = name;
        this.done = false;
    }

    ToDo(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    @Override
    Task done() {
        return new ToDo(this.name, true);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo td = (ToDo) obj;
            return td.name.equals(this.name) && (td.done == this.done);
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.name;
        }
        return "[T][ ] " + this.name;
    }
}
