package vergil.types.exceptions;

/**
 * Represents an exception for anomalies (timing clashes) between tasks.
 */
public class VergilAnomalyException extends VergilException {
    /**
     * Constructs a new anomaly exception.
     * @param   message a details message to include with the exception.
     */
    public VergilAnomalyException(String message) {
        super(message);
    }
}
