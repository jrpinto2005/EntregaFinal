package exceptions;

public class ProfesorNoCreadorException extends Exception {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "El learning path fue creado por otro profesor y por lo tanto no lo puedes editar";
	}
}