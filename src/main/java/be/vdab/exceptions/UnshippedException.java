package be.vdab.exceptions;

public class UnshippedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnshippedException() {
		super();

	}

	public UnshippedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public UnshippedException(String message, Throwable cause) {
		super(message, cause);

	}

	public UnshippedException(String message) {
		super(message);

	}

	public UnshippedException(Throwable cause) {
		super(cause);
	}

}