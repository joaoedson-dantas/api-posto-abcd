package posto.abcd.api.infra.exceptions;
public class GlobalSettingsNotFoundException extends RuntimeException{
    public GlobalSettingsNotFoundException(String message) {
        super(message);
    }
}
