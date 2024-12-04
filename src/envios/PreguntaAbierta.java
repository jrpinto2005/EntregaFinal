package envios;

public class PreguntaAbierta extends Pregunta {

	public double valorPregunta;

	public PreguntaAbierta(String textoPregunta, int idPregunta, double d) {
		super(textoPregunta, idPregunta);
		this.valorPregunta = d;
		 
	}

	public double getValorPregunta() {
		return valorPregunta;
	}

}
