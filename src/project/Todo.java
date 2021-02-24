package main.java;

public class todo extends Task {

    protected String by;

    public todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

