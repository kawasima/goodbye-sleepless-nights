package example.logger;

import example.exception.ManualRecoverableException;
import org.slf4j.Logger;
import org.slf4j.helpers.SubstituteLogger;

/**
 * @author kawasima
 */
public class RecoveryLogger extends SubstituteLogger {
    public RecoveryLogger(Logger delegate) {
        super("recovery");
        setDelegate(delegate);
    }

    public void log(ManualRecoverableException ex) {
        warn(ex.getSummary() + "\n" + ex.getInstruction());
    }
}
