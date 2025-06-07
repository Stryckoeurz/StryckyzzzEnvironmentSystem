package utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This was made really late in the evening, i was tired and needed a better log method than catching exceptions
 * <p>
 * So i made this monstrosity
 */
public class Logger {

	private static Class<? extends Object> c;
	private static File file;
    private static final long startTime = System.currentTimeMillis();

    public Logger(Class<? extends Object> c) {
    	this.c = c;
    	resetLogFile();
    }
    
    public void resetLogFile() {
    	file = new File(
        		System.getProperty("user.dir")+ 
        		File.separator+
        		"logs"+
        		File.separator
        		+c.getName()+
        		"_log.txt");
        file.getAbsoluteFile();
        if (file.exists()) {
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.write(""); // Clear content
            } catch (IOException e) {
                System.err.println("Failed to reset log file: " + e.getMessage());
            }
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

    private static void writeToFile(String entry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException ioException) {
            System.err.println("Logger failed: " + ioException.getMessage());
        }
    }
}



