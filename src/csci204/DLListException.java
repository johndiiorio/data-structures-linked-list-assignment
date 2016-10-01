package csci204;

// Suppress the warning that GListException does not declare a static final serialVersionUID field of type long
@SuppressWarnings("serial")
public class DLListException extends Exception {

	public DLListException(String problem) {
		//Pass the problem to the super class Exception
		super(problem);
	}
}
