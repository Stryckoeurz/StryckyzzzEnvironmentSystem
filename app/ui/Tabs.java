package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import appWindow.EnvironmentApplication;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class Tabs {
	private JTabbedPane tabbedPane;
	private JPanel exploringTab;
	private JPanel overviewTab;
	private JLabel label;
	private JSplitPane splitPane;
	private JSplitPane splitpane;
	private JLabel label_1;
	
	public Tabs(JFrame frame) {
		tabbedPane = new JTabbedPane();
	    
	    exploringTab = new JPanel();
	    exploringTab.setLayout(new BorderLayout(0, 0));
	    label_1 = new JLabel(EnvironmentApplication.LL.getSingle("tab.explorer"));
	    exploringTab.add(label_1);

	    overviewTab = new JPanel();
	    overviewTab.setLayout(new BorderLayout(0, 0));
	    label = new JLabel(EnvironmentApplication.LL.getSingle("tab.overview"));
	    overviewTab.add(label);

	    tabbedPane.addTab(EnvironmentApplication.LL.getSingle("tab.explorer"), exploringTab);
	    tabbedPane.addTab(EnvironmentApplication.LL.getSingle("tab.overview"), overviewTab);
	    
	    splitPane = new JSplitPane();
	    overviewTab.add(splitPane);
	    
	    splitpane = new JSplitPane();
	    exploringTab.add(splitpane);
	    
	    frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
	
}
