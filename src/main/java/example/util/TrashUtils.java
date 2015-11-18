package example.util;

import example.exception.IORuntimeException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * @author kawasima
 */
public class TrashUtils {
    static Path trashDirectory = Paths.get("trash");

    public static void throwAray(File uselessFile) {
        throwAway(uselessFile, 3);
    }

    public static void throwAway(File uselessFile, long storagePeriod) {
        LocalDate expiry = LocalDate.now().plus(storagePeriod, ChronoUnit.DAYS);
        String fileName = DateTimeFormatter.ofPattern("yyyyMMdd").format(expiry) + "_" + uselessFile.getName();
        Path trashPath = trashDirectory.resolve(fileName);
        if (Files.exists(trashPath)) {
            trashPath = trashDirectory.resolve(trashPath.getFileName() + "." + UUID.randomUUID().toString());
        }

        try {
            Files.move(uselessFile.toPath(), trashPath);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    protected static void clean() {
        try {
            Files.deleteIfExists(trashDirectory);
            Files.createDirectories(trashDirectory);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }
}
