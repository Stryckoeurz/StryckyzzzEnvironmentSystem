package appWindow;

import javax.swing.JFrame;

import utils.StryckyzzzComponents.StryckyzzzTextAreas;

import javax.swing.*;


public class EnvironmentApplication {
	public static volatile String lang = "fr_fr";
	public static StryckyzzzTextAreas STA = new StryckyzzzTextAreas();
	
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Simple Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a menu bar
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

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel tab1 = new JPanel();
        tab1.add(new JLabel("Welcome to Tab 1"));

        JPanel tab2 = new JPanel();
        tab2.add(new JLabel("This is Tab 2"));

        tabbedPane.addTab("Tab One", tab1);
        tabbedPane.addTab("Tab Two", tab2);

        // Add tabbed pane to frame
        frame.add(tabbedPane);

        // Show the frame
        frame.setVisible(true);
    }
}
