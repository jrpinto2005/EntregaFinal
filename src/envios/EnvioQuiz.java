package envios;

import java.util.List;

import envios.CalificadorRespuestaMultiple;
import learningPaths.Actividad;

public class EnvioQuiz extends Envio {
	private double puntaje;
	private int puntajeMaximo;
	private double notaPorcentaje;
	private List<RespuestaMultiple> respuestas;

	public EnvioQuiz(Actividad actividad, String idEstudiante, String tituloLP, boolean completado, double puntaje,
			int puntajeMaximo, double notaPorcentaje, List<RespuestaMultiple> respuestas) {
		super(actividad, idEstudiante, tituloLP, completado);
		this.puntaje = puntaje;
		this.puntajeMaximo = puntajeMaximo;
		this.notaPorcentaje = notaPorcentaje;
		this.respuestas = respuestas;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	public List<RespuestaMultiple> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<RespuestaMultiple> respuestas) {
		this.respuestas = respuestas;
	}

	public double getPuntaje() {
		return puntaje;
	}

	public int getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public double getNotaPorcentaje() {
		return notaPorcentaje;
	}

	public void setPuntajeMaximo(int puntajeMax) {
		this.puntajeMaximo = puntajeMax;
	}

	public void setNotaPorcentaje(double nota) {
		this.notaPorcentaje = nota;
	}

	public void calificarQuiz() {
		int puntaje = 0;
		CalificadorRespuestaMultiple calificador = new CalificadorRespuestaMultiple();
		for (RespuestaMultiple respuesta : this.respuestas) {
			int valor = 0;
			valor = calificador.esCorrecta(respuesta);
			puntaje += valor;
		}
		this.puntaje = puntaje;
		this.notaPorcentaje = puntaje / this.puntajeMaximo;
		if (this.notaPorcentaje >= 3.0) {
			this.completado = true;
		}
	}

}
