package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import appWindow.EnvironmentApplication;
import utils.StryckyzzzComponents.StryckyzzzClasses.StryckyzzzTextArea;

public class Menu {

    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem newItem;
    private JMenuItem langItem;
    private JMenuItem exitItem;
    private JMenuBar menuBar;
    private JMenuItem aboutItem;
    private JDialog dialog;
	private JFrame frame;

    public Menu(JFrame fr) {

    	frame = fr;
    	
        menuBar = new JMenuBar();

        fileMenu = new JMenu(new StryckyzzzTextArea("menu.file").getText());
        newItem = new JMenuItem(EnvironmentApplication.LL.getSingle("menu.newFile"));
        langItem = new JMenuItem(EnvironmentApplication.LL.getSingle("menu.lang"));
        exitItem = new JMenuItem(EnvironmentApplication.LL.getSingle("menu.exit"));

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(langItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        helpMenu = new JMenu(EnvironmentApplication.LL.getSingle("menu.help"));
        aboutItem = new JMenuItem(EnvironmentApplication.LL.getSingle("menu.help"));
        aboutItem.addActionListener(e ->
            JOptionPane.showMessageDialog(
                    frame,
                    EnvironmentApplication.NAME + " " + EnvironmentApplication.VERSION,
                    EnvironmentApplication.LL.getSingle("menu.help"),
                    JOptionPane.INFORMATION_MESSAGE)
        );
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);
        
        langItem.addActionListener(e -> {
            JPanel langPanel = new JPanel(new GridLayout(0, 1));
            List<JButton> jbuttonList = new ArrayList<>();

            EnvironmentApplication.LL.getLangs().forEach((s) -> {
                JButton langButton = new JButton(s);
                langButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        EnvironmentApplication.changeDefaultLang(s);
                        EnvironmentApplication.LL.loadLanguage();
                        frame = EnvironmentApplication.reloadUIs();
                    }
                });
                jbuttonList.add(langButton);
            });

            jbuttonList.forEach(langPanel::add);

            dialog = new JDialog(frame, EnvironmentApplication.LL.getSingle("menu.lang"), true);
            dialog.getContentPane().add(langPanel);
            dialog.pack();
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        });
        
    }
}

