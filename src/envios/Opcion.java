package envios;

public class Opcion {
	private String descripcion;
	private boolean esCorrecto;
	private int indice;

	public Opcion(String descripcion, boolean esCorrecto, int indice) {
		super();
		this.descripcion = descripcion;
		this.esCorrecto = esCorrecto;
		this.indice = indice;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean esCorrecto() {
		return esCorrecto;
	}

	public int getIndice() {
		return indice;
	}

}
