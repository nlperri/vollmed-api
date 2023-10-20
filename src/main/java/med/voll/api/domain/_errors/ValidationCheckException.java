package med.voll.api.domain._errors;

public class ValidationCheckException extends RuntimeException {
    public ValidationCheckException(String message) {
        super(message);
    }
}
