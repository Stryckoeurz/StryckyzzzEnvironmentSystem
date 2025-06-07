package appWindow;

import javax.swing.JFrame;

import Handlers.PNGHandler;
import languageHandlers.LanguageLoader;

import ui.Menu;
import ui.Tabs;
import utils.StryckyzzzComponents.StryckyzzzClasses.StryckyzzzTextAreas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

public class EnvironmentApplication {
	
	public static final String NAME = "StryckyzzzEnvironmentSystem";
	public static final String VERSION = "V0.1-indev";
	
	public static volatile String lang = "en_EN.txt";
	public static StryckyzzzTextAreas STAS ;
	public static LanguageLoader LL;
	
	private static Menu menu;
	private static Tabs tabs;
	
	public JFrame frame;
	
	public static void initEnv() {
		STAS = new StryckyzzzTextAreas();
		LL = new LanguageLoader();
	}
	
	/**
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    	initEnv();
    	
        JFrame frame = new JFrame(NAME);
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
        tabs = new Tabs(frame);
        
        frame.setVisible(true);
    }
}
