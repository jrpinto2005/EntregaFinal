
package exceptions;

public class LPNoEncontradaException extends Exception {

	private String titulo;

	public LPNoEncontradaException(String titulo) {
		super();
		this.titulo = titulo;

	}

	@Override
	public String getMessage() {
		return "El learning path con titulo :  " + titulo + " no existe";
	}

}
