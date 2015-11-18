package example.exception;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author kawasima
 */
public class FileHasNotArrivedException extends ManualRecoverableException {
    private File targetFile;
    private String sourceSystem;
    private LocalTime expected;
    private long graceSeconds;

    public FileHasNotArrivedException(File file, String sourceSystem, LocalTime expected, long graceSeconds) {
        this.targetFile = targetFile;
        this.sourceSystem = sourceSystem;
        this.expected = expected;
        this.graceSeconds = graceSeconds;
    }

    @Override
    public String getSummary() {
        return sourceSystem + "からのファイルが到着予定時刻"
                + DateTimeFormatter.ofPattern("HH:mm:ss").format(expected)
                + "を越えても届きませんでした。";
    }

    @Override
    public String getInstruction() {
        StringBuilder msg = new StringBuilder();
        String deadline = DateTimeFormatter.ofPattern("HH:mm").format(expected.plus(graceSeconds, ChronoUnit.SECONDS));
        msg.append(deadline).append("までに")
                .append(targetFile.getAbsolutePath())
                .append("のファイルが存在すれば、バッチを再実行してください。")
                .append('\n')
                .append(deadline)
                .append("を越えて到着しない場合は、連絡ルートAにしたがって連絡してください。");
        return msg.toString();
    }
}
