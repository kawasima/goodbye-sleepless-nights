package example.exception;

import java.io.IOException;

/**
 * @author kawasima
 */
public class IORuntimeException extends RuntimeException {
    public IORuntimeException(IOException ioe) {
        super(ioe);
    }
}
