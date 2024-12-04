package exceptions;

public class UsuarioContraseñaIncorrectoException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioContraseñaIncorrectoException (String usuario)
	{
		super("El usuario " + usuario +" no existe o la contraseña es incorrecta ");
	}
}
