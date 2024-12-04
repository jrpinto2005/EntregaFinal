package envios;

import java.util.List;

import learningPaths.Examen;

public class CalificadorEnvioExamen {

	public void calificarExamen(double valor, EnvioExamen envio) {
		Examen actividad= (Examen) envio.getActividad();
		int puntajeMaximo=actividad.getPuntajeMaximo();
		double nota=puntajeMaximo/valor;
		envio.setPuntaje(valor);
		envio.setNotaPorcentaje(nota);
		if(nota>=0.6) {
			envio.setCompletado(true);
		}
		else {
			envio.setCompletado(false);
		}
	}

}