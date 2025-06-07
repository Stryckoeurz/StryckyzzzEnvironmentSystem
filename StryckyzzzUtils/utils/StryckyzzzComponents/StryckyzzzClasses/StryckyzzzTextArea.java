package utils.StryckyzzzComponents.StryckyzzzClasses;

import javax.swing.JTextArea;

import appWindow.EnvironmentApplication;

public class StryckyzzzTextArea extends JTextArea{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7987654582414968335L;

	private String currentLang = EnvironmentApplication.lang;
	
	public StryckyzzzTextArea(String ref) {
		super(EnvironmentApplication.LL.getSingle(ref));
		EnvironmentApplication.STAS.add(this);
	}
	
	public void changeTextToLang() {
		if(currentLang != EnvironmentApplication.lang) {
			currentLang = EnvironmentApplication.lang;
		}
	}

}
