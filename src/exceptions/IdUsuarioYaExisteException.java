package exceptions;

public class IdUsuarioYaExisteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public IdUsuarioYaExisteException (String usuario)
	{
		super("El usuario " + usuario +" ya esta tomado");
	} 

}
