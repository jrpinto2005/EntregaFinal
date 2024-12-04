package constructoresTest;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constructores.ConstructorTarea;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.Tarea;
import learningPaths.LearningPath;
import usuario.Sistema;

class ConstructorTareaTest {

    private ConstructorTarea constructorTarea;
    Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {
    	LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
 				1, "profesor01", "Entender lo básico de Java", 0.0);
    	sistema.addLP(lp1);
    	
        constructorTarea = new ConstructorTarea();
    }

    @Test
    void testCrearTarea() throws ActivdadNoEcontradaException {
        
        String descripcion = "Tarea de Java Básico";
        String objetivo = "Evaluar los conocimientos básicos de Java";
        String id = "Tarea1";
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        int duracion = 30;
        int dificultad = 2;
        double rating = 4.5;
        String tipoActividad = "Tarea";
        boolean obligatoria = true;
        String learningPath = "Java Básico";

        Tarea tarea = constructorTarea.crearTarea(descripcion, objetivo, id, fechaInicio, fechaFin,
                duracion, dificultad, rating, tipoActividad, obligatoria, learningPath);
        
        assertNotNull(tarea, "La tarea debería haberse creado y no ser nula.");
        assertEquals(descripcion, tarea.getDescripcion());
        assertEquals(objetivo, tarea.getObjetivo());
        assertEquals(learningPath + "." + id, tarea.getId());
        assertEquals(duracion, tarea.getDuracion());
        assertEquals(dificultad, tarea.getDificultad());
        assertEquals(rating, tarea.getRating(), 0.01);
        assertEquals(tipoActividad, tarea.getTipoActividad());
        assertEquals(obligatoria, tarea.isObligatoria());
        assertEquals(learningPath, tarea.getLearningPath().getTitulo());
        assertEquals(tarea, sistema.encontrarActividad(learningPath + "." + id), "La tarea debería estar registrada en el sistema.");
    }

    @Test
    void testEditarTarea() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ActivdadNoEcontradaException {
         
        String id = "Tarea2";
        String learningPath = "Java Básico";
        Tarea tarea = constructorTarea.crearTarea("Tarea de prueba", "Prueba de edición", id, 
                new Date(), new Date(), 20, 1, 3.0, "Tarea", true, "Java Básico");
        
        String nuevoDescripcion = "Nueva Descripción";
        constructorTarea.editarTarea(learningPath + "." + id, "descripcion", nuevoDescripcion);
        assertEquals(nuevoDescripcion, tarea.getDescripcion(), "La descripción debería haber sido actualizada.");
        
        Integer nuevaDificultad = 3;
        constructorTarea.editarTarea(learningPath + "." + id, "dificultad", nuevaDificultad);
        assertEquals(nuevaDificultad, tarea.getDificultad(), "La dificultad debería haber sido actualizada.");
        
        Integer nuevoDuracion = 40;
        constructorTarea.editarTarea(learningPath + "." + id, "duracion", nuevoDuracion);
        assertEquals(nuevoDuracion, tarea.getDuracion(), "La duración debería haber sido actualizada.");
        
    }

}
