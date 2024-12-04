package exceptions;

public class ActivdadNoEcontradaException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActivdadNoEcontradaException(String idActividad) {
        super("La actividad con ID " + idActividad + " no fue encontrada en el sistema.");
    }
}
