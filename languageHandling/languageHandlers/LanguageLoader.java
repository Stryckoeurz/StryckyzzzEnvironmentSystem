package languageHandlers;

import java.io.*;
import java.util.*;

import appWindow.EnvironmentApplication;

public class LanguageLoader {
    private final Map<String, String> translations = new HashMap<>();
    
    public LanguageLoader() {
    	loadLanguageFile(EnvironmentApplication.lang);
    }
    
    public boolean loadLanguageFile(String fileName) {
        translations.clear();
        //Try using your filenames with a .txt or not, i dare you
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }
        File file = new File(System.getProperty("user.dir") + File.separator + "languageHandling" + File.separator + "lang" + File.separator + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int sep = line.indexOf('=');
                if (-1 != sep) {
                    String key = line.substring(0, sep).trim();
                    String value = line.substring(sep + 1).trim();
                    translations.put(key, value);
                }
            }
            reloadAllLanguageMethods();
            return true;
        } catch (IOException e) {
        	System.err.println(e);
            return false;
        }
        
    }

    private void reloadAllLanguageMethods() {
		EnvironmentApplication.STA.forEach(
					(lang) -> lang.changeTextToLang());
	}

	public String getSingle(String key) {
        return translations.getOrDefault(key, "[" + key + "]");
    }
   
    public Map<String, String> getAllParts(String name) {
        Map<String, String> result = new HashMap<>();
        String prefix = name + ".";

        for (Map.Entry<String, String> entry : translations.entrySet()) {
            String fullKey = entry.getKey();
            if (fullKey.startsWith(prefix)) {
                String part = fullKey.substring(prefix.length());
                String value = entry.getValue();
                if (value == null || value.isEmpty()) {
                    value = fullKey; // fallback: use key itself
                }
                result.put(part, value);
            }
        }

        return result;
    }
}

