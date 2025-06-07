package languageHandlers;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

public class LanguageManager {

    public static List<File> getAvailableLanguageCodes() {
        File file = new File(System.getProperty("user.dir") + File.separator + "languageHandling" + File.separator + "lang" + File.separator);
		Stream<File> streamFiles = Arrays.stream(file.listFiles()); 
		List<File> files = streamFiles.toList();
		return files;
    }
}



