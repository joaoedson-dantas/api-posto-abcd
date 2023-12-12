package posto.abcd.api.infra.exceptions;

public class TankAlreadyExistsException extends RuntimeException {
    public TankAlreadyExistsException(String tank) {
        super("Tank " + tank  + " already registered in the system");
    }
}
