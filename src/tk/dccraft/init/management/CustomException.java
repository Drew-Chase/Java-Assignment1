package tk.dccraft.init.management;

import tk.dccraft.init.Main;

public class CustomException extends Exception {
	private static final long serialVersionUID = 1L;
	
	Main m = new Main();

	public CustomException() {
	}

	public CustomException(String message) {
		super(message);
	}

	@Override
	public void printStackTrace() {
		m.print("Sorry had an issue in the class " + this.getClass());
	}

}
