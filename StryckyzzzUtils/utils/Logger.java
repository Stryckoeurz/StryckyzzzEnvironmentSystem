package utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;

/**
 * This was made really late in the evening, i was tired and needed a better log method than catching exceptions
 * <p>
 * So i made this monstrosity
 */
public class Logger {

	private static List<File> logPath = new LinkedList<File>();
	private static String className;
    private static long startTime;
    
	private File logFile;
    
	public Logger(Class<?> clazz) {
        this.className = clazz.getName();
        this.startTime = System.currentTimeMillis();
        this.logFile = createLogFile();
        resetLogFile();
        logInfo("Generated LogFile for the following class : " + clazz.getName());
    }

    private File createLogFile() {
        File baseDir = new File(System.getProperty("user.dir"), "logs");
        String[] parts = className.split("\\.");
        String simpleClassName = parts[parts.length - 1];

        File logDir = baseDir;
        for (int i = 0; i < parts.length - 1; i++) {
            logDir = new File(logDir, parts[i]);
        }
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        return new File(logDir, simpleClassName + "_log.txt");
    }

    public void resetLogFile() {
        try (FileWriter writer = new FileWriter(logFile, false)) {
            writer.write("");
            System.out.println("Reset log file: " + logFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to reset log file: " + e.getMessage());
        }
    }

    public synchronized void log(LogLevel level, String context, Exception e) {
        String logEntry = buildLogEntry(level, context, e);
        writeToFile(logEntry);
    }

    public void logInfo(String context) {
        log(LogLevel.INFO, context, null);
    }

    public void logWarn(String context, Exception e) {
        log(LogLevel.WARN, context, null);
    }

    public void logError(String context, Exception e) {
        log(LogLevel.ERROR, context, e);
    }
    
    public String getFileName() {
    	return className;
    }

    public void logDuration(String label) {
        long elapsedMillis = System.currentTimeMillis() - startTime;
        long seconds = elapsedMillis / 1000;
        long ms = elapsedMillis % 1000;
        logInfo(label + " completed in " + seconds + "." + String.format("%03d", ms) + "s");
    }

    private static String buildLogEntry(LogLevel level, String context, Exception e) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(timestamp).append("] ")
          .append(level).append(" - ").append(context);
        if (e != null) {
            sb.append(" | Exception: ").append(e.getClass().getSimpleName()).append(" - ").append(e.getMessage());
            for (StackTraceElement el : e.getStackTrace()) {
                sb.append("\n    at ").append(el.toString());
            }
        }
        return sb.toString();
    }

    private void writeToFile(String entry) {
        if (logFile == null) {
            System.err.println("Log file not initialized.");
            return;
        }
        File parent = logFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Logger failed: " + e.getMessage());
        }
    }
}



