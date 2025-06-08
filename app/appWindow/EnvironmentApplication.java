package appWindow;

import javax.swing.JFrame;

import Handlers.PNGHandler;
import languageHandlers.LanguageLoader;

import ui.Menu;
import ui.Tabs;
import utils.ClassUtil;
import utils.Logger;
import utils.StryckyzzzComponents.StryckyzzzClasses.StryckyzzzTextAreas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

public class EnvironmentApplication {
	
	public static final String NAME = "StryckyzzzEnvironmentSystem";
	public static final String VERSION = "V0.1-indev";
	
	public static StryckyzzzTextAreas STAS ;
	public static LanguageLoader LL;
	public static Logger logger;
	
    public static String defaultLang = "en_EN.txt";
	
	private static Menu menu;
	private static Tabs tabs;
	
	public static JFrame frame;
	
	private static void initEnv() {
		logger = new Logger(ClassUtil.getClassName());
		STAS = new StryckyzzzTextAreas();
		LL = new LanguageLoader();
		logger.logInfo("Initialized app");
	}
	
	/**
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    	initEnv();
    	logger.logInfo("Instantiated Main");
    	logger.logInfo("Starting Main");
    	frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.setIconImage(PNGHandler.loadImage(
        	    System.getProperty("user.dir") 
        	    + File.separator + "files" 
        	    + File.separator + "img" 
        	    + File.separator + "png" 
        	    + File.separator + "icon.png"
        	));
        frame.setLayout(new BorderLayout());

        menu = new Menu(frame);
        logger.logInfo("Created menu");
        tabs = new Tabs(frame);
        logger.logInfo("Created tabs");
        frame.setVisible(true);
        
        logger.logDuration(logger.getFileName());
    }
    
    public static JFrame reloadUIs() {
    	if (frame == null) {
    		throw new IllegalStateException("Frame is not initialized!");
    	}
    	menu = new Menu(frame);
    	tabs = new Tabs(frame);
    	return frame;
    }
    
    public static String getDefaultLang() {
    	return defaultLang;
    }
    
    public static void changeDefaultLang(String s) {
    	defaultLang = s;
    }
}
