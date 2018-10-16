package DAO.Exceptions;

public class AlreadyExists extends Exception{

	private static final long serialVersionUID = 1L;

	public AlreadyExists() { super(); }
	
	public AlreadyExists(String arg0) { super(arg0); }
	
	public AlreadyExists(Throwable arg0) { super(arg0); }
	
	public AlreadyExists(String arg0, Throwable arg1) { super(arg0, arg1); }
}
