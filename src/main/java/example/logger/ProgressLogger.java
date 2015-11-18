package example.logger;

import org.slf4j.Logger;
import org.slf4j.helpers.SubstituteLogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author kawasima
 */
public class ProgressLogger extends SubstituteLogger {
    private long targetCount;
    private LocalDateTime startDateTime;

    public ProgressLogger(Logger delegate) {
        super("PROGRESS_DELEGATE");
        setDelegate(delegate);
    }

    public void start(long targetCount) {
        this.targetCount = targetCount;
        this.startDateTime = LocalDateTime.now();
        LocalDateTime expected = null;
        info("処理を開始します");
        info("総処理対象は{}件で、終了予想時刻は{}です。", targetCount,
                expected == null ? "不明" : DateTimeFormatter.ofPattern("HH:mm:ss").format(expected));
    }

    public void progress(long completedCount) {
        if (completedCount <= 0) return;

        LocalDateTime current = LocalDateTime.now();
        long elaspeSec = startDateTime.until(current, ChronoUnit.SECONDS);
        double unitSec = (double) elaspeSec / (double) completedCount;
        long remainSec = (long) (unitSec * (targetCount - completedCount));

        info("{}件({}%)処理しました。現時点での終了予想時刻は{}です。",
                completedCount,
                ((double) completedCount * 100)/ (double) targetCount,
                DateTimeFormatter.ofPattern("HH:mm:ss").format(current.plusSeconds(remainSec)));
    }

    public void end() {
        info("処理を終了しました。");
    }
}
