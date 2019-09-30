package src_stack;


public class EmptyStackException extends RuntimeException{
	
	public EmptyStackException() {
		super();
	}
	
	public EmptyStackException(String info) {
		super(info);
	}
	
	public String getMessage() {
		return super.getMessage();
	}

}
