package ui;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import appWindow.EnvironmentApplication;

public class Menu {
	
	public Menu(JFrame frame) {
		
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu(EnvironmentApplication.LL.getSingle("menu.file"));
        JMenuItem newItem = new JMenuItem(EnvironmentApplication.LL.getSingle("menu.newFile"));
        JMenuItem langItem = new JMenuItem(EnvironmentApplication.LL.getSingle("menu.lang"));
        JMenuItem exitItem = new JMenuItem(EnvironmentApplication.LL.getSingle("menu.exit"));

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(langItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu(EnvironmentApplication.LL.getSingle("menu.help"));
        JMenuItem aboutItem = new JMenuItem(EnvironmentApplication.LL.getSingle("menu.help"));
        aboutItem.addActionListener(e ->
            JOptionPane.showMessageDialog(
            		frame, 
            		EnvironmentApplication.NAME + " " + EnvironmentApplication.VERSION, 
            		EnvironmentApplication.LL.getSingle("menu.help"),
            		JOptionPane.INFORMATION_MESSAGE)
        );
        helpMenu.add(aboutItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);
	}
	
}
