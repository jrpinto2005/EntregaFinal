package envios;

public class RespuestaMultiple {
	private int puntaje;
	private int indice;
	private PreguntaOpcionMultiple pregunta;

	public RespuestaMultiple(int puntaje, int indice, PreguntaOpcionMultiple pregunta) {
		super();
		this.puntaje = puntaje;
		this.indice = indice;
		this.pregunta = pregunta;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getIndice() {
		return indice;
	}

	public PreguntaOpcionMultiple getPregunta() {
		return pregunta;
	}

}
