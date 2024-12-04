package envios;

import java.util.List;

import learningPaths.Actividad;

public class EnvioExamen extends Envio {
	private double puntaje;
	private int puntajeMaximo;
	private double notaPorcentaje;
	private List<RespuestaAbierta> respuestas;

	public EnvioExamen(Actividad actividad, String idEstudiante, String tituloLP, boolean completado, double puntaje,
			int puntajeMaximo, double notaPorcentaje, List<RespuestaAbierta> respuestas) {
		super(actividad, idEstudiante, tituloLP, completado);
		this.puntaje = puntaje;
		this.puntajeMaximo = puntajeMaximo;
		this.notaPorcentaje = notaPorcentaje;
		this.respuestas = respuestas;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	public void setPuntajeMaximo(int puntajeMax) {
		this.puntajeMaximo = puntajeMax;
	}

	public void setNotaPorcentaje(double nota) {
		this.notaPorcentaje = nota;
	}

	public List<RespuestaAbierta> getRespuestas() {
		return respuestas;
	}

	
	
	

}
