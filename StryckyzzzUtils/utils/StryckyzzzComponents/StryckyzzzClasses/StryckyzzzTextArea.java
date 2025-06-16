package utils.StryckyzzzComponents.StryckyzzzClasses;

import appWindow.EnvironmentApplication;

public class StryckyzzzTextArea {


	private String currentLang = EnvironmentApplication.getDefaultLang();
	private final String refKey;
	private String currentText = "";

	public StryckyzzzTextArea(String ref) {
		this.refKey = ref;
		this.currentText = EnvironmentApplication.LL.getSingle(refKey);
		EnvironmentApplication.STAS.add(this);
	}

	public void changeTextToLang() {
		String newLang = EnvironmentApplication.getDefaultLang();
		if (!currentLang.equals(newLang)) {
			this.currentText = (EnvironmentApplication.LL.getSingle(refKey));
			currentLang = newLang;
		}
	}
	
	public String getText() {
		currentText = EnvironmentApplication.LL.getSingle(refKey);
		if(currentText == null) {
			return refKey;
		}
		return currentText;
	}
}
