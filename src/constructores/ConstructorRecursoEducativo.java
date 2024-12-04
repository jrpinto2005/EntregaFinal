package constructores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import exceptions.ActivdadNoEcontradaException;
import learningPaths.Examen;
import learningPaths.LearningPath;
import learningPaths.RecursoEducativo;
import usuario.Sistema;

public class ConstructorRecursoEducativo {
	private Sistema sistema;

	public ConstructorRecursoEducativo() {
		super();
		this.sistema = Sistema.getInstancia();
	}

	public RecursoEducativo crearRecursoEducativo(String descripcion, String objetivo, String id, Date fechaInicio,
			Date fechaFin, int duracion, int dificultad, double rating, String tipoActividad, boolean obligatoria,
			String learningPath) {
		RecursoEducativo recurso = new RecursoEducativo(descripcion, objetivo, id, fechaInicio, fechaFin, duracion,
				dificultad, rating, tipoActividad, obligatoria, learningPath);
		sistema.addActividad(recurso);
		return recurso;
	}

	public void editarRecursoEducativo(String id, String atributo, Object valorNuevo) throws ActivdadNoEcontradaException {
	    try {
	    	RecursoEducativo recurso = (RecursoEducativo) sistema.encontrarActividad(id);
	        String setter = "set" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
	        Class<?> valorClase = valorNuevo.getClass();
	        Method metodoSetter = recurso.getClass().getMethod(setter, valorClase);
	        metodoSetter.invoke(recurso, valorNuevo);
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