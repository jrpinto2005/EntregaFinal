package constructores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import exceptions.ActivdadNoEcontradaException;
import learningPaths.Examen;
import learningPaths.LearningPath;
import usuario.Sistema;

public class ConstructorExamen {

	private Sistema sistema;

	public ConstructorExamen() {
		super();
		this.sistema = Sistema.getInstancia();
	}

	public Examen crearExamen(String descripcion, String objetivo, String id, Date fechaInicio, Date fechaFin,
			int duracion, int dificultad, double rating, String tipoActividad, boolean obligatoria, String learningPath,
			int puntajeMaximo) {
		Examen examen = new Examen(descripcion, objetivo, id, fechaInicio, fechaFin, duracion, dificultad, rating,
				tipoActividad, obligatoria, learningPath, puntajeMaximo);
		sistema.addActividad(examen);
		return examen;
	}

	public void editarExamen(String id, String atributo, Object valorNuevo) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ActivdadNoEcontradaException {
	    try {
	    	Examen examen = (Examen) sistema.encontrarActividad(id);
	        String setter = "set" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
	        Class<?> valorClase = valorNuevo.getClass();
	        Method metodoSetter = examen.getClass().getMethod(setter, valorClase);
	        metodoSetter.invoke(examen, valorNuevo);
	    } catch (NoSuchMethodException e) {
	        System.out.println("Error: El método no existe. Revisa el nombre del atributo.");
	        e.printStackTrace();
	    } catch (SecurityException e) {
	        System.out.println("Error de seguridad al acceder al método.");
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        System.out.println("Error: No se tiene acceso al método.");
	        e.printStackTrace();
	    } catch (InvocationTargetException e) {
	        System.out.println("Error: Fallo al invocar el método.");
	        e.printStackTrace();
	    }
	}

}
