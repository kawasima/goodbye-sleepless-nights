package example.logger;

import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

/**
 * @author kawasima
 */
@Named
@Dependent
public class RecoveryLoggerProducer {
    @Produces
    public RecoveryLogger getProgressLogger(InjectionPoint ip) {
        return new RecoveryLogger(LoggerFactory.getLogger("RECOVERY"));
    }

}
