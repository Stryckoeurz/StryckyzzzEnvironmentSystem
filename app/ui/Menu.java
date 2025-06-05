package ui;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import languageHandlers.LanguageLoader;

public class Menu {
	
	private LanguageLoader ll = new LanguageLoader();
	private HashMap<String, String> stringMenuMap = new HashMap<String,String>();
	
	
	public Menu(JFrame frame) {
		
		ll.get("menu");
		
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e ->
            JOptionPane.showMessageDialog(frame, "Simple Swing App\nVersion 1.0", "About", JOptionPane.INFORMATION_MESSAGE)
        );
        helpMenu.add(aboutItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);
	}
	
}
