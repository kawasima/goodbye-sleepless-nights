package example.logger;

import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

/**
 * @author kawasima
 */
@Dependent
@Named
public class ProgressLoggerProducer {
    @Produces
    public ProgressLogger getProgressLogger(InjectionPoint ip) {
        return new ProgressLogger(LoggerFactory.getLogger("PROGRESS"));
    }
}
