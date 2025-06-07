package languageHandlers;

import java.io.*;
import java.util.*;

import LibraryClasses.Library;
import LibraryClasses.Shelf;
import LibraryClasses.TextSpace;
import appWindow.EnvironmentApplication;
import languageHandlers.langException.LangException;
import utils.LogLevel;
import utils.Logger;

public class LanguageLoader {
    private final Library translations;
    private final List<TextSpace> txtspaces;
    private final Logger logger;
    

    public LanguageLoader() {
        this.logger = new Logger(this.getClass());
        this.translations = new Library();
        this.txtspaces = new ArrayList<>();
        logger.log(LogLevel.INFO, "Initializing LanguageLoader...", null);
        loadLanguage(EnvironmentApplication.lang);
    }

    public boolean loadLanguage(String lang) {
        translations.clear();
        logger.log(LogLevel.INFO, "Attempting to load language: " + lang, null);

        File langDir = new File(System.getProperty("user.dir") + File.separator + "languageHandling" + File.separator + "lang");
        if (langDir.exists()) {
            return loadLanguageFile(langDir);
        } else {
            logger.logError("Language directory not found: " + langDir.getAbsolutePath(), LangException.MissingLanguageFile.get());
            return false;
        }
    }

    private boolean loadLanguageFile(File file) {
        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                loadLanguageFile(f);
            }
        } else if (file.isFile() && file.getName().equals(EnvironmentApplication.lang)) {
            logger.logInfo("Loading language file: " + file.getName());
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;

                    int typesep = line.indexOf('.');
                    int textsep = line.indexOf('=');

                    if (typesep != -1 && textsep != -1 && typesep < textsep) {
                        String type = line.substring(0, typesep).trim();
                        String key = line.substring(typesep + 1, textsep).trim();
                        String value = line.substring(textsep + 1).trim();

                        Shelf shelf = translations.getShelf(type);
                        if (shelf == null) {
                            shelf = new Shelf(type);
                            translations.put(type, shelf);
                            logger.logInfo("Loaded shelf: " + shelf.getName());
                        }

                        if (!shelf.containsKey(key)) {
                            TextSpace txtspace = new TextSpace(value);
                            txtspaces.add(txtspace);
                            shelf.put(key, txtspace);
                            logger.logInfo("Loaded entry: " + type + "." + key + " = " + value);
                        }
                    }
                }
                reloadAllLanguageMethods();
                logger.logDuration(logger.getFileName());
                return true;
            } catch (IOException e) {
                logger.logError("IOException while reading language file: ", e);
                return false;
            }
        }
        return true;
    }

    private void reloadAllLanguageMethods() {
        logger.logInfo("Reloading language across all STAs...");
        EnvironmentApplication.STAS.forEach(STA -> STA.changeTextToLang());
    }
    
    public String getSingle(String compositeKey) {
        int dotIndex = compositeKey.indexOf('.');
        if (dotIndex == -1) {
            logger.logError("Invalid key format: " + compositeKey + ". Expected format: type.key", LangException.InvalidFormatKey.get());
            return null;
        }

        String type = compositeKey.substring(0, dotIndex);
        String key = compositeKey.substring(dotIndex + 1);
        
        Shelf shelf = translations.getShelf(type);
        if (shelf == null) {
            logger.logError("No shelf found for type: " + type, LangException.InvalidShelf.get());
            return null;
        }

        TextSpace textSpace = shelf.getDictionary(key);
        if (textSpace == null) {
            logger.logError("No dictionary entry for key: " + key + " in type: " + type, LangException.InvalidTextSpace.get());
            return new String(type+"."+key);
        }
        logger.logInfo("Could not find key " + key);
        return textSpace.getTextSpace();
    }
    
}