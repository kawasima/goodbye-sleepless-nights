package example.exception;

/**
 * @author kawasima
 */
public abstract class ManualRecoverableException extends Exception {
    public abstract String getSummary();
    public abstract String getInstruction();
}
