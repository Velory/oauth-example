package resource.server.validation;

public class EmailExistsException extends Throwable {
    public EmailExistsException(final String message) {
        super(message);
    }

}
