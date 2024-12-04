package envios;

public class RespuestaEncuesta {
	private int valor;
	private PreguntaEncuesta pregunta;

	public RespuestaEncuesta(int valor, PreguntaEncuesta pregunta) {
		super();
		this.valor = valor;
		this.pregunta = pregunta;
	}

	public int getValor() {
		return valor;
	}

}
