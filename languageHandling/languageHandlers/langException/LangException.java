package languageHandlers.langException;

public enum LangException {
	MissingLanguageFile { 
		@Override
		public Exception get() {
			return new Exception("Missing Language File");
		}
	}, InvalidFormatKey {
		@Override
		public Exception get() {
			return new Exception("Invalid FormatKey");
		}
	}, InvalidShelf {
		@Override
		public Exception get() {
			return new Exception("Invalid Shelf");
		}
	}, InvalidTextSpace {
		@Override
		public Exception get() {
			return new Exception("Invalid TextSpace");
		}
	};

	public abstract Exception get();
}
