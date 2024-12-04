package constructores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import exceptions.ActivdadNoEcontradaException;
import exceptions.ProfesorNoCreadorException;
import learningPaths.Actividad;
import learningPaths.LearningPath;
import usuario.Sistema;

public class ConstructorLearningPath {
	private Sistema sistema;
	

	public ConstructorLearningPath() {
		super();
		this.sistema = Sistema.getInstancia();
	}

	public LearningPath crearLP(String titulo, String descripcionGeneral, int nivelDificultad, int duracion, int rating,
			Date fechaDuracion, Date fechaModificacion, int version, String idCreador, String objetivos,
			double promedioActividadesCompletadas) {
		LearningPath LP = new LearningPath(titulo, descripcionGeneral, nivelDificultad, duracion, rating, fechaDuracion,
				fechaModificacion, version, idCreador, objetivos, promedioActividadesCompletadas);
		sistema.addLP(LP);
		return LP;
	}

	public void editarLP(LearningPath lp, String atributo, Object valorNuevo, String idProfesor) throws ProfesorNoCreadorException 
	{
		if (lp.esElDueño(idProfesor))
		{
	    try {
	        String setter = "set" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
	        Class<?> valorClase = valorNuevo.getClass();
	        Method metodoSetter = lp.getClass().getMethod(setter, valorClase);
	        sistema.getLearningPaths().remove(lp.getTitulo());
	        metodoSetter.invoke(lp, valorNuevo);
	        sistema.addLP(lp);
	        
	    } 
	    catch (NoSuchMethodException e) {
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
		else 
		{
			throw new ProfesorNoCreadorException();
		}
	}
	
	public void clonarActividad(LearningPath origen, LearningPath destino, String id) throws ActivdadNoEcontradaException {
		Actividad actividad = null;
		for (Actividad elemento : origen.getActividadesOrdenadas()) {
			if (elemento.getId().equals(id)) {
				actividad = elemento;
			}
		}
		if (actividad == null) {
			throw new ActivdadNoEcontradaException(id);
		} else {
			actividad.setLearningPath(destino);
			actividad.setId(destino.getTitulo() + "." + actividad.getId());
			destino.agregarActividad(actividad);
			sistema.addActividad(actividad);
		

		}

	}

}
