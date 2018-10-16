package DAO.Exceptions;

public class DatabaseError extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DatabaseError() { super(); }
	
	public DatabaseError(String arg0) { super(arg0); }
	
	public DatabaseError(Throwable arg0) { super(arg0); }
	
	public DatabaseError(String arg0, Throwable arg1) { super(arg0, arg1); }

}
