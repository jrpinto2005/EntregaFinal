package constructoresTest;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constructores.ConstructorRecursoEducativo;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.RecursoEducativo;
import learningPaths.LearningPath;
import usuario.Sistema;

class ConstructorRecursoEducativoTest {

    private ConstructorRecursoEducativo constructorRecursoEducativo;
    Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {
    	LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
 				1, "profesor01", "Entender lo básico de Java", 0.0);
    	sistema.addLP(lp1);
    	
        constructorRecursoEducativo = new ConstructorRecursoEducativo();
    }

    @Test
    void testCrearRecursoEducativo() throws ActivdadNoEcontradaException {
        
        String descripcion = "Recurso Educativo de Java Básico";
        String objetivo = "Aprender lo básico de Java";
        String id = "Recurso1";
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        int duracion = 30;
        int dificultad = 2;
        double rating = 4.5;
        String tipoActividad = "Recurso Educativo";
        boolean obligatoria = true;
        String learningPath = "Java Básico";

        RecursoEducativo recurso = constructorRecursoEducativo.crearRecursoEducativo(descripcion, objetivo, id, fechaInicio, fechaFin,
                duracion, dificultad, rating, tipoActividad, obligatoria, learningPath);
        
        assertNotNull(recurso, "El recurso educativo debería haberse creado y no ser nulo.");
        assertEquals(descripcion, recurso.getDescripcion());
        assertEquals(objetivo, recurso.getObjetivo());
        assertEquals(learningPath + "." + id, recurso.getId());
        assertEquals(duracion, recurso.getDuracion());
        assertEquals(dificultad, recurso.getDificultad());
        assertEquals(rating, recurso.getRating(), 0.01);
        assertEquals(tipoActividad, recurso.getTipoActividad());
        assertEquals(obligatoria, recurso.isObligatoria());
        assertEquals(learningPath, recurso.getLearningPath().getTitulo());
        assertEquals(recurso, sistema.encontrarActividad(learningPath + "." + id), "El recurso educativo debería estar registrado en el sistema.");
    }

    @Test
    void testEditarRecursoEducativo() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ActivdadNoEcontradaException {
         
        String id = "Recurso2";
        String learningPath = "Java Básico";
        RecursoEducativo recurso = constructorRecursoEducativo.crearRecursoEducativo("Recurso de prueba", "Prueba de edición", id, 
                new Date(), new Date(), 20, 1, 3.0, "Recurso Educativo", true, "Java Básico");
        
        String nuevoDescripcion = "Nueva Descripción";
        constructorRecursoEducativo.editarRecursoEducativo(learningPath + "." + id, "descripcion", nuevoDescripcion);
        assertEquals(nuevoDescripcion, recurso.getDescripcion(), "La descripción debería haber sido actualizada.");
        
        Integer nuevaDificultad = 3;
        constructorRecursoEducativo.editarRecursoEducativo(learningPath + "." + id, "dificultad", nuevaDificultad);
        assertEquals(nuevaDificultad, recurso.getDificultad(), "La dificultad debería haber sido actualizada.");
        
        Integer nuevoDuracion = 40;
        constructorRecursoEducativo.editarRecursoEducativo(learningPath + "." + id, "duracion", nuevoDuracion);
        assertEquals(nuevoDuracion, recurso.getDuracion(), "La duración debería haber sido actualizada.");
        
    }

}
