package duke.responses;

/**
 * Response types by Duke.
 *
 * OK indicates user input is valid and operations were performed successfully.
 * BAD indicates user input is invalid, or operations failed.
 * EXIT is a signal to the program to terminate immediately.
 */
public enum ResponseStatus {
    OK,
    BAD,
    EXIT
}
