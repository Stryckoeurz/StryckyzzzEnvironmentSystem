package stryckyzzzComponents.StryckyzzzClasses;

import java.awt.Component;

import StryckyzzzInterfaces.Reloadable;
import appWindow.EnvironmentApplication;

public class StryckyzzzTextArea implements Reloadable {


	private String currentLang = EnvironmentApplication.getDefaultLang();
	private final String refKey;
	private String currentText = "";

	public StryckyzzzTextArea(String ref) {
		this.refKey = ref;
		this.currentText = EnvironmentApplication.LL.getSingle(refKey);
		EnvironmentApplication.STAS.add(this);
	}

	public void changeTextToLang(String lang) {
		if (lang == null) {
			String newLang = EnvironmentApplication.getDefaultLang();
			if (!currentLang.equals(newLang)) {
				this.currentText = (EnvironmentApplication.LL.getSingle(refKey));
				currentLang = newLang;
			}
		} else  {
			if (!currentLang.equals(lang)) {
				this.currentText = (EnvironmentApplication.LL.getSingle(refKey));
				currentLang = lang;
			}
		}
	}
	
	public String getText() {
		currentText = EnvironmentApplication.LL.getSingle(refKey);
		if(currentText == null) {
			return refKey;
		}
		return currentText;
	}

	@Override
	public void reload() {
		changeTextToLang(null);
	}

	/**
	 * Non implementable method, returns on call
	 */
	@Override
	public void reloadMapped(Component c) {
		return;
	}
}
