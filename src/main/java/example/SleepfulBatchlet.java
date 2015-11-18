package example;

import example.logger.ProgressLogger;

import javax.batch.api.AbstractBatchlet;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.TimeUnit;

/**
 * @author kawasima
 */
@Named("SleepfulBatchlet")
@Dependent
public class SleepfulBatchlet extends AbstractBatchlet {
    @Inject
    ProgressLogger progressLogger;

    @Override
    public String process() throws Exception {
        long TOTAL = 500000;
        progressLogger.start(TOTAL);

        for (int i=0; i< TOTAL; i++) {
            TimeUnit.MILLISECONDS.sleep(1);
            if (i % 10000 == 0) {
                progressLogger.progress(i);
            }
        }

        progressLogger.end();

        return "SUCCESS";
    }
}
