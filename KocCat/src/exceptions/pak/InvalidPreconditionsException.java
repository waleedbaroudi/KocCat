package exceptions.pak;

@SuppressWarnings("serial")
public class InvalidPreconditionsException extends IllegalArgumentException {// an exceptions in case of entering
																				// invalid ghost, fruit, or poison
																				// count.

	public InvalidPreconditionsException() {
		super();
	}

	public InvalidPreconditionsException(String message) {
		super(message);
	}

}
