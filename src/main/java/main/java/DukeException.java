package main.java;

class DukeException extends Exception {
    private final String errorMessage;

    public DukeException(String error) {
        this.errorMessage = error;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}