package DAO.Exceptions;

public class ConnectionFailure extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ConnectionFailure() { super(); }
	
	public ConnectionFailure(String arg0) { super(arg0); }
	
	public ConnectionFailure(Throwable arg0) { super(arg0); }
	
	public ConnectionFailure(String arg0, Throwable arg1) { super(arg0, arg1); }
}
