package be.vdab.exceptions;

public class ToysException extends Exception {
	private static final long serialVersionUID = 1L;

	public ToysException() {
		super();

	}

	public ToysException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

	public ToysException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public ToysException(String arg0) {
		super(arg0);

	}

	public ToysException(Throwable arg0) {
		super(arg0);

	}

}