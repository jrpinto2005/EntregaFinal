package envios;

public class RespuestaAbierta {
	private int puntaje;
	private String contenido;
	private double valor;
	private PreguntaAbierta pregunta;

	public RespuestaAbierta(int puntaje, String contenido, double d, PreguntaAbierta pregunta) {
		super();
		this.puntaje = puntaje;
		this.contenido = contenido;
		this.valor = d;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public String getContenido() {
		return contenido;
	}

	public double getValor() {
		return valor;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public PreguntaAbierta getPregunta() {
		return this.pregunta;
	}

}
