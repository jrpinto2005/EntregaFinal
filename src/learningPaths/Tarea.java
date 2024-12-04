package learningPaths;

import java.util.Date;

public class Tarea extends Actividad {

	private String contenido;


	public Tarea(String descripcion, String objetivo, String nombre, Date fechaInicio, Date fechaFin, int duracion,
			int dificultad, double rating, String tipoActividad, boolean obligatoria, String learningPath) {
		super(descripcion, objetivo, nombre, fechaInicio, fechaFin, duracion, dificultad, rating, tipoActividad,
				obligatoria, learningPath);
		 
	}

	public void setContenido(String contenido) {
		{
			this.contenido = contenido;
		}

	}
}
