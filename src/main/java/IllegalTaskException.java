class IllegalTaskException extends StringIndexOutOfBoundsException {
    String taskType;

    IllegalTaskException(String message) {
        super(message);
    }

    IllegalTaskException(String message, String taskType) {
        super(message);
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "You forgot to enter the description of the " + taskType + "!\n"
                + (char) 9 + (char) 9 + "Please re-enter!";
    }
}
